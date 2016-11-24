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
public class Deteccion {
    private int allocation[][];
    private int disponible[];
    private int request[][];
    private int maximo[][];
    private int auxiliar[];
    private boolean marca[];
    private int posicionI;
    
    //Constructor
    public Deteccion(int[][] allocation, int[] recursos, int[][] request, int[] auxiliar, boolean[] marca, int[][] maximo) {
        this.allocation = allocation;
        this.disponible = recursos;
        this.request = request;
        this.auxiliar = auxiliar;
        this.marca = marca;
        this.maximo = maximo;
        posicionI=0;
    }
     
   

    //Sets y gets
    
     public int[][] getMaximo() {
        return maximo;
    }
     
    public void setMaximo(int[][] maximo) {
        this.maximo = maximo;
    }

    public int getPosicionI() {
        return posicionI;
    }

    public void setPosicionI(int posicionI) {
        this.posicionI = posicionI;
    }
    
    public int[][] getAllocation() {
        return allocation;
    }

    public void setAllocation(int[][] allocacion) {
        this.allocation = allocacion;
    }

    public int[] getDisponible() {
        return disponible;
    }

    public void setDisponible(int[] recursos) {
        this.disponible = recursos;
    }

    public int[][] getRequest() {
        return request;
    }

    public void setRequest(int[][] request) {
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

    
    
    //Metodos Propios
    
    private void insertarProceso(int[] max){//Insercion de proceso cuando se crea
        for (int i = 0; i < maximo.length; i++) {
            maximo[posicionI][i]=max[i];
        }
        
        marca[posicionI]=false;
        
        posicionI++;
    }
    
    private void requestProceso(int[] request){
             for(int j=0; j<disponible.length; j++){
            allocation[posicionI][j]=request[j];
        }  
    }
    
    
    private void calcular(int [] pedido){//Algoritmo de deteccion
        for (int i = 0; i < disponible.length; i++) {
            auxiliar[i]=disponible[i];
        }
        
        for (int i = 0; i < pedido.length; i++) {
            
            
            
        }
        
        
    }
}
