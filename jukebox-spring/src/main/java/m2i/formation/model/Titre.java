package m2i.formation.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TITRE")
public class Titre {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "nom", length = 20)
	private String nom;
	@Column(name = "artiste", length = 20)
	private String artiste;
	@Column(name = "lien", length = 255)
	private String lien;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PLAYLIST_TITRE", joinColumns = @JoinColumn(name = "titre_id"), inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private List<Playlist> playlists;

	public Titre() {
	}

	public Titre(String nom, String artiste, String lien) {
		this.nom = nom;
		this.artiste = artiste;
		this.lien = lien;
	}

	public Titre(Long id, String nom, String artiste, String lien) {
		this.id = id;
		this.nom = nom;
		this.artiste = artiste;
		this.lien = lien;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getArtiste() {
		return artiste;
	}

	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Titre [id=" + id + ", nom=" + nom + ", artiste=" + artiste + ", lien=" + lien + ", playlists="
				+ playlists + "]";
	}

}
