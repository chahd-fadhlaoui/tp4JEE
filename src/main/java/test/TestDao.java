package test;

import java.util.List;

import dao.VoyageDaoImpl;
import metier.entities.Voyage; 

public class TestDao {
    public static void main(String[] args) {
        VoyageDaoImpl vdao = new VoyageDaoImpl(); 
        Voyage voyage = vdao.save(new Voyage("Istanbul Trip", 1200.0)); 
        System.out.println(voyage);
        
        List<Voyage> voyages = vdao.voyagesParMC("France"); 
        for (Voyage v : voyages) {
            System.out.println(v);
        }
    }
}
