package com.gajamy.rundryservice.member.service;

import com.gajamy.rundryservice.member.entity.Member;
import com.gajamy.rundryservice.member.param.MemberParam;
import com.gajamy.rundryservice.member.repository.MemberRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	private final MemberRepository memberRepository;
	@Override
	public boolean register(MemberParam param) {
		Optional<Member> optionalMember = memberRepository.findById(param.getEmail());
		if (optionalMember.isPresent()) {
			return false;
		}

		String encPassword = BCrypt.hashpw(param.getPassword(),BCrypt.gensalt());

		Member member = new Member();
		member.setEmail(param.getEmail());
		member.setPassword(encPassword);
		member.setName(param.getName());
		member.setPhone(param.getPhone());
		member.setAdmin(false);

		memberRepository.save(member);

		return true;
	}

	@Override
	public String getReturnAccessToken(String code) {
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=dff0cc1a6aa3a9880f70376c249edea3");
			sb.append("&redirect_uri=http://localhost:8080/kakao_callback");
			sb.append("&code="+code);
			bw.write(sb.toString());
			bw.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String br_line = "";
			String result = "";

			while ((br_line = br.readLine()) != null) {
				result += br_line;
			}

			JsonElement element = JsonParser.parseString(result);

			access_token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();

			br.close();
			bw.close();
 		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_token;
	}

	@Override
	public Map<String, Object> getuserInfo(String access_token) {
		Map<String,Object> resultMap = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			conn.setRequestProperty("Authorization", "Bearer " + access_token);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String br_line = "";
			String result = "";

			while ((br_line = br.readLine()) != null) {
				result += br_line;
			}

			System.out.println("response:" + result);

			JsonElement element = JsonParser.parseString(result);

			JsonObject kakao_account = element.getAsJsonObject().getAsJsonObject("kakao_account").getAsJsonObject();

			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			resultMap.put("email",email);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultMap;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Member> optionalMember = memberRepository.findById(username);

		if (!optionalMember.isPresent()) {
			throw new UsernameNotFoundException("회원정보가 존재하지 않습니다.");
		}

		Member member = optionalMember.get();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		if (member.isAdmin()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		return new User(member.getEmail(),member.getPassword(),grantedAuthorities);
	}


}
