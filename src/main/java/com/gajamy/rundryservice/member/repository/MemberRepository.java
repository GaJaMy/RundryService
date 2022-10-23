package com.gajamy.rundryservice.member.repository;

import com.gajamy.rundryservice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
