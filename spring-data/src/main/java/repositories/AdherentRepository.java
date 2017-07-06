package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import jpa.entities.Adherent;

@Transactional
@RepositoryRestResource(collectionResourceRel = "adherents", path = "adherents")
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
	 List<Adherent> findByPrenom(@Param("prenom") String prenom);
	 List<Adherent> getByEmail(String email);
	 Long deleteByPrenom(String lastname);
	 Long countByNomAndPrenom(String nom, String prenom);
	 List<Adherent> removeByEmail(String email);
	 @Query("select a from Adherent a where a.nom=?1 and a.email = ?2")
	 public Adherent getAdherentFromNomAndEmail(String nom, String email);
	 @Query(value="select * from ADHERENT where NOM=?1 and EMAIL = ?2", nativeQuery=true)
	 public Adherent getAdherentFromNativeQuery(String nom, String email);
	 @Query("select case when (count(*) > 0) then true else false end from Adherent a where a.nom = ?1 and a.prenom = ?2")
		public boolean isPresent(String nom, String prenom) ;
		@Modifying
		@Query("update Adherent a set a.nom = ?2, a.prenom = ?3, a.tel = ?4, a.email = ?5 where a.id = ?1")
		public void update(Integer id, String nom, String prenom, String tel, String email);
		@Query("select a from Adherent a where a.id not in (select distinct(e.adherent.id) from Emprunt e where e.fin = null group by e.adherent.id having count(e.adherent.id) >= 5)")
		List<Adherent> getAdherentNoQuotaMax();
}
