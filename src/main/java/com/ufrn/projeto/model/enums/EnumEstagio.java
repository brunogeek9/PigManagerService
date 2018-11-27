package com.ufrn.projeto.model.enums;

public enum EnumEstagio {
    COBERTA("COBERTA"),
    PRENHES("PRENHES"),
    LACTACAO("LACTACAO"),
    VAZIA("VAZIA");

    public String getValor() {
        return valor;
    }    
    
    private final String valor;
    EnumEstagio(String valor){
        this.valor = valor;
    }
    
    public static EnumEstagio getETipoFatura(String value){
        for (EnumEstagio tipoFatura : EnumEstagio.values()){
            if(tipoFatura.getValor() == value){
                    return tipoFatura;
            }
        }
        return null;
    }
        
    public EnumEstagio getEnum(String code) {
        switch (code) {
            case "COBERTA":
                return COBERTA;
            case "PRENHES":
                return PRENHES;
            case "LACTACAO":
                return LACTACAO;
            case "VAZIA":
                return VAZIA;
            default:
                return null;
         }
   }
}
