package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.PreferateRepository;
import obiecte.Preferate;

@Service
public class PreferateService {

	@Autowired
	private PreferateRepository preferateRepository;
	
	public void addPreferate(Preferate pref) {
		preferateRepository.save(pref);
	}
	
	public void removePreferate(Preferate pref) {
		preferateRepository.delete(pref);
	}
	
	public PreferateRepository getPreferateRepository() {
		return preferateRepository;
	}
}
