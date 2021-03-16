package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Enchere;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;

public interface ITitreDao extends JpaRepository<Titre, Long> {

	@Query("select t from Playlist p join p.titres t where p = :playlist")
	public List<Titre> findAllByPlaylist(@Param("playlist") Playlist playlist);
	
	@Query("select e.titre from Enchere e where e = :enchere")
	public Titre findByEnchere(@Param("enchere") Enchere enchere);
	
}
