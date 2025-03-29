package Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.UtilizatorRepository;
import obiecte.Persoane;

@Service
public class UtilizatorService {

	@Autowired
	private UtilizatorRepository utilizatorRepository;
	
	public List<Persoane> getAllUtilizatori(){
		List<Persoane> utilizatori = new ArrayList<>();
		utilizatorRepository.findAll().forEach(utilizatori::add);
		return utilizatori;
	}
	
	public void addUtilizator(Persoane util) {
		utilizatorRepository.save(util);
	}

	public UtilizatorRepository getUtilizatorRepository() {
		return utilizatorRepository;
	}

	public Persoane LogIn(String email, String parola) {
		return utilizatorRepository.findByEmailAndParola(email, parola);
	}
	
	public Persoane verificareEmail(String email) {
		return utilizatorRepository.findByEmail(email);
	}
}
