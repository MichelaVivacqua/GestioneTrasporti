package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
//import team4.entities.DistributoreAutomatico;
import team4.entities.RivenditoreAutorizzatoAutomatico;
import team4.entities.Rivenditore_Autorizzato;

import java.util.List;


public class Rivenditore_AutorizzatoDAO {
        private EntityManager em;

        public Rivenditore_AutorizzatoDAO(EntityManager em) {
            this.em = em;
        }

        public void save(team4.entities.Rivenditore_Autorizzato d) {
            try {
                EntityTransaction e = em.getTransaction();
                e.begin();
                em.persist(d);
                e.commit();
                System.out.println("Distributore id: " + d.getId() + " creato!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    public List<Rivenditore_Autorizzato> findAll() {
        return em.createQuery("SELECT r FROM Rivenditore_Autorizzato r", Rivenditore_Autorizzato.class)
                .getResultList();
    }

        public team4.entities.Rivenditore_Autorizzato findById(long id) {
            return em.find(team4.entities.Rivenditore_Autorizzato.class, id);
        }

        public void findByIdAndDelete(long id) {
            try {
                EntityTransaction t = em.getTransaction();
                t.begin();
                team4.entities.Rivenditore_Autorizzato found = em.find(team4.entities.Rivenditore_Autorizzato.class, id);
                if (found != null) {
                    em.remove(found);
                    t.commit();
                    System.out.println("Distributore eliminato");
                } else System.out.println("Distributore non trovato");


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
//------------DISTRIBUTORE AUTOMATICO (avrà anche il metodo di verifica)--------------------
public void save(RivenditoreAutorizzatoAutomatico distributore, boolean attivo) {
    try {
        if (attivo){
        EntityTransaction e = em.getTransaction();
        e.begin();
        distributore.setAttivo(true);
        em.persist(distributore);
        e.commit();
        System.out.println("Distributore automatico con id: " + distributore.getId() + " creato!");
        } else {
            System.out.println("\\u001B[31mImpossibile emettere i biglietti, distributore automatico non in funzione\\u001B[0m");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}


}


