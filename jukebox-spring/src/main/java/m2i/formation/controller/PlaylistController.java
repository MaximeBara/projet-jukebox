package m2i.formation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/playlist")
@PropertySource("classpath:config.properties")
public class PlaylistController {

	@Value("${data.googleKey}")
	private String googleKey;

	@Autowired
	private IPlaylistDao playlistDao;

	@Autowired
	private ITitreDao titreDao;

	@GetMapping("")
	@JsonView(IViews.IViewPlaylist.class)
	public List<Playlist> list() {
		List<Playlist> playlists = playlistDao.findAll();

		return playlists;
	}

	@GetMapping("/{id}/allByMembre")
	@JsonView(IViews.IViewPlaylist.class)
	public List<Playlist> findAllByMembre(@PathVariable Long id) {
		List<Playlist> playlists = playlistDao.findAllByMembre(id);

		return playlists;
	}

	@GetMapping("/{id}/allByTitre")
	@JsonView(IViews.IViewPlaylist.class)
	public List<Playlist> findAllByTitre(@PathVariable Long id) {
		List<Playlist> playlists = playlistDao.findAllByTitre(id);

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

	/**
	 * Créé une playlist si le lien passé en paramètre n'existe pas en base de
	 * données, update la playlist ayant comme lien le paramètre si cette dernière
	 * existe.
	 * 
	 * @param nom
	 * @param lien
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/{nom}/createByLien/{lien}")
	@JsonView(IViews.IViewTitre.class)
	public Playlist createByLien(@PathVariable String nom, @PathVariable String lien) {
		Optional<Playlist> optPlaylist = playlistDao.findByLien(lien);
		Playlist p;

		if (optPlaylist.isPresent()) {
			p = optPlaylist.get();
		} else {
			p = new Playlist(nom, new Date(), lien);
		}
		
		List<Titre> titres = findAllTitreFromLien(lien);
		
		System.out.println("Titre.size(): " + titres.size());
		
		if( !titres.isEmpty() )
			p.setTitres(titres);
		
		return playlistDao.save(p);
	}

	/**
	 * Créer une liste de titres provenant de la playlist Youtube passée en
	 * paramètre.
	 * 
	 * @param playlistId
	 * @return
	 */
	private List<Titre> findAllTitreFromLien(String playlistId) {

		List<Titre> liste = new ArrayList<Titre>();

		RestTemplate rest = new RestTemplate();
		String token = "";

		do {
			String url = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&pageToken="
					+ token + "&playlistId=" + playlistId + "&key=" + googleKey;
			String result = rest.getForObject(url, String.class);
			JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
			token = jsonObject.get("nextPageToken") == null ? "" : jsonObject.get("nextPageToken").getAsString();

			for (JsonElement elt : jsonObject.getAsJsonArray("items")) {
				String titre = elt.getAsJsonObject().getAsJsonObject("snippet").get("title").getAsString();
				String lien = elt.getAsJsonObject().getAsJsonObject("snippet").getAsJsonObject("resourceId")
						.get("videoId").getAsString();
				Optional<Titre> optTitre = titreDao.findByLien(lien);
				if(optTitre.isEmpty()) {
					Titre t = new Titre(titre, lien);
					titreDao.save(t);
					liste.add(t);
				}else {
					liste.add(optTitre.get());
				}
			}
		} while (token != "");

		return liste;
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
