package com.example.miravereda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CuentaRepository {

    private List<Cuenta> cuentas;

    public CuentaRepository(){
        cuentas=new ArrayList<>();
        cuentas.add(new Cuenta("dav","David","Devis Tamarit",new Date(),"david@email.com","david"));
    }

    public void anyadirCuentas(Cuenta cuenta){
        cuentas.add(cuenta);
    }

}
