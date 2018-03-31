/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco.utils;

import com.zed.banco.ContaPoupanca;

/**
 *
 * @author arrud
 */
@FunctionalInterface
public interface ISeguro {

    double getValorSeguro(ContaPoupanca contaPoupanca);
}
