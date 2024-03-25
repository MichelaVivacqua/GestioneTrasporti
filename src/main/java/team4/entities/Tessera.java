package team4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_emissione")

    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    public Tessera() {}

    public Tessera(String nome, String cognome, LocalDate dataEmissione) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataEmissione = dataEmissione;
        this.dataScadenza = dataEmissione.plusYears(1);
    }
}
