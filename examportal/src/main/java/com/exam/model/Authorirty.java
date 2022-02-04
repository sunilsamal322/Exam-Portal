package com.exam.model;

import org.springframework.security.core.GrantedAuthority;

public class Authorirty implements GrantedAuthority{

	private String authority;
	
	public Authorirty(String authority)
	{
		this.authority=authority;
	}
	
	@Override
	public String getAuthority() {
		
		return this.authority;
	}

}
