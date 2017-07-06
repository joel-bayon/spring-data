package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import mongo.entities.Livre;
import repositories.LivreMongoRepository;

@Component
public class ClientMongoDB {
	
	@Autowired
	private LivreMongoRepository repository;
	@Autowired
	private MongoTemplate template;

	public void doSomething() {
		
		repository.deleteAll();
		
		Livre l1 = new Livre("Les Particules élémentaires",1999, "Michel Houellebecq");
		Livre l2 = new Livre("Stupeur et tremblements",1999, "Amééélie Nothomb");
		Livre l3 = new Livre("Réglez-lui son compte !",1949, "Frédéric Dard");	
		Livre l4 = new Livre("La Carte et le Territoire",2005, "Michel Houellebecq");
		
		l1.setId(1); l2.setId(2); l3.setId(3); l4.setId(4);
		
		int id1 = repository.save(l1).getId();
		repository.save(l2); repository.save(l3); repository.save(l4);
		
		System.out.println("findOne *** ="+ repository.findOne(id1));
		System.out.println("findByTitre *** ="+ repository.findByTitre("Stupeur et tremblements"));
		System.out.println("findByParution *** ="+ repository.findByParution(1999));
		
		System.out.println("findAll ***");
		for(Livre livre : repository.findAll()) 
			System.out.println(livre);

		
		System.out.println("findByAuteur ***");
		for(Livre livre : repository.findByAuteur("Michel Houellebecq")) 
			System.out.println(livre);
		
		
		System.out.println("findLivreByRegexpTitre ***");
		for(Livre livre : repository.findLivreByRegexpTitre("^(M|F)")) 
			System.out.println(livre);
		
		
		System.out.println("findLivresByParutionBetween ***");
		for(Livre livre : repository.findLivresByParutionBetween(1990, 2008)) 
			System.out.println(livre);
		
		
		l1 = template.findOne(
				  Query.query(Criteria.where("auteur").is("Amééélie Nothomb")), Livre.class);
		l1.setAuteur("Amélie Nothomb");
		template.save(l1);
		System.out.println("findOne *** ="+ repository.findOne(l1.getId()));
					
		System.out.println("deleteByAuteur *** =" + repository.deleteByAuteur("Amélie Nothomb"));
	}
	

	public static void main(String[] args) {
		ConfigurableApplicationContext spring = new ClassPathXmlApplicationContext("spring.xml");
		spring.getBean(ClientMongoDB.class).doSomething();
		spring.close();

	}

}
