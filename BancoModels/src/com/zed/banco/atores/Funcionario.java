/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco.atores;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author arrud
 */
public class Funcionario {

    private String nome, telefone, departamento;
    private int idade;
    private final String cpf;
    private double salario;
    private LocalDateTime dataDaContratacao;
    private boolean naEmpresa;

    public Funcionario(String nome, String telefone, String departamento, int idade, String cpf, double salario) {
        this(nome, departamento, idade, cpf, salario);
        this.telefone = telefone;
    }

    public Funcionario(String nome, String departamento, int idade, String cpf, double salario) {
        this(nome, departamento, idade, cpf);
        this.salario = salario;
    }

    public Funcionario(String nome, String departamento, int idade, String cpf) {
        this.nome = nome;
        this.departamento = departamento;
        this.idade = idade;
        this.cpf = cpf;
        naEmpresa = true;
    }

    public void AumentarSalario(double valor) {
        if (valor > 0) {
            salario += valor;
        }
    }

    public void ReduzirSalario(double valor) {
        if (valor < salario) {
            salario -= valor;
        }
    }

    public void FazerAniversario() {
        idade++;
    }

    public void Demitir() {
        naEmpresa = false;
    }

    public void SetTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void SetDataDaContratacao(LocalDateTime dataDaContratacao) {
        this.dataDaContratacao = dataDaContratacao;
    }

    public String GetNome() {
        return nome;
    }

    public String GetTelefone() {
        return telefone;
    }

    public String GetDepartamento() {
        return departamento;
    }

    public int GetIdade() {
        return idade;
    }

    public String GetCpf() {
        return cpf;
    }

    public double GetSalario() {
        return salario;
    }

    public LocalDateTime GetDataDaContratacao() {
        return dataDaContratacao;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nTelefone: " + telefone + "\nDepartamento: " + departamento + "\nIdade: " + idade + "\nCpf: " + cpf + "\nSalário: " + salario + "\nData da contratacao: " + dataDaContratacao + "\nEstá na empresa: " + naEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.telefone);
        hash = 89 * hash + Objects.hashCode(this.departamento);
        hash = 89 * hash + this.idade;
        hash = 89 * hash + Objects.hashCode(this.cpf);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.salario) ^ (Double.doubleToLongBits(this.salario) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.dataDaContratacao);
        hash = 89 * hash + (this.naEmpresa ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Funcionario))
            return false;
        Funcionario func = (Funcionario)obj;
        return nome.equals(func.nome) && telefone.equals(func.telefone) && departamento.equals(func.departamento)
                && idade == func.idade && cpf.equals(func.cpf) && salario == func.salario && dataDaContratacao.equals(func.dataDaContratacao)
                && naEmpresa == func.naEmpresa;
    }
}