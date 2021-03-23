package m2i.formation.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import m2i.formation.model.Administrateur;
import m2i.formation.model.Invite;
import m2i.formation.model.Membre;
import m2i.formation.model.Utilisateur;

public interface IUtilisateurDao extends JpaRepository<Utilisateur, Long> {

	@Query("select user from Utilisateur user ")
	List<Utilisateur> findAllUtilisateur();

	@Query("select user from Utilisateur user where user.id = :id")
	 Optional <Membre> findMembreId(@Param("id") long id);
	
	@Query("select user from Utilisateur user where user.id = :id")
	 Optional <Invite> findInviteId(@Param("id") long id);
	
	@Query("select m from Membre m")
	List<Membre> findMembre();

	@Query("select inv from Invite inv")
	List<Invite> findAllInvite();

	@Query("select admin from Administrateur admin")
	List<Administrateur> findAllAdmin();
	
	@Query("select user from Utilisateur user where user.id = :id")
	 Optional <Administrateur> findAdminId(@Param("id") long id);

}
