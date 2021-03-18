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
import m2i.formation.model.IViews;
import m2i.formation.model.Invite;
import m2i.formation.model.Membre;

@RestController
@RequestMapping("/api/invite")
public class InviteController {

	@Autowired
	private IUtilisateurDao inviteDao;

	@GetMapping("")
	@JsonView(IViews.IViewInvite.class)
	public List<Invite> list() {
		List<Invite> invites = inviteDao.findAllInvite();

		return invites;
	}

	@GetMapping("/{id}")
	@JsonView(IViews.IViewInvite.class)
	public Invite find(@PathVariable Long id) {
		Optional<Invite> inviteOpt = inviteDao.findInviteId(id);

		if (inviteOpt.isPresent()) {
			return inviteOpt.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	@JsonView(IViews.IViewInvite.class)
	public Invite create(@RequestBody Invite invite) {
		invite = inviteDao.save(invite);

		return invite;
	}

	@PutMapping("/{id}")
	@JsonView(IViews.IViewInvite.class)
	public Invite update(@RequestBody Invite invite, @PathVariable Long id) {
		if (!inviteDao.existsById(id) || !id.equals(invite.getId())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		invite = inviteDao.save(invite);

		return invite;
	}

	@DeleteMapping("/{id}")
	@JsonView(IViews.IViewInvite.class)
	public void delete(@PathVariable Long id) {
		if (!inviteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		inviteDao.deleteById(id);

		if (inviteDao.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Unable to find resource");
		}
	}
}
