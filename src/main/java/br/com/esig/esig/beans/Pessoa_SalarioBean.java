package br.com.esig.esig.beans;

import br.com.esig.esig.model.*;
import br.com.esig.esig.service.CargoService;
import br.com.esig.esig.service.PessoaService;
import br.com.esig.esig.service.Pessoa_SalarioService;
import br.com.esig.esig.service.impl.CargoServiceImpl;
import br.com.esig.esig.service.impl.PessoaServiceImpl;
import br.com.esig.esig.service.impl.Pessoa_SalarioServiceImpl;
import jakarta.inject.Named;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@Named
@SessionScoped
public class Pessoa_SalarioBean implements Serializable{

    private final CargoService cargoService;
    private final PessoaService pessoaService;
    private final Pessoa_SalarioService pessoa_SalarioService;

    private Map<Integer, Integer> mapCargoSalario = new HashMap<>();
    private List<Pessoa> pessoas;
    private int paginaAtual;

    public Pessoa_SalarioBean(){
        this.cargoService = new CargoServiceImpl();
        this.pessoaService = new PessoaServiceImpl();
        this.pessoa_SalarioService = new Pessoa_SalarioServiceImpl();

        pessoas = this.pessoaService.findAll();
        paginaAtual =1;
        this.mapeiaSalarios();
    }
    private void mapeiaSalarios(){
        List<Cargo> cargos;

        cargos = this.cargoService.findAll();
        for(Cargo cargo :cargos) {
            int soma = 0;
            for(Vencimento v : cargo.getVencimentos()) {
                soma = v.getTipo().equals("CREDITO") ? soma + v.getValor() : soma - v.getValor();

            }
            mapCargoSalario.put(cargo.getId(), soma);
        }
    }
    public List<Pessoa> listarPessoas(){
        int inicio = (paginaAtual - 1) * 100;
        int fim = inicio + 100;

        if (fim > pessoas.size()) {
            fim = pessoas.size();
        }
        return pessoas.subList(inicio, fim);
    }

    public void proximaPagina() {
        paginaAtual++;
    }

    public void paginaAnterior() {

        if (paginaAtual > 1) {
            paginaAtual--;
        }
    }

    public void calculaSalarios(){
        List<Pessoa_Salario> pessoasSalarios = new ArrayList<>();

        for(Pessoa pessoa : pessoas) {
            Pessoa_Salario pessoaSalario = new Pessoa_Salario();


            pessoaSalario.setId(pessoaSalario.getId());
            pessoaSalario.setNome(pessoaSalario.getNome());
            pessoaSalario.setSalario(mapCargoSalario.get(pessoa.getCargo().getId()));

            pessoasSalarios.add(pessoaSalario);
        }
        pessoa_SalarioService.createMany(pessoasSalarios);
    }

    //GET e SET
    public Map<Integer, Integer> getMapCargoSalario() {
        return mapCargoSalario;
    }

    public void setMapCargoSalario(Map<Integer, Integer> mapCargoSalario) {
        this.mapCargoSalario = mapCargoSalario;
    }
    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
}
