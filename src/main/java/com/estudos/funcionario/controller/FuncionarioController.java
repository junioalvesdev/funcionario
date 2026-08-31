package com.estudos.funcionario.controller;

import com.estudos.funcionario.entity.Funcionario;
import com.estudos.funcionario.service.FuncionarioService;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping("/funcionario")
    public List<Funcionario> listarTodos() {
        return this.service.listarTodos();
    }

    @GetMapping("/funcionario/{id}")
    public Funcionario buscarPorId(@PathVariable Long id) {
        return this.service.buscarPorId(id);
    }

    @GetMapping("/departamento/{departamentoId}/funcionario")
    public List<Funcionario> listarPorDepartamento(@PathVariable Long departamentoId) {
        return this.service.listarPorDepartamento(departamentoId);
    }

    @PostMapping("/departamento/{departamentoId}/funcionario")
    public Funcionario criar(@PathVariable Long departamentoId, @Valid @RequestBody Funcionario novoFuncionario) {
        return this.service.criar(departamentoId, novoFuncionario);
    }


    @GetMapping("/funcionario/paginado")
    public Page<Funcionario> listarPaginado(
            @RequestParam int page,
            @RequestParam int size) {
        if (size != 10 && size != 15 && size != 20 && size != 30 && size != 40 && size != 50) {
            throw new IllegalArgumentException("Tamanho de página inválido");
        }

        Pageable pageable = PageRequest.of(page, size);
        return this.service.listarPaginado(pageable);
    }

    // 🔧 espaço pra você expor os métodos novos que criar no Service (drills do Bloco A/B)

}
