package m2i.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Playlist;

public interface IPlaylistDao extends JpaRepository<Playlist, Long> {

	@Query("select p from Playlist p where p.createur.id = :id")
	public List<Playlist> findAllByMembre(@Param("id") Long id);

	@Query("select p from Playlist p left join p.titres t where t.id = :id")
	public List<Playlist> findAllByTitre(@Param("id") Long id);

	@Query("select p from Playlist p where p.lien LIKE :lien")
	public Optional<Playlist> findByLien(@Param("lien") String lien);
	
	public List<Playlist> findByOrderByDateCreationDesc();
	
}