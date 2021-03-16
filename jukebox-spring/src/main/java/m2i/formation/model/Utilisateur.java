package m2i.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UTILISATEUR")
public abstract class Utilisateur {

	@Id
	@GeneratedValue
	private Long id ;
	@Column(length= 15)
	private String pseudo ;
	@ManyToOne
	@JoinColumn(name="jukebox_id")
	private Jukebox jukebox;
	public Utilisateur(){
		super();
	}
	
	public Utilisateur(String pseudo){
		super();
		this.pseudo = pseudo;
	}

	public Utilisateur(Long id, String pseudo){
		super();
		this.id = id;
		this.pseudo = pseudo;
	}
	
	public Jukebox getJukebox() {
		return jukebox;
	}

	public void setJukebox(Jukebox jukebox) {
		this.jukebox = jukebox;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", pseudo=" + pseudo + ", getClass()=" + getClass() 
		+ ", toString()=" + super.toString() + "]";
	}
	
	
}
