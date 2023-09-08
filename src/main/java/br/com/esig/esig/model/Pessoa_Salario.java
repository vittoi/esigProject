package br.com.esig.esig.model;

import javax.persistence.*;


@Entity
@Table(name = "Pessoa_Salario")
public class Pessoa_Salario {

    @Id
    @Column(name = "pessoa_id")
    private Integer id;
    @Column(name = "nome")
    private String nome;

    @Column(name = "salario")
    private Integer salario;

    public Pessoa_Salario() {
    }

    public Pessoa_Salario(Integer id, String nome, Integer salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    // GET e SET

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

