package team4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nome")
    private String nome;
    @Column(name = "Cognome")

    private String cognome;
    @Column(name = "Data_emissione")
    private LocalDate dataDiEmissione;

    @Column(name = "Data_scandeza")
    private LocalDate dataDiScadenza;


    public Tessera() {}

    public Tessera(String nome, String cognome, LocalDate dataDiEmissione) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiEmissione = dataDiEmissione;
        this.dataDiScadenza = dataDiEmissione.plusYears(1);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiEmissione() {
        return dataDiEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataDiEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataDiEmissione;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataDiEmissione = dataScadenza;
    }
}
