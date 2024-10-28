package com.dogognon.sohliou.kone.security.oauth2ressourceserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.dogognon.sohliou.kone.security.data.User;

import lombok.Getter;
import lombok.Setter;

public class UserPrincipal implements OAuth2User, UserDetails {
    private static final long serialVersionUID = 1L;
    @Getter
	private String id;
    @Getter
    private String email;
    @Getter
    private String phone;
    @Getter
    private String nomprenoms;
    
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;
     @Setter
    private Map<String, Object> attributes;

    public UserPrincipal(String id,String nomprenoms, String phone,String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nomprenoms = nomprenoms;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {

    	List<GrantedAuthority> authorities = new ArrayList<>();
    	user.getRoles().forEach(r ->{
        		authorities.add(new SimpleGrantedAuthority(r.getRoleName().name()));
        });
        
        
        return new UserPrincipal(
                user.getId(),
                user.getNomprenoms(),
                user.getPhone(),
                user.getEmail(),
                user.getPasswords(),
                authorities
        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }



    @Override
    public String getPassword() {
        return password;
    }

  

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // je reutilise cette methode vu qu'elle me facilite le transfert de l'id de l'utilisateur courant ou proprietaire du jwt
    @Override
    public String getName() {
    	return getId();
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
}
