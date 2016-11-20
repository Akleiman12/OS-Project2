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
   
   //Constructor 

    public Prediccion(int[][] allocation, int[][] maximo, int[] recursos, int[] disponibles) {
        this.allocation = allocation;
        this.maximo = maximo;
        this.recursos = recursos;
        posicionI=0;
        this.disponibles = disponibles;
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
    
    
    //Metodos Propios
    private void insertarProceso(int[] max){//Insercion de proceso cuando se crea
        
        for(int j=0; j<recursos.length; j++){
            allocation[posicionI][j]=0;
        }    
        
        for(int j=0; j<recursos.length; j++){
            maximo[posicionI][j]=max[j];
        }
        
        posicionI++;
    }
    
    private void alocar(int [] al, int proceso){//Inserion en la matriz allocation cuando se hace una solicitud
        
        for(int j=0; j<recursos.length; j++){
            allocation[proceso][j]=al[j];
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
    
    private void calcular(){
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
       if(aux==posicionI)  //si todos los procesos se asignaron
        System.out.println("\nSe asigno con seguridad");
       else
        System.out.println("No todos los procesos pueden asignarse con seguridad");
    }
            


}
        
        
    
   
   

