package com.oidc.idp.helper;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * Creates Singleton instance.
 * Stores Authorization code in a concurrent HashMap.
 *
 */

public class TokenDB {
	
	private static volatile TokenDB tokenDBInstance;
	private static Object obj = new Object();
	private ConcurrentHashMap<String, Token> tokenDB;
	private TokenDB() {
		tokenDB = new ConcurrentHashMap<String, Token>();
	}

	public static TokenDB getInstance() {
		TokenDB tempInstance = tokenDBInstance;
		if(tempInstance != null)
			return tempInstance;
		synchronized(obj) {
				tokenDBInstance = new TokenDB();
		}			
		return tokenDBInstance;
	}

	public void addToken(Token t) {
		tokenDB.putIfAbsent(t.getAuthZCode(), t);
	}
	
	public void removeToken(Token t) {
		if(tokenDB != null && tokenDB.containsKey(t.getAuthZCode()))
			tokenDB.remove(t.getAuthZCode());
	}
	
	public Token getToken(String code) {
		if(tokenDB == null || !tokenDB.containsKey(code))
				return null;
		return tokenDB.get(code);
		
	}
	
	public void printAllCodes() {
		if(tokenDB == null || tokenDB.isEmpty())
				System.out.println("Token DB null or Empty");
		Set<String> keys = tokenDB.keySet();
		Iterator<String> iter = keys.iterator();
		
		while(iter.hasNext()) {
			System.out.println("Key found : " + iter.next());
		}
		
	}

}
