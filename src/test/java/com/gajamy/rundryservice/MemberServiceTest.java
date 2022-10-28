package com.gajamy.rundryservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gajamy.rundryservice.member.entity.Member;
import com.gajamy.rundryservice.member.repository.MemberRepository;
import com.gajamy.rundryservice.member.service.MemberService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	@Autowired
	MemberService memberService;

	@Autowired
	MemberRepository memberRepository;

	@Test
	void registerMemberTest() {
		//given
		String encPassword = BCrypt.hashpw("1234",BCrypt.gensalt());
		Member member = new Member();
		member.setEmail("test@eamil.com");
		member.setName("백수");
		member.setPhone("123456");
		member.setPassword(encPassword);

		//when
		memberRepository.save(member);

		//then
		Optional<Member> result = memberRepository.findById("test@eamil.com");
		assertEquals(result.get().getPassword(),encPassword);
	}

	@Test
	void registerKakaoLoinTest() {
		//given
		Member member = new Member();
		member.setEmail("test@eamil.com");
		member.setName("백수");
		member.setKakaoLinked(true);
		member.setAdmin(false);

		//when
		memberRepository.save(member);

		//then
		Optional<Member> result = memberRepository.findById("test@eamil.com");
		assertEquals(result.get().getEmail(),member.getEmail());
		assertEquals(result.get().getPassword(),member.getPassword());
		assertEquals(result.get().getName(),member.getName());
		assertEquals(result.get().getPhone(),member.getPhone());
		assertEquals(result.get().isKakaoLinked(),member.isKakaoLinked());
		assertEquals(result.get().isAdmin(),member.isAdmin());
	}

}
