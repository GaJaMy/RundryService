package com.gajamy.rundryservice.member.service;

import com.gajamy.rundryservice.member.entity.Member;
import com.gajamy.rundryservice.member.param.MemberParam;
import com.gajamy.rundryservice.member.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
