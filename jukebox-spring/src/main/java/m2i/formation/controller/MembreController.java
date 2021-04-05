package m2i.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Utilisateur;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/membre")
public class MembreController {

	@Autowired
	private IUtilisateurDao membreDao;

	@Autowired
	private IJukeboxDao jukeboxDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("")
	@JsonView(IViews.IViewMembre.class)
	public List<Membre> list() {
		List<Membre> membres = membreDao.findMembre();

		return membres;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewMembre.class)
	public Membre find(@PathVariable Long id) {
		Optional<Membre> membreOpt = membreDao.findMembreId(id);

		if (membreOpt.isPresent()) {
			return membreOpt.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewMembre.class)
	public Membre create(@RequestBody Membre membre) {
		membre = membreDao.save(membre);

		return membre;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewMembre.class)
	public Membre update(@RequestBody Membre membre, @PathVariable Long id) {

		if (!membreDao.existsById(id) || !id.equals(membre.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
		membre = membreDao.save(membre);

		return membre;
	}

	@DeleteMapping("/{id}")
	@JsonView(IViews.IViewMembre.class)
	public void delete(@PathVariable Long id) {
		if (!membreDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		membreDao.deleteById(id);

		if (membreDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}

	@GetMapping("/{idUser}/{idJukebox}/isFavori")
	@JsonView(IViews.IViewUtilisateur.class)
	public boolean isFavori(@PathVariable Long idUser, @PathVariable Long idJukebox) {
		Membre membre = (Membre) membreDao.findById(idUser).get();
		boolean b = membre.getJukeboxFavoris().contains(jukeboxDao.findById(idJukebox).get());
		System.out.println("=>>>>>>>>" + b);
		return b;
	}

	@PutMapping("/{id}/removeFavori")
	@JsonView(IViews.IViewJukebox.class)
	public Membre removeFavori(@RequestBody Jukebox jukebox, @PathVariable Long id) {
		
		System.out.println("=>>>>>>> remove");
		
		Membre membre = membreDao.findMembreId(id).get();
		
		System.out.println(membre.getJukeboxFavoris());
		System.out.println(jukebox.getId());
		
		for(Jukebox j : membre.getJukeboxFavoris()) {
			if(j.getId() == jukebox.getId()) {
				System.out.println(j);
				j.getFans().remove(membre);
				membre.getJukeboxFavoris().remove(j);
				jukebox = j;
				break;
			}
		}	
		
		jukeboxDao.save(jukebox);

		return membre;
	}

	@PutMapping("/{id}/addFavori")
	@JsonView(IViews.IViewJukebox.class)
	public Membre addFavori(@RequestBody Jukebox jukebox, @PathVariable Long id) {

		System.out.println("=>>>>>>> add");

		Membre membre = membreDao.findMembreId(id).get();
		membre.getJukeboxFavoris().add(jukebox);

		membre = membreDao.save(membre);

		return membre;
	}
}
