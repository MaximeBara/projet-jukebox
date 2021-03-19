package m2i.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Titre;

public interface ITitreDao extends JpaRepository<Titre, Long> {

	@Query("select t from Playlist p join p.titres t where p.id = :id")
	public List<Titre> findAllByPlaylist(@Param("id") Long id);

	@Query("select e.titre from Enchere e where e = :id")
	public Titre findByEnchere(@Param("id") Long id);

	@Query("select t from Titre t where t.lien LIKE :lien")
	public Optional<Titre> findByLien(@Param("lien") String lien);

}
