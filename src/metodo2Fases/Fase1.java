/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodo2Fases;

public class Fase1 {
    double matrizF1[][];
    int nVarArt = 0, totalVar = 0, nTotalHolguras = 0;
    int columnasAr[];
    char objetivo = '-';
    int columna;
    int nvar, nrest; //numero de variables de decision y restricciones
    int nholgurasB, nholgurasNB;
      
    public Fase1 (int nrest, int nvar, int nholgurasB, int nholgurasNB){       
        this.nrest = nrest;
        this.nvar = nvar;
        this.nholgurasB = nholgurasB;
        this.nholgurasNB = nholgurasNB;
        
        nTotalHolguras = nholgurasB + nholgurasNB; //Total de variables
        final int fE = 1;
        
        totalVar = nrest + nvar + nholgurasNB; //Todas las variables del problema
        nVarArt = totalVar - (nTotalHolguras + nvar); //Numero de variables atificiales
        columnasAr = new int[nVarArt];
        //System.out.println(totalVar + " " + nrest + " " + nvar);
        matrizF1 = new double[nrest+fE][totalVar + fE];     
    }
      
    public void matrizFase1 (double[][] restriccionI, int[] holguras){
        //Procedimiento que calcula la matriz de valores de la fase 1
        final int fE = 1; //fila Extra, columna Extra 
        int nArt = 0; 
        boolean varArtificialAsignada = false;
        
        for (int i = 0; i <= nrest; i++){ //Filas            
            for (int j = 0; j <= totalVar; j++){ //Columnas
                if (i == 0){ //Fila r (función objetivo artificial)
                    if ( (j < (nvar + nTotalHolguras)) || (j == totalVar) ){ 
                        //Variables de decision, holguras basicas y resultado de fila r Inicial
                        matrizF1[i][j] = 0;
                    }
                    else{ //Variables artificiales
                        matrizF1[i][j] = -1;
                    }
                }
                else { 
                    if (j < nvar){ 
                    //Variables de decision de función objetivo y coeficientes de restricciones              
                        matrizF1[i][j] = restriccionI[i-fE][j];
                    }                    
                    else if ( j < (nvar + nTotalHolguras) ){ //variables holgura
                        if ( (i-fE) == (j - nvar) ){ 
                            matrizF1[i][j] = holguras[i-fE];
                        }
                        else{
                            matrizF1[i][j] = 0;
                        }
                    }
                    else if (j < totalVar){ //Variables artificiales
                        if (j == (nArt + nvar + nTotalHolguras)){
                            if ( ((holguras[i-fE] == -1) || (holguras[i-fE] == 0)) && varArtificialAsignada == false ){ //nrest - 1
                                matrizF1[i][j] = 1; // campo con Variable artificial
                                columnasAr[j - (nTotalHolguras + nvar)] = j; //Columna con variable artificial
                                nArt += 1;
                                varArtificialAsignada = true;
                            }
                            else{ 
                                matrizF1[i][j] = 0; //campo sin variable artificial
                            }
                            
                        }
                    }
                    else{ //Resultado
                        matrizF1[i][j] = restriccionI[i-fE][nvar];    
                    }    
                }   
            }
            varArtificialAsignada = false;
        }  
    } 
    
    protected void convertirACero(double[][] matriz, int nrest, int totalVar, int c, int f){
        //Este procedimiento calcula la fila obtenida al convertir un elemento no pivote de la columna pivote a 0 
        double[][] filaAuxiliar = new double[1][totalVar+1]; 
        double eNoPivot;
        int filar = 0;
        eNoPivot = (matriz[filar][c]);  

        for (int j = 0; j <= totalVar; j++){            
            filaAuxiliar[0][j] = ((-1)* eNoPivot *(matriz[f][j])); //-(c)Fi + Ff           
            
            matriz[filar][j] += filaAuxiliar[0][j];
        }
    }
    
    private boolean esVarBasica(int i){
        //Esta funcion permite ubicar la columna de las variables basicas
        boolean varbas = false;
        int cero = 0;
        
        for (int j = nvar; j < totalVar; j++){ //Columnas
            if (matrizF1[i][j] == 1){ 
                for (int k = 0; k <= nrest; k++){ //Fila de elemento 1
                    if (k != i){ //Diferente a fila evaluada
                        if ( (matrizF1[k][j] == 0)  || (matrizF1[k][j] == -1) ){ //modificado
                            cero += 1;
                        }
                    }
                }
                if (cero == nrest){ //columna tiene variable básica
                    varbas = true;
                    columna = j; //Columna de variable básica
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
    
    protected String[] variablesBasicas (int nrest, int nvar, String[] varBasica){
        //Variables basicas (filas)  
        for (int i = 0; i <= nrest; i++){
            if (i == 0){
                varBasica[i] = "Z";
            }
            else{
                if (esVarBasica(i) == true){ //Se calcula si es holgura
                    if ( (columna >= nvar) && (columna < (nvar + nTotalHolguras)) ){
                        varBasica[i] = "s" + ((columna - nvar) + 1);
                    }
                    else if (columna < totalVar){ //Es variable holgura
                        varBasica[i] = "R" + ( columna - (nvar + nTotalHolguras) + 1 );  
                    }
                }
            }   
        }
        return varBasica;
    }
    
  
    
}
