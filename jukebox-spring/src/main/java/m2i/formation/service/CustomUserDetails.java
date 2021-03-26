package m2i.formation.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import m2i.formation.model.Membre;
import m2i.formation.model.Utilisateur;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateur;

	public CustomUserDetails(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("User"));
		return authorities;
	}
	
	public Long getId() {
		return utilisateur.getId();
	}

	@Override
	public String getPassword() {
		return new BCryptPasswordEncoder().encode(((Membre) utilisateur).getMotDePasse());
	}

	@Override
	public String getUsername() {
		return utilisateur.getPseudo();
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

}
