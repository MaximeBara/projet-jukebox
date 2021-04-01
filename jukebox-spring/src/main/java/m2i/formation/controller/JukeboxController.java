package m2i.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import m2i.formation.dao.IEnchereDao;
import m2i.formation.dao.IJukeboxDao;
import m2i.formation.model.Enchere;
import m2i.formation.model.IViews;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Titre;
import m2i.formation.model.Utilisateur;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/jukebox")
public class JukeboxController {

	@Autowired
	private IJukeboxDao jukeboxDao;
	@Autowired
	private IEnchereDao enchereDao;

	@GetMapping("")
	@JsonView(IViews.IViewJukebox.class)
	public List<Jukebox> list() {
		List<Jukebox> jukeboxes = jukeboxDao.findAll();

		return jukeboxes;
	}

	@GetMapping("allWithPlaylist")
	@JsonView(IViews.IViewJukeboxWithPlaylist.class)
	public List<Jukebox> findAllWithPlaylist() {
		List<Jukebox> jukeboxes = jukeboxDao.findAllWithPlaylist();

		return jukeboxes;
	}

	@GetMapping("allWithPlaylistAndTitre")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public List<Jukebox> findAllWithPlaylistAndTitre() {
		List<Jukebox> jukeboxes = jukeboxDao.findAllWithPlaylistAndTitre();

		return jukeboxes;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewJukebox.class)
	public Jukebox find(@PathVariable Long id) {
		Optional<Jukebox> optJukebox = jukeboxDao.findById(id);

		if (optJukebox.isPresent()) {
			return optJukebox.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findByCode/{code}")
	@JsonView(IViews.IViewJukebox.class)
	public Jukebox findByCode(@PathVariable String code) {
		Optional<Jukebox> optJukebox = jukeboxDao.findByCode(code);

		if (optJukebox.isPresent()) {
			return optJukebox.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/allConnectes")
	@JsonView(IViews.IViewJukebox.class)
	public List<Utilisateur> findAllConnectes(@PathVariable Long id) {
		List<Utilisateur> utilisateurs = jukeboxDao.findAllConnectesById(id);

		return utilisateurs;
	}

	@GetMapping("/{id}/allByAdministrateur")
	@JsonView(IViews.IViewJukebox.class)
	public List<Jukebox> findAllByAdministrateur(@PathVariable Long id) {
		List<Jukebox> jukeboxes = jukeboxDao.findAllByAdministrateur(id);

		return jukeboxes;
	}

	@GetMapping("/{id}/allByMembre")
	@JsonView(IViews.IViewJukebox.class)
	public List<Jukebox> findAllByMembre(@PathVariable Long id) {
		List<Jukebox> jukeboxes = jukeboxDao.findAllByMembre(id);

		return jukeboxes;
	}

	@GetMapping("/{id}/allByEnchere")
	@JsonView(IViews.IViewJukebox.class)
	public Jukebox findByEnchere(@PathVariable Long id) {
		Optional<Jukebox> optJukebox = jukeboxDao.findByEnchere(id);

		if (optJukebox.isPresent()) {
			return optJukebox.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/withPlaylist")
	@JsonView(IViews.IViewJukeboxWithPlaylist.class)
	public Jukebox findWithPlaylist(@PathVariable Long id) {
		Optional<Jukebox> optJukebox = jukeboxDao.findByIdWithPlaylist(id);

		if (optJukebox.isPresent()) {
			return optJukebox.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/withPlaylistAndTitre")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public Jukebox findWithPlaylistAndTitre(@PathVariable Long id) {
		Optional<Jukebox> optJukebox = jukeboxDao.findByIdWithPlaylistAndTitre(id);

		if (optJukebox.isPresent()) {
			return optJukebox.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/allTitreOrderByEnchere")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public List<Titre> findAllTitreOrderByEnchere(@PathVariable Long id) {
		List<Titre> titres = jukeboxDao.findAllTitreOrderByEnchere(id);

		return titres;
	}

	@GetMapping("/{id}/allTitreOrderByEnchereSwapEnCours")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public List<Titre> findAllTitreOrderByEnchereSwapEnCours(@PathVariable Long id) {
		List<Titre> titres1 = jukeboxDao.findAllTitreOrderByEnchere(id);
		List<Titre> titres2 = jukeboxDao.findAllTitreWithoutEnchere(id);

		Optional<Jukebox> jukebox = jukeboxDao.findById(id);

		titres1.addAll(titres2);

		jukebox.get().setTitreEnCours(titres1.get(0));

		jukeboxDao.save(jukebox.get());

		return titres1;
	}

	@GetMapping("/{id}/nextTitre")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public Optional<Titre> findNextTitre(@PathVariable Long id) {
		Optional<Titre> titre = Optional.ofNullable(null);

		try {
			titre = Optional.ofNullable(jukeboxDao.findNextTitre(id).get(0));
		} catch (Exception e) {

		}

		return titre;
	}

	@GetMapping("/{id}/allTitreWithoutEnchere")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public List<Titre> findAllTitreWithoutEnchere(@PathVariable Long id) {
		List<Titre> titres = jukeboxDao.findAllTitreWithoutEnchere(id);

		return titres;
	}

	@GetMapping("/{id}/allTitre")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public List<Titre> findAllTitre(@PathVariable Long id) {
		List<Titre> titres = jukeboxDao.findAllTitreOrderByEnchere(id);
		titres.addAll(jukeboxDao.findAllTitreWithoutEnchere(id));

		return titres;
	}

	@PutMapping("/{idJukebox}/setTerminee/{idTitre}")
	@JsonView(IViews.IViewJukeboxWithPlaylistAndTitre.class)
	public void setTerminee(@PathVariable Long idJukebox, @PathVariable Long idTitre) {
		List<Enchere> encheres = enchereDao.findAllEnchereNotTermineeByTitreAndJukebox(idJukebox, idTitre);
		for (Enchere e : encheres) {
			e.setTerminee(true);
			enchereDao.save(e);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewJukebox.class)
	public Jukebox create(@RequestBody Jukebox jukebox) {
		jukebox = jukeboxDao.save(jukebox);

		return jukebox;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewJukebox.class)
	public Jukebox update(@RequestBody Jukebox jukebox, @PathVariable Long id) {
		if (!jukeboxDao.existsById(id) || !id.equals(jukebox.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		jukebox = jukeboxDao.save(jukebox);

		return jukebox;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!jukeboxDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		jukeboxDao.deleteById(id);

		if (jukeboxDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}

}
