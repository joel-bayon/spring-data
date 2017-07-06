package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jpa.entities.Adherent;
import repositories.AdherentRepository;

@Component
public class ClientJpa {
	
	@Autowired
	private AdherentRepository repository;
	
	//@Transactional
	public void doSomething() {
		
		Adherent ad1 = new Adherent("Durant", "Pascal", "0240563412", "pascal.durant@free.fr");
		Adherent ad2 = new Adherent("Martin", "Jean", "0240992345", "jean.martin@laposte.fr");
		
		int id1 = repository.save(ad1).getId();
		int id2 = repository.save(ad2).getId();
		repository.save(new Adherent("nom", "prenom", "tel", "email"));
		repository.flush();
		
		
		System.out.println("findByPrenom="+ repository.findByPrenom("Jean"));
		System.out.println("getAdherentFromNomAndEmail="+ repository.getAdherentFromNomAndEmail("nom", "emaill"));
		System.out.println("getAdherentFromNativeQuery="+ repository.getAdherentFromNativeQuery("nom", "email"));
		repository.deleteByPrenom("prenom");
		for(Adherent adherent : repository.findAll()) {
			System.out.println(adherent);
			adherent.setPrenom("Paul");
		};
		System.out.println("getByEmail="+repository.getByEmail("jean.martin@laposte.fr"));
		System.out.println(repository.countByNomAndPrenom("nom", "prenom"));
		System.out.println(repository.countByNomAndPrenom("Martin", "Paul"));
		System.out.println(repository.removeByEmail("jean.martin@laposte.fr"));
		
	}
	

	public static void main(String[] args) {
		ConfigurableApplicationContext spring = new ClassPathXmlApplicationContext("spring.xml");
		spring.getBean(ClientJpa.class).doSomething();
		spring.close();

	}

}
