package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Rivenditore_Autorizzato;
//import team4.entities.DistributoreAutomatico;
import team4.entities.RivenditoreAutorizzatoAutomatico;


public class DistributoriDAO {
        private EntityManager em;

        public DistributoriDAO(EntityManager em) {
            this.em = em;
        }


        public void save(Rivenditore_Autorizzato d) {
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


        public Rivenditore_Autorizzato findById(long id) {
            return em.find(Rivenditore_Autorizzato.class, id);
        }

        public void findByIdAndDelete(long id) {
            try {
                EntityTransaction t = em.getTransaction();
                t.begin();
                Rivenditore_Autorizzato found = em.find(Rivenditore_Autorizzato.class, id);
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
            System.out.println("Impossibile emettere i biglietti, distributore automatico non in funzione");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    }


