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
import m2i.formation.model.Enchere;
import m2i.formation.model.IViews;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/enchere")
public class EnchereController {

	@Autowired
	private IEnchereDao enchereDao;

	@GetMapping("")
	@JsonView(IViews.IViewEnchere.class)
	public List<Enchere> list() {
		List<Enchere> encheres = enchereDao.findAll();

		return encheres;
	}

	@GetMapping("/findByMembre/{id}")
	@JsonView(IViews.IViewEnchereWithAll.class)
	public List<Enchere> findAllByMembre(@PathVariable Long id) {

		List<Enchere> encheres = enchereDao.findAllEnchereByMembre(id);

		return encheres;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewEnchere.class)
	public Enchere find(@PathVariable Long id) {
		Optional<Enchere> optEnchere = enchereDao.findById(id);

		if (optEnchere.isPresent()) {
			return optEnchere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/withMembre")
	@JsonView(IViews.IViewEnchereWithMembre.class)
	public Enchere findWithMembre(@PathVariable Long id) {
		Optional<Enchere> optEnchere = enchereDao.findByIdWithMembre(id);

		if (optEnchere.isPresent()) {
			return optEnchere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/withJukebox")
	@JsonView(IViews.IViewEnchereWithJukebox.class)
	public Enchere findWithJukebox(@PathVariable Long id) {
		Optional<Enchere> optEnchere = enchereDao.findByIdWithJukebox(id);

		if (optEnchere.isPresent()) {
			return optEnchere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/withTitre")
	@JsonView(IViews.IViewEnchereWithTitre.class)
	public Enchere findWithTitre(@PathVariable Long id) {
		Optional<Enchere> optEnchere = enchereDao.findByIdWithTitre(id);

		if (optEnchere.isPresent()) {
			return optEnchere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}/withMembreJukeboxAndTitre")
	@JsonView(IViews.IViewEnchereWithMembreJukeboxAndTitre.class)
	public Enchere findWithMembreJukeboxAndTitre(@PathVariable Long id) {
		Optional<Enchere> optEnchere = enchereDao.findByIdWithTitre(id);

		if (optEnchere.isPresent()) {
			return optEnchere.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/non-terminee-by-jukebox-and-titre-with-membre/{idJukebox}/{idTitre}")
	@JsonView(IViews.IViewEnchereWithMembreJukeboxAndTitre.class)
	public List<Enchere> findAllEnchereNonTermineeByTitreWithMembre(@PathVariable Long idJukebox,
			@PathVariable Long idTitre) {
		Optional<List<Enchere>> optEncheres = enchereDao.findAllEnchereNonTermineeByJukeboxAndTitreWithMembre(idJukebox,
				idTitre);

		if (optEncheres.isPresent()) {
			return optEncheres.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewEnchere.class)
	public Enchere create(@RequestBody Enchere enchere) {
		enchere = enchereDao.save(enchere);
		return enchere;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewEnchere.class)
	public Enchere update(@RequestBody Enchere enchere, @PathVariable Long id) {
		if (!enchereDao.existsById(id) || !id.equals(enchere.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		enchere = enchereDao.save(enchere);

		return enchere;
	}

	@DeleteMapping("/{id}")
	@JsonView(IViews.IViewEnchere.class)
	public void delete(@PathVariable Long id) {
		if (!enchereDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		enchereDao.deleteById(id);

		if (enchereDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}

}
