package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Repositories.CosRepository;
import obiecte.Cos;

@Service
public class CosService {

	@Autowired
	private CosRepository cosRepository;
	
	public void addCos(Cos cos) {
		cosRepository.save(cos);
	}
	
	public void removeCos(Cos cos) {
		cosRepository.delete(cos);
	}
	
	public CosRepository getCosRepository() {
		return cosRepository;
	}
}
