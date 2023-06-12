/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo2Fases;

import java.text.DecimalFormat;

public class Tabla2F {
           
    String[] varBasica;
    String[] variables;
    double[][] matriz; 
    int ntotalHolguras = 0;
    int co;
    int nrest, nvar;
    int nholgurasB, nholgurasNB;
    
    DecimalFormat decimal = new DecimalFormat("0.00");
    
    public Tabla2F(int nrest, int nvar, int nholgurasB, int nholgurasNB){
        this.nrest = nrest;        
        this.nvar = nvar;
        this.nholgurasB = nholgurasB;
        this.nholgurasNB = nholgurasNB;
        
        final int e = 1;
        
        varBasica = new String[nrest+e];
        iteracion = new Iteraciones2F();
        fase1 = new Fase1(nrest, nvar, nholgurasB, nholgurasNB);
    }
    
    Iteraciones2F iteracion;
    Fase1 fase1;
    
    private boolean esVarBasica(int nrest, int totalVar, int i, double[][] matriZ){
        //Esta funcion permite ubicar la columna de las variables basicas
        boolean varbas = false;
        int cero = 0;
        
        for (int j = 0; j < totalVar; j++){ //Columnas
            if (matriZ[i][j] == 1){ 
                for (int k = 0; k <= nrest; k++){ //Fila de elemento 1
                    if (k != i){ //Diferente a fila evaluada
                        if (matriZ[k][j] == 0){
                            cero += 1;
                        }
                    }
                }
                if (cero == nrest){ //columna tiene variable básica
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
        boolean solucionI = true;
        for (int i = 0; i < nrest; i++){
            if (holguras[i] != 1){
               solucionI = false;
            }
        }
        return solucionI;
    }
    
    public void matrizI (int totalVar, double[] vardecision, double[][] restriccionI, int[] holguras, boolean solucionB, int nholgurasB, int nholgurasNB){
        int e = 1; //Fila z y columna resultado extra a restricciones
        ntotalHolguras = nholgurasB; 
        matriz = new double[nrest+e][totalVar+e];
        if (solucionB == true){
          //Filas         
          System.out.println("\tMetodo simplex");
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
                    matriz[i][j] = restriccionI[i-e][j];
                }
                else if(j < totalVar){ //holgura de restriccion i-1
                    if ((i-e) == (j - nvar)){
                        matriz[i][j] = holguras[i-e];
                    } 
                    else{
                        matriz[i][j] = 0;
                    } 
                }
                else{ //Resultados restricciones i-1
                    matriz[i][j] = restriccionI[i-e][nvar];    
                }
              }
            }
          }
          //Variables basicas (filas)
          variablesBasicasD(nrest, totalVar, nvar, ntotalHolguras, matriz);        
          //Variables (columnas)
          tVariables(totalVar, nvar, ntotalHolguras);
        }
        else {
            System.out.println("\tMetodo de 2 Fases");
            fase1.matrizFase1(restriccionI, holguras);
            varBasica = fase1.variablesBasicas(nrest, nvar, varBasica); //Variables basicas (filas)
            tVariables(fase1.totalVar, nvar, fase1.nTotalHolguras);
            imprimir(fase1.matrizF1, nrest, fase1.totalVar, variables);
            System.out.println();
            for (int i = 1; i <= fase1.nVarArt; i++){ 
                int filaP = iteracion.filaP(fase1.matrizF1, nrest, fase1.totalVar, fase1.columnasAr[i-1]);
                //System.out.println(fase1.columnasAr[i-1] + " " + filaP);
                fase1.convertirACero(fase1.matrizF1, nrest, fase1.totalVar, fase1.columnasAr[i-1], filaP); 
            }
            //variablesBasicas, tVariables
            //variablesBasicasD(nrest, totalVar, nvar, ntotalHolguras, matriz);   
            //Variables (columnas) 
            
        }
        
    }
    
    private void tVariables (int totalVar, int nvar, int nTotalHolguras){ 
        int e = 1;
        variables = new String[totalVar+e];
        
        for (int j = 0; j <= totalVar; j++){
            if (j < nvar){
                variables[j] = "x" + (j + 1);
            }
            else if ( j < (nvar + nTotalHolguras) ){
                variables[j] = "s" + ((j - nvar) + 1);
            
            }
            else if (j < totalVar){
                /*if(holguras[j-nvar] == 1 or holguras[j-nvar] == -1)
                else if (artificiales[j-nvar] == 1){}*/
                variables[j] = ("R" + ( (j - (nvar + nTotalHolguras) ) + 1) );               
            }
            else{
                variables[j] = "Resultado";
            }
        }
    }
    
    private void variablesBasicasD (int nrest, int totalVar, int nvar, int nTotalHolguras, double[][] matriZ){
        //Variables basicas (filas)
        
        for (int i = 0; i <= nrest; i++){
            if (i == 0){
                varBasica[i] = "Z";
            }
            else{
                if (esVarBasica(nrest, totalVar, i, matriZ) == true){ //Se calcula si es holgura
                    if (co < nvar){ //es variable decision 
                    //co es columna donde se encontro variable artificial
                        varBasica[i] = "x" + (co + 1);                 
                    }
                    else if ((co) < (nvar + nTotalHolguras)){
                        varBasica[i] = "s" + ((co - nvar) + 1);
                    }
                }
            }   
        }
    }
    
    private String[] nuevaVBasica(int totalVar, int nvar, int nTotalHolguras, int j, int i, String[] varBasica){
        if (j < nvar){ //es variable decision 
        //co es columna donde se encontro variable artificial
            varBasica[i] = "x" + (j + 1);                 
        }
        else if ((j) < (nvar + nTotalHolguras)){
            varBasica[i] = "s" + ((j - nvar) + 1);
        }
        else if ((j) < totalVar){ //Es variable artificial
            varBasica[i] = "R" + ( j - (nvar + nTotalHolguras) + 1 );  
        }       
        return varBasica;
    }
    
    private void imprimir(double[][] matriz, int nrest, int totalVar, String[] variables){
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
    
    public void resultado(int totalVar, char objetivo, int nrest, int nvar, boolean solucionB, double[] vardecision){
        int columnaPivote, filaPivote; 
        boolean pararI = false;
        final int filar = 0;
               
        if (solucionB == false){ //Fase 1
            imprimir(fase1.matrizF1, nrest, fase1.totalVar, variables);
            System.out.println();
            while (pararI == false){ //Fase 1
                columnaPivote = iteracion.columnaP(fase1.matrizF1, fase1.totalVar, fase1.objetivo);
                filaPivote = iteracion.filaP(fase1.matrizF1, nrest, fase1.totalVar, columnaPivote);
                iteracion.convertirAUno(fase1.matrizF1, fase1.totalVar, columnaPivote, filaPivote);
                iteracion.convertirACero(fase1.matrizF1, nrest, fase1.totalVar, columnaPivote, filaPivote);
                fase1.matrizF1[0][fase1.totalVar] = fase1.matrizF1[0][fase1.totalVar]; 
                //redondea valor de resultado, posible desacomodo de columnas con numeros grandes y pequeños
                varBasica = nuevaVBasica(fase1.totalVar, nvar, fase1.nTotalHolguras,columnaPivote, filaPivote, varBasica); 
                imprimir(fase1.matrizF1, nrest, fase1.totalVar, variables);
                System.out.println();
                
                if (iteracion.parar(fase1.matrizF1, fase1.totalVar, fase1.objetivo) == true){ //Para iteracion
                    if (fase1.matrizF1[filar][fase1.totalVar] == 0){ //Problema factible
                        for (int i = 0; i <= nrest; i++){ //Transicion a fase 2
                            for (int j = 0; j<= totalVar; j++){
                                if (i == 0){
                                    if (j < nvar){
                                        matriz[i][j] = vardecision[j];
                                    }
                                    else{
                                        matriz[i][j] = 0;
                                    }
                                }
                                else if (j < totalVar){
                                    matriz[i][j] = fase1.matrizF1[i][j];        
                                }
                                else{
                                    matriz[i][j] = fase1.matrizF1[i][fase1.totalVar];
                                }
                            }  
                        }
                        ntotalHolguras = fase1.nTotalHolguras;
                        tVariables(totalVar, nvar, ntotalHolguras);
                        solucionB = true; //Pasa a fase2
                        pararI = true;
                    } //Fin de fase 1
                    else{ //Problema no factible 
                        System.out.println("Problema no tiene solucion factible");
                        pararI = true;
                    }
                }    
            } //Fin de while
        }
        
        if (solucionB == true){ //Fase 2
            pararI = false;
            imprimir(matriz, nrest, totalVar, variables); //Se imprime matriz F2 inicial
            System.out.println();
            for (int j = 0; j < nvar; j++){
                fase1.convertirACero(matriz, nrest, totalVar, j, iteracion.filaP(matriz, nrest, totalVar, j));
            } //Se balancea para empezar a iterar en fase 2
            imprimir(matriz, nrest, totalVar, variables);
            System.out.println();
            while (pararI == false){ //Proceso simplex (iterativo)
                columnaPivote = iteracion.columnaP(matriz, totalVar, objetivo);
                filaPivote = iteracion.filaP(matriz, nrest, totalVar, columnaPivote);
                matriz = iteracion.convertirAUno(matriz, totalVar, columnaPivote, filaPivote);
                matriz = iteracion.convertirACero(matriz, nrest, totalVar, columnaPivote, filaPivote);
                varBasica = nuevaVBasica(totalVar, nvar, ntotalHolguras, columnaPivote, filaPivote, varBasica); 
                imprimir(matriz, nrest, totalVar, variables);
                System.out.println();
                pararI = iteracion.parar(matriz, totalVar, objetivo);           
            } //Fin de while
        }         
    }    
}
