package com.example.security;

import com.example.entities.TaiKhoan;
import com.example.repository.TaiKhoanRepository;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private TaiKhoanRepository taiKhoanRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		TaiKhoan taiKhoan = taiKhoanRepository.findById(username).get();
		if (taiKhoan == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}
		return new org.springframework.security.core.userdetails.User(taiKhoan.getMatk(), new BCryptPasswordEncoder().encode(taiKhoan.getPassword()),
				getGrantedAuthority(taiKhoan));
	}
	
//	private Collection<GrantedAuthority> getGrantedAuthority(TaiKhoan user) {
//		Collection<GrantedAuthority> authorities = new ArrayList<>();
//		if (user.getQuyen().getTenquyen().equalsIgnoreCase("ROLE_ADMIN")) {
//			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		}else {
//			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		}
//		return authorities;
//	}
	private Collection<GrantedAuthority> getGrantedAuthority(TaiKhoan user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getQuyen().getTenquyen().equalsIgnoreCase("ROLE_ADMIN")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if(user.getQuyen().getTenquyen().equalsIgnoreCase("ROLE_NV")){
			authorities.add(new SimpleGrantedAuthority("ROLE_NV"));
		}else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authorities;
	}

}