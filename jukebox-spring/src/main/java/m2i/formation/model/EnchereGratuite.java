package m2i.formation.model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("G")
@JsonTypeName("G")
public class EnchereGratuite extends Enchere {

	public EnchereGratuite() {
		super();
	}

	public EnchereGratuite(LocalDateTime dateTime, int valeur) {
		super(dateTime, valeur);
	}

	public EnchereGratuite(Long id, LocalDateTime dateTime, int valeur) {
		super(id, dateTime, valeur);
	}

}