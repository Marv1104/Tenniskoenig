package com.tco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private AuthUserLookUpService userLookupService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userLookupService.findUser(username);
	}

}
