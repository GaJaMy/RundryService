package com.gajamy.rundryservice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommonResponse {
	boolean result;
	int statusCode;
	String errorMessage;

	public void setResponseResult(boolean result, int statusCode, String errorMassage) {
		this.result = result;
		this.statusCode = statusCode;
		this.errorMessage = errorMassage;
	}
}
