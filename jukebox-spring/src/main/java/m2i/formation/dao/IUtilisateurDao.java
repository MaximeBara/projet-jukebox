package m2i.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Invite;
import m2i.formation.model.Membre;
import m2i.formation.model.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long> {

	@Query("select user from Utilisateur user ")
	List<Utilisateur> findAllUtilisateur();

	@Query("select user from Utilisateur user where user.id = :id")
	Membre findByID(@Param("id") long id);
	
	/*@Query("select jukeboxfav from Membre jukeboxfav left join jukeboxfav.Favoris s where jukeboxfav.id = :id")
	Membre findByFavoris(@Param("id") long id);*/
	
	@Query("select m from Membre m")
	List<Membre> findMembre();

	@Query("select inv from Invite inv")
	List<Invite> findAllInvite();

	@Query("select admin from Administrateur admin")
	List<Invite> findAllAdmin();

	/*@Query("select m from Membre m left join m.encheres e where e.valeur > 0 ")
	List<Membre> findAllMembresByValeur(@Param("valeur") int valeur); */

	/*
	 * @Query("select s.formateur from Stagiaire s where s.id = :id") Formateur
	 * findByStagiaire(@Param("id") Long id);
	 * 
	 * @Query("select s from Stagiaire s join s.formateur f where f.id = :id")
	 * List<Stagiaire> findAllStagiaireByFormateur(@Param("id") Long id);
	 * 
	 * @Query("select f from Formateur f where f.experience > :experience")
	 * List<Formateur> findAllFormateurExperienceGreaterThan(@Param("experience")
	 * int experience);
	 */

}
