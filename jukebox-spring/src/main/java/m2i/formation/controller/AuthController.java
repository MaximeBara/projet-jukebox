package m2i.formation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import m2i.formation.config.jwt.JwtUtils;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Membre;
import m2i.formation.payload.request.LoginRequest;
import m2i.formation.payload.request.SignupRequest;
import m2i.formation.payload.response.JwtResponse;
import m2i.formation.payload.response.MessageResponse;
import m2i.formation.service.CustomUserDetails;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUtilisateurDao utilisateurDao;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getPseudo(), loginRequest.getMotDePasse()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (utilisateurDao.existsByPseudo(signUpRequest.getPseudo())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur: le pseudo est déjà pris !"));
		}

		// Create new user's account
		Membre membre = new Membre(signUpRequest.getPseudo(), encoder.encode(signUpRequest.getMotDePasse()));

		utilisateurDao.save(membre);

		return ResponseEntity.ok(new MessageResponse("Le membre a bien été enregistré !!"));
	}
}
