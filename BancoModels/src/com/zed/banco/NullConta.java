/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco;

import com.zed.banco.atores.Cliente;

/**
 *
 * @author arrud
 */
public class NullConta extends Conta{
    
    public NullConta(int numero, double limite, double saldo, Cliente titular) {
        super(numero, limite, saldo, titular);
    }
}
