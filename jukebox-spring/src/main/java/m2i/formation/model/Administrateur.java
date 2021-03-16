package m2i.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ADMINISTRATEUR")
public class Administrateur extends Membre {

	@OneToMany(mappedBy = "administrateur", fetch = FetchType.LAZY)
	private List<Jukebox> jukeboxes = new ArrayList<Jukebox>();

	public Administrateur() {
		super();
	}

	public Administrateur(Long id, String pseudo, int point, String motDePasse) {
		super(id, pseudo, point, motDePasse);
	}

	public Administrateur(String pseudo, int point, String motDePasse) {
		super(pseudo, point, motDePasse);
	}

	@Override
	public String toString() {
		return "Administrateur [getMotDePasse()=" + getMotDePasse() + ", getPoint()=" + getPoint() + ", toString()="
				+ super.toString() + ", getId()=" + getId() + ", getPseudo()=" + getPseudo() + ", getClass()="
				+ getClass() + "]";
	}

	public List<Jukebox> getJukeboxes() {
		return jukeboxes;
	}

	public void setJukeboxes(List<Jukebox> jukeboxes) {
		this.jukeboxes = jukeboxes;
	}
	
	
}
