package obiecte;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;

@Entity
public class Comanda {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "utilizator_id")
    private Persoane utilizator;
	
	@ElementCollection
    @CollectionTable(name = "comanda_produse", joinColumns = @JoinColumn(name = "comanda_id"))
    @MapKeyJoinColumn(name = "produs_id")
    @Column(name = "quantity")
	private Map<Produs, Integer> comanda = new HashMap<>();
	
	private Integer pretT;
	private String adress;
	private String numarDeTf;
	
	public Comanda() {
		
	}
	
	
	public Comanda(String adress, String numar) {
		super();
		this.adress = adress;
		this.numarDeTf = numar;
	}

	public void calculeazaPretT() {
		for(Produs e:comanda.keySet()) {
			pretT=pretT+e.getPret()*comanda.get(e);
		}
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Persoane getUtilizator() {
		return utilizator;
	}


	public void setUtilizator(Persoane utilizator) {
		this.utilizator = utilizator;
	}


	public Map<Produs, Integer> getComanda() {
		return comanda;
	}


	public void setComanda(Map<Produs, Integer> comanda) {
		this.comanda = comanda;
	}


	public Integer getPretT() {
		return pretT;
	}


	public void setPretT(Integer pretT) {
		this.pretT = pretT;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getNumarDeTf() {
		return numarDeTf;
	}


	public void setNumarDeTf(String numarDeTf) {
		this.numarDeTf = numarDeTf;
	}
	
	
}
