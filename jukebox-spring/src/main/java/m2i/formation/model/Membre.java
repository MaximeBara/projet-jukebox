package m2i.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBRE")
public class Membre extends Utilisateur {

	@Column(length= 15)
	private String motDePasse;
	
	private int point;
	
	@OneToMany(mappedBy = "membre", fetch = FetchType.LAZY)
	private List<Enchere> encheres = new ArrayList<Enchere>();
	
	@OneToMany(mappedBy = "createur", fetch = FetchType.LAZY)
	private List<Playlist> playlists = new ArrayList<Playlist>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FAVORIS", joinColumns = @JoinColumn(name = "membre_id"), inverseJoinColumns = @JoinColumn(name = "jukebox_id"))
	private List<Jukebox> jukeboxFavoris = new ArrayList<Jukebox>();


	public Membre() {
		super();
	}

	public Membre(Long id, String pseudo, int point, String motDePasse) {
		super(id, pseudo);
		this.motDePasse = motDePasse;
		this.point = point;
	}

	public Membre(String pseudo, int point, String motDePasse) {
		super(pseudo);
		this.motDePasse = motDePasse;
		this.point = point;

	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Membre [motDePasse=" + motDePasse + ", point=" + point + ", getId()=" + getId() + ", getPseudo()="
				+ getPseudo() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Jukebox> getJukeboxFavoris() {
		return jukeboxFavoris;
	}

	public void setJukeboxFavoris(Jukebox jukeboxFavoris) {
		this.jukeboxFavoris.add(jukeboxFavoris);
	}

}
