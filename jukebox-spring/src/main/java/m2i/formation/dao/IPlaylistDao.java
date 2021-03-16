package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;

public interface IPlaylistDao extends JpaRepository<Playlist, Long> {

	@Query("select p from Playlist p where p.createur = :membre")
	public List<Playlist> findAllByMembre(@Param("membre") Membre membre);
	
	
	@Query("select p from Playlist p left join p.titres t where t = :titre")
	public List<Playlist> findAllByTitre(@Param("titre") Titre titre);
}
