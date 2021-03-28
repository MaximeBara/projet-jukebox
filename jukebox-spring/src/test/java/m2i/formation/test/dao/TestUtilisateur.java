package m2i.formation.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Administrateur;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
import m2i.formation.model.TypeEnchere;

@SpringBootTest
public class TestUtilisateur {

	@Autowired
	private IUtilisateurDao userDao;

	@Autowired
	private IJukeboxDao jukeboxDao;

	@Autowired
	private IPlaylistDao playlistDao;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testDeleteAdminAndCascade() {

		Administrateur admin1 = new Administrateur("Maxime", 10, "test");

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		jukeboxDao.save(j1);

		userDao.save(admin1);

		Optional<Jukebox> findJukbox = jukeboxDao.findById(j1.getId());
		// faire un jukbox.getAdmin
		findJukbox.get().setAdministrateur(admin1);

		jukeboxDao.save(findJukbox.get());

		assertEquals("Maxime", findJukbox.get().getAdministrateur().getPseudo());

		Optional<Administrateur> findadmin = userDao.findAdminId(admin1.getId());

		userDao.delete(findadmin.get());

	}

	@Test
	public void testAdminAndMemberDeleteCascadePlayist() throws ParseException {

		Administrateur admin2 = new Administrateur("Josse", 12, "lolo");

		Playlist p1 = new Playlist("Ma musique", sdf.parse("19/03/2021"));
		playlistDao.save(p1);

		userDao.save(admin2);

		Optional<Playlist> findPlaylist = playlistDao.findById(p1.getId());

		findPlaylist.get().setCreateur(admin2);
		playlistDao.save(findPlaylist.get());

		assertEquals("Josse", findPlaylist.get().getCreateur().getPseudo());

		Optional<Membre> findMembre = userDao.findMembreId(admin2.getId());

		userDao.delete(findMembre.get());

	}

	@Test
	public void testIsAdmin() {
		Administrateur admin = new Administrateur("admin", 10, "password");
		userDao.save(admin);
		
		Jukebox j = new Jukebox("Jukebox", "test", TypeEnchere.PAYANTE);
		j.setAdministrateur(admin);
		jukeboxDao.save(j);
		
		Administrateur admin2 = new Administrateur("admin2", 10, "password");
		userDao.save(admin2);
		
		Membre membre = new Membre("membre", 10, "password");
		userDao.save(membre);
		
		Jukebox j2 = new Jukebox("Jukebox2", "test2", TypeEnchere.PAYANTE);
		j2.setAdministrateur(admin2);
		jukeboxDao.save(j2);
		
		assertEquals(true, userDao.isAdmin(admin.getId(), j.getId()));
		assertEquals(false, userDao.isAdmin(membre.getId(), j.getId()));
		assertEquals(false, userDao.isAdmin(admin2.getId(), j.getId()));
		assertEquals(true, userDao.isAdmin(admin2.getId(), j2.getId()));
	}

}
