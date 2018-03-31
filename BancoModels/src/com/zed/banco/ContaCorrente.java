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
public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, double limite, Cliente titular) {
        super(numero, limite, titular);
    }

    public ContaCorrente(int numero, double limite, double saldo, Cliente titular) {
        super(numero, limite, saldo, titular);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContaCorrente)) {
            return false;
        }
        return super.equals(o);
    }

}
