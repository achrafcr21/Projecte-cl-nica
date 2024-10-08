package com.clinica.controlador;

import com.clinica.entity.Paciente;
import com.clinica.servicio.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }

    @PutMapping("/{id}")
    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails) {
        Paciente paciente = pacienteService.findById(id).orElseThrow();
        paciente.setNombre(pacienteDetails.getNombre());
        paciente.setApellido(pacienteDetails.getApellido());
        paciente.setDni(pacienteDetails.getDni());
        paciente.setEmail(pacienteDetails.getEmail());
        return pacienteService.save(paciente);
    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);
    }
}