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
public class Prediccion {
   private int allocation [][] = new int [150][150];//matriz asignacion
   private int maximo[][] = new int [150][150];//maximo de recursos por proceso
   private int necesidad[][] = new int [150][150];//matriz necesidad
   private int recursos[]= new int[150];//recursos del sistema maximo
   private int disponibles[]= new int[150];//recursos disponibles del sitema
   private int posicionI;
   private int bloqueados[][] = new int [150][150];
   private int bloqueadosActual;
   private int bloqueadosTotal;
   private int procesosTotal;
   private long tiempo;
   private int procesosFinalizados;
   private int finalizados[] = new int [150];
   
   //Constructor 

    public Prediccion(int[] r, int[] d){
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                allocation[i][j]=0;
            }
        }
        
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                maximo[i][j]=0;
            }
        }
        
        for (int i = 0; i < 150; i++) {
            recursos[i]=r[i];
        }
        posicionI=0;
        for (int i = 0; i < 150; i++) {
            disponibles[i]=d[i];
        }
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                bloqueados[i][j]=0;
            }
        }
        for (int i = 0; i < 150; i++) {   
                finalizados[i]=0;    
        }
        bloqueadosActual=0;
        bloqueadosTotal=0;
        procesosTotal=0;
        tiempo=0;
        procesosFinalizados=0;
    }

    

    //Gets y Sets
    public int[] getDisponibles() {
        return disponibles;
    }
    
    public void setDisponibles(int[] disponibles) {
        this.disponibles = disponibles;
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
    
    
    
    //Metodos Propios
    
    public void insertarProceso(int[] max, int posicion){//Insercion de proceso cuando se crea
        procesosTotal++;
        for(int j=0; j<recursos.length; j++){
            allocation[posicion][j]=0;
        }    
        
        for(int j=0; j<max.length; j++){
            maximo[posicion][j]=max[j];
        }
        
        if(posicion>posicionI)
            posicionI=posicion;
    }
    
    public void finalizar(int posicion){//Saber si finalizar un proceso que ha llegado a sus requerimientos maximos
        boolean finalizo=true;
        for (int i = 0; i < allocation[posicion].length; i++) {
            if(allocation[posicion][i]!=maximo[posicion][i])
                finalizo=false;
        }
        if(finalizo){
            for (int i = 0; i < allocation[posicion][i]; i++) {
                disponibles[i]=allocation[posicion][i];
                allocation[posicion][i]=maximo[posicion][i]=0;
            
            }
         finalizados[posicion]=1;
         procesosFinalizados++;
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
            for (int i = 0; i < recursos.length; i++) {
                if(al[i]>disponibles[i])
                    permitir=false;
            }

            if(permitir){
                for(int j=0; j<recursos.length; j++){
                    allocation[posicion][j]=allocation[posicion][j]+al[j];
                    disponibles[j]=disponibles[j]-al[j];
                }
            }
            else{
                bloquear(posicion, al, 0);
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
    public void bloquear(int posicion, int[] request, int a){//Bloqueado de proceso por no cumplir el requerimiento     
        if(a==1){
            for (int i = 0; i < request.length; i++) {
             bloqueados[posicion][i]=request[i];
             allocation[posicion][i]=allocation[posicion][i]-request[i];
             disponibles[i]=disponibles[i]+request[i];
             
            }
            
            bloqueadosActual++;
            bloqueadosTotal++;
        }
        else
        {
          for (int i = 0; i < request.length; i++) {
             bloqueados[posicion][i]=request[i];
            }
            bloqueadosActual++;
            bloqueadosTotal++;  
        }
    }
    
    /*private void chequeaBloqueados(){//para saber si desbloquear algun proceso
        for (int i = 0; i <bloqueados.length; i++) {
            boolean go=true;
            boolean vacio=true;
            for (int j = 0; j < bloqueados[i].length; j++) {
                if(bloqueados[i][j]!=0)
                    vacio=false;
                if(bloqueados[i][j]>disponibles[j])
                    go=false;
            }
            if(vacio)
                go=false;
            
            if(go){
                int request[] = new int[bloqueados[i].length];
                for (int j = 0; j < bloqueados[i].length; j++) {
                    request[j]=bloqueados[i][j];
                }
                asignar(request, i);
                boolean asignar=calcular(i);
                if(!asignar){
                    int a[]= new int[bloqueados[i].length];
                    for (int j = 0; j < bloqueados[i].length; j++) {
                        a[j]=0;   
                    }
                    bloquear(i,a);
                }
            }
        }
        
    }*/
    
    public void calc_need(){//donde se calcula la matriz necesidad
       for(int i=0;i<allocation.length;i++){
            for(int j=0;j<recursos.length;j++){ 
                necesidad[i][j]=maximo[i][j]-allocation[i][j];
            }
        }
    }
    
    public boolean check(int i, int[] auxiliar){
       //se chequea si se pueden asignar todos los recursos
       for(int j=0;j<recursos.length;j++){
       if(auxiliar[j]<necesidad[i][j])
          return false;
       }
       return true;
    }
    
    public boolean calcular(int posicion){//banquero
        calc_need();
        boolean [] listo = new boolean[allocation.length];
        int aux=0;

        int[] auxiliar = new int[150];
        for (int j = 0; j <disponibles.length; j++) {
                        auxiliar[j]=disponibles[j];
                    }
        while(aux<allocation.length){
            boolean asignado=false;
            for(int i=0;i<allocation.length;i++){
                if(!listo[i] && check(i, auxiliar)){  //se trata de asignar
                    
                    
                 for(int k=0;k<recursos.length;k++){
                    auxiliar[k]=auxiliar[k]-necesidad[i][k]+maximo[i][k];
                 }
                 asignado=true;
                 listo[i]=true;
                 aux++;
             }
            }
             if(!asignado) 
                 break;  //si no hay asignacion
        }
       if(aux==allocation.length){  //si todos los procesos se asignaron
        System.out.println("\nSe asigno con seguridad");
        return true;
       }
       else{
        System.out.println("No todos los procesos pueden asignarse con seguridad");
        return false;
       }
    }
    public void correr(int posicion, int[] request){
        long startTime = System.nanoTime();
        boolean finalizo=checkFinalizo(posicion);
        if(!finalizo){
            boolean paso=asignar(request, posicion);
            if(paso){
                if(!calcular(posicion)){
                    
                    bloquear(posicion, request, 1);
                }else 
                    finalizar(posicion);
            }
            else
                System.out.println("SE NEGO LA SOLICITUD");
        }
        long finishTime = System.nanoTime();
        tiempo=(finishTime-startTime)/1000000;
        
        for (int k = 0; k <disponibles.length; k++) {
                System.out.println("recurso disponible"+k+" : "+disponibles[k]);
                System.out.println("recurso pedido"+k+" : "+request[k]);
                System.out.println("Maximo: "+maximo[posicion][k]+" Ocupado: "+allocation[posicion][k]);
                
                
        }
        
        
    }


}
        
        
    
   
   

