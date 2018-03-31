/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco.exceptions;

/**
 *
 * @author arrud
 */
public class SaldoInvalidoException extends RuntimeException{
    public SaldoInvalidoException(String mensagem){
        super(mensagem);
    }
}
