package com.oidc.idp.helper;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonValidator {
	
	@Autowired
	OidcConfigProperties oidcConfig;
	
	public boolean isValidAuthZRequest(String rUri, String rType, String rMode, String cChal, String cChalMethod, String scope) {
		if(isValidURL(rUri) 
				&& isValidResponseType(rType) 
				&& isValidResponseMode(rMode)
				&& isValidcodeChal(cChal)
				&& isValidcodeChalMethod(cChalMethod)
				&& isValidScope(scope))
			return true;
		return false;
	}
	
	public boolean isValidURL(String url) {
		try {
			if(url == null) return false;
			
			URL u = new URL(url);
			u.toURI();
			return true;
		} catch (MalformedURLException e) {			
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}
	
	public boolean isValidResponseType(String s) {
		if(s!= null && s.equalsIgnoreCase("code"))
			return true;
		return false;
	}
	
	public boolean isValidResponseMode(String s) {
		if(s!= null && s.equalsIgnoreCase("query"))
			return true;
		return false;
	}
	
	public boolean isValidcodeChal(String s) {
		if(s== null || s.length() != 43)
			return false;
		return true;
	}
	
	public boolean isValidcodeChalMethod(String s) {
		if(s!= null && s.equalsIgnoreCase("S256"))
			return true;
		return false;
	}
	
	public boolean isValidScope(String s) {
		if(s== null)
			return false;
		for(int i=0; i<oidcConfig.getScopes_supported().length; i++)
			if(oidcConfig.getScopes_supported()[i].equalsIgnoreCase(s))
				return true;
		return false;
	}
	
	

}
