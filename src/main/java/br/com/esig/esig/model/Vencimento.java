package br.com.esig.esig.model;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "vencimentos")
public class Vencimento {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "valor", nullable = false)
    private Integer valor;
    @Column(name = "tipo", nullable = false)
    private String tipo;
    @ManyToMany
    @JoinTable(name = "cargo_vencimentos",
            joinColumns = @JoinColumn(name = "vencimento_id"),
            inverseJoinColumns = @JoinColumn(name = "cargo_id"))
    private List<Cargo> cargos;

    // GET e SET
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }
}

