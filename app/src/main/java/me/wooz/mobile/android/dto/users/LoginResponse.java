package me.wooz.mobile.android.dto.users;

import me.wooz.mobile.android.dto.BaseResponse;

/**
 * Created by byron on 6/11/16.
 */

public class LoginResponse extends BaseResponse {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
