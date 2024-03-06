package dao;

import java.util.List;

import metier.entities.Voyage;

public interface IVoyageDao { 
    public Voyage save(Voyage v);
    public List<Voyage> voyagesParMC(String mc); 
    public Voyage getVoyage(Long id); 
    public Voyage updateVoyage(Voyage v); 
    public void deleteVoyage(Long id);
}
