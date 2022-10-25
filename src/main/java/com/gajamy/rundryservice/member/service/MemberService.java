package com.gajamy.rundryservice.member.service;

import com.gajamy.rundryservice.member.param.MemberParam;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface MemberService extends UserDetailsService{
	boolean register(MemberParam param);
}
