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

import m2i.formation.dao.IJukeboxDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Jukebox;

@RestController
@RequestMapping("/api/jukebox")
public class JukeboxController {

	@Autowired
	private IJukeboxDao jukeboxDao;

	@GetMapping("")
	@JsonView(IViews.IViewJukebox.class)
	public List<Jukebox> list() {
		List<Jukebox> jukeboxes = jukeboxDao.findAll();

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
