package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.PersoanaRepository;
import obiecte.Persoana;

@Service
public class PersoanaService {

	@Autowired
	private PersoanaRepository persoanaRepository;
	
	public void addPersoane(Persoana util) {
		persoanaRepository.save(util);
	}

	public PersoanaRepository getPersoaneRepository() {
		return persoanaRepository;
	}

	public Persoana LogIn(String nume, String parola) {
		return persoanaRepository.findByNumeAndParola(nume, parola);
	}
	
	public Persoana verificareNume(String nume) {
		return persoanaRepository.findByNume(nume);
	}
}
