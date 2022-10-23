package com.gajamy.rundryservice.member.service;

import com.gajamy.rundryservice.member.param.MemberParam;
import org.springframework.stereotype.Service;

public interface MemberService {
	boolean register(MemberParam param);
	boolean login(MemberParam param);
}
