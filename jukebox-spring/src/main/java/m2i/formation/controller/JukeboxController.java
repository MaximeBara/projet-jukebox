package m2i.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import m2i.formation.dao.IJukeboxDao;

@RestController
@RequestMapping("/api/jukebox")
public class JukeboxController {
	
	@Autowired
	private IJukeboxDao jukeboxDao;

}
