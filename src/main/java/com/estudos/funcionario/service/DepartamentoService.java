package com.estudos.funcionario.service;

import com.estudos.funcionario.entity.Departamento;
import com.estudos.funcionario.exception.EntidadeNaoEncontradaException;
import com.estudos.funcionario.repository.DepartamentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // Exercício 41 -- demonstração do problema N+1
    @Transactional
    public void demonstrarProblemaN1() {
        List<Departamento> departamentos = this.repository.findAll(); // 1a query
        System.out.println("===== Buscou " + departamentos.size() + " departamentos =====");

        for (Departamento d : departamentos) {
            // cada .size() aqui dispara UMA query nova, pra buscar os funcionarios DAQUELE departamento
            System.out.println("Departamento " + d.getNome() + " tem " + d.getFuncionarios().size() + " funcionarios");
        }
    }

    // Exercício 43 -- mesma demonstração, mas usando JOIN FETCH (deve gerar só 1 query no total)
    @Transactional
    public void demonstrarJoinFetch() {
        List<Departamento> departamentos = this.repository.listarComFuncionarios(); // 1 unica query
        System.out.println("===== JOIN FETCH: buscou " + departamentos.size() + " departamentos =====");

        for (Departamento d : departamentos) {
            System.out.println("Departamento " + d.getNome() + " tem " + d.getFuncionarios().size() + " funcionarios");
        }
    }

}
