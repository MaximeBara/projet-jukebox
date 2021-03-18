package m2i.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Enchere;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Titre;

public interface IEnchereDao extends JpaRepository<Enchere, Long> {

	@Query("SELECT e FROM Enchere e JOIN e.jukebox j WHERE j = :leJukebox AND e.terminee = false")
	List<Enchere> findAllEnchereNotTermineeByJukebox(@Param("leJukebox") Jukebox jukebox);

	@Query("SELECT e FROM Enchere e JOIN e.jukebox j WHERE j = :leJukebox AND e.terminee = true")
	List<Enchere> findAllEnchereTermineeByJukebox(@Param("leJukebox") Jukebox jukebox);

	@Query("SELECT e from Enchere e JOIN e.membre m WHERE m = :leMembre")
	List<Enchere> findAllEnchereByMembre(@Param("leMembre") Membre membre);

	@Query("SELECT e from Enchere e JOIN e.membre m WHERE m = :leMembre AND e.terminee = true")
	List<Enchere> findAllEnchereTermineeByMembre(@Param("leMembre") Membre membre);

	@Query("SELECT e from Enchere e JOIN e.membre m WHERE m = :leMembre AND e.terminee = false")
	List<Enchere> findAllEnchereNotTermineeByMembre(@Param("leMembre") Membre membre);

	@Query("SELECT e from Enchere e JOIN e.titre t JOIN e.jukebox j WHERE t = :leTitre AND j = :leJukebox AND e.terminee = false")
	List<Enchere> findAllEnchereNotTermineeByTitreAndJukebox(@Param("leTitre") Titre titre, @Param("leJukebox") Jukebox jukebox);
	
	@Query("select e from Enchere e left join fetch e.membre where e.id = :id")
	public Optional<Enchere> findByIdWithMembre(@Param("id") Long id);
	
	@Query("select e from Enchere e left join fetch e.jukebox where e.id = :id")
	public Optional<Enchere> findByIdWithJukebox(@Param("id") Long id);
	
	@Query("select e from Enchere e left join fetch e.titre where e.id = :id")
	public Optional<Enchere> findByIdWithTitre(@Param("id") Long id);
	
	@Query("select e from Enchere e left join fetch e.membre left join fetch e.jukebox left join fetch e.titre where e.id = :id")
	public Optional<Enchere> findByIdWithMembreJukeboxAndTitre(@Param("id") Long id);

}