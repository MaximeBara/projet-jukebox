package m2i.formation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IEnchereDao;
import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Administrateur;
import m2i.formation.model.EnchereGratuite;
import m2i.formation.model.Invite;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;
import m2i.formation.model.TypeEnchere;

@SpringBootTest
public class TestJukebox {
	@Autowired
	IJukeboxDao jukeboxDao;
	@Autowired
	IUtilisateurDao utilisateurDao;
	@Autowired
	IPlaylistDao playlistDao;
	@Autowired
	ITitreDao titreDao;
	@Autowired
	IEnchereDao enchereDao;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testFindAllConnectes() {
		Jukebox jukeboxDisco = new Jukebox("Le jukebox disco", "54198498", TypeEnchere.GRATUITE);
		jukeboxDao.save(jukeboxDisco);
		int before = jukeboxDao.findAllConnectesById(jukeboxDisco.getId()).size();
		Membre membre = new Membre("membre", 0, "***");
		membre.setJukebox(jukeboxDisco);
		utilisateurDao.save(membre);
		assertEquals(before + 1, jukeboxDao.findAllConnectesById(jukeboxDisco.getId()).size());
	}

	@Test
	public void testFindAllFansById() {
		Jukebox jukeboxDisco = new Jukebox("Le jukebox disco", "54198498", TypeEnchere.GRATUITE);
		jukeboxDao.save(jukeboxDisco);
		int before = jukeboxDao.findAllFansById(jukeboxDisco.getId()).size();
		Membre membre = new Membre("membre", 0, "***");
		membre.setJukeboxFavoris(jukeboxDisco);
		utilisateurDao.save(membre);
		assertEquals(before + 1, jukeboxDao.findAllFansById(jukeboxDisco.getId()).size());
	}

	@Test
	public void testFindAdministrateurById() {
		Administrateur admin = new Administrateur("AdminTest", 0, "******");
		utilisateurDao.save(admin);

		Jukebox jukebox = new Jukebox("AdminTest", "19448", TypeEnchere.MIXTE);
		jukebox.setAdministrateur(admin);

		jukeboxDao.save(jukebox);

		Administrateur newAdmin = jukeboxDao.findAdministrateurById(jukebox.getId());
		assertEquals(admin.getPseudo(), newAdmin.getPseudo());
		assertEquals(admin.getMotDePasse(), newAdmin.getMotDePasse());
		assertEquals(admin.getPoint(), newAdmin.getPoint());
	}

	@Test
	public void testFindPlaylistById() throws ParseException {

		Jukebox jukebox = new Jukebox("AdminTest", "19448", TypeEnchere.MIXTE);
		Playlist playlist = new Playlist("La playlist", sdf.parse("01/03/2021"));
		playlistDao.save(playlist);
		jukebox.setPlaylist(playlist);
		jukeboxDao.save(jukebox);

		Titre titre = new Titre("Le titre", "L'artiste", "youtube.fr/test");

		titreDao.save(titre);

		playlist.getTitres().add(titre);
		playlistDao.save(playlist);
		jukeboxDao.save(jukebox);

		Playlist newPlaylist = jukeboxDao.findPlaylistById(jukebox.getId());

		assertEquals(playlist.getId(), newPlaylist.getId());
		assertEquals(playlist.getNom(), newPlaylist.getNom());
		assertEquals(playlist.getDateCreation(), newPlaylist.getDateCreation());
	}

	@Test
	public void testFindByUtilisateur() {
		Jukebox jukeboxDisco = new Jukebox("Le jukebox disco", "54198498", TypeEnchere.GRATUITE);

		jukeboxDao.save(jukeboxDisco);

		Membre membre = new Membre("membreTest", 0, "***");
		Invite invite = new Invite("inviteTest");

		membre.setJukebox(jukeboxDisco);
		invite.setJukebox(jukeboxDisco);

		utilisateurDao.save(membre);
		utilisateurDao.save(invite);

		Jukebox newJukeboxMembre = jukeboxDao.findByUtilisateur(membre);
		Jukebox newJukeboxInvite = jukeboxDao.findByUtilisateur(invite);

		assertEquals(jukeboxDisco.getId(), newJukeboxMembre.getId());
		assertEquals(jukeboxDisco.getNom(), newJukeboxMembre.getNom());
		assertEquals(jukeboxDisco.getCode(), newJukeboxMembre.getCode());
		assertEquals(jukeboxDisco.getTypeEnchere(), newJukeboxMembre.getTypeEnchere());

		assertEquals(jukeboxDisco.getId(), newJukeboxInvite.getId());
		assertEquals(jukeboxDisco.getNom(), newJukeboxInvite.getNom());
		assertEquals(jukeboxDisco.getCode(), newJukeboxInvite.getCode());
		assertEquals(jukeboxDisco.getTypeEnchere(), newJukeboxInvite.getTypeEnchere());
	}

	@Test
	public void testFindAllByMembre() {
		Jukebox jukeboxDisco = new Jukebox("Le jukebox disco", "54198498", TypeEnchere.GRATUITE);
		Jukebox jukeboxRock = new Jukebox("Le jukebox rock", "54198498", TypeEnchere.MIXTE);

		jukeboxDao.save(jukeboxDisco);
		jukeboxDao.save(jukeboxRock);

		Membre membre = new Membre("membreTest", 0, "***");

		membre.getJukeboxFavoris().add(jukeboxDisco);
		membre.getJukeboxFavoris().add(jukeboxRock);

		utilisateurDao.save(membre);

		assertEquals(2, jukeboxDao.findAllByMembre(membre).size());
	}

	@Test
	public void testFindByEnchere() {
		Jukebox jukeboxDisco = new Jukebox("FindByEnchere", "54198498", TypeEnchere.GRATUITE);
		jukeboxDao.save(jukeboxDisco);
		EnchereGratuite enchere = new EnchereGratuite(LocalDateTime.now(), 100);
		enchere.setJukebox(jukeboxDisco);
		enchereDao.save(enchere);
		Jukebox newJukebox = jukeboxDao.findByEnchere(enchere);

		assertEquals(jukeboxDisco.getId(), newJukebox.getId());
		assertEquals(jukeboxDisco.getNom(), newJukebox.getNom());
		assertEquals(jukeboxDisco.getCode(), newJukebox.getCode());
		assertEquals(jukeboxDisco.getTypeEnchere(), newJukebox.getTypeEnchere());
	}

	@Test
	public void testFindByAdministrateur() {
		Jukebox jukeboxDisco = new Jukebox("FindByEnchere", "54198498", TypeEnchere.GRATUITE);
		jukeboxDao.save(jukeboxDisco);
		Administrateur admin = new Administrateur("Admin", 0, "***");
		jukeboxDisco.setAdministrateur(admin);

		utilisateurDao.save(admin);
		jukeboxDao.save(jukeboxDisco);

		Jukebox newJukebox = jukeboxDao.findByAdministrateur(admin);

		assertEquals(jukeboxDisco.getId(), newJukebox.getId());
		assertEquals(jukeboxDisco.getNom(), newJukebox.getNom());
		assertEquals(jukeboxDisco.getCode(), newJukebox.getCode());
		assertEquals(jukeboxDisco.getTypeEnchere(), newJukebox.getTypeEnchere());
	}
}
