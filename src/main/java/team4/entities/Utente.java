package team4.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Cognome")
    private String cognome;


    @Column(name = "Possiede_Tessera")
    private boolean possiedeTessera;



    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Tessera tessera;

    public Utente() {
    }

    public Utente(String nome, String cognome, boolean possiedeTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.possiedeTessera= possiedeTessera;

        if (possiedeTessera){
        this.tessera = new Tessera();
            this.tessera.setUtente(this);
        }
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

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public boolean isPossiedeTessera() {
        return possiedeTessera;
    }

    public void setPossiedeTessera(boolean possiedeTessera) {
        this.possiedeTessera = possiedeTessera;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", possiedeTessera=" + possiedeTessera +
                ", tessera=" + tessera +
                '}';
    }
}
