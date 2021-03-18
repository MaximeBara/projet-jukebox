package m2i.formation.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.IUtilisateurDao;

@SpringBootTest
public class TestUtilisateur {

	@Autowired
	private IUtilisateurDao userDao;
	
	@Autowired
	private IJukeboxDao JukboxDao;
	
	
	
}
