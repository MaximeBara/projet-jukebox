package m2i.formation.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INVITE")
public class Invite extends Utilisateur {

	
	public Invite() {
		super();
	}

	public Invite(Long id, String pseudo) {
		super(id, pseudo);
	}

	public Invite(String pseudo) {
		super(pseudo);
	}

	@Override
	public String toString() {
		return "Invite [getId()=" + getId() + ", getPseudo()=" + getPseudo() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
