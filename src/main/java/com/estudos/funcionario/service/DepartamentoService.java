package com.estudos.funcionario.service;

import com.estudos.funcionario.entity.Departamento;
import com.estudos.funcionario.exception.EntidadeNaoEncontradaException;
import com.estudos.funcionario.repository.DepartamentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public List<Departamento> listarTodos() {
        return this.repository.findAll();
    }

    public Departamento buscarPorId(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Departamento", id));
    }

    public Departamento criar(Departamento departamento) {
        return this.repository.save(departamento);
    }

    // 🔧 espaço pra você treinar os drills do Bloco A (Streams) aqui

}
