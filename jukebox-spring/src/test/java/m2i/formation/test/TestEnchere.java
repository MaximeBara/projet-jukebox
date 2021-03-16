package m2i.formation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IEnchereDao;
import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.EnchereGratuite;
import m2i.formation.model.EncherePayante;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Titre;
import m2i.formation.model.TypeEnchere;

@SpringBootTest
public class TestEnchere {

	@Autowired
	private IEnchereDao enchereDao;
	@Autowired
	private IJukeboxDao jukeboxDao;
	@Autowired
	private IUtilisateurDao utilisateurDao;
	@Autowired
	private ITitreDao titreDao;

	@Test
	public void testFindAllEnchereNotTermineeByJukebox() {
		EnchereGratuite eg1 = new EnchereGratuite(LocalDateTime.now(), 1);
		EnchereGratuite eg2 = new EnchereGratuite(LocalDateTime.now(), 2);
		EncherePayante ep1 = new EncherePayante(LocalDateTime.now(), 1);
		EncherePayante ep2 = new EncherePayante(LocalDateTime.now(), 2);
		EncherePayante ep3 = new EncherePayante(LocalDateTime.now(), 3);

		Membre m1 = new Membre("Maxime", 10, "test");
		utilisateurDao.save(m1);

		Titre t1 = new Titre("Offender", "Dimension", "https://www.youtube.com/watch?v=d2u3BRGd2rs");
		titreDao.save(t1);

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		jukeboxDao.save(j1);

		Jukebox j2 = new Jukebox("Rock music Jukebox", "987654321", TypeEnchere.PAYANTE);
		jukeboxDao.save(j2);

		eg1.setTerminee(true);
		ep1.setTerminee(true);

		eg1.setJukebox(j1);
		eg1.setMembre(m1);
		eg1.setTitre(t1);

		eg2.setJukebox(j1);
		eg2.setMembre(m1);
		eg2.setTitre(t1);

		ep1.setJukebox(j1);
		ep1.setMembre(m1);
		ep1.setTitre(t1);

		ep2.setJukebox(j1);
		ep2.setMembre(m1);
		ep2.setTitre(t1);

		ep3.setJukebox(j2);
		ep3.setMembre(m1);
		ep3.setTitre(t1);

		enchereDao.save(eg1);
		enchereDao.save(eg2);
		enchereDao.save(ep1);
		enchereDao.save(ep2);
		enchereDao.save(ep3);

		assertEquals(2, enchereDao.findAllEnchereNotTermineeByJukebox(j1).size());

		assertEquals(1, enchereDao.findAllEnchereNotTermineeByJukebox(j2).size());

	}

	@Test
	public void testFindAllEnchereTermineeByJukebox() {

		EnchereGratuite eg1 = new EnchereGratuite(LocalDateTime.now(), 1);
		EnchereGratuite eg2 = new EnchereGratuite(LocalDateTime.now(), 2);
		EncherePayante ep1 = new EncherePayante(LocalDateTime.now(), 1);
		EncherePayante ep2 = new EncherePayante(LocalDateTime.now(), 2);
		EncherePayante ep3 = new EncherePayante(LocalDateTime.now(), 3);

		Membre m1 = new Membre("Maxime", 10, "test");
		utilisateurDao.save(m1);

		Titre t1 = new Titre("Offender", "Dimension", "https://www.youtube.com/watch?v=d2u3BRGd2rs");
		titreDao.save(t1);

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		jukeboxDao.save(j1);

		Jukebox j2 = new Jukebox("Rock music Jukebox", "987654321", TypeEnchere.PAYANTE);
		jukeboxDao.save(j2);

		eg1.setTerminee(true);
		ep1.setTerminee(true);

		eg1.setJukebox(j1);
		eg1.setMembre(m1);
		eg1.setTitre(t1);

		eg2.setJukebox(j1);
		eg2.setMembre(m1);
		eg2.setTitre(t1);

		ep1.setJukebox(j1);
		ep1.setMembre(m1);
		ep1.setTitre(t1);

		ep2.setJukebox(j1);
		ep2.setMembre(m1);
		ep2.setTitre(t1);

		ep3.setJukebox(j2);
		ep3.setMembre(m1);
		ep3.setTitre(t1);

		enchereDao.save(eg1);
		enchereDao.save(eg2);
		enchereDao.save(ep1);
		enchereDao.save(ep2);
		enchereDao.save(ep3);

		assertEquals(2, enchereDao.findAllEnchereTermineeByJukebox(j1).size());

		assertEquals(0, enchereDao.findAllEnchereTermineeByJukebox(j2).size());
	}

	@Test
	public void testFindAllEnchereByMembre() {

		EnchereGratuite eg1 = new EnchereGratuite(LocalDateTime.now(), 1);
		EnchereGratuite eg2 = new EnchereGratuite(LocalDateTime.now(), 2);
		EncherePayante ep1 = new EncherePayante(LocalDateTime.now(), 1);
		EncherePayante ep2 = new EncherePayante(LocalDateTime.now(), 2);
		EncherePayante ep3 = new EncherePayante(LocalDateTime.now(), 3);

		Membre m1 = new Membre("Maxime", 10, "test");
		Membre m2 = new Membre("Josse", 15, "test");
		Membre m3 = new Membre("Guillaume", 20, "test");
		utilisateurDao.save(m1);
		utilisateurDao.save(m2);
		utilisateurDao.save(m3);

		Titre t1 = new Titre("Offender", "Dimension", "https://www.youtube.com/watch?v=d2u3BRGd2rs");
		titreDao.save(t1);

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		Jukebox j2 = new Jukebox("Rock music Jukebox", "987654321", TypeEnchere.PAYANTE);
		jukeboxDao.save(j1);
		jukeboxDao.save(j2);

		eg1.setTerminee(true);
		ep1.setTerminee(true);

		eg1.setJukebox(j1);
		eg1.setMembre(m1);
		eg1.setTitre(t1);

		eg2.setJukebox(j1);
		eg2.setMembre(m1);
		eg2.setTitre(t1);

		ep1.setJukebox(j1);
		ep1.setMembre(m1);
		ep1.setTitre(t1);

		ep2.setJukebox(j1);
		ep2.setMembre(m2);
		ep2.setTitre(t1);

		ep3.setJukebox(j2);
		ep3.setMembre(m2);
		ep3.setTitre(t1);

		enchereDao.save(eg1);
		enchereDao.save(eg2);
		enchereDao.save(ep1);
		enchereDao.save(ep2);
		enchereDao.save(ep3);

		assertEquals(3, enchereDao.findAllEnchereByMembre(m1).size());
		assertEquals(2, enchereDao.findAllEnchereByMembre(m2).size());
		assertEquals(0, enchereDao.findAllEnchereByMembre(m3).size());
	}

	@Test
	public void testFindAllEnchereNotTermineeByTitreAndJukebox() {

		EnchereGratuite eg1 = new EnchereGratuite(LocalDateTime.now(), 1);
		EnchereGratuite eg2 = new EnchereGratuite(LocalDateTime.now(), 2);
		EncherePayante ep1 = new EncherePayante(LocalDateTime.now(), 1);
		EncherePayante ep2 = new EncherePayante(LocalDateTime.now(), 2);
		EncherePayante ep3 = new EncherePayante(LocalDateTime.now(), 3);

		Membre m1 = new Membre("Maxime", 10, "test");
		Membre m2 = new Membre("Josse", 15, "test");
		Membre m3 = new Membre("Guillaume", 20, "test");
		utilisateurDao.save(m1);
		utilisateurDao.save(m2);
		utilisateurDao.save(m3);

		Titre t1 = new Titre("Offender", "Dimension", "https://www.youtube.com/watch?v=d2u3BRGd2rs");
		Titre t2 = new Titre("Saviour", "Dimension", "https://www.youtube.com/watch?v=R0ppOs2o-cw");
		Titre t3 = new Titre("Organ", "Dimension", "https://www.youtube.com/watch?v=GJQZrANyNJY");
		titreDao.save(t1);
		titreDao.save(t2);
		titreDao.save(t3);

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		Jukebox j2 = new Jukebox("Rock music Jukebox", "987654321", TypeEnchere.PAYANTE);
		jukeboxDao.save(j1);
		jukeboxDao.save(j2);

		eg1.setTerminee(true);
		ep1.setTerminee(true);

		eg1.setJukebox(j1);
		eg1.setMembre(m1);
		eg1.setTitre(t1);

		eg2.setJukebox(j1);
		eg2.setMembre(m1);
		eg2.setTitre(t1);

		ep1.setJukebox(j1);
		ep1.setMembre(m1);
		ep1.setTitre(t2);

		ep2.setJukebox(j2);
		ep2.setMembre(m2);
		ep2.setTitre(t2);

		ep3.setJukebox(j2);
		ep3.setMembre(m2);
		ep3.setTitre(t2);

		enchereDao.save(eg1);
		enchereDao.save(eg2);
		enchereDao.save(ep1);
		enchereDao.save(ep2);
		enchereDao.save(ep3);

		assertEquals(1, enchereDao.findAllEnchereNotTermineeByTitreAndJukebox(t1, j1).size());

		assertEquals(2, enchereDao.findAllEnchereNotTermineeByTitreAndJukebox(t2, j2).size());

		assertEquals(0, enchereDao.findAllEnchereNotTermineeByTitreAndJukebox(t3, j1).size());

	}

	@Test
	public void testFindAllEnchereTermineeByMembre() {

		EnchereGratuite eg1 = new EnchereGratuite(LocalDateTime.now(), 1);
		EnchereGratuite eg2 = new EnchereGratuite(LocalDateTime.now(), 2);
		EncherePayante ep1 = new EncherePayante(LocalDateTime.now(), 1);
		EncherePayante ep2 = new EncherePayante(LocalDateTime.now(), 2);
		EncherePayante ep3 = new EncherePayante(LocalDateTime.now(), 3);

		Membre m1 = new Membre("Maxime", 10, "test");
		Membre m2 = new Membre("Josse", 15, "test");
		Membre m3 = new Membre("Guillaume", 20, "test");
		utilisateurDao.save(m1);
		utilisateurDao.save(m2);
		utilisateurDao.save(m3);

		Titre t1 = new Titre("Offender", "Dimension", "https://www.youtube.com/watch?v=d2u3BRGd2rs");
		Titre t2 = new Titre("Saviour", "Dimension", "https://www.youtube.com/watch?v=R0ppOs2o-cw");
		Titre t3 = new Titre("Organ", "Dimension", "https://www.youtube.com/watch?v=GJQZrANyNJY");
		titreDao.save(t1);
		titreDao.save(t2);
		titreDao.save(t3);

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		Jukebox j2 = new Jukebox("Rock music Jukebox", "987654321", TypeEnchere.PAYANTE);
		jukeboxDao.save(j1);
		jukeboxDao.save(j2);

		eg1.setTerminee(true);
		ep1.setTerminee(true);
		ep3.setTerminee(true);

		eg1.setJukebox(j1);
		eg1.setMembre(m1);
		eg1.setTitre(t1);

		eg2.setJukebox(j1);
		eg2.setMembre(m1);
		eg2.setTitre(t1);

		ep1.setJukebox(j1);
		ep1.setMembre(m1);
		ep1.setTitre(t2);

		ep2.setJukebox(j2);
		ep2.setMembre(m2);
		ep2.setTitre(t2);

		ep3.setJukebox(j2);
		ep3.setMembre(m2);
		ep3.setTitre(t2);

		enchereDao.save(eg1);
		enchereDao.save(eg2);
		enchereDao.save(ep1);
		enchereDao.save(ep2);
		enchereDao.save(ep3);

		assertEquals(2, enchereDao.findAllEnchereTermineeByMembre(m1).size());

		assertEquals(1, enchereDao.findAllEnchereTermineeByMembre(m2).size());

		assertEquals(0, enchereDao.findAllEnchereTermineeByMembre(m3).size());

	}

	@Test
	public void testFindAllEnchereNotTermineeByMembre() {
		EnchereGratuite eg1 = new EnchereGratuite(LocalDateTime.now(), 1);
		EnchereGratuite eg2 = new EnchereGratuite(LocalDateTime.now(), 2);
		EncherePayante ep1 = new EncherePayante(LocalDateTime.now(), 1);
		EncherePayante ep2 = new EncherePayante(LocalDateTime.now(), 2);
		EncherePayante ep3 = new EncherePayante(LocalDateTime.now(), 3);

		Membre m1 = new Membre("Maxime", 10, "test");
		Membre m2 = new Membre("Josse", 15, "test");
		Membre m3 = new Membre("Guillaume", 20, "test");
		utilisateurDao.save(m1);
		utilisateurDao.save(m2);
		utilisateurDao.save(m3);

		Titre t1 = new Titre("Offender", "Dimension", "https://www.youtube.com/watch?v=d2u3BRGd2rs");
		Titre t2 = new Titre("Saviour", "Dimension", "https://www.youtube.com/watch?v=R0ppOs2o-cw");
		Titre t3 = new Titre("Organ", "Dimension", "https://www.youtube.com/watch?v=GJQZrANyNJY");
		titreDao.save(t1);
		titreDao.save(t2);
		titreDao.save(t3);

		Jukebox j1 = new Jukebox("Bass music Jukebox", "123456789", TypeEnchere.GRATUITE);
		Jukebox j2 = new Jukebox("Rock music Jukebox", "987654321", TypeEnchere.PAYANTE);
		jukeboxDao.save(j1);
		jukeboxDao.save(j2);

		eg1.setTerminee(true);
		ep1.setTerminee(true);

		eg1.setJukebox(j1);
		eg1.setMembre(m1);
		eg1.setTitre(t1);

		eg2.setJukebox(j1);
		eg2.setMembre(m1);
		eg2.setTitre(t1);

		ep1.setJukebox(j1);
		ep1.setMembre(m1);
		ep1.setTitre(t2);

		ep2.setJukebox(j2);
		ep2.setMembre(m2);
		ep2.setTitre(t2);

		ep3.setJukebox(j2);
		ep3.setMembre(m2);
		ep3.setTitre(t2);

		enchereDao.save(eg1);
		enchereDao.save(eg2);
		enchereDao.save(ep1);
		enchereDao.save(ep2);
		enchereDao.save(ep3);

		assertEquals(1, enchereDao.findAllEnchereNotTermineeByMembre(m1).size());

		assertEquals(2, enchereDao.findAllEnchereNotTermineeByMembre(m2).size());

		assertEquals(0, enchereDao.findAllEnchereNotTermineeByMembre(m3).size());
	}

}
