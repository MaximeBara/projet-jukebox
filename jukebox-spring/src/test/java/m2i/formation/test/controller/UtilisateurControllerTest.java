package m2i.formation.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Administrateur;
import m2i.formation.model.Invite;
import m2i.formation.model.Membre;


@SpringBootTest
@AutoConfigureMockMvc
public class UtilisateurControllerTest {
	
	
	@Autowired
	private IUtilisateurDao userDao;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void findAllMembre() throws Exception {
		
		int sizeStart = userDao.findMembre().size();

		Administrateur admin1 = new Administrateur("Maxime", 10, "test");
		userDao.save(admin1);
		Membre membre = new Membre("Josse", 12, "lolo");
		userDao.save(membre);
		Invite invite = new Invite("guillaume");
		userDao.save(invite);
		
		System.out.println("ICI = "+ userDao.findMembre());
		
		mockMvc.perform(get("/api/membre")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(sizeStart+2));
	}
	
	@Test
	public void findAllAdmin() throws Exception {
		
		int sizeStart = userDao.findAllAdmin().size();

		Administrateur admin1 = new Administrateur("josse", 12, "test");
		userDao.save(admin1);
		
		mockMvc.perform(get("/api/administrateur")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(sizeStart+1));
		
	}
}
