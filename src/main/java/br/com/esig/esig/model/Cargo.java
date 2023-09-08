package br.com.esig.esig.model;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "cargo")

public class Cargo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "nome", nullable = false)
    public String nome;
    @OneToMany(mappedBy = "cargo")
    private List<Pessoa> pessoas;
    @ManyToMany(mappedBy = "cargos")
    private List<Vencimento> vencimentos;

    // GET e SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Vencimento> getVencimentos() {
        return vencimentos;
    }

    public void setVencimentos(List<Vencimento> vencimentos) {
        this.vencimentos = vencimentos;
    }
}

