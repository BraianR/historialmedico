package com.historialmedico.historialmedico.service;

import com.historialmedico.historialmedico.model.Paciente;
import com.historialmedico.historialmedico.model.RegistroMedico;
import com.historialmedico.historialmedico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PacienteServiceWithRedis pivot;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, PacienteServiceWithRedis pivot) {
        this.pacienteRepository = pacienteRepository;
        this.pivot = pivot;
    }

    public Paciente agregarPaciente(Paciente paciente) throws Exception {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCi(paciente.getCi());
        if (pacienteExistente.isPresent()) {
            throw new Exception("El paciente ya existe");
        }
        return pacienteRepository.save(paciente);
    }

    public Paciente agregarRegistroMedico(String ci, RegistroMedico registroMedico) throws Exception {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByCi(ci);

        if (!pacienteOptional.isPresent())
            throw new Exception("No existe un paciente con la cedula aportada como parametro");

        Paciente paciente = pacienteOptional.get();
        paciente.getHistorialMedico().add(registroMedico);
        return pacienteRepository.save(paciente);
    }

    public List<RegistroMedico> consultarHistorialMedico(String ci, int page, int size) throws Exception {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByCi(ci);

        if (!pacienteOptional.isPresent())
            throw new Exception("No existe un paciente con la cedula aportada como parametro");

        Paciente paciente = pacienteOptional.get();
        List<RegistroMedico> registros = paciente.getHistorialMedico();

        registros.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));

        int fromIndex = page * size;
        int toIndex = Math.min(fromIndex + size, registros.size());

        if (fromIndex >= registros.size())
            return Collections.emptyList();

        return registros.subList(fromIndex, toIndex);
    }

    public List<RegistroMedico> obtenerRegistrosPorCriterio(
            String ci,
            String tipoRegistroMedico,
            String diagnostico,
            String medico,
            String institucion) throws Exception {

        Optional<Paciente> pacienteOptional = pivot.obtenerPaciente(ci);

        if (!pacienteOptional.isPresent())
            throw new Exception("No existe un paciente con la cedula aportada como parametro");

        List<RegistroMedico> registros = pacienteOptional.get().getHistorialMedico();

        return registros.stream()
                .filter(r -> tipoRegistroMedico == null || r.getTipoRegistroMedico().name().equalsIgnoreCase(tipoRegistroMedico))
                .filter(r -> diagnostico == null || r.getDiagnostico().equalsIgnoreCase(diagnostico))
                .filter(r -> medico == null || r.getMedico().equalsIgnoreCase(medico))
                .filter(r -> institucion == null || r.getInstitucion().equalsIgnoreCase(institucion))
                .collect(Collectors.toList());
    }

}