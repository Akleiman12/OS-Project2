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
    private int allocation[][] = new int [150][150];
    private int disponible[]= new int[150];
    private int request[][] = new int [150][150];
    private int maximo[][] = new int [150][150];
    private int auxiliar[] = new int [150];
    private boolean marca[] = new boolean [150];
    private int bloqueados[][]= new int [150][150];
    private int bloqueadosActual;
    private int bloqueadosTotal;
    private int procesosTotal;
    private int eliminados;
    private long tiempo;
    private int procesosFinalizados;
    private int finalizados[]= new int [150];
    private int eliminar []= new int [150];
    
    //Constructor
    public Deteccion(int[] r) {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                allocation[i][j]=0;
            }
        }

        for (int i = 0; i < 150; i++) {
            disponible[i]=r[i];
        }
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                request[i][j]=0;
            }
        }
        for (int i = 0; i < 150; i++) {
               auxiliar[i]=0;    
        }
        for (int i = 0; i < 150; i++) {
                marca[i]=false;    
        }
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                maximo[i][j]=0;
            }
        }
        bloqueadosActual=0;
        bloqueadosTotal=0;
        procesosTotal=0;
        eliminados=0;
        tiempo=0;
        procesosFinalizados=0;
        for (int i = 0; i < 150; i++) {
                eliminar[i]=0;           
        }
        
        for (int i = 0; i < 150; i++) {
                finalizados[i]=0;           
        }
        
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                bloqueados[i][j]=0;
            }
        }
        
        
    }
     
   

    //Sets y gets
    
     public int[][] getMaximo() {
        return maximo;
    }
     
    public void setMaximo(int[][] maximo) {
        this.maximo = maximo;
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

    public int[][] getBloqueados() {
        return bloqueados;
    }

    public void setBloqueados(int[][] bloqueados) {
        this.bloqueados = bloqueados;
    }

    public int getBloqueadosActual() {
        return bloqueadosActual;
    }

    public void setBloqueadosActual(int bloqueadosActual) {
        this.bloqueadosActual = bloqueadosActual;
    }

    public int getBloqueadosTotal() {
        return bloqueadosTotal;
    }

    public void setBloqueadosTotal(int bloqueadosTotal) {
        this.bloqueadosTotal = bloqueadosTotal;
    }

    public int getProcesosTotal() {
        return procesosTotal;
    }

    public void setProcesosTotal(int procesosTotal) {
        this.procesosTotal = procesosTotal;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public int getProcesosFinalizados() {
        return procesosFinalizados;
    }

    public void setProcesosFinalizados(int procesosFinalizados) {
        this.procesosFinalizados = procesosFinalizados;
    }

    public int[] getFinalizados() {
        return finalizados;
    }

    public void setFinalizados(int[] finalizados) {
        this.finalizados = finalizados;
    }

    public int getEliminados() {
        return eliminados;
    }

    public void setEliminados(int eliminados) {
        this.eliminados = eliminados;
    }

    public int[] getEliminar() {
        return eliminar;
    }

    public void setEliminar(int[] eliminar) {
        this.eliminar = eliminar;
    }

    
    
    //Metodos Propios
    
    public void insertarProceso(int posicion, int[] max){//Insercion de proceso cuando se crea
        for (int i = 0; i < maximo.length; i++) {
            maximo[posicion][i]=max[i];
        }
        procesosTotal++;
        marca[posicion]=false;
    }
    
    public void finalizar(int posicion){//Saber si finalizar un proceso que ha llegado a sus requerimientos maximos
        boolean finalizo=true;
        for (int i = 0; i < allocation[posicion].length; i++) {
            if(allocation[posicion][i]!=maximo[posicion][i])
                finalizo=false;
        }
        if(finalizo){
            for (int i = 0; i < allocation[posicion][i]; i++) {
                disponible[i]=allocation[posicion][i];
                allocation[posicion][i]=maximo[posicion][i]=0;
            
        }
         finalizados[posicion]=1;
         procesosFinalizados++;
         procesosTotal--;
        }
            
    }
    
    public boolean checkFinalizo(int posicion){//Ver si un proceso que hizo un requerimiento ya habia finalizado
        if(finalizados[posicion]!=0)
            return true;
        else
            return false;
        
    }
     
    public boolean asignar(int [] al, int posicion){//Insercion en la matriz allocation cuando se hace una solicitud
        boolean desbloquea=true;
        boolean bloqueado=false;
        for (int j = 0; j < bloqueados[posicion].length; j++) {
            if(bloqueados[posicion][j]!=al[j])
                desbloquea=false;
            if(bloqueados[posicion][j]!=0)
                bloqueado=true;
            
        }
        if(desbloquea){
            desbloquear(posicion);
            return true;
        }
        else if(!bloqueado){
            boolean permitir=true;
            for (int i = 0; i < disponible.length; i++) {
                if(al[i]>disponible[i])
                    permitir=false;
            }
            if(permitir){
                for(int j=0; j<disponible.length; j++){
                    allocation[posicion][j]=allocation[posicion][j]+al[j];
                    disponible[j]=disponible[j]-al[j];
                }
            }
            else{
                bloquear(posicion, al);
            }
            return true;
        }
        else{
            return false;
        }
        
    }
     
    public void desbloquear (int posicion){//Para desbloquear un proceso previamente bloqueado
        for (int i = 0; i < bloqueados[posicion].length; i++) {
             allocation[posicion][i]=allocation[posicion][i]+bloqueados[posicion][i];
             bloqueados[posicion][i]=0;
        }
        bloqueadosActual--;
        
    }
     
    public void bloquear(int posicion, int[] request){//Bloqueado de proceso por no cumplir el requerimiento     
        for (int i = 0; i < request.length; i++) {
            bloqueados[posicion][i]=request[i];
        }
        bloqueadosActual++;
        bloqueadosTotal++;
    }

    public void calc_need(){//donde se calcula la matriz request
       for(int i=0;i<allocation.length;i++){
            for(int j=0;j<disponible.length;j++){ 
                request[i][j]=maximo[i][j]-allocation[i][j];
            }
        }
    }
    
    public boolean calcular(){//Algoritmo de deteccion
        calc_need();
        
        for (int i = 0; i < allocation.length; i++) {//marcar los que no tuviesen nada asignado
            boolean marcar=true;
            for (int j = 0; j < allocation[i].length; j++) {
                if(allocation[i][j]!=0)
                    marcar=false;
            }
            if(marcar)
                marca[i]=true;
        }
        
        for (int i = 0; i <disponible.length; i++) {
            auxiliar[i]=disponible[i];
        }
        
        boolean seguir=true;
        
        while(seguir){//algoritmo pasos 3 y 4
            seguir=false;
            for(int i = 0; i <allocation.length; i++) {
                boolean verificar=true;
                if(!marca[i]){
                    for (int j = 0;j < allocation[i].length; j++) {
                        if(request[i][j]>auxiliar[j])
                            verificar=false;
                    }
                    if(verificar){
                        seguir=true;
                        for (int j = 0; j < allocation[i].length; j++) {
                            auxiliar[j]=auxiliar[j]+allocation[i][j];
                            marca[i]=true;
                        }
                    }
                }    
            }
        }
        boolean logro=true;
        for (int i = 0; i < marca.length; i++) {
            if(!marca[i]){
                eliminar(i);
                eliminados++;
                procesosTotal--;
                logro=false;
            }
        }
        return logro;
    }
    
    public void eliminar(int posicion){
        
        
        for (int i = 0; i <allocation[posicion][i]; i++) {
            disponible[i]=disponible[i]+allocation[posicion][i];
            allocation[posicion][i]=maximo[posicion][i]=0;
        }

        eliminar[posicion]=1;
    }
    
    public boolean checkEliminado(int posicion){
        if(eliminar[posicion]!=0)
            return true;
        else
            return false;
        
    }
    
   public int[] correr(int posicion, int[] request){
        long startTime = System.nanoTime();
        boolean finalizo=checkFinalizo(posicion);
        boolean eliminado=checkEliminado(posicion);
        if(!finalizo && !eliminado){
            boolean logrado;
            boolean res=asignar(request, posicion);
            logrado=calcular();
            if(logrado)
                finalizar(posicion);
        }
        for (int i = 0; i < marca.length; i++) {
           marca[i]=false;
       }
        long finishTime = System.nanoTime();
        tiempo=(finishTime-startTime)/1000000;
        return eliminar;
    }
}
