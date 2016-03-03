package com.mikkysoft.auth;

import java.security.Principal;

public class UserPrincipal implements Principal {
	
	private String name;
	
	public UserPrincipal(String name) {
		// TODO Auto-generated constructor stub
		super();
		this.name = name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
