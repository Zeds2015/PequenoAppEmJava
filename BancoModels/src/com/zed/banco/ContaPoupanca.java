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
public class ContaPoupanca extends Conta {

    public ContaPoupanca(int numero, double limite, Cliente titular) {
        super(numero, limite, titular);
    }

    public ContaPoupanca(int numero, double limite, double saldo, Cliente titular) {
        super(numero, limite, saldo, titular);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContaPoupanca)) {
            return false;
        }
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

}
