package team4.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    public void setDataDiEmissione(LocalDate dataDiEmissione) {
        this.dataDiEmissione = dataDiEmissione;
    }

    public LocalDate getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(LocalDate dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }


}
