/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoSimplex;

import java.text.DecimalFormat;

public class TablaSimplex {   
    
    private String[] varBasica, variables; //Son para imprimirse en la tabla
    double[][] matriz; //matriz simplex
    
    protected int co;
    
    public TablaSimplex(int nrest, int totalVar) { //Constructor
    	varBasica = null;
    	variables = null;
    	co = 0;
    	int e = 1; //Fila z y columna resultado extra a restricciones
        matriz = new double[nrest+e][totalVar+e];
        varBasica = new String[nrest+e];
        variables = new String[totalVar+e];
    }
    
    private boolean esVarBasica(int nrest, int totalVar, int i){
        //Esta funcion permite ubicar la columna de las variables basicas
        boolean varbas = false;
        int cero = 0;
        
        for (int j = 0; j < totalVar; j++){ //Columnas
            if (matriz[i][j] == 1){ 
                for (int k = 0; k <= nrest; k++){ //Fila de elemento 1
                    if (k != i){
                        if (matriz[k][j] == 0){
                            cero += 1; //Cantidad de ceros en la columna
                        }
                    }
                }
                if (cero == nrest){
                    varbas = true;
                    co = j; //Columna de variable básica
                    break; //Sale de for
                }
                else{
                    cero = 0;
                }
            }
              /*if (datos.holguras[i] != 1){
               varBasica[i] = ;//fila columna
          }*/
        }
        return varbas;
    }
    
    public boolean solBasicaI(int nrest, int[] holguras){
    	//Funcion que devuelve si tiene solucion basica inicial (para saber si se puede solucionar con el simplex)
        boolean solucionI = true;
        for (int i = 0; i < nrest; i++){
            if (holguras[i] != 1){
               solucionI = false;
            }
        }
        return solucionI;
    }
    
    public void matrizI(int nrest, int totalVar, double[] vardecision, double[][] restriccionI, int[] holguras, int nvar){
    	//procedimiento que redistribuye datos leidos a una matriz       
        
        //Filas
        for (int i = 0; i <= nrest; i++){
            for (int j = 0; j <= totalVar; j++){ //Columnas
              if (i == 0){ //Funcion objetivo
                if (j == totalVar){ //Resultado de funcion objetivo
                    matriz[i][j] = vardecision[nvar];//
                }
                else if (j < nvar){ //Coeficientes variables decision
                    matriz[i][j] = vardecision[j];
                }
                else { //Var holgura
                    matriz[i][j] = 0;
                }
              }
              else{ //Restricciones
                if (j < nvar){ //coeficientes de variables
                    matriz[i][j] = restriccionI[i-1][j];
                }
                else if(j < totalVar){ //holgura de restriccion i-1
                    if ((i-1) == (j - nvar)){
                        matriz[i][j] = holguras[i-1];
                    } 
                    else{
                        matriz[i][j] = 0;
                    } 
                }
                else{ //Resultados restricciones i-1
                    matriz[i][j] = restriccionI[i-1][nvar];    
                }
              }
            }
        }
        //Variables basicas (filas)
        variablesBasicas(nrest, totalVar, nvar);        
        //Variables (columnas)
        tVariables(nrest, totalVar, nvar);
     
    }
    
    private void tVariables(int nrest, int totalVar, int nvar){ 
        for (int j = 0; j <= totalVar; j++){
            if (j < nvar){
                variables[j] = "x" + (j + 1);
            }
            else if (j < totalVar){
                /*if(holguras[j-nvar] == 1 or holguras[j-nvar] == -1)
                else if (artificiales[j-nvar] == 1){}*/
                if ((j - nvar) < nrest){ //Se calcula si es holgura
                    variables[j] = ("s" + ((j - nvar) + 1));
                }
            }
            else{
                variables[j] = "Resultado";
            }
        }   
    }
    
    private void variablesBasicas(int nrest, int totalVar, int nvar){
        //Variables basicas (filas)        
        for (int i = 0; i <= nrest; i++){
            if (i == 0){
                varBasica[i] = "Z";
            }
            else{
                if (esVarBasica(nrest, totalVar, i) == true){ /*Se calcula 
                    si es holgura*/
                    if (co < nvar){ //es variable decision 
                    //co es columna donde se encontro variable artificial
                        varBasica[i] = "x" + (co + 1);                 
                    }
                    else if ((co - nvar) < nrest){ //Es variable holgura
                        varBasica[i] = "s" + ((co - nvar) + 1);  
                    }
                }
            }   
        }
    }
    
    private void imprimir(double[][] matriz, int nrest, int totalVar){
        DecimalFormat decimal = new DecimalFormat("0.00");
        //Filas
        //imprimir variables
        System.out.print("\t");
        for (int j = 0; j <= totalVar; j++){
            System.out.print(variables[j] + "\t"); //Todas las variables
        }
        System.out.println();
        for (int i = 0; i <= nrest; i++){
            System.out.print(varBasica[i] + "\t"); //Variable básica
            for (int j = 0; j <= totalVar; j++){ //Columnas
                if ((matriz[i][j] % 1) == 0){
                    System.out.print(matriz[i][j] + "\t"); //matriz de valores
                }
                else{
                    System.out.print(decimal.format(matriz[i][j]) + "\t");                
                }
            }
            System.out.println();
        }
    }
    
    public void resultado(int totalVar, char objetivo, int nrest, int nvar){
    	IteracionesSimplex iteracion = new IteracionesSimplex();
        int columnaPivote, filaPivote; 
        boolean pararI = false;
        
        imprimir(matriz, nrest, totalVar);
        System.out.println();
        
        while (pararI == false){
            columnaPivote = iteracion.columnaP(matriz, totalVar, objetivo); 
            filaPivote = iteracion.filaP(matriz, nrest, totalVar, columnaPivote);
            
            //Se ajusta la tabla de acuerdo a la columna pivote y fila pivote
            iteracion.convertirAUno(matriz, totalVar, columnaPivote, filaPivote); 
            //Se puede pasar a un procedimiento
            iteracion.convertirACero(matriz, nrest, totalVar, columnaPivote, filaPivote);
            
            //==
            variablesBasicas(nrest, totalVar, nvar); //Se actualiza para imprimir var. Basicas
            imprimir(matriz, nrest, totalVar);
            System.out.println();
            pararI = iteracion.parar(matriz, totalVar, objetivo); 
        }                    
    }
}
