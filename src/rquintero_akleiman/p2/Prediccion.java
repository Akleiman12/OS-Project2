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
   private int allocation [][];
   private int maximo[][];
   private int necesidad[][];
   private int recursos[];
   private int disponibles[];
   private int posicionI;
   private int bloqueados[][];
   private int 
   
   //Constructor 

    public Prediccion(int[][] allocation, int[][] maximo, int[] recursos, int[] disponibles, int[][] bloqueados) {
        this.allocation = allocation;
        this.maximo = maximo;
        this.recursos = recursos;
        posicionI=0;
        this.disponibles = disponibles;
        this.bloqueados=bloqueados;
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
    
    
    
    //Metodos Propios
    private void insertarProceso(int[] max, int posicion){//Insercion de proceso cuando se crea
        
        for(int j=0; j<recursos.length; j++){
            allocation[posicion][j]=0;
        }    
        
        for(int j=0; j<max.length; j++){
            maximo[posicion][j]=max[j];
        }
        
        if(posicion>posicionI)
            posicionI=posicion;
    }
    
    private void asignar(int [] al, int posicion){//Insercion en la matriz allocation cuando se hace una solicitud
        
        for(int j=0; j<recursos.length; j++){
            allocation[posicion][j]=allocation[posicion][j]+al[j];
        }
        
    }
    
    private void bloquear(int posicion, int[] request){//Bloqueado de proceso por no cumplir el requerimiento     
        for (int i = 0; i < request.length; i++) {
         disponibles[i]=disponibles[i]+allocation[posicion][i];
         bloqueados[posicion][i]=request[i]+allocation[posicion][i];
         allocation[posicion][i]=0;      
        }       
    }
    
    private void chequeaBloqueados(){//para saber si desbloquear algun proceso
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
        
    }
    
    void calc_need(){//donde se calcula la matriz necesidad
       for(int i=0;i<posicionI;i++){
            for(int j=0;j<recursos.length;j++){ 
                necesidad[i][j]=maximo[i][j]-allocation[i][j];
            }
        }
    }
    
    private boolean check(int i){
       //se chequea si se pueden asignar todos los recursos
       for(int j=0;j<recursos.length;j++){
       if(disponibles[j]<necesidad[i][j])
          return false;
       }
       return true;
    }
    
    private boolean calcular(int posicion){//banquero
        calc_need();
        boolean [] listo = new boolean[posicionI];
        int aux=0;
        while(aux<posicionI){
            boolean asignado=false;
            for(int i=0;i<posicionI;i++){
                if(!listo[i] && check(i)){  //se trata de asignar
                 for(int k=0;k<recursos.length;k++){
                    disponibles[k]=disponibles[k]-necesidad[i][k]+maximo[i][k];
                 }
                 System.out.println("Proceso asignado: "+i);
                 asignado=listo[i]=true;
                 aux++;
             }
            }
             if(!asignado) 
                 break;  //si no hay asignacion
        }
       if(aux==posicionI){  //si todos los procesos se asignaron
        System.out.println("\nSe asigno con seguridad");
        return true;
       }
       else{
        System.out.println("No todos los procesos pueden asignarse con seguridad");
        return false;
       }
    }
            


}
        
        
    
   
   

