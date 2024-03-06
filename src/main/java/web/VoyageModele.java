package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Voyage;

public class VoyageModele {
	 private String motCle;
	    List<Voyage> voyages = new ArrayList<>(); 

	    public String getMotCle() {
	        return motCle;
	    }

	    public void setMotCle(String motCle) {
	        this.motCle = motCle;
	    }

	    public List<Voyage> getVoyages() { 
	        return voyages;
	    }

	    public void setVoyages(List<Voyage> voyages) { 
	        this.voyages = voyages;
	    }

}
