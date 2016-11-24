/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rquintero_akleiman.p2;

/**
 *
 * @author Asher
 */
public class Recurso {
 
    private int cantidasd;
    private String nombre;
    
    //Constructor

    public Recurso(int cantidasd, String nombre) {
        this.cantidasd = cantidasd;
        this.nombre = nombre;
    }
    
    //Set y Get

    public int getCantidasd() {
        return cantidasd;
    }

    public void setCantidasd(int cantidasd) {
        this.cantidasd = cantidasd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
