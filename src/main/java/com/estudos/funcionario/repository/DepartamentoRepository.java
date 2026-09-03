package com.estudos.funcionario.repository;

import com.estudos.funcionario.entity.Departamento;
import com.estudos.funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {



    @Query("SELECT d FROM Departamento d LEFT JOIN d.funcionarios f WHERE f IS NULL")
    List<Departamento> buscarDepartamentos();

    // Exercício 43 -- resolve o N+1: busca departamentos JA com os funcionarios carregados, numa unica query
    @Query("SELECT d FROM Departamento d LEFT JOIN FETCH d.funcionarios")
    List<Departamento> listarComFuncionarios();

}
