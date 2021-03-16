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

import m2i.formation.dao.IPlaylistDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Playlist;

@RestController
@RequestMapping("/api/titre")
public class PlaylistController {

	@Autowired
	private IPlaylistDao playlistDao;

	@GetMapping("")
	@JsonView(IViews.IViewPlaylist.class)
	public List<Playlist> list() {
		List<Playlist> playlists = playlistDao.findAll();

		return playlists;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewPlaylist.class)
	public Playlist find(@PathVariable Long id) {
		Optional<Playlist> optPlaylist = playlistDao.findById(id);

		if (optPlaylist.isPresent()) {
			return optPlaylist.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewPlaylist.class)
	public Playlist create(@RequestBody Playlist playlist) {
		playlist = playlistDao.save(playlist);

		return playlist;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewPlaylist.class)
	public Playlist update(@RequestBody Playlist playlist, @PathVariable Long id) {
		if (!playlistDao.existsById(id) || !id.equals(playlist.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		playlist = playlistDao.save(playlist);

		return playlist;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!playlistDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		playlistDao.deleteById(id);

		if (playlistDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
