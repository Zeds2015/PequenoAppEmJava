/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco.atores;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author arrud
 */
public class Gerente extends Funcionario{
    private List<Funcionario> funcionariosGerenciados = new ArrayList<>();
    
    public Gerente(String nome, String telefone, String departamento, int idade, String cpf, double salario) {
        super(nome, telefone, departamento, idade, cpf, salario);
    }

    public Gerente(String nome, String departamento, int idade, String cpf, double salario) {
        super(nome, departamento, idade, cpf, salario);
    }

    public Gerente(String nome, String departamento, int idade, String cpf) {
        super(nome, departamento, idade, cpf);
    }
    public void AdicionarFuncionarioGerenciado(Funcionario func){
        funcionariosGerenciados.add(func);
    }
    public void RemoverFuncionarioGerenciado(Funcionario func){
        funcionariosGerenciados.remove(func);
    }
    public int quantidadeDeFuncionariosGerenciados(){
        return funcionariosGerenciados.size();
    }
    public boolean GerenciaFuncionarios(){
        return !funcionariosGerenciados.isEmpty();
    }
    public boolean GerenciaDeterminadoFuncionario(Funcionario func){
        return funcionariosGerenciados.contains(func);
    }
    public void AcessarInformacoesDeFuncionarioGerenciado(Funcionario func){
        funcionariosGerenciados.stream().filter(c -> c.equals(func)).forEach(System.out::println);
    }
    public void ImprimirTodosOsFuncionariosGerenciados(){
        funcionariosGerenciados.forEach(System.out::println);
    }

    public List<Funcionario> getFuncionariosGerenciados() {
        return funcionariosGerenciados;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFuncionários Gerenciados: "+funcionariosGerenciados;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Gerente))
            return false;
        Gerente outroGerente = (Gerente)obj;
        return super.equals(obj) && outroGerente.funcionariosGerenciados.equals(this.funcionariosGerenciados);
    }
}
