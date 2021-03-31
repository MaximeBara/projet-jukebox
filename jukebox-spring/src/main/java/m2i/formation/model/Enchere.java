package m2i.formation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "ENCHERE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = EnchereGratuite.class, name = "G"),
	@JsonSubTypes.Type(value = EncherePayante.class, name = "P")
})
public abstract class Enchere {

	@Id
	@GeneratedValue
	@JsonView(IViews.IViewBasic.class)
	private Long id;
	@Column(name = "date_time")
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonView(IViews.IViewBasic.class)
	private LocalDateTime dateTime;
	@Column(name = "valeur")
	@JsonView(IViews.IViewBasic.class)
	private int valeur;
	@Column(name = "terminee")
	@JsonView(IViews.IViewBasic.class)
	private boolean terminee;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "membre_id")
	@JsonView(IViews.IViewEnchereWithMembre.class)
	private Membre membre;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jukebox_id")
	@JsonView(IViews.IViewEnchereWithJukebox.class)
	private Jukebox jukebox;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "titre_id")
	@JsonView(IViews.IViewEnchereWithTitre.class)
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "EnchereGratuite [id=" + id + ", dateTime=" + dateTime + ", valeur=" + valeur + ", membre=" + membre
				+ ", jukebox=" + jukebox + ", titre=" + titre + "]";
	}

}