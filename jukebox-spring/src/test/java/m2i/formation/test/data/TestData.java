package m2i.formation.test.data;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import m2i.formation.dao.IEnchereDao;
import m2i.formation.dao.IJukeboxDao;
import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
import m2i.formation.model.TypeEnchere;

@SpringBootTest
@AutoConfigureMockMvc
public class TestData {
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
	
	

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testData() throws Exception {
		
		Jukebox jukebox = new Jukebox("Le jukebox data", "MyJukebox", TypeEnchere.PAYANTE);
		
		//mockMvc.perform(get("/api/playlist/maPlaylist/createByLien/PLbAFXJC0J5Gbogs-3Jk3nay0sAeAqi3SS"));

		var client = HttpClient.newHttpClient();

		// create a request
		var request = HttpRequest.newBuilder(
		       URI.create("http://localhost:8080/api/playlist/myPlaylist/createByLien/PLbAFXJC0J5Gbogs-3Jk3nay0sAeAqi3SS"))
		   .header("accept", "application/json")
		   .build();

		// use the client to send the request
		var response = client.send(request, BodyHandlers.ofString());

		// the response:
		System.out.println(response.body());
		
		
		Optional<Playlist> playlist = playlistDao.findById(5l);
		jukebox.setPlaylist(playlist.get());
		
		
		Membre membre = new Membre("GuillaumeAdmin", 5000, "******");
		membre.setJukebox(jukebox);
		
		utilisateurDao.save(membre);
		jukeboxDao.save(jukebox);
		
	}

}
