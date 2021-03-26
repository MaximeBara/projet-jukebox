package m2i.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.api.client.repackaged.com.google.common.base.Optional;

import m2i.formation.model.Role;
import m2i.formation.model.UtilisateurRole;

@Repository
public interface IRoleDao extends JpaRepository<UtilisateurRole, Long> {
	Optional<UtilisateurRole> findByNom(Role nom);
}
