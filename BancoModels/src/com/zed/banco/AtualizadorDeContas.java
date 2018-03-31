/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco;

import com.zed.banco.utils.ISeguro;
import com.zed.banco.utils.ITributavel;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author arrud
 */
public class AtualizadorDeContas<T extends Conta> implements ITributavel<T>, ISeguro {

    private double taxa;
    private static double total;
    private final Map<LocalDateTime, String> datasDaAtualizacao = new HashMap<>();

    public AtualizadorDeContas(double taxa) {
        if (taxa > 1.08 && taxa < 1.3) {
            this.taxa = taxa;
        }
        //Chamar Gerente
    }

   /* public void AtualizarContas(T... contas) {
        List<T> listaDeContas = new ArrayList<>();
        listaDeContas.addAll(Arrays.asList(contas));
        listaDeContas.forEach(t -> AtualizarConta(t));
    } */

    public void AtualizarConta(T conta) {
        double valorASerAtualizado = 0;
        if (conta instanceof ContaPoupanca) {
            valorASerAtualizado = atualizarContaPoupanca(conta);
            valorASerAtualizado -= getValorSeguro((ContaPoupanca) conta);
        } else {
            valorASerAtualizado = atualizarContaCorrente(conta);
        }
        valorASerAtualizado -= getValorTributo(conta);

        if (valorASerAtualizado > 0) {
            conta.Depositar(valorASerAtualizado);
            datasDaAtualizacao.put(LocalDateTime.now(),String.format("Valor atualizado: %.2f\nConta: %s",valorASerAtualizado,conta));
        }
    }

    @Override
    public double getValorTributo(T conta) {
        if (conta instanceof ContaCorrente) {
            return conta.GetSaldo() * 0.08;
        } else {
            return conta.GetSaldo() * 0.1;
        }
    }

    @Override
    public double getValorSeguro(ContaPoupanca contaPoupanca) {
        return contaPoupanca.GetSaldo() * 0.0001;
    }

    public static double GetTotal() {
        return total;
    }

    private double atualizarContaPoupanca(T conta) {
        if (taxa > 1.1) {
            return taxa * conta.GetSaldo();
        }
        return getValorTributo(conta) + getValorSeguro((ContaPoupanca)conta);
    }

    private double atualizarContaCorrente(T conta) {
        if(taxa < 1.25){
            return taxa * conta.GetSaldo();
        }
        return getValorTributo(conta);
    }

    @Override
    public String toString() {
        return "Total atualizado: R$"+total;
    }
    
    
}
