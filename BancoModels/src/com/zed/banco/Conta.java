/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco;

import com.zed.banco.atores.Cliente;
import com.zed.banco.exceptions.SaldoInvalidoException;
import com.zed.banco.exceptions.TransferenciaNaoConcluidaException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author arrud
 */
public abstract class Conta{

    private int numero;
    private double saldo, limite;
    private Cliente titular;
    private LocalDateTime dataDeCriacao;
    
    public Conta(int numero, double limite, Cliente titular) {
        this.limite = limite;
        this.titular = titular;
        this.numero = numero;
        dataDeCriacao = LocalDateTime.now();
    }

    public Conta(int numero, double limite, double saldo, Cliente titular) {
        this(numero, limite, titular);
        this.saldo = saldo;
    }

    protected boolean Sacar(double valor) throws SaldoInvalidoException {
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        throw new SaldoInvalidoException("Saldo Insuficiente");
    }

    protected boolean Depositar(double valor) throws IllegalArgumentException {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        throw new IllegalArgumentException("Só aceitamos valores positivos.");
    }

    protected void Transferir(double valor, Conta outraConta) throws TransferenciaNaoConcluidaException {
        if (Sacar(valor)) {
            outraConta.Depositar(valor);
        } else {
            throw new TransferenciaNaoConcluidaException("Transferencia Não Concluída verifique seu saldo!");
        }
    }

    public void MudarTitular(Cliente outroTitular) {
        if (outroTitular.equals(titular)) {
            JOptionPane.showMessageDialog(null, "Titular já é o dono da conta", "Operação não feita", JOptionPane.PLAIN_MESSAGE);
        } else {
            titular = outroTitular;
        }
    }

    public double GetSaldo() {
        return saldo;
    }

    public int GetNumero() {
        return numero;
    }

    public Cliente GetTitular() {
        return titular;
    }

    public double GetLimite() {
        return limite;
    }
    public LocalDateTime GetDataDeCriacao(){
        return dataDeCriacao;
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Conta)) {
            return false;
        }
        Conta outraConta = (Conta) o;
        return numero == outraConta.numero && limite == outraConta.limite
                && saldo == outraConta.saldo && titular.equals(outraConta.titular);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.numero;
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.saldo) ^ (Double.doubleToLongBits(this.saldo) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.limite) ^ (Double.doubleToLongBits(this.limite) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.titular);
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Informações sobre a conta\nNúmero: %.2f\n"
                + "Saldo: %.2f\nLimite: %.2f\nInformações sobre o cliente\n %s",
                numero, saldo, limite, titular);
    }
}
