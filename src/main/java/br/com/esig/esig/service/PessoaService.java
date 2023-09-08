package br.com.esig.esig.service;


import br.com.esig.esig.model.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa findById(Long id);
    List<Pessoa> findAll();
    List<Pessoa> findPage(int page);
    Pessoa updatePessoa(Pessoa pessoa);
    void deleteById(Long id_pessoa);

    Pessoa create(Pessoa pessoa);

}

