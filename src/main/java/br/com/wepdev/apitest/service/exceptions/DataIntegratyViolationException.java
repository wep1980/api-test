package br.com.wepdev.apitest.service.exceptions;

public class DataIntegratyViolationException extends RuntimeException{


    public DataIntegratyViolationException(String message) {
        super(message);
    }
}
