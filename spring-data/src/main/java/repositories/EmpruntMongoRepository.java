package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import mongo.entities.Emprunt;

public interface EmpruntMongoRepository extends MongoRepository<Emprunt, Integer> {}
