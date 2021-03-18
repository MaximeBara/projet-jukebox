package m2i.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Membre;

@RestController
@RequestMapping("/api/membre")
public class MembreController {
	
	@Autowired
	private IUtilisateurDao membreDao;

	@GetMapping("")
	@JsonView(IViews.IViewMembre.class)
	public List<Membre> list() {
		List<Membre> membres = membreDao.findMembre();

		return membres;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewMembre.class)
	public Membre find(@PathVariable Long id) {
		Optional<Membre> membreOpt = membreDao.findByID(id);

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

}
