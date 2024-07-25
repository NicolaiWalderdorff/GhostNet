import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@Named
@RequestScoped
public class GeisternetzController {

    private String standort;
    private String groesse;
    private String status;

    // JPA EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("onlineshop");

    // Getter und Setter
    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getGroesse() {
        return groesse;
    }

    public void setGroesse(String groesse) {
        this.groesse = groesse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Speichern der Daten
    public void save() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Geisternetz geisternetz = new Geisternetz(standort, groesse, status);
            em.persist(geisternetz);
            tx.commit();
            FacesContext.getCurrentInstance().addMessage(null, new jakarta.faces.application.FacesMessage("Daten erfolgreich gespeichert."));
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new jakarta.faces.application.FacesMessage("Fehler beim Speichern der Daten."));
        } finally {
            em.close();
        }
    }
}









