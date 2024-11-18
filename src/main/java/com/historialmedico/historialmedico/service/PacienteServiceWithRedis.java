package com.historialmedico.historialmedico.service;

import com.historialmedico.historialmedico.model.Paciente;
import com.historialmedico.historialmedico.repository.PacienteRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableCaching
public class PacienteServiceWithRedis {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceWithRedis(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Cacheable(value = "pacientes", key = "#ci")
    public Optional<Paciente> obtenerPaciente(String ci) {
        System.out.println("Consultando MongoDB...");
        return pacienteRepository.findByCi(ci);
    }
}
