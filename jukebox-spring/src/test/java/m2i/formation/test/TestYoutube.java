package m2i.formation.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import m2i.formation.model.Titre;

@SpringBootTest
public class TestYoutube {

	private static String googleKey = "AIzaSyBQ71OScLZnxIU09fKStdgj5h7dvUG4IxM";

	private static List<Titre> findAllTitre(String playlistId) {

		List<Titre> liste = new ArrayList<Titre>();

		RestTemplate rest = new RestTemplate();
		String url = "https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + playlistId
				+ "&key=" + googleKey;
		String result = rest.getForObject(url, String.class);

		JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();

		for (JsonElement elt : jsonObject.getAsJsonArray("items")) {
			String titre = elt.getAsJsonObject().getAsJsonObject("snippet").get("title").getAsString();
			String lien = elt.getAsJsonObject().getAsJsonObject("snippet").getAsJsonObject("resourceId").get("videoId")
					.getAsString();
			liste.add(new Titre(titre, lien));
		}

		return liste;
	}

	public static void main(String[] args) {

		System.out.println(findAllTitre("PLZ8d5-HBlr_QEkUR-rhJ3T29wvw8-1pSL"));

	}

}