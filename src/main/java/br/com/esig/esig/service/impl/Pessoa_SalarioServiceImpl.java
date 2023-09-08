package br.com.esig.esig.service.impl;
import br.com.esig.esig.model.Pessoa_Salario;
import br.com.esig.esig.service.Pessoa_SalarioService;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
@Named(value = "Pessoa_SalarioService")
@SessionScoped
public class Pessoa_SalarioServiceImpl implements Pessoa_SalarioService {
    private final EntityManager entityManager;

    public Pessoa_SalarioServiceImpl(){
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("postgresMain");

        entityManager = emFactory.createEntityManager();
    }
    @Override
    public List<Pessoa_Salario> findAll() {
        return entityManager.createQuery("SELECT ps FROM Pessoa_Salario ps", Pessoa_Salario.class).getResultList();
    }


    @Override
    public List<Pessoa_Salario> createMany(List<Pessoa_Salario> pessoas_salario) {
        List<Pessoa_Salario> newPessoas_salario = new ArrayList<>();

        try {
            for(Pessoa_Salario ps : pessoas_salario) {
                entityManager.persist(ps);
                newPessoas_salario.add(ps);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return newPessoas_salario;
    }
}
