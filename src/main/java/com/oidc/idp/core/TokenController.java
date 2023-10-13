package com.oidc.idp.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.oidc.idp.helper.OidcConfigProperties;
import com.oidc.idp.helper.PriJwk;
import com.oidc.idp.helper.Token;
import com.oidc.idp.helper.TokenDB;
import com.oidc.idp.helper.TokenResponse;


/***
 * Controller to handle token requests. Generates id_token, by validating authorization code, redirect_uri and code_verifier.
 */

@RestController
public class TokenController {
	
	@Autowired
	PriJwk priJwk;
	
	@Autowired
	OidcConfigProperties ocp;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<TokenResponse> getToken(@RequestParam String code, @RequestParam String code_verifier, @RequestParam String redirect_uri){
		Token t = TokenDB.getInstance().getToken(code);
	
		if(t == null) {
			System.out.println("Token not found");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		TokenDB.getInstance().removeToken(t);
		
		if(isUriValid(redirect_uri, t) && isCVerifierValid(code_verifier, t)) {
			String jwt = genAccessToken();
			if(jwt != null) {
				TokenResponse tRes = new TokenResponse();
				tRes.setId_token(jwt);
				return new ResponseEntity<TokenResponse>(tRes, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	private boolean isUriValid(String redirect_uri, Token t) {
		if(redirect_uri != null && t.getRedirect_uri().equals(redirect_uri))
			return true;
		return false;
	}

	private boolean isCVerifierValid(String cVerifier, Token t) {
		try {
			MessageDigest md = MessageDigest.getInstance(t.getcChallMethod());
			Base64URL cc = Base64URL.encode(md.digest(cVerifier.getBytes()));
			if(cc.toString().equals(t.getcChallenge()))
				return true;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private String genAccessToken() {
		JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256)
				.keyID(priJwk.getKid()).build();
		
		JWTClaimsSet claims = new JWTClaimsSet.Builder()
				.issuer(ocp.getIssuer())
				.audience("OIDC Tester")
				.subject("JohnDoe")
				.issueTime(new Date())
				.build();
		
		
		
		ECKey key = new ECKey.Builder(Curve.P_256, 
				Base64URL.from(priJwk.getX()), 
				Base64URL.from(priJwk.getY()))
				.d(Base64URL.from(priJwk.getD()))
				.algorithm(JWSAlgorithm.ES256)
				.keyID(priJwk.getKid()).build();
		
		SignedJWT sJwt = new SignedJWT(header, claims);
		try {
			sJwt.sign(new ECDSASigner(key.toECPrivateKey()));
			String jwt = sJwt.serialize();
			return jwt;
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return null;
	}


}
