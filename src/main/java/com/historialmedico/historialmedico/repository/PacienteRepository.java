package com.historialmedico.historialmedico.repository;

import com.historialmedico.historialmedico.model.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PacienteRepository extends MongoRepository<Paciente, String> {

    Optional<Paciente> findByCi(String ci);

}