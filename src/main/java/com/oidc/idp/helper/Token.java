package com.oidc.idp.helper;

public class Token {
	
	private String cChallenge;
	private String authZCode;
	private String cChallMethod;
	private String redirect_uri;
	
	public String getcChallenge() {
		return cChallenge;
	}
	public void setcChallenge(String cChallenge) {
		this.cChallenge = cChallenge;
	}
	public String getAuthZCode() {
		return authZCode;
	}
	public void setAuthZCode(String authZCode) {
		this.authZCode = authZCode;
	}
	public String getcChallMethod() {
		return cChallMethod;
	}
	public void setcChallMethod(String cChallMethod) {
		this.cChallMethod = cChallMethod;
	}
	public String getRedirect_uri() {
		return redirect_uri;
	}
	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}
	
	

}
