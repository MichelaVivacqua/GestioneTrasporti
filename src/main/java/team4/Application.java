package team4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import team4.entities.Mezzo;
import team4.entities.Tratta;
import team4.dao.TessereDAO;
import team4.entities.Tessera;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionetrasporti");
        EntityManager em = emf.createEntityManager();
        TessereDAO tessereDAO = new TessereDAO(em);

        Tessera tessera1 = new Tessera("Michela","Vivacqua", LocalDate.parse("25-03-2024"));
        tessereDAO.save(tessera1);
    }
}
