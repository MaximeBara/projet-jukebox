package m2i.formation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IPlaylistDao;

@SpringBootTest
public class TestPlaylist {
	@Autowired
	IPlaylistDao playlistDao;

	@Test
	public void testFindAllByMembre() {
		// TODO
	}

	@Test
	public void testFindAllByTitre() {
		// TODO
	}

}
