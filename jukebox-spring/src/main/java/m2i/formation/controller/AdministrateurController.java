package m2i.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import m2i.formation.dao.IUtilisateurDao;
import m2i.formation.model.Administrateur;
import m2i.formation.model.IViews;

@RestController
@RequestMapping("/api/invite")
public class AdministrateurController {

	
	@Autowired
	private IUtilisateurDao adminDao;

	@GetMapping("")
	@JsonView(IViews.IViewAdministrateur.class)
	public List<Administrateur> list() {
		List<Administrateur> admins = adminDao.findAllAdmin();

		return admins;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewAdministrateur.class)
	public Administrateur find(@PathVariable Long id) {
		Optional<Administrateur> adminOpt = adminDao.findAdminId(id);

		if (adminOpt.isPresent()) {
			return adminOpt.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewAdministrateur.class)
	public Administrateur create(@RequestBody Administrateur admin) {
		admin = adminDao.save(admin);

		return admin;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewAdministrateur.class)
	public Administrateur update(@RequestBody Administrateur admin, @PathVariable Long id) {
		if (!adminDao.existsById(id) || !id.equals(admin.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		admin = adminDao.save(admin);

		return admin;
	}

	@DeleteMapping("/{id}")
	@JsonView(IViews.IViewAdministrateur.class)
	public void delete(@PathVariable Long id) {
		if (!adminDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		adminDao.deleteById(id);

		if (adminDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
