package repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongo.entities.Adherent;
import mongo.entities.Emprunt;

public interface AdherentMongoRepository extends MongoRepository<Adherent, Integer> {}


