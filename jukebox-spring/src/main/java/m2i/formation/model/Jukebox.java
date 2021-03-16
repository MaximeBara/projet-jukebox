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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "JUKEBOX")
public class Jukebox {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "nom", length = 20)
	private String nom;
	@Column(name = "code", length = 20)
	private String code;
	@Column(name = "typeEnchere", length = 10)
	private TypeEnchere typeEnchere;

	@OneToMany(mappedBy = "jukebox", fetch = FetchType.LAZY)
	private List<Utilisateur> connectes;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FAVORIS", joinColumns = @JoinColumn(name = "jukebox_id"), inverseJoinColumns = @JoinColumn(name = "membre_id"))
	private List<Membre> fans;

	@ManyToOne()
	@JoinColumn(name = "administrateur_id")
	private Administrateur administrateur;

	@ManyToOne()
	@JoinColumn(name = "playlist_id")
	private Playlist playlist;

	@OneToMany(mappedBy = "jukebox", fetch = FetchType.LAZY)
	private List<Enchere> encheres;

	public Jukebox() {
	}

	public Jukebox(String nom, String code, TypeEnchere typeEnchere) {
		this.nom = nom;
		this.code = code;
		this.typeEnchere = typeEnchere;
	}

	public Jukebox(Long id, String nom, String code, TypeEnchere typeEnchere) {
		this.id = id;
		this.nom = nom;
		this.code = code;
		this.typeEnchere = typeEnchere;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TypeEnchere getTypeEnchere() {
		return typeEnchere;
	}

	public void setTypeEnchere(TypeEnchere typeEnchere) {
		this.typeEnchere = typeEnchere;
	}

	public List<Utilisateur> getConnectes() {
		return connectes;
	}

	public void setConnectes(List<Utilisateur> connectes) {
		this.connectes = connectes;
	}

	public List<Membre> getFans() {
		return fans;
	}

	public void setFans(List<Membre> fans) {
		this.fans = fans;
	}

	public Administrateur getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(Administrateur administrateur) {
		this.administrateur = administrateur;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	@Override
	public String toString() {
		return "Jukebox [id=" + id + ", nom=" + nom + ", code=" + code + ", typeEnchere=" + typeEnchere + "]";
	}

}
