package team4.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import team4.entities.Distributore;
import team4.entities.DistributoreAutomatico;
import team4.entities.Mezzo;


    public class DistributoriDAO {
        private EntityManager em;

        public DistributoriDAO(EntityManager em) {
            this.em = em;
        }


        public void save(Distributore d) {
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


        public Distributore findById(long id) {
            return em.find(Distributore.class, id);
        }

        public void findByIdAndDelete(long id) {
            try {
                EntityTransaction t = em.getTransaction();
                t.begin();
                Distributore found = em.find(Distributore.class, id);
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
public void save(DistributoreAutomatico da, boolean attivo) {
    try {
        if (attivo){
        EntityTransaction e = em.getTransaction();
        e.begin();
        da.setAttivo(true);
        em.persist(da);
        e.commit();
        System.out.println("Distributore automatico con id: " + da.getId() + " creato!");
        } else {
            System.out.println("Impossibile emettere i biglietti, distributore automatico non in funzione");
        }
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    }


