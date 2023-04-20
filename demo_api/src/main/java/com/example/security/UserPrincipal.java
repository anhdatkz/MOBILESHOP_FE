//package com.example.security;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToOne;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.example.entities.KhachHang;
//import com.example.entities.NhanVien;
//import com.example.entities.Quyen;
//import com.example.entities.TaiKhoan;
//
//public class UserPrincipal implements UserDetails {
//	
//	private String matk;
//	
//	private String password;
//	private Collection<? extends GrantedAuthority> authorities;
//	
//	public UserPrincipal(String matk, String password, Collection<? extends GrantedAuthority> authorities) {
//		super();
//		this.matk = matk;
//		this.password = password;
//		this.authorities = authorities;
//	}
//	
//	public static UserPrincipal create(TaiKhoan user) {
//        GrantedAuthority authorities = user.getQuyen().getTenquyen()
//
//        return new UserPrincipal(
//                user.getMatk(),
//                user.getPassword(),
//                authorities
//        );
//    }
//	
//	public String getMatk() {
//		return matk;
//	}
//
//	public void setMatk(String matk) {
//		this.matk = matk;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//		this.authorities = authorities;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//}