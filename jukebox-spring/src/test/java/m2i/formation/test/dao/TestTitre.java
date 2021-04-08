package m2i.formation.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import m2i.formation.dao.IEnchereDao;
import m2i.formation.dao.IPlaylistDao;
import m2i.formation.dao.ITitreDao;
import m2i.formation.model.EnchereGratuite;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;

@SpringBootTest
public class TestTitre {
	@Autowired
	ITitreDao titreDao;
	@Autowired
	IPlaylistDao playlistDao;
	@Autowired
	IEnchereDao enchereDao;

	@Test
	public void testFindAllByPlaylist() {
		Titre titre1 = new Titre("le titre1", "artiste", "FindAllByPlaylist1");
		Titre titre2 = new Titre("le titre2", "artiste", "FindAllByPlaylist2");
		Titre titre3 = new Titre("le titre3", "artiste", "FindAllByPlaylist3");

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
		Titre titre = new Titre("le titre1", "artiste", "null");

		titreDao.save(titre);

		EnchereGratuite enchere = new EnchereGratuite(LocalDateTime.now(), 1);
		enchere.setTitre(titre);

		enchereDao.save(enchere);

		Titre newTitre = titreDao.findByEnchere(enchere.getId());

		assertEquals(titre.getId(), newTitre.getId());
		assertEquals(titre.getNom(), newTitre.getNom());
		assertEquals(titre.getArtiste(), newTitre.getArtiste());
		assertEquals(titre.getLien(), newTitre.getLien());
	}

	@Test
	public void testFindByLien() {
		String lien = "monLienUnique";

		Titre titre = new Titre("le titre1", "artiste", lien);

		titreDao.save(titre);

		Optional<Titre> newTitre = titreDao.findByLien(lien);

		assertEquals(titre.getId(), newTitre.orElseThrow().getId());
		assertEquals(titre.getNom(), newTitre.orElseThrow().getNom());
		assertEquals(titre.getArtiste(), newTitre.orElseThrow().getArtiste());
		assertEquals(titre.getLien(), newTitre.orElseThrow().getLien());
		
		titreDao.delete(titre);
	}

}
