package com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.exception;

public class BusinessException extends RuntimeException{
    private static final long servialVersionUID = 1L;
    public BusinessException(String message){
        super(message);
    }
    public BusinessException(Throwable cause) {
        super(cause);
    }
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
