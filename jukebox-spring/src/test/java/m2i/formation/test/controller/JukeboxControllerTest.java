package m2i.formation.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import m2i.formation.dao.IEnchereDao;
import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.TypeEnchere;

@SpringBootTest
@AutoConfigureMockMvc
public class JukeboxControllerTest {
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
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void jukeboxGet() throws Exception {
		mockMvc.perform(get("/api/jukebox")).andExpect(status().isOk());
	}

	@Test
	public void jukeboxPost() throws Exception {
		//TODO
		Jukebox jukebox = new Jukebox("Le jukebox", "jukeboxPost", TypeEnchere.GRATUITE);

		ObjectMapper mapper = new ObjectMapper();
		String jsonJukebox = mapper.writeValueAsString(jukebox);

		mockMvc.perform(post("/api/jukebox").contentType(MediaType.APPLICATION_JSON).content(jsonJukebox))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.nom").value("Le jukebox")).andExpect(jsonPath("$.code").value("jukeboxPost"));

	}

	@Test
	public void findAllWithPlaylistGet() {
		// TODO
	}

	@Test
	public void findAllWithPlaylistAndTitreGet() {
		// TODO
	}

	@Test
	public void findAllConnectesGet() throws Exception {
		// TODO
		Jukebox jukebox = new Jukebox("Le jukebox", "jukeboxPost", TypeEnchere.GRATUITE);

		jukeboxDao.save(jukebox);
		
		Membre membre1 = new Membre("Membre1", 0, "***");
		Membre membre2 = new Membre("Membre2", 0, "***");
		Membre membre3 = new Membre("Membre3", 0, "***");

		membre1.setJukebox(jukebox);
		membre2.setJukebox(jukebox);
		membre3.setJukebox(jukebox);
		
		utilisateurDao.save(membre1);
		utilisateurDao.save(membre2);
		utilisateurDao.save(membre3);
		
		mockMvc.perform(get("/api/jukebox/"+jukebox.getId()+"/allConnectes"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(3))
			.andExpect(jsonPath("$.length()").value(3));
	}

}
