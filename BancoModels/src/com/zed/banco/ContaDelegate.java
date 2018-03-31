package com.zed.banco;

import java.util.Objects;
import javax.swing.JOptionPane;

public class ContaDelegate<T extends Conta> {

    private T conta;

    public ContaDelegate(T conta) {
        this.conta = conta;
    }

    public void SacarDelegate(double valor) {
        try {
            conta.Sacar(valor);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Saldo Insuficiente", "Erro ao sacar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DepositarDelegate(double valor) {
        try {
            conta.Depositar(valor);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Valor de depósito inválido.\nDigite um valor maior que 0!", "Erro ao depositar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void TransferirDelegate(double valor, T outraConta) {
        try {
            conta.Transferir(valor, outraConta);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Transferência não concluída", "Erro ao Transferir", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        return conta.equals(o);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.conta);
        return hash;
    }
    
    @Override
    public String toString() {
        return conta.toString();
    }
}
