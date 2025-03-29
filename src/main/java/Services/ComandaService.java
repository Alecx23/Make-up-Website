package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.ComandaRepository;
import jakarta.transaction.Transactional;
import obiecte.Comanda;

@Service
public class ComandaService {

	@Autowired
	private ComandaRepository comandaRepository;
	
	@Transactional
	public void addComanda(Comanda comanda) {
		comandaRepository.save(comanda);
	}
	
	public void removeComanda(Comanda comanda) {
		comandaRepository.delete(comanda);
	}
}
