package m2i.formation.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;

@SpringBootTest
public class TestPlaylist {
	@Autowired
	IPlaylistDao playlistDao;
	@Autowired
	IUtilisateurDao membreDao;
	@Autowired
	ITitreDao titreDao;

	@Test
	public void testFindAllByMembre() {
		Membre membre = new Membre("membrePlaylist", 0, "null");

		Playlist playlist1 = new Playlist("Playlist 1", new Date());
		Playlist playlist2 = new Playlist("Playlist 2", new Date());
		Playlist playlist3 = new Playlist("Playlist 3", new Date());

		membreDao.save(membre);
		playlistDao.save(playlist1);
		playlistDao.save(playlist2);
		playlistDao.save(playlist3);

		assertEquals(0, playlistDao.findAllByMembre(membre.getId()).size());

		playlist1.setCreateur(membre);
		playlist2.setCreateur(membre);
		playlist3.setCreateur(membre);

		playlistDao.save(playlist1);
		playlistDao.save(playlist2);
		playlistDao.save(playlist3);

		assertEquals(3, playlistDao.findAllByMembre(membre.getId()).size());
	}

	@Test
	public void testFindAllByTitre() {
		Titre titre = new Titre("le titre", "artiste", "null");

		Playlist playlist1 = new Playlist("Playlist 1", new Date());
		Playlist playlist2 = new Playlist("Playlist 2", new Date());
		Playlist playlist3 = new Playlist("Playlist 3", new Date());

		titreDao.save(titre);
		playlistDao.save(playlist1);
		playlistDao.save(playlist2);
		playlistDao.save(playlist3);

		assertEquals(0, playlistDao.findAllByTitre(titre.getId()).size());

		playlist1.getTitres().add(titre);
		playlist2.getTitres().add(titre);
		playlist3.getTitres().add(titre);

		playlistDao.save(playlist1);
		playlistDao.save(playlist2);
		playlistDao.save(playlist3);

		assertEquals(3, playlistDao.findAllByTitre(titre.getId()).size());
	}

}
