package Repositories;

import org.springframework.data.repository.CrudRepository;

import obiecte.Produs;

public interface ProdusRepository extends CrudRepository<Produs, Long>{

	Produs findByNume(String nume);
}
