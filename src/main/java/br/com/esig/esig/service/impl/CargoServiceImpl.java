package br.com.esig.esig.service.impl;

import br.com.esig.esig.model.Cargo;
import br.com.esig.esig.service.CargoService;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Named(value = "cargoService")
@SessionScoped
public class CargoServiceImpl implements CargoService {
    private final EntityManager entityManager;

    public CargoServiceImpl(){
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("postgresMain");

        // Crie a EntityManager a partir da EntityManagerFactory
        entityManager = emFactory.createEntityManager();
    }

    @Override
    public Cargo findById(Long id) {
        return new Cargo();//entityManager.find(Cargo.class, id);
    }

    @Override
    public List<Cargo> findAll(){
        List<Cargo> cargos = entityManager.createQuery("SELECT c FROM Cargo c", Cargo.class).getResultList();

        return cargos;
    }
}

