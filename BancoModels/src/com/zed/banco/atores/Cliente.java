/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco.atores;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author arrud
 */
public class Cliente {

    private String nome, telefone;
    private int idade;
    private final String cpf;
    private List<String> feedBacks = new ArrayList<>();

    public Cliente(String nome, final String cpf, String telefone, int idade) {
        this(nome, cpf, idade);
        this.telefone = telefone;
    }

    public Cliente(String nome, final String cpf, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public void fazerAniversario() {
        idade++;
    }

    public void AdicionarFeedBack(String mensagem) {
        feedBacks.add(mensagem);
    }

    public void RemoverFeedBack(String mensagem) {
        feedBacks.remove(mensagem);
    }

    public int QuantidadeDeFeedBacks() {
        return feedBacks.size();
    }

    public boolean IsEmptyFeedBacks() {
        return feedBacks.isEmpty();
    }

    public boolean ContemFeedBack(String mensagem) {
        return feedBacks.contains(mensagem);
    }

    public List<String> ProcurarFeedBack(String mensagem) {
        return feedBacks.stream().filter(s -> s.contains(mensagem)).collect(Collectors.toList());
    }

    /*public String EditarFeedBack(int pos){
        
    }*/
    public void SetTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String GetCpf() {
        return cpf;
    }

    public int GetIdade() {
        return idade;
    }

    public String GetNome() {
        return nome;
    }

    public String GetTelefone() {
        return telefone;
    }

    public List<String> GetFeedBacks() {
        return feedBacks;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nIdade: %d\nTelefone: %s\nCpf: %s\nListaDeFeedBacks: %s", nome, idade, telefone, cpf, feedBacks);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente)) {
            return false;
        }
        Cliente outroCliente = (Cliente) o;
        return nome.equals(outroCliente.nome) && idade == outroCliente.idade
                && cpf.equals(outroCliente.cpf) && feedBacks.equals(outroCliente.feedBacks)
                && telefone.equals(outroCliente.telefone);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.nome);
        hash = 53 * hash + Objects.hashCode(this.telefone);
        hash = 53 * hash + this.idade;
        hash = 53 * hash + Objects.hashCode(this.cpf);
        hash = 53 * hash + Objects.hashCode(this.feedBacks);
        return hash;
    }

}
