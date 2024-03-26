package team4.entities;
import java.time.LocalDate;

import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long durata;

    @ManyToOne
    @JoinColumn(name = "mezzoDiVidimazioneId")
    private Mezzo mezzoDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "emessoDaId")
    private Distributore emessoDa;

    private LocalDate dataDiEmissione;
    private LocalDate dataDiVidimazione;

    @ManyToOne
    @JoinColumn(name = "tesseraId")
    private Tessera tessera;
}
