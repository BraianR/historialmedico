package com.historialmedico.historialmedico.controller;

import com.historialmedico.historialmedico.model.Paciente;
import com.historialmedico.historialmedico.model.RegistroMedico;
import com.historialmedico.historialmedico.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarPaciente(@Valid @RequestBody Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteService.agregarPaciente(paciente);
            return new ResponseEntity<>(nuevoPaciente, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("El paciente ya existe", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/registroMedico/{ci}")
    public ResponseEntity<?> agregarRegistroMedico( @PathVariable String ci, @RequestBody RegistroMedico registroMedico) {
        try {
            Paciente pacienteActualizado = pacienteService.agregarRegistroMedico(ci, registroMedico);
            return new ResponseEntity<>(pacienteActualizado, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage().equals("No existe un paciente con la cedula aportada como parametro")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(402));
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/historialMedico/{ci}")
    public ResponseEntity<?> consultarHistorialMedico(
            @PathVariable String ci,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<RegistroMedico> registros = pacienteService.consultarHistorialMedico(ci, page, size);
            return new ResponseEntity<>(registros, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("No existe un paciente con la cedula aportada como parametro")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(402));
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/registros/{ci}")
    public ResponseEntity<?> obtenerRegistrosPorCriterio(
            @PathVariable String ci,
            @RequestParam(required = false) String tipoRegistroMedico,
            @RequestParam(required = false) String diagnostico,
            @RequestParam(required = false) String medico,
            @RequestParam(required = false) String institucion) {
        try {
            List<RegistroMedico> registros = pacienteService.obtenerRegistrosPorCriterio(ci, tipoRegistroMedico, diagnostico, medico, institucion);
            return new ResponseEntity<>(registros, HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("No existe un paciente con la cedula aportada como parametro")) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(402));
            }
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}