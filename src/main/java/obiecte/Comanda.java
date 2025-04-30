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
    private Persoana utilizator;
	
	@ElementCollection
    @CollectionTable(name = "comanda_produse", joinColumns = @JoinColumn(name = "comanda_id"))
    @MapKeyJoinColumn(name = "produs_id")
    @Column(name = "quantity")
	private Map<Produs, Integer> comanda = new HashMap<>();
	
	private Integer pretT;
	private String adresa,numarDeTf,email,codPostal,oras,provincie,nume, prenume;
	
	public Comanda() {
		
	}
	
	
	public void puneDate(String adresa, String numarDeTf, String email, String codPostal, String oras,
			String provincie, String nume, String prenume) {
		this.adresa = adresa;
		this.numarDeTf=numarDeTf;
		this.email=email;
		this.codPostal=codPostal;
		this.oras = oras;
		this.provincie = provincie;
		this.nume=nume;
		this.prenume=prenume;
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


	public Persoana getUtilizator() {
		return utilizator;
	}


	public void setUtilizator(Persoana utilizator) {
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


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public String getNumarDeTf() {
		return numarDeTf;
	}


	public void setNumarDeTf(String numarDeTf) {
		this.numarDeTf = numarDeTf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCodPostal() {
		return codPostal;
	}


	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}


	public String getOras() {
		return oras;
	}


	public void setOras(String oras) {
		this.oras = oras;
	}


	public String getProvincie() {
		return provincie;
	}


	public void setProvincie(String provincie) {
		this.provincie = provincie;
	}


	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public String getPrenume() {
		return prenume;
	}


	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	
}
