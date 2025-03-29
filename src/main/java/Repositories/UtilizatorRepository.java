package Repositories;

import org.springframework.data.repository.CrudRepository;

import obiecte.Persoane;

public interface UtilizatorRepository extends CrudRepository<Persoane, Long>{

	Persoane findByEmail(String email);
	Persoane findByEmailAndParola(String email, String parola);
}
