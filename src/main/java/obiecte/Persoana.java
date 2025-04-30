package obiecte;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Persoana {

	private String nume, parola;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="utilizator_id")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "preferate_id", referencedColumnName = "id")
    private Preferate pref = new Preferate();
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cos_id", referencedColumnName = "id")
	private Cos cos = new Cos();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilizator")
    private List<Comanda> comenzi = new ArrayList<>();
	
	public Persoana() {
		
	}
	
	public Persoana(String nume, String parola) {
		this.nume = nume;
		this.parola=parola;
	}

	public String getNume() {
		return nume;
	}

	public void setEmail(String nume) {
		this.nume = nume;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Preferate getPref() {
		return pref;
	}

	public void setPref(Preferate pref) {
		this.pref = pref;
	}

	public Cos getCos() {
		return cos;
	}

	public void setCos(Cos cos) {
		this.cos = cos;
	}

	public List<Comanda> getComenzi() {
		return comenzi;
	}

	public void setComenzi(List<Comanda> comenzi) {
		this.comenzi = comenzi;
	}
	
	
}
