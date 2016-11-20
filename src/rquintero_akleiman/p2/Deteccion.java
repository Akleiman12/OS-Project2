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
public class Deteccion {
    private int allocacion[][];
    private int recursos[];
    private int request[];
    private int auxiliar[];
    private boolean marca[];
    
    //Constructor
    public Deteccion(int[][] allocacion, int[] recursos, int[] request, int[] auxiliar, boolean[] marca) {
        this.allocacion = allocacion;
        this.recursos = recursos;
        this.request = request;
        this.auxiliar = auxiliar;
        this.marca = marca;
    }
    
    //Sets y gets

    public int[][] getAllocacion() {
        return allocacion;
    }

    public void setAllocacion(int[][] allocacion) {
        this.allocacion = allocacion;
    }

    public int[] getRecursos() {
        return recursos;
    }

    public void setRecursos(int[] recursos) {
        this.recursos = recursos;
    }

    public int[] getRequest() {
        return request;
    }

    public void setRequest(int[] request) {
        this.request = request;
    }

    public int[] getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(int[] auxiliar) {
        this.auxiliar = auxiliar;
    }

    public boolean[] getMarca() {
        return marca;
    }

    public void setMarca(boolean[] marca) {
        this.marca = marca;
    }

    
    
    //Metodos propios
    
}
