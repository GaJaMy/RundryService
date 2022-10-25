package com.gajamy.rundryservice.member.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Member {
	@Id
	private String email;
	private String name;
	private String phone;
	private String password;

	private boolean isAdmin;
}
