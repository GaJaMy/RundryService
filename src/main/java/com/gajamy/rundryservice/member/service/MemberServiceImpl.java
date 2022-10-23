package com.gajamy.rundryservice.member.service;

import com.gajamy.rundryservice.member.dto.MemberDto;
import com.gajamy.rundryservice.member.entity.Member;
import com.gajamy.rundryservice.member.param.MemberParam;
import com.gajamy.rundryservice.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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

		Member member = new Member();
		member.setEmail(param.getEmail());
		member.setPassword(param.getPassword());
		member.setName(param.getName());
		member.setPhone(param.getPhone());

		memberRepository.save(member);

		return true;
	}

	@Override
	public boolean login(MemberParam param) {
		Optional<Member> optionalMember = memberRepository.findById(param.getEmail());
		if (!optionalMember.isPresent()) {
			return false;
		}

		Member member = optionalMember.get();
		if (!param.getPassword().equals(member.getPassword())) {
			return false;
		}

		return true;
	}
}
