package com.estudos.funcionario.repository;

import com.estudos.funcionario.entity.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findByDepartamentoId(Long departamentoId);




    @Query("SELECT COUNT(f) FROM Funcionario f WHERE f.departamento.id = :departamentoId")
    Long contarPorDepartamento(@Param("departamentoId") Long departamentoId);
    //List<Funcionario>findByListarNomes(Long nome, Double salario);
    @Query("SELECT f.nome FROM Funcionario f WHERE f.nome LIKE CONCAT('%', :nome, '%')")
    List<String> buscarFuncionarios(@Param("nome") String nome);
    @Query("select f from Funcionario  f where f.salario between :salarioMin and :salarioMax")
    List<Funcionario> listaSalarios( @Param("salarioMin") Double salarioMin,@Param("salarioMax") Double salarioMax  );

    @Query("SELECT f.departamento.id, COUNT(f) FROM Funcionario f GROUP BY f.departamento.id")
    List<Object[]> contarPorTodosDepartamentos();

    @Query("SELECT f FROM Funcionario f WHERE f.departamento.id = :departamentoId ORDER BY f.salario")
    List<Funcionario> listarPorDepartamentoOrdenadoPorSalario(@Param("departamentoId")Long departamentoId);

    @Query("SELECT AVG(f.salario) FROM Funcionario f WHERE f.departamento.id = :departamentoId")
    Double listarPorDepartamentoMediaSalarial(@Param("departamentoId")Long departamentoId);
}
