package m2i.formation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENCHERE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "monnaie")
public abstract class Enchere {

	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "dateTime")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTime;
	@Column(name = "valeur")
	private int valeur;
	@Column(name = "terminee")
	private boolean terminee;
	@ManyToOne
	@JoinColumn(name = "membre_id")
	private Membre membre;
	@ManyToOne
	@JoinColumn(name = "jukebox_id")
	private Jukebox jukebox;
	@ManyToOne
	@JoinColumn(name = "titre_id")
	private Titre titre;

	public Enchere() {
		super();
	}

	public Enchere(LocalDateTime dateTime, int valeur) {
		this.dateTime = dateTime;
		this.valeur = valeur;
		this.terminee = false;
	}

	public Enchere(Long id, LocalDateTime dateTime, int valeur) {
		this.id = id;
		this.dateTime = dateTime;
		this.valeur = valeur;
		this.terminee = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return dateTime;
	}

	public void setDate(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public Jukebox getJukebox() {
		return jukebox;
	}

	public void setJukebox(Jukebox jukebox) {
		this.jukebox = jukebox;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}
	
	public boolean isTerminee() {
		return terminee;
	}

	public void setTerminee(boolean terminee) {
		this.terminee = terminee;
	}

	@Override
	public String toString() {
		return "EnchereGratuite [id=" + id + ", dateTime=" + dateTime + ", valeur=" + valeur + ", membre=" + membre
				+ ", jukebox=" + jukebox + ", titre=" + titre + "]";
	}

}