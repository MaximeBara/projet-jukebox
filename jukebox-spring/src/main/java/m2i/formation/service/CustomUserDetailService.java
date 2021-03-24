package m2i.formation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Utilisateur;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private IUtilisateurDao utilisateurDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> opt = utilisateurDao.findByUsername(username);

		if (opt.isPresent()) {
			return new CustomUserDetails(opt.get());
		} else {
			throw new UsernameNotFoundException(username + " Inconnu");
		}
	}

}