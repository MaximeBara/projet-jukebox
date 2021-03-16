package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Administrateur;
import m2i.formation.model.Enchere;
import m2i.formation.model.Jukebox;
import m2i.formation.model.Membre;
import m2i.formation.model.Playlist;
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
	 * @param utilisateur
	 * @return Le jukebox
	 */
	@Query("select j from Utilisateur u left join u.jukebox j where u = :utilisateur")
	public Jukebox findByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);
	
	/**
	 * Retourne les jukebox qu'un membre a ajouté à ses favoris.
	 * @param membre
	 * @return Une liste de jukebox
	 */
	@Query("select j from Membre m left join m.jukeboxFavoris j where m = :membre")
	public List<Jukebox> findAllByMembre(@Param("membre") Membre membre);
	
	@Query("select j from Enchere e left join e.jukebox j where e = :enchere")
	public Jukebox findByEnchere(@Param("enchere") Enchere enchere);
	
	@Query("select j from Jukebox j where j.administrateur = :administrateur")
	public Jukebox findByAdministrateur(@Param("administrateur") Administrateur administrateur);
	
}
