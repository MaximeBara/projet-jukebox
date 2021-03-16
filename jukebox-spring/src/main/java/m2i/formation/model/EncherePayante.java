package m2i.formation.model;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class EncherePayante extends Enchere {

	public EncherePayante() {
		super();
	}

	public EncherePayante(LocalDateTime dateTime, int valeur) {
		super(dateTime, valeur);
	}

	public EncherePayante(Long id, LocalDateTime dateTime, int valeur) {
		super(id, dateTime, valeur);
	}

}
