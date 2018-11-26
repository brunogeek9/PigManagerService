
package com.ufrn.projeto.model.enums;

public enum EnumEstagio {
    COBERTA("COBERTA"),
    PRENHES("PRENHES"),
    LACTACAO("LACTACAO"),
    VAZIA("VAZIA");
    private final String valor;
    EnumEstagio(String valor){
        this.valor = valor;
    }
}
