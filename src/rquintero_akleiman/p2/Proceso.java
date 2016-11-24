/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rquintero_akleiman.p2;

/**
 *
 * @author Asher y Reynaldo
 */
public class Proceso {
    private int ID;
    private int recursosMaximos[];
    private String nombre;
    
    //Constructor
      public Proceso(int ID, int[] recursosMaximos, String nombre) {
        this.ID = ID;
        this.recursosMaximos = recursosMaximos;
        this.nombre=nombre;
    }
      
    //Sets y Gets

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getRecursosMaximos() {
        return recursosMaximos;
    }

    public void setRecursosMaximos(int[] recursosMaximos) {
        this.recursosMaximos = recursosMaximos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
      
  
    
}
