package com.estudos.funcionario.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String nomeEntidade, Long id) {
        super(nomeEntidade + " nao encontrado, id: " + id);
    }
}
