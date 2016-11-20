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
public class Prevencion {
   private int allocation [][];
   private int maximo[][];
   private int necesidad[][];
   private int recursos[];
   
   //Constructor 

    public Prevencion(int[][] allocation, int[][] maximo, int[][] necesidad, int[] recursos) {
        this.allocation = allocation;
        this.maximo = maximo;
        this.necesidad = necesidad;
        this.recursos = recursos;
    }
   
    
    //Gets y Sets

    public int[][] getAllocation() {
        return allocation;
    }

    public void setAllocation(int[][] allocation) {
        this.allocation = allocation;
    }

    public int[][] getMaximo() {
        return maximo;
    }

    public void setMaximo(int[][] maximo) {
        this.maximo = maximo;
    }

    public int[][] getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(int[][] necesidad) {
        this.necesidad = necesidad;
    }

    public int[] getRecursos() {
        return recursos;
    }

    public void setRecursos(int[] recursos) {
        this.recursos = recursos;
    }
    
    
    //Metodos Propios
    
   
   
}
