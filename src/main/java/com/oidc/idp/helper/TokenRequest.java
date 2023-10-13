package com.oidc.idp.helper;

public class TokenRequest {
	
	private String code;
	private String code_verifier;
	private String grant_type;
	private String redirect_uri;
	
	
	public void getTokenRequest(String req) {
		String [] tArr = req.split("&");
		for(int i=0; i<tArr.length;i++) {
			String [] param = req.split("=");
			if(param[0].equals("code"))
				this.setCode(param[1]);
			else if(param[0].equals("code_verifier"))
				this.setCode_verifier(param[1]);
			else if(param[0].equals("grant_type"))
				this.setGrant_type(param[1]);
			else if(param[0].equals("redirect_uri"))
				this.setRedirect_uri(param[1]);
		}
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_verifier() {
		return code_verifier;
	}
	public void setCode_verifier(String code_verifier) {
		this.code_verifier = code_verifier;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	@Override
	public String toString() {
		return "TokenRequest [code=" + code + ", code_verifier=" + code_verifier + ", grant_type=" + grant_type
				+ ", redirect_uri=" + redirect_uri + "]";
	}
	
	

}
