package m2i.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Utilisateur;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

	@Autowired
	private IUtilisateurDao utilisateurDao;

	@GetMapping("")
	@JsonView(IViews.IViewUtilisateur.class)
	public List<Utilisateur> list() {
		List<Utilisateur> utilisateurs = utilisateurDao.findAllUtilisateur();
		return utilisateurs;
	}

}
