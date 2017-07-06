package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import mongo.entities.Adherent;
import mongo.entities.Emprunt;
import mongo.entities.Livre;
import repositories.AdherentMongoRepository;
import repositories.EmpruntMongoRepository;
import repositories.LivreMongoRepository;

@Component
public class EmpruntClientMongoDB {
	
	@Autowired
	private EmpruntMongoRepository empruntRepo;
	@Autowired
	private LivreMongoRepository livreRepo;
	@Autowired
	private AdherentMongoRepository adherentRepo;

	public void doSomething() {
		
		livreRepo.deleteAll(); adherentRepo.deleteAll(); empruntRepo.deleteAll();
		
		Livre l = new Livre("Les Particules élémentaires",1999, "Michel Houellebecq");
		Adherent a = new Adherent("lui", "elle", "0612342356", "lui.elle@gmail.com");
		Emprunt e = new Emprunt(l, a);
		
		l.setId(1);
		a.setId(2);
		e.setId(3);
		
		
		livreRepo.save(l); adherentRepo.save(a);
		int id1 = empruntRepo.save(e).getId();
		
		System.out.println("findOne *** ="+ empruntRepo.findOne(id1));

	}
	

	public static void main(String[] args) {
		ConfigurableApplicationContext spring = new ClassPathXmlApplicationContext("spring.xml");
		spring.getBean(EmpruntClientMongoDB.class).doSomething();
		spring.close();

	}

}
