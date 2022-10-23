package com.gajamy.rundryservice.member.param;

import lombok.Data;

@Data
public class MemberParam {
	String email;
	String name;
	String phone;
	String password;

	boolean isAdmin;
}
