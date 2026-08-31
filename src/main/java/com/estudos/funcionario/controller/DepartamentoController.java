package com.estudos.funcionario.controller;

import com.estudos.funcionario.entity.Departamento;
import com.estudos.funcionario.service.DepartamentoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @GetMapping("/departamento")
    public List<Departamento> listarTodos() {
        return this.service.listarTodos();
    }

    @GetMapping("/departamento/{id}")
    public Departamento buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @PostMapping("/departamento")
    public Departamento criar(@Valid @RequestBody Departamento novoDepartamento) {
        return this.service.criar(novoDepartamento);
    }

    // 🔧 espaço pra você expor os métodos novos que criar no Service (drills do Bloco A/B)

}
