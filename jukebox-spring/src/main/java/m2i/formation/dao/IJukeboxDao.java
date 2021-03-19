package m2i.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Administrateur;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
import m2i.formation.model.Titre;
import m2i.formation.model.Utilisateur;

public interface IJukeboxDao extends JpaRepository<Jukebox, Long> {

	@Query("select u from Utilisateur u where u.jukebox.id = :id")
	public List<Utilisateur> findAllConnectesById(@Param("id") Long id);

	@Query("select m from Membre m left join m.jukeboxFavoris j where j.id = :id")
	public List<Membre> findAllFansById(@Param("id") Long id);

	@Query("select j.administrateur from Jukebox j where j.id = :id")
	public Administrateur findAdministrateurById(@Param("id") Long id);

	@Query("select j.playlist from Jukebox j where j.id = :id")
	public Playlist findPlaylistById(@Param("id") Long id);

	/**
	 * Retourne le jukebox sur lequel est connecté un utilisateur
	 * 
	 * @param utilisateur
	 * @return Le jukebox
	 */
	@Query("select j from Utilisateur u left join u.jukebox j where u = :utilisateur")
	public Jukebox findByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);

	/**
	 * Retourne les jukebox qu'un membre a ajouté à ses favoris.
	 * 
	 * @param membre
	 * @return Une liste de jukebox
	 */
	@Query("select j from Membre m left join m.jukeboxFavoris j where m.id = :id")
	public List<Jukebox> findAllByMembre(@Param("id") Long id);

	@Query("select j from Enchere e left join e.jukebox j where e.id = :id")
	public Optional<Jukebox> findByEnchere(@Param("id") Long id);

	@Query("select j from Jukebox j where j.code LIKE :code")
	public Optional<Jukebox> findByCode(@Param("code") String code);

	@Query("select distinct j from Jukebox j left join fetch j.playlist where j.id = :id")
	public Optional<Jukebox> findByIdWithPlaylist(@Param("id") Long id);

	@Query("select distinct j from Jukebox j left join fetch j.playlist p left join fetch p.titres where j.id = :id")
	public Optional<Jukebox> findByIdWithPlaylistAndTitre(@Param("id") Long id);

	@Query("select j from Jukebox j where j.administrateur.id = :id")
	public List<Jukebox> findAllByAdministrateur(@Param("id") Long id);

	@Query("select distinct j from Jukebox j left join fetch j.playlist")
	public List<Jukebox> findAllWithPlaylist();

	@Query("select distinct j from Jukebox j left join fetch j.playlist p left join fetch p.titres")
	public List<Jukebox> findAllWithPlaylistAndTitre();

	@Query("select t from Enchere e join e.titre t " + "where e.jukebox.id = :id AND e.terminee = FALSE "
			+ "GROUP BY e.titre.id " + "ORDER BY sum(e.valeur) DESC")
	public List<Titre> findAllTitreOrderByEnchere(@Param("id") Long id);

	@Query("select t from Jukebox j join j.playlist.titres t where j.id = :id "
			+ "AND t NOT IN (select t from Enchere e join e.titre t where e.jukebox.id = :id AND e.terminee = FALSE ) ")
	public List<Titre> findAllTitreWithoutEnchere(@Param("id") Long id);

}
