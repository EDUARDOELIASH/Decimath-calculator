/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodohungaro;

import javax.swing.JOptionPane;

public class IteracionesHungaro {
    boolean primerPaso;
    int[] nFila, nColumna;
    boolean[][] esSombreado;
    int menorC;        
    int[] f_or_c; //filas y columnas sombreadas
    int[] celdas;

    public IteracionesHungaro() {
        primerPaso = false;
        menorC = 0;
    }
    
	
     private int menorFila(int fila, int nDestinos, int[][] matrizC) {
    	 //Función que calcula valor menor de la fila
    	 int menor = 0;
    	 for (int j = 0; j<nDestinos; j++) {
    		 if (j == 0) {
    			 menor = matrizC[fila][j];   			 
    		 }   	
    		 else {
    			 if (matrizC[fila][j] < menor) {
    				 menor = matrizC[fila][j];
    			 }
    		 }
    	 }    	 
    	 return menor;
     }
     
     private int menorColumna(int columna, int nOrigenes, int[][] matrizC) {
    	 //Funcion que calcula valor de menor valor en la columna
    	 int menor = 0;
    	 for (int i = 0; i < nOrigenes; i++) {
             if (i == 0) {
                     menor = matrizC[i][columna];
             }
             else {
                     if (matrizC[i][columna] < menor) {
                            menor = matrizC[i][columna]; 
                     }
             }
    	 }
    	 
    	 return menor;
     }
     
     private int[][] balancearTabla(int nOrigenes, int nDestinos, int[][] matrizC) {
    	 //Resta valores menores a filas y columnas
    	 int menor = 0;
    	 for (int i = 0; i < nOrigenes; i++) {   		 
    		 menor = menorFila(i, nDestinos, matrizC);   
    		 for (int j = 0; j < nDestinos; j++) {
    			 matrizC[i][j] -= menor;
    		 }
    	 }
    	 for (int j = 0; j < nDestinos; j++) {
			 menor = menorColumna(j, nOrigenes, matrizC);
			 for (int i = 0; i < nOrigenes; i++) {
				 matrizC[i][j] -= menor;
			 }
		 }
    	 return matrizC;
     }
     
     private int[] nCerosC(int[][] matrizC, int[] nCerosEnC, int nOrigenes, int nDestinos) {
    	for (int j = 0; j < nOrigenes; j++) { //Calcula numero de ceros en fila
            nCerosEnC[j] = 0;   			
    	}
        
         for (int j = 0; j < nDestinos; j ++) { //Calcula numero de ceros en columna
    		 for (int i = 0; i < nOrigenes; i++) {
    			 if (matrizC[i][j] == 0) {
    				 nCerosEnC[j] += 1;
    			 }
    		 }
    		 //System.out.println("c " + nCerosEnC[j]);
    	 }
    	 return nCerosEnC;
     }
     
     private int[] nCerosF(int[][] matrizC, int[] nCerosEnF, int nOrigenes, int nDestinos) {
    	 for (int i = 0; i < nOrigenes; i++) { //Calcula numero de ceros en fila
            nCerosEnF[i] = 0;   			  		 
    	 }
         
         for (int i = 0; i < nOrigenes; i++) { //Calcula numero de ceros en fila
    		 for (int j = 0; j < nDestinos; j++) {
    			if (matrizC[i][j] == 0) {
    			    nCerosEnF[i] += 1;
    			}
    		 }
    		 //System.out.println("f " + nCerosEnF[i]);
    	 }
    	 return nCerosEnF;
     }        
     
     private int nCerosC(int[][] matrizC, int nOrigenes, int j) {
         //La diferencia al otro nCerosC es que no asigna el numero de ceros a un arreglo, se hace a una  variable local
         int nCerosEnC = 0;   
            for (int i = 0; i < nOrigenes; i++) {
                if (matrizC[i][j] == 0) {
                    nCerosEnC += 1;
                }
            }
             //System.out.println("c " + nCerosEnC[j]);
    	 return nCerosEnC;
     }
     
     private int nCerosF(int[][] matrizC, int nDestinos, int i) {
        //La diferencia al otro nCerosF es que no asigna el numero de ceros a un arreglo, se hace a una  variable local
        int nCerosEnF = 0;    	
        for (int j = 0; j < nDestinos; j++) {
            if (matrizC[i][j] == 0) {
                nCerosEnF += 1;
            }
        }
        //System.out.println("f " + nCerosEnF[i]);
        return nCerosEnF;
     }
     
    private int menorCeroColumnas (int[] nCerosEnF, int nOrigenes){
        int menorC1 = 0;
        boolean primerN = false;
        for (int i = 0; i < nOrigenes; i++){ //ceros de filas
            if ((primerN == false) && (nCerosEnF[i] != 0)){
                menorC1 = nCerosEnF[i];
                primerN = true;
            }
            else if (nCerosEnF[i] != 0){
                if (nCerosEnF[i] < menorC1){
                    menorC1 = nCerosEnF[i];
                }
            }
        }
        return menorC1;
    }
    
    private int menorCeroFilas(int[] nCerosEnC, int nDestinos, int menorC){
        for (int j = 0; j < nDestinos; j++){
            if ((nCerosEnC[j] < menorC) && (nCerosEnC[j] != 0)){
                menorC = nCerosEnC[j];
            }
        }
        
        return menorC;
    }
    
    private int sombrearFila (int k, int f, int nDestinos, int nOrigenes, int cerosCubiertos, int[] nCerosEnC, int[] nCerosEnF, int[][] matrizC){
        f_or_c[k] = 1;
        celdas[k] = nFila[f];
        if(cerosCubiertos != 0) {
            cerosCubiertos += nCerosEnF[f];
        }
        else {
            cerosCubiertos += nCerosEnF[f];
        }
        for (int j = 0; j < nDestinos; j++) {
            if ((matrizC[nFila[f]][j] == 0) && (esSombreado[nFila[f]][j] == false)) {
                    int l = 0;
                    boolean encontrar = false;
                while ((l < nDestinos) && (encontrar == false)) { //Esto se hace para compensar el dinamismo del sombreo de filas
                    if (nColumna[l] == j) { //se encuentra columna la cual tiene una celda con valor 0 e intersecta con fila sombreada
                        nCerosEnC[l] -= 1;
                        encontrar = true;
                    }
                    l++;
                }
                 ordenarCColumna(nOrigenes, nDestinos, nCerosEnC);
            }
            esSombreado[nFila[f]][j] = true;
            
        }
        System.out.println("Fila " + nFila[f] + " ha sido sombreada");        				 
        nCerosEnF[f] -= nCerosEnF[f];
        ordenarCFila(nOrigenes, nOrigenes, nCerosEnF);
        return cerosCubiertos;        
    }
    
    private int sombrearColumna (int k, int c, int nDestinos, int nOrigenes, int cerosCubiertos, int[] nCerosEnC, int[] nCerosEnF, int[][] matrizC){
        f_or_c[k] = 2;
        celdas[k] = nColumna[c];
        if(cerosCubiertos != 0) {
            cerosCubiertos += nCerosEnC[c];
        }
        else {
            cerosCubiertos += nCerosEnC[c];
        }
        for (int i = 0; i < nOrigenes; i++) {
            if ((matrizC[i][nColumna[c]] == 0) && (esSombreado[i][nColumna[c]] == false)) {
                int l = 0;
                boolean encontrar = false;
                while ((l < nOrigenes) && (encontrar == false)) { //Esto se hace para compensar el dinamismo del sombreo de filas
                    if (nFila[l] == i) {
                        nCerosEnF[l] -= 1;
                        encontrar = true;
                    }
                    l++;
                }
                ordenarCFila(nOrigenes, nOrigenes, nCerosEnF);     			
            }   
            esSombreado[i][nColumna[c]] = true;
            
        }
        System.out.println("Columna " + nColumna[c] + " ha sido sombreada");
        nCerosEnC[c] -= nCerosEnC[c];
        ordenarCColumna(nOrigenes, nDestinos, nCerosEnC);
        
        return cerosCubiertos;
    }
     
     private int seleccionFyC(int nOrigenes, int nDestinos,  int[] f_or_c, int[] nCerosEnC, int[] nCerosEnF, int TotalCeros, int[][] matrizC, int[] celdas, int nFCSombreadas) {
    	 //Se utiliza una pila que se reordena para saber que filas y columnas conviene sombrear
    	int f = (nOrigenes - 1), c = (nDestinos - 1);
    	int cerosCubiertos = 0;
    	esSombreado = new boolean[nOrigenes][nDestinos];    	 
    	//nOrigenes, nDestinos, f_or_c, nCerosEnC, nCerosEnF, totalC, matrizC, celdas
        
        menorC = menorCeroColumnas(nCerosEnF, nOrigenes);
        menorC = menorCeroFilas(nCerosEnC, nDestinos, menorC);       
        
    	 for (int k = 0; k < nOrigenes; k++) { //Se ve donde hay mas ceros, en fila o columa y se seleccionan todos los 0  		 
            if ((nCerosEnF[f] >= nCerosEnC[c]) && (cerosCubiertos < TotalCeros)) { //Hay mas ceros en fila
                cerosCubiertos = sombrearFila(k, f, nDestinos, nOrigenes, cerosCubiertos, nCerosEnC, nCerosEnF, matrizC);
                nFCSombreadas += 1;
                System.out.println("f " + cerosCubiertos + " of " + TotalCeros);
            }
            else if ((nCerosEnC[c] > nCerosEnF[f]) && (cerosCubiertos < TotalCeros)){
                cerosCubiertos = sombrearColumna(k, c, nDestinos, nOrigenes, cerosCubiertos, nCerosEnC, nCerosEnF, matrizC);
                System.out.println("c " + cerosCubiertos + " of " + TotalCeros);   
                nFCSombreadas += 1; 
    	    }
            else {
                f_or_c[k] = 3;
            }
    		 /*else {
    			 if ((f!= 0)) {
    				 f--;
    				 f_or_c[k] = 3;    			 
    		 }}*/
    	 }    	 
    	 return nFCSombreadas;
     }
     
     private int totalCeros(int[][] matrizC, int nOrigenes, int nDestinos) {
    	 int totalCeRos = 0;
    	 for (int i = 0; i < nDestinos; i ++) { //Calcula numero de ceros en columna
    		 for (int j = 0; j < nOrigenes; j++) {
    			 if (matrizC[i][j] == 0) {
    				 totalCeRos += 1;
    			 }
    		 }
    	 }
    	 return totalCeRos;
     }
     
     private void ordenarCFila(int nOrigenes, int nDestinos, int[] nCerosEnF) {
         //Ordena de menor a mayor el numero de ceros no sombreados en las filas
    	 for(int i=0;i<nOrigenes;i++){
             for(int j=i+1;j<nOrigenes;j++){
                 if(nCerosEnF[i]>nCerosEnF[j]){
                     //Intercambiamos valores
                     int variableauxiliar=nCerosEnF[i];
                     int varAuxF = nFila[i];
                     nCerosEnF[i]=nCerosEnF[j];
                     nCerosEnF[j]=variableauxiliar;
                     nFila[i] = nFila[j];
                     nFila[j] = varAuxF;
                 }
             }            
         }
    	 for (int i = 0; i<nOrigenes; i++) {
        	// System.out.println(nCerosEnF[i] + " " + nFila[i]);
         }
     }
     
     private void ordenarCColumna(int nOrigenes, int nDestinos, int[] nCerosEnC) {
        //Ordena de menor a mayor el numero de ceros sin sombrear en las columnas 
    	for(int j=0;j<nDestinos;j++){
            for(int i=j+1;i<nOrigenes;i++){
                 if(nCerosEnC[j]>nCerosEnC[i]){
                     //Intercambiamos valores
                     int variableauxiliar=nCerosEnC[j];
                     int varAuxC = nColumna[j];
                     nCerosEnC[j]=nCerosEnC[i];
                     nCerosEnC[i]=variableauxiliar;
                     nColumna[j] = nColumna[i];
                     nColumna[i] = varAuxC;                 
                 }
             }
         }
    	 for (int j = 0; j<nOrigenes; j++) {
        	// System.out.println(nCerosEnC[j] + " " + nColumna[j]);
         }
     }
     
     private void ordenarmaM(int nOrigenes, int nDestinos, int[] nCerosEnF, int[] nCerosEnC) {
    	nFila = new int[nOrigenes];
    	nColumna = new int[nDestinos];
    	//ordenar de menor a mayor numero de ceros en fila
    	for (int i = 0; i<nOrigenes; i++) {
            nFila[i] = i;
    	    //System.out.println(nCerosEnF[i] + " " + nFila[i]);
    	    // System.out.println();
    	}  	 
        ordenarCFila(nOrigenes, nDestinos, nCerosEnF);
         
        for (int i = 0; i<nOrigenes; i++) {
            // System.out.println(nCerosEnF[i] + " " + nFila[i]);
        }
         
        System.out.println();
        //Ordenar numero de ceros en columna de menor a mayor
        for (int j = 0; j < nDestinos; j++) {
            nColumna[j]= j;
            // System.out.println(nCerosEnC[j] + " " + nColumna[j]);
            // System.out.println();
        }
         
        ordenarCColumna(nOrigenes, nDestinos, nCerosEnC);
         
        for (int j = 0; j<nOrigenes; j++) {
            // System.out.println(nCerosEnC[j] + " " + nColumna[j]);
        }
         
     }
     
    private int nFCSombreadas (int nOrigenes, int nDestinos, int[][] matrizC){
        //1 fila, 2 columna, 3 no se toma en cuenta
        int nFCSombreadas = 0; 
    	f_or_c = new int[nOrigenes];
    	int[] nCerosEnC = new int[nDestinos];
    	int[] nCerosEnF = new int[nOrigenes];
    	celdas = new int[nOrigenes];
    	 
    	int totalC = totalCeros(matrizC, nOrigenes, nDestinos); //Calcula los ceros de toda la matriz
    	nCerosEnC = nCerosC(matrizC, nCerosEnC, nOrigenes, nDestinos); //Calcula numero de ceros en columna
    	nCerosEnF = nCerosF(matrizC, nCerosEnF, nOrigenes, nDestinos); //Calcula numero de ceros en fila
    	ordenarmaM(nOrigenes, nDestinos, nCerosEnF, nCerosEnC);
    	nFCSombreadas = seleccionFyC(nOrigenes, nDestinos, f_or_c, nCerosEnC, nCerosEnF, totalC, matrizC, celdas, nFCSombreadas);
        
        return nFCSombreadas;
    }
     
    private int[][] casoEspecial(int nOrigenes, int nDestinos, int[][] matrizC) { 
    //Funcion que hace la iteracion especial, donde se sombrea menor cantidad de filas y columnas        
    	 int menor = 0;
    	 boolean primenor = false; //primer numero menor
    	 for (int k = 0; k<nOrigenes; k++) { //Se calcula numero menor de los no sombreados
    		  for (int j = 0; j<nDestinos; j++) {
    			  if (esSombreado[k][j] == false) {
        		      if (primenor == false) {
        		          primenor = true;
        		    	  menor = matrizC[k][j];
        		      }       		    	 
        		      else if (matrizC[k][j] < menor) {
        		          menor = matrizC[k][j]; 
        			  }
        		  }
    		       //}   		     
    		 }
    	 }
    	//System.out.println("menor " + menor);
    	 for (int i = 0; i<nOrigenes; i++) { //Se resta menor a no sombreados y se suma a intersecciones
    		if (f_or_c[i] == 1) { //fila sombreadas   			 
    		    for (int j = 0; j<nDestinos; j++) {
    			if (f_or_c[j] == 2) { //columnas sombreadas
        		    matrizC[celdas[i]][celdas[j]] += menor;
        		}
    		    }
    		}
    		
                for (int j = 0; j<nDestinos; j++) {
                    if (esSombreado[i][j] == false) { //columnas factibles
                        matrizC[i][j] -= menor;
                    }
                }   		 
    	 }
    	 //JOptionPane.showMessageDialog(null, " entro a caso especial");
    	 return matrizC;
     }
             
     public boolean diagonalP(int nOrigenes, int nDestinos, int[][] matrizC, int[] filaC, int[] columnaC, int[][] costos, String[] destino) {
    	 //Identifica si tabla de parametros tiene diagonal principal o no
    	boolean tieneD = false;
        int nFCSombreadas = 0;
        nFCSombreadas = nFCSombreadas(nOrigenes, nDestinos, matrizC); //numero de celdas sombreadas
        
        if (nFCSombreadas == nOrigenes){            
            //else{
              tieneD = true;
              //boolean todosE = false;
              boolean[] esFilaA = new boolean[nOrigenes];
              boolean[] esColumnaA = new boolean[nOrigenes];
              boolean[][] cero = new boolean[nOrigenes][nDestinos];
              int[] nCerosEnC = new int[nDestinos];
              int[] nCerosEnF = new int[nOrigenes];
            
              int d = 0; //Numero de elemento diagonal
              //System.out.println("menorC " + menorC);
              int em = 0;
              while (d < nOrigenes && em != nOrigenes){ 
                em++;  
                for (int i = 0; i<nOrigenes; i++){
                    for (int j = 0; j<nDestinos; j++){
                        if (d < nOrigenes){
                        if ( ((nCerosC(matrizC, nOrigenes, j) == menorC) || (nCerosF(matrizC, nDestinos, i) == menorC)) && (matrizC[i][j] == 0) && ( ((esFilaA[i] == false) && (esColumnaA[j] == false)) ) && (cero[i][j] == false) ){
                            cero[i][j] = true;
                            filaC[d] = i;
                            columnaC[d] = j;
                            esFilaA[i] = true;
                            esColumnaA[j] = true;
                            d++;
                            matrizC[i][j] -= 1;
                            //System.out.println(d);
                            //se asigna valor
                        }
                        if ( ((esFilaA[i] == true) || (esColumnaA[j] == true)) && (matrizC[i][j] == 0) && (cero[i][j] == false) && ((nCerosC(matrizC, nOrigenes, j) != menorC) || (nCerosF(matrizC, nDestinos, i) != menorC)) ){
                            matrizC[i][j] -= 2;
                            cero[i][j] = true;              
                            //celda toma valor diferente a 0 para balancear la tabla                           
                        }
                        }
                        /*for (int k = 0; k<nOrigenes; k++){
                            for (int l = 0; l<nDestinos; l++){
                                System.out.print(matrizC[k][l] + " ");
                            }
                            System.out.println();
                        }                       
                        JOptionPane.showMessageDialog(null, "entro");  */                    
                    } 
                    nCerosEnC = nCerosC(matrizC, nCerosEnC, nOrigenes, nDestinos);
                    nCerosEnF = nCerosF(matrizC, nCerosEnF, nOrigenes, nDestinos);
                    menorC = menorCeroColumnas(nCerosEnF, nOrigenes);  
                    menorC = menorCeroFilas(nCerosEnC, nDestinos, menorC);
                    //System.out.println(menorC);              
                }
                
              }
              if (em == nOrigenes){
                  tieneD = false;
              }
            }
        
        //}     
    	 return tieneD;
     }
     
    public int[][] AsignacionDeT(int nOrigenes, int nDestinos, int[][] matrizC) {
    	//Metodo que hace asignaciones a tabla de parametros
    	if (primerPaso == false) {
    	    matrizC = balancearTabla(nOrigenes, nDestinos, matrizC);
    	    primerPaso = true;
    	    
    	}
    	else {
            matrizC = casoEspecial(nOrigenes, nDestinos, matrizC);
    	}
    	return matrizC; 	 
    }    
}
