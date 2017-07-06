package mongo.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="livre_collection")
public class Livre {
	@Id
	Integer id=0; 
	String titre;
	int parution;
	String auteur;
	
	public Livre(String titre, int parution, String auteur) {
		this.titre = titre;
		this.parution = parution;
		this.auteur = auteur;
	}
	public Livre() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getParution() {
		return parution;
	}
	public void setParution(int parution) {
		this.parution = parution;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	@Override
	public String toString() {
		return "id="+ id +" titre=" + titre + " auteur=" + auteur + " anneeParution=" + parution;
	}
}
