package com.gajamy.rundryservice.member.service;

import com.gajamy.rundryservice.member.param.MemberParam;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService{
	boolean register(MemberParam param);

	String getReturnAccessToken(String code);

	Map<String,Object> getuserInfo(String access_toekn);

}
