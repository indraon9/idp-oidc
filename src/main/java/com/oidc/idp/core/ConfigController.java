package com.oidc.idp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oidc.idp.helper.OidcConfigProperties;

/***
 * Controller to handle configuration requests. Generates OIDC IDP properties
 */
@RestController
public class ConfigController {
	
	@Autowired
	OidcConfigProperties oidcProp;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/.well-known/openid-configuration", method = RequestMethod.GET)
	public ResponseEntity<OidcConfigProperties> getConfigDetails(){
		return new ResponseEntity<>(oidcProp, HttpStatus.OK);
	}
	
}
