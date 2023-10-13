package com.oidc.idp.helper;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OidcConfigProperties {

	@Value("${issuer}")
	private String issuer;
	
	@Value("${authorization_endpoint}")
	private String authorization_endpoint;
	
	@Value("${token_endpoint}")
	private String token_endpoint;
	
	@Value("${jwks_uri}")
	private String jwks_uri;
	
	@Value("${scopes_supported}")
	private String [] scopes_supported;

	public String getIssuer() {
		return issuer;
	}

	public String getAuthorization_endpoint() {
		return authorization_endpoint;
	}

	public String getToken_endpoint() {
		return token_endpoint;
	}

	public String getJwks_uri() {
		return jwks_uri;
	}
	
	public String [] getScopes_supported() {
		return scopes_supported;
	}

	@Override
	public String toString() {
		return "OidcConfigProperties [issuer=" + issuer + ", authorization_endpoint=" + authorization_endpoint + ", tokenEndpoint="
				+ token_endpoint + ", jwksUri=" + jwks_uri + ", scopes=" + Arrays.toString(scopes_supported) + "]";
	}
	
	
	
}
