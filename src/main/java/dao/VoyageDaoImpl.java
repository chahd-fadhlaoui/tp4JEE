package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Voyage;
import util.JPAutil;
public class VoyageDaoImpl implements IVoyageDao {
private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE");
@Override
public Voyage save(Voyage v) {


EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.persist(v);
tx.commit();
return v;
}
@Override
public List<Voyage> voyagesParMC(String mc) {
List<Voyage> voyas =
entityManager.createQuery("select v from Voyage v where v.nomVoyage like :mc").setParameter("mc", "%"+mc+"%")

.getResultList();

return voyas;
}
@Override
public Voyage getVoyage(Long id) {
return entityManager.find(Voyage.class, id);
}
@Override
public Voyage updateVoyage(Voyage v) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.merge(v);
tx.commit();
return v;
}
@Override
public void deleteVoyage(Long id) {
	Voyage voyage = entityManager.find(Voyage.class, id);
entityManager.getTransaction().begin();
entityManager.remove(voyage);
entityManager.getTransaction().commit();
}
}