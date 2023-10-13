package com.oidc.idp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oidc.idp.helper.PubJwkList;

/***
 * Controller to handle JWKS requests. Displays IDP public keys in JSON format
 */

@RestController
public class JKWSController {

	@Autowired
	PubJwkList pubJwkList;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/jwks.json", method = RequestMethod.GET)
	public ResponseEntity<PubJwkList> getPubJWK(){
		return new ResponseEntity<PubJwkList>(pubJwkList, HttpStatus.OK);
	}
	
	

}
