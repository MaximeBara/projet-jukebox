package m2i.formation.test.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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
@AutoConfigureMockMvc
public class EnchereControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private IUtilisateurDao utilisateurDao;
	@Autowired
	private IEnchereDao enchereDao;
	@Autowired
	private IJukeboxDao jukeboxDao;
	@Autowired
	private ITitreDao titreDao;

	@Test
	public void testList() throws Exception {
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

		this.mockMvc.perform(get("/api/enchere")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("trouduc")));

	}

}
