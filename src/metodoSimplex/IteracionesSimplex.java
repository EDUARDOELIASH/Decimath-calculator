/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoSimplex;

public class IteracionesSimplex {
	
	//Esta función calcula la columna pivote
    protected int columnaP(double[][] matriz, int totalVar, char objetivo){    
        final int filaZ = 0; 
        int c = 0; //Columna pivote
        if(objetivo == '-'){ //Minimizar
            double mayor = 0;
            for (int j = 0; j < totalVar; j++){
                if (matriz[filaZ][j] > mayor){
                    mayor = matriz[filaZ][j];
                    c = j;
                }
            }
        }
        
        if (objetivo == '+'){ //Maximizar
          double menor = 0;
          for (int j = 0; j < totalVar; j++){
            if (matriz[filaZ][j] < menor){
                menor = matriz[filaZ][j];
                c = j;
            }
          }  
        }  
        return c; //Devuelve columna pivote
    }
    
    //Esta función calcula la Fila pivote
    protected int filaP(double[][] matriz, int nrest, int totalVar, int c){
        double div = 0;
        int f = 0, k = 0; // f Fila pivote
        double menor = 0;
        while ( (menor == 0) && (k <= nrest) ){
            k++;
            if(matriz[k][c] > 0){ //Se comprueba que elemento de columna pivote sea no negativo y no igual a 0
                menor = matriz[k][totalVar] / matriz[k][c];  
                f = k;
            }
        }
        if (k <= nrest){
            for (int i = k; i <= nrest; i++){
                if(matriz[i][c] > 0){ //Se comprueba que elemento de columna pivote sea no negativo y no igual a 0
                    div = matriz[i][totalVar] / matriz[i][c]; 
                    if (div <= menor){    
                        menor = div;
                        f = i;
                    }
                }
            }
        }    
        return f; //Devuelve fila Pivote
    }
    
    //Este procedimiento calcula la fila obtenida al convertir el elemento pivote a 1
    protected void convertirAUno(double[][] matriz, int totalVar, int c, int f){
      double elementoPivote = matriz[f][c]; 
      for (int j = 0; j <= totalVar; j++){
        matriz[f][j] /= elementoPivote;
      }
    }
    
    //Este procedimiento calcula la fila obtenida al convertir un elemento no pivote de la columna pivote a 0 
    protected void convertirACero(double[][] matriz, int nrest, int totalVar, int c, int f){
      double[][] filaAuxiliar = new double[1][totalVar+1]; 
      double eNoPivot;
      
        for (int i = 0; i <= nrest; i++){
            eNoPivot = (matriz[i][c]);  
            if (i != f){ //f Fila pivote no se modifica
              for (int j = 0; j <= totalVar; j++){
                filaAuxiliar[0][j] = ((-1)* eNoPivot *(matriz[f][j])); //-(c)Fi + Ff
                matriz[i][j] += filaAuxiliar[0][j];
              }

            }
        }
    }
    
    //Esta función devuelve true cuando se cumple la decisión de calcular las iteraciones
    protected boolean parar(double[][] matriz, int totalVar, char objetivo){
        int filaZ = 0;
        boolean p = true;
        
        if (objetivo == '+'){
            for (int j = 0; j < totalVar; j++){
                if (matriz[filaZ][j] < 0) {
                   p = false;   
                }
            }
        }
        else if (objetivo == '-'){
            for (int j = 0; j < totalVar; j++){
                if (matriz[filaZ][j] > 0) {
                   p = false;   
                }
            }
        }
    return p;
    }
}
