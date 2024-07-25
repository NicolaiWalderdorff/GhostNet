import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzListController {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("onlineshop");

    public List<Geisternetz> getGeisternetze() {
        EntityManager em = emf.createEntityManager();
        List<Geisternetz> result = null;
        try {
            result = em.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
        } finally {
            em.close();
        }
        return result;
    }

    @Transactional
    public void updateStatus(Geisternetz geisternetz, String newStatus) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Geisternetz managedGeisternetz = em.find(Geisternetz.class, geisternetz.getId());
            if (managedGeisternetz != null) {
                managedGeisternetz.setStatus(newStatus);
                em.merge(managedGeisternetz);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}













