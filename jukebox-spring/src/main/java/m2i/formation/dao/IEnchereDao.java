package m2i.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Enchere;

public interface IEnchereDao extends JpaRepository<Enchere, Long> {

	@Query("SELECT e FROM Enchere e JOIN e.jukebox j WHERE j.id = :id")
	List<Enchere> findAllEnchereByJukebox(@Param("id") Long id);

	@Query("SELECT e FROM Enchere e JOIN e.jukebox j WHERE j.id = :id AND e.terminee = true")
	List<Enchere> findAllEnchereTermineeByJukebox(@Param("id") Long id);

	@Query("SELECT e FROM Enchere e JOIN e.jukebox j WHERE j.id = :id AND e.terminee = false")
	List<Enchere> findAllEnchereNotTermineeByJukebox(@Param("id") Long id);

	@Query("SELECT e from Enchere e JOIN e.membre m WHERE m.id = :id")
	List<Enchere> findAllEnchereByMembre(@Param("id") Long id);

	@Query("SELECT e from Enchere e JOIN e.membre m WHERE m.id = :id AND e.terminee = true")
	List<Enchere> findAllEnchereTermineeByMembre(@Param("id") Long id);

	@Query("SELECT e from Enchere e JOIN e.membre m WHERE m.id = :id AND e.terminee = false")
	List<Enchere> findAllEnchereNotTermineeByMembre(@Param("id") Long id);

	@Query("SELECT e from Enchere e JOIN e.titre t JOIN e.jukebox j WHERE t.id = :idTitre AND j.id = :idJukebox AND e.terminee = false")
	List<Enchere> findAllEnchereNotTermineeByTitreAndJukebox(@Param("idJukebox") Long idJukebox,
			@Param("idTitre") Long idTitre);

	@Query("select e from Enchere e left join fetch e.membre where e.id = :id")
	public Optional<Enchere> findByIdWithMembre(@Param("id") Long id);

	@Query("select e from Enchere e left join fetch e.jukebox where e.id = :id")
	public Optional<Enchere> findByIdWithJukebox(@Param("id") Long id);

	@Query("select e from Enchere e left join fetch e.titre where e.id = :id")
	public Optional<Enchere> findByIdWithTitre(@Param("id") Long id);

	@Query("select e from Enchere e left join fetch e.membre left join fetch e.jukebox left join fetch e.titre where e.id = :id")
	public Optional<Enchere> findByIdWithMembreJukeboxAndTitre(@Param("id") Long id);

	@Query("select e from Enchere e left join fetch e.membre where e.titre.id = :idTitre AND e.jukebox.id = :idJukebox AND e.terminee = false")
	public Optional<List<Enchere>> findAllEnchereNonTermineeByJukeboxAndTitreWithMembre(
			@Param("idJukebox") Long idJukebox, @Param("idTitre") Long idTitre);

}