/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zed.banco.utils;

import com.zed.banco.Conta;

/**
 *
 * @author arrud
 */
@FunctionalInterface
public interface ITributavel<T extends Conta> {

    double getValorTributo(T conta);
}
