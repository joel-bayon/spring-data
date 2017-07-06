package repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Query;

import mongo.entities.Livre;

public interface LivreMongoRepository extends MongoRepository<Livre, Integer> {
	public Livre findByTitre(String titre);
	public List<Livre> findByParution(int parution);
	public List<Livre> deleteByAuteur(String auteur);
	@Query("{ 'auteur' : ?0 }")
	public List<Livre> findByAuteur(String auteur);
	@Query("{ 'auteur' : { $regex: ?0 } }")
	List<Livre> findLivreByRegexpTitre(String regexp);
	@Query("{ 'parution' : { $gt: ?0, $lt: ?1 } }")
	List<Livre> findLivresByParutionBetween(int parutionGT, int parutionLT);
	//doc : http://www.baeldung.com/queries-in-spring-data-mongodb
}
