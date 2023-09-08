package br.com.esig.esig.service.impl;

import br.com.esig.esig.model.Pessoa;
import br.com.esig.esig.service.PessoaService;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;

import java.util.List;

@Named(value = "pessoaService")
@SessionScoped
public class PessoaServiceImpl implements PessoaService {
    private final EntityManager entityManager;

    public PessoaServiceImpl(){
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("postgresMain");

        // Crie a EntityManager a partir da EntityManagerFactory
        entityManager = emFactory.createEntityManager();
    }
    public Pessoa findById(Long id) {
        return entityManager.find(Pessoa.class, id);
    }

    public List<Pessoa> findAll() {
        return entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public List<Pessoa> findPage(int page){
        int registrosPorPagina = 100;
        int primeiraPosicao = (page - 1) * registrosPorPagina;

        Query query = entityManager.createQuery("SELECT p FROM Pessoa p");

        query.setFirstResult(primeiraPosicao);
        query.setMaxResults(registrosPorPagina);

        return (List<Pessoa>) query.getResultList();
    }

    @Override
    public Pessoa updatePessoa(Pessoa pessoa) {
        return entityManager.merge(pessoa);
    }
    @Override
    public void deleteById(Long id) {
        Pessoa pessoa = findById(id);
        if (pessoa != null) {
            entityManager.remove(pessoa);
        }
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        entityManager.persist(pessoa);
        return pessoa;
    }
}
