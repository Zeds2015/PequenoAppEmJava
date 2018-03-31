package com.zed.banco;

import com.zed.banco.atores.Cliente;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class GerenciadorDeContas<K extends Cliente, V extends Conta> {

    private AtualizadorDeContas atualizadorContas;
    private Map<K, V> mapaClientes = new HashMap<>();
    private V conta;
    
    
    public GerenciadorDeContas(AtualizadorDeContas atualizador) {
        OrganizarGerenciador();
        if (atualizador != null) {
            atualizadorContas = atualizador;
        } else {
            //Taxa padrão de atualização do banco... 1.08
            atualizadorContas = new AtualizadorDeContas(1.08);
        }
    }

    public void AdicionarContaNoGerenciador(K cliente ,V conta) {
        mapaClientes.put(cliente,conta);
        OrganizarGerenciador();
    }

    public void RemoverContaNoGerenciador(K cliente, V conta) {
        mapaClientes.remove(cliente,conta);
        OrganizarGerenciador();
    }

    public int QuantasContasExistemNoGerenciador() {
        return mapaClientes.values().size();
    }

    private void OrganizarGerenciador() {
       mapaClientes.entrySet().stream().sorted(Comparator.comparing(c -> c.getValue().GetDataDeCriacao()));
    }

    public boolean IsEmptyContas() {
        return mapaClientes.isEmpty();
    }

    public Map<K,V> FiltrarLista(Cliente filtro) {
        return mapaClientes.entrySet().stream().filter(e -> e.getKey().equals(filtro)).collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
    }

    public Map<K,V> FiltrarLista(String filtro) {
        return mapaClientes.entrySet().stream().filter(e -> e.getKey().GetNome().contains(filtro)).collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
    }

    public Map<K,V> FiltrarLista(String filtro, int idade) {
        return mapaClientes.entrySet().stream().filter(e -> e.getKey().GetCpf().contains(filtro)).collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
    }

    public void AtualizarContas(double taxa) {
        if (!IsEmptyContas()) {
            mapaClientes.entrySet().forEach(c -> atualizadorContas.AtualizarConta(c.getValue()));
        } else {
            JOptionPane.showMessageDialog(null, "Não há contas para atualizar.", "Erro ao atualizar contas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean ContaCadastrada(V conta) {
        return mapaClientes.containsValue(conta);
    }

    private void configurarConta(V conta) {
        if (!IsEmptyContas()) {
            mapaClientes.entrySet().stream().filter(c -> c.getValue().equals(conta)).findAny().ifPresent(c -> this.conta = c.getValue());
        }
    }

    public Conta retornarConta(V conta) {
        configurarConta(conta);
        if (conta != null) {
            return conta;
        }
        JOptionPane.showMessageDialog(null, "Não encotramos a conta", "Erro ao achar conta", JOptionPane.ERROR_MESSAGE);
        return new NullConta(0, 0, 0, null);
    }

    public void AtualizarConta(V conta) {
        configurarConta(conta);
        if (this.conta != null) {
            atualizadorContas.AtualizarConta(this.conta);
        } else {
            JOptionPane.showMessageDialog(null, "Não encotramos a conta", "Erro ao achar conta", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    public String ImprimirTodasAsContas(){
        StringBuilder sb = new StringBuilder();
        mapaClientes.values().forEach(System.out::println);
        mapaClientes.values().forEach(c -> sb.append(c));
        return sb.toString();
    }
    
    public double VerificarSomaDosSaldos(){
        return mapaClientes.values().stream().mapToDouble(Conta::GetSaldo).sum();
    }
    
    public double MediaDosSaldos(){
        return mapaClientes.values().stream().mapToDouble(Conta::GetSaldo).average().orElse(0.0);
    }
    
    public void ContaComMaiorSaldo(){
        Conta maiorSaldo = mapaClientes.values().stream().max(Comparator.comparingDouble(Conta::GetSaldo)).get();
        JOptionPane.showMessageDialog(null, maiorSaldo.toString(), "Conta com maior saldo",JOptionPane.PLAIN_MESSAGE);
    } 
    public void ContaComMenorSaldo(){
        Conta menorSaldo = mapaClientes.values().stream().min(Comparator.comparingDouble(Conta::GetSaldo)).get();
        JOptionPane.showMessageDialog(null, menorSaldo.toString(), "Conta com maior saldo",JOptionPane.PLAIN_MESSAGE);
    }
    public void InformacoesDeTodosClientesComConta(){
        mapaClientes.keySet().forEach(System.out::println);
    }
    
    public void SomaDosSaldosPorCliente(){
        Map<Cliente,Double> saldoClientes = mapaClientes.entrySet().stream().collect(Collectors.groupingBy(c -> c.getKey(), Collectors.summingDouble(c -> c.getValue().GetSaldo())));
        System.out.println(saldoClientes);
    }

    public Map<K, V> getMapaClientes() {
        return mapaClientes;
    }
    
}