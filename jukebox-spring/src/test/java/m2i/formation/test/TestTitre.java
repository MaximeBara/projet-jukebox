package m2i.formation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;

@SpringBootTest
public class TestTitre {
	@Autowired
	ITitreDao titreDao;
	@Autowired
	IPlaylistDao playlistDao;

	@Test
	public void testFindAllByPlaylist() {
		Titre titre1 = new Titre("le titre1", "artiste", "null");
		Titre titre2 = new Titre("le titre2", "artiste", "null");
		Titre titre3 = new Titre("le titre3", "artiste", "null");

		Playlist playlist = new Playlist("Playlist", new Date());

		titreDao.save(titre1);
		titreDao.save(titre2);
		titreDao.save(titre3);
		playlistDao.save(playlist);


		assertEquals(0, titreDao.findAllByPlaylist(playlist.getId()).size());

		playlist.getTitres().add(titre1);
		playlist.getTitres().add(titre2);
		playlist.getTitres().add(titre3);

		playlistDao.save(playlist);


		assertEquals(3, titreDao.findAllByPlaylist(playlist.getId()).size());
	}

	@Test
	public void testFindByEnchere() {
		// TODO
	}
	
	
	@Test
	public void testFindByLien() {
		// TODO
	}
	

}
