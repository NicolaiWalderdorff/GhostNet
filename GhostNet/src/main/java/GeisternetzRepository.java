import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class GeisternetzRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save(Geisternetz geisternetz) {
        entityManager.persist(geisternetz);
    }
}


