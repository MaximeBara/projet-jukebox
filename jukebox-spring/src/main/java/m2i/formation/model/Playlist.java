package m2i.formation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "PLAYLIST")
public class Playlist {
	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@Column(name = "nom", length = 20)
	@JsonView(IViews.IViewBasic.class)
	private String nom;
	@Temporal(TemporalType.DATE)
	@JsonView(IViews.IViewBasic.class)
	@Column(name = "dateCreation")
	private Date dateCreation;
	@Column(name = "lien", length = 255, unique = true)
	@JsonView(IViews.IViewBasic.class)
	private String lien;

	@ManyToOne()
	@JoinColumn(name = "createur_id")
	private Membre createur;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PLAYLIST_TITRE", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "titre_id"))
	@JsonView(IViews.IViewPlaylistWithTitre.class)
	private List<Titre> titres = new ArrayList<>();

	@OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
	private List<Jukebox> jukeboxes = new ArrayList<>();

	public Playlist() {
	}

	public Playlist(String nom, Date dateCreation) {
		this.nom = nom;
		this.dateCreation = dateCreation;
	}

	public Playlist(String nom, Date dateCreation, String lien) {
		this.nom = nom;
		this.dateCreation = dateCreation;
		this.lien = lien;
	}

	public Playlist(Long id, String nom, Date dateCreation) {
		this.id = id;
		this.nom = nom;
		this.dateCreation = dateCreation;
	}

	public Playlist(Long id, String nom, Date dateCreation, String lien) {
		this.id = id;
		this.nom = nom;
		this.dateCreation = dateCreation;
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Membre getCreateur() {
		return createur;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public void setCreateur(Membre createur) {
		this.createur = createur;
	}

	public List<Titre> getTitres() {
		return titres;
	}

	public void setTitres(List<Titre> titres) {
		this.titres = titres;
	}

	public List<Jukebox> getJukeboxes() {
		return jukeboxes;
	}

	public void setJukeboxes(List<Jukebox> jukeboxes) {
		this.jukeboxes = jukeboxes;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", nom=" + nom + ", dateCreation=" + dateCreation + ", createur=" + createur
				+ ", titres=" + titres + ", jukeboxes=" + jukeboxes + "]";
	}

}
