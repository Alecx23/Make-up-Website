package Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.ProdusRepository;
import obiecte.Produs;

@Service
public class ProduseService {

	@Autowired
	private ProdusRepository produsRepository;
	
	public List<Produs> getAllProduse(){
		List<Produs> produse = new ArrayList<>();
		produsRepository.findAll().forEach(produse::add);
		return produse;
	}
	
	public void addProduse(Produs produs) {
		produsRepository.save(produs);
	}

	public ProdusRepository getProduseRepository() {
		return produsRepository;
	}
}
