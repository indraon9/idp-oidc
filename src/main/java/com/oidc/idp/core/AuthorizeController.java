package com.oidc.idp.core;

import java.net.URI;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oidc.idp.helper.CommonValidator;
import com.oidc.idp.helper.Token;
import com.oidc.idp.helper.TokenDB;



/***
 * Controller to handle authorization requests. Generates authorization code and redirects to redirect_uri
 */

@RestController
public class AuthorizeController {
	
	@Autowired
	CommonValidator cv;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/authorize", method = RequestMethod.GET)
	public ResponseEntity<Object> authorize(
			@RequestParam(name="redirect_uri", required = true) String rUri, 
			@RequestParam(name="response_type", required = true) String rType, 
			@RequestParam(name="response_mode", required = true) String rMode, 
			@RequestParam(name="code_challenge", required = true) String cChal, 
			@RequestParam(name="code_challenge_method", required = true) String cChalMethod, 
			@RequestParam(name="scope", required = true) String scope){
		
		if(!cv.isValidAuthZRequest(rUri, rType, rMode, cChal, cChalMethod, scope))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		String authzCode = generateAuthZCode();
		
		Token t = new Token();
		t.setAuthZCode(authzCode);
		t.setcChallenge(cChal);
		t.setcChallMethod("SHA-256");
		t.setRedirect_uri(rUri);
		TokenDB.getInstance().addToken(t);
		
		URI uri = URI.create(rUri + "?code="+ authzCode);
		
		return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
	}
	
	private static String generateAuthZCode() {
		SecureRandom random = new SecureRandom();
	    byte bytes[] = new byte[40];
	    random.nextBytes(bytes);
	    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    String token = encoder.encodeToString(bytes);
	    return token;
	}
}
