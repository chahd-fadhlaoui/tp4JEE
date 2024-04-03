package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VOYAGES")
public class Voyage implements Serializable {
	
	@Id
	@Column (name="ID_VOYAGE")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long idVoyage;
	@Column (name="NOM_VOYAGE")
    private String nomVoyage;
    private double prix;

    public Voyage() {
        super();
    }

    public Voyage(String nomVoyage, double prix) {
        super();
        this.nomVoyage = nomVoyage;
        this.prix = prix;
    }

    public Long getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Long idVoyage) {
        this.idVoyage = idVoyage;
    }

    public String getNomVoyage() {
        return nomVoyage;
    }

    public void setNomVoyage(String nomVoyage) {
        this.nomVoyage = nomVoyage;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Voyage [idVoyage=" + idVoyage + ", nomVoyage=" + nomVoyage + ", prix=" + prix + "]";
    }
}
