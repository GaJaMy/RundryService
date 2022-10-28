package com.gajamy.rundryservice.common;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ListResponse<T> extends CommonResponse {
	List<T> dataList;

	public ListResponse() {
		super(false,100,"");
		this.dataList = null;
	}
	@Builder
	public ListResponse(boolean result, int statusCode, String errorMessage,List<T> dataList) {
		super(result,statusCode,errorMessage);
		this.dataList = dataList;
	}
}
