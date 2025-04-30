package Repositories;

import org.springframework.data.repository.CrudRepository;

import obiecte.Persoana;

public interface PersoanaRepository extends CrudRepository<Persoana, Long>{

	Persoana findByNume(String nume);
	Persoana findByNumeAndParola(String nume, String parola);
}
