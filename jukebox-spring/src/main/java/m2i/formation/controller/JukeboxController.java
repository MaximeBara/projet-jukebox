package m2i.formation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
