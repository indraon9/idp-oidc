package com.oidc.idp.helper;

public class AuthZRequest {
	
	private String redirect_uri;
	private String response_type;
	private String response_mode;
	private String code_challenge;
	private String code_challenge_method;
	private String scope;
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	public String getResponse_mode() {
		return response_mode;
	}
	public void setResponse_mode(String response_mode) {
		this.response_mode = response_mode;
	}
	public String getCode_challenge() {
		return code_challenge;
	}
	public void setCode_challenge(String code_challenge) {
		this.code_challenge = code_challenge;
	}
	public String getCode_challenge_method() {
		return code_challenge_method;
	}
	public void setCode_challenge_method(String code_challenge_method) {
		this.code_challenge_method = code_challenge_method;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	

}
