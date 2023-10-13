package com.oidc.idp.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PubJwkList {
	
	
	private List<PubJwk> keys = new ArrayList<PubJwk>();
	
	@Autowired
	private void setKeys(@Autowired PubJwk pubJwk) {
		this.keys.add(pubJwk);
	}

	public List<PubJwk> getKeys() {
		return keys;
	}

}
