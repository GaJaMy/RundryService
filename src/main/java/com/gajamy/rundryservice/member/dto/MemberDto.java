package com.gajamy.rundryservice.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberDto {
	String email;
	String name;
	String phone;
	String password;

	boolean isKakaoLinked;
}
