package com.estudos.funcionario.service;

import com.estudos.funcionario.entity.Funcionario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private com.estudos.funcionario.repository.FuncionarioRepository repository;

    @Mock
    private DepartamentoService departamentoService;

    @InjectMocks
    private FuncionarioService service;

    @Test
    void deveCalcularMediaSalarial() {
        Funcionario f1 = new Funcionario("Junio", 5000, null);
        Funcionario f2 = new Funcionario("Maria", 7000, null);

        double resultado = service.mediaSalarial(List.of(f1, f2));

        assertEquals(9999, resultado);
    }
}