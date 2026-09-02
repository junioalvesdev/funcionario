package com.estudos.funcionario.service;

import com.estudos.funcionario.entity.Departamento;
import com.estudos.funcionario.entity.Funcionario;
import com.estudos.funcionario.exception.EntidadeNaoEncontradaException;
import com.estudos.funcionario.repository.FuncionarioRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final DepartamentoService departamentoService;

    public FuncionarioService(FuncionarioRepository repository, DepartamentoService departamentoService) {
        this.repository = repository;
        this.departamentoService = departamentoService;
    }

    public List<Funcionario> listarTodos() {
        return this.repository.findAll();
    }

    public List<Funcionario> listarPorDepartamento(Long departamentoId) {
        return this.repository.findByDepartamentoId(departamentoId);
    }

    public Funcionario buscarPorId(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario", id));
    }



    public Funcionario criar(Long departamentoId, Funcionario funcionario) {
        Departamento departamento = this.departamentoService.buscarPorId(departamentoId);
        funcionario.setDepartamento(departamento);
        Funcionario salvo = this.repository.save(funcionario);


        System.out.println("[LOG] Funcionario salvo com sucesso: " + salvo);


        return salvo;
    }

    public List<String> listarNomesComSalarioAlto(List<Funcionario> funcionarios) {
        Date dataLimite = java.sql.Date.valueOf("2026-01-01");
        return funcionarios.stream()
                .filter(f -> f.getSalario() > 5000 && f.getDataAdmissao().after(dataLimite))
                .map(f -> f.getNome())
                .collect(Collectors.toList());
    }

    public long contarFuncionariosPorDepartamentoEDataAdmissao(Long departamentoId) {
        Date dataLimite = java.sql.Date.valueOf("2026-01-01");
        List<Funcionario> funcionarios = this.listarPorDepartamento(departamentoId);
        Long resultado = funcionarios.stream().filter(funcionario -> funcionario.getDataAdmissao().after(dataLimite) ).count();
        if(resultado>10){
            System.out.println("é maior que 10");
        }
        return resultado;
    }
    //objeto todos os campos
    public Funcionario  ListarfuncinarioMaiorSalario(List<Funcionario> funcionarios){
        return  funcionarios.stream()
            .max(Comparator.comparing(funcionario -> funcionario.getSalario()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario", 0L));

    }

    //apenas o nome
    public String buscarNomeComMaiorSalario(List<Funcionario> funcionarios) {
        Funcionario maiorSalario = funcionarios.stream()
                .max(Comparator.comparing(f -> f.getSalario()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario", 0L));

        return maiorSalario.getNome();
    }

    public List<Funcionario> listaOrdenaSalario (List<Funcionario> funcionarios){
        return funcionarios.stream().sorted(Comparator.comparing(funcionario -> funcionario.getSalario())).collect(Collectors.toList());
    }

    public Map<Long, List<Funcionario>>  agrupamentoDeFuncionario(List<Funcionario>funcionarios){
        return  funcionarios.stream().collect(Collectors.groupingBy(funcionario -> funcionario.getDepartamento().getId()));
    }

    public double mediaSalarial (List<Funcionario> funcionarios){
        return funcionarios.stream().mapToDouble(funcionario -> funcionario.getSalario())
                .average()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario", 0l));
    }

    public Boolean salarioMaiorMil (List<Funcionario> funcionarios){//devolve uma lista verdadeiro ou falso
        return funcionarios.stream().anyMatch(funcionario -> funcionario.getSalario() > 1000);
    }

    public List<String> ListaNomesDistintosDepartamento (List<Funcionario> funcionarios){
        return  funcionarios.stream().map(funcionario -> funcionario.getDepartamento().getNome()).
                distinct().
                collect(Collectors.toList());

    }


    public double contarTodos (List<Funcionario> funcionarios){
        return funcionarios.stream().mapToDouble(funcionario -> funcionario.getSalario())
                .average()
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Funcionario", 0l));
    }

    public Page<Funcionario> listarPaginado(Pageable pageable) {

        return this.repository.findAll(pageable);
    }
}
