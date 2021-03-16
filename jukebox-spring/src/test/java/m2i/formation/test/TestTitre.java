package m2i.formation.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.ITitreDao;

@SpringBootTest
public class TestTitre {
	@Autowired
	ITitreDao titreDao;

	@Test
	public void testFindAllByPlaylist() {
		// TODO
	}

	@Test
	public void testFindByEnchere() {
		// TODO
	}

}
