package obiecte;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Preferate {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Produs> produs = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Produs> getProdus() {
		return produs;
	}

	public void setProdus(Set<Produs> produs) {
		this.produs = produs;
	}
	
	
}
