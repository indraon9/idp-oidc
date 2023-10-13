package com.oidc.idp.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PubJwk {
	@Value("${jwks.crv}")
	private String crv;

	@Value("${jwks.ext}")
	private String ext;

	@Value("${jwks.kty}")
	private String kty;

	@Value("${jwks.kid}")
	private String kid;

	@Value("${jwks.x}")
	private String x;

	@Value("${jwks.y}")
	private String y;

	@Value("${jwks.pub.key_ops}")
	private String [] pubKeyOps;

	public String getCrv() {
		return crv;
	}

	public String getExt() {
		return ext;
	}

	public String getKty() {
		return kty;
	}

	public String getKid() {
		return kid;
	}

	public String getX() {
		return x;
	}

	public String getY() {
		return y;
	}

	public String[] getPubKeyOps() {
		return pubKeyOps;
	}
}
