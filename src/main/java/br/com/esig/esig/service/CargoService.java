package br.com.esig.esig.service;

import br.com.esig.esig.model.Cargo;
import java.util.List;

public interface CargoService {
    Cargo findById(Long id);
    List<Cargo> findAll();
}


