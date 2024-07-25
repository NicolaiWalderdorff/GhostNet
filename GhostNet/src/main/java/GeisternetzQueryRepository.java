import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class GeisternetzQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Geisternetz> findAll() {
        return entityManager.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }
}

