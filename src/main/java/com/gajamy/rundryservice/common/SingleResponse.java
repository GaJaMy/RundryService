package com.gajamy.rundryservice.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends CommonResponse {
	T data;

	public SingleResponse() {
		super(false,100,"");
		this.data = null;
	}
	@Builder
	public SingleResponse(boolean result, int statusCode, String errorMessage,T data) {
		super(result,statusCode,errorMessage);
		this.data = data;
	}
	public void MakeResponse(boolean result, int statusCode, String errorMessage,T data) {
		setResponseResult(result,statusCode,errorMessage);
		this.data = data;
	}
}
