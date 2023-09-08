package br.com.esig.esig.service.impl;
import br.com.esig.esig.model.Vencimento;
import br.com.esig.esig.service.VencimentoService;
import jakarta.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Stateless
public class VencimentoServiceImpl implements VencimentoService {
    @PersistenceContext(unitName = "postgresMain")
    private EntityManager entityManager;
    @Override
    public Vencimento findById(Long id) {
        return entityManager.find(Vencimento.class, id);
    }
}

