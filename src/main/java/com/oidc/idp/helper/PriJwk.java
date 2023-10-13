package com.oidc.idp.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PriJwk {
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
	
	@Value("${jwks.pri.d}")
	private String d;

	@Value("${jwks.pri.key_ops}")
	private String [] priKeyOps;

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

	public String[] getPriKeyOps() {
		return priKeyOps;
	}

	public String getD() {
		return d;
	}
}
