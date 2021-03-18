package m2i.formation.controller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.ParseException;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import m2i.formation.dao.ITitreDao;
import m2i.formation.model.IViews;
import m2i.formation.model.Titre;

@RestController
@RequestMapping("/api/titre")
public class TitreController {

	@Autowired
	private ITitreDao titreDao;

	@GetMapping("")
	@JsonView(IViews.IViewTitre.class)
	public List<Titre> list() {
		List<Titre> titres = titreDao.findAll();

		return titres;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewTitre.class)
	public Titre find(@PathVariable Long id) {
		Optional<Titre> optTitre = titreDao.findById(id);

		if (optTitre.isPresent()) {
			return optTitre.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{lien}")
	@JsonView(IViews.IViewTitre.class)
	public Titre findByLien(@PathVariable String lien) {
		Optional<Titre> optTitre = titreDao.findByLien(lien);

		if (optTitre.isPresent()) {
			return optTitre.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewTitre.class)
	public Titre create(@RequestBody Titre titre) {
		titre = titreDao.save(titre);

		return titre;
	}

	@PostMapping("/{lien}/createByLien")
	@JsonView(IViews.IViewTitre.class)
	public Titre createByLien(@PathVariable String lien) throws ParseException {
		Optional<Titre> optTitre = titreDao.findByLien(lien);

		if (optTitre.isPresent()) {
			return optTitre.get();
		} else {
			return titreDao.save(createNewTitre(lien));
		}
	}

	private Titre createNewTitre(String lien) {
		RestTemplate rest = new RestTemplate();
		String url = "https://youtube.googleapis.com/youtube/v3/videos?part=snippet&id=" + lien
				+ "&alt=json&key=AIzaSyBQ71OScLZnxIU09fKStdgj5h7dvUG4IxM\r\n";
		String result = rest.getForObject(url, String.class);
		JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
		JsonObject je = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject().getAsJsonObject("snippet");

		System.out.println("-->" + je.get("title").getAsString() + je.get("channelTitle").getAsString());
		
		return new Titre(je.get("title").getAsString(), je.get("channelTitle").getAsString(), lien);
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewTitre.class)
	public Titre update(@RequestBody Titre titre, @PathVariable Long id) {
		if (!titreDao.existsById(id) || !id.equals(titre.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		titre = titreDao.save(titre);
 
		return titre;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!titreDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		titreDao.deleteById(id);

		if (titreDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
