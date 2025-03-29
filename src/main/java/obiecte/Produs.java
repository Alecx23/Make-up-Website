package obiecte;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produs {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nume;
	private String desc="Este o descriere";
	private Integer pret, stoc;
	private String img;
	
	public Produs() {
		
	}
	
	public Produs(String nume, Integer pret, Integer stoc, String img) {
		this.nume=nume;
		this.pret=pret;
		this.stoc=stoc;
		this.img=img;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getPret() {
		return pret;
	}

	public void setPret(Integer pret) {
		this.pret = pret;
	}

	public Integer getStoc() {
		return stoc;
	}

	public void setStoc(Integer stoc) {
		this.stoc = stoc;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
