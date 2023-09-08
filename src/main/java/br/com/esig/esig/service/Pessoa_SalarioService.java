package br.com.esig.esig.service;

import br.com.esig.esig.model.Pessoa_Salario;

import java.util.List;

public interface Pessoa_SalarioService {

    List<Pessoa_Salario> findAll();


    List<Pessoa_Salario> createMany(List<Pessoa_Salario> pessoas_salario);
}

