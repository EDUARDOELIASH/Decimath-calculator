/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoModi;

//import javax.swing.JOptionPane;

public class IteracionesMODI {	 
    int[] u, v, filaEsquinaLazo, columnaEsquinaLazo, filaCeroRU, columnaCeroRU; //u y v almacenan el valor de los multiplicadores. 
    //filaEsquinaLazo y columnaEsquinaLazo guardan posicion de esquina del lazo del recorrido en la tabla
    boolean [][][] intentoCO; //intento celda Ocupada
    boolean[] uEsC, vEsC, uEsF, vEsF; //u es para costo marginal, u y v estan factibles (posibles lugares de asignación cuando los multiplicadores no estan completos)
    boolean[][] celOcupada; //Define que celdas estan ocupadas
    
    boolean ultimo, parar; //Se utiliza para saber si la celda inicial se ubica al ultimo (en recorrido del lazo)
    int cmenorV, fmenorV; //Celda inicial en recorrido del lazo
    int nCeldasOcupadas, nEpsilon, nIntento; //Se utilizan para el manejo de celdas ocupadas en casos especiales
    int nCeldasOC, nCeldaOC, nCeldasFactibles, nCeldaFactible; //nCeldasOC = numero de celdas ocupadas en la reasignacion de celdas con asignacion 0
    
    public IteracionesMODI(int nOrigenes, int nDestinos, int[][] matrizA) { //Constructor
    	parar = false;
    	nIntento = 0;
    	nCeldasOC = 0;
    	nCeldaOC = 0;
    	nCeldasFactibles = 0;
    	nCeldaFactible = 0;
    	nCeldasOcupadas = 0;
    	
    	//Incializa valor de variables necesarias
        u = new int[nOrigenes];
        v = new int[nDestinos];
        uEsC = new boolean[nOrigenes];
        vEsC = new boolean[nDestinos];
        uEsF = new boolean[nOrigenes];
        vEsF = new boolean[nDestinos];
        
        celOcupada = new boolean[nOrigenes][nDestinos];
        for (int i = 0; i < nOrigenes; i++){
            for (int j = 0; j < nDestinos; j++){
            	if ( (matrizA[i][j] != 0) ){ //&& variable que guarde nuevas celdas ocupadas
            		celOcupada[i][j] = true;
                    nCeldasOcupadas += 1;
                }
            	else {
            		celOcupada[i][j] = false;
            	}
            }
        }
    }
    
    private boolean celdaEsOcupada(int[][] matrizA, int fila, int columna){
        //Función que devuelve si celda esta ocupada por una variable básica
        boolean esOcupada = false;
        
        if ( (matrizA[fila][columna] != 0) || (celOcupada[fila][columna] == true) ){ //Variable que guarde nuevas celdas ocupadas
            esOcupada = true;
        }        
        
        return esOcupada;
    }
    
    private void inicializar_u_vEsC(int nOrigenes, int nDestinos){ 
        //Procedimiento que permite calcular u y v 
        for (int i = 0; i < nOrigenes; i++){
            if (i == 0){
                uEsC[i] = true;
            }
            else{  
                uEsC[i] = false;
            }
            u[i] = 0;                  
        }
        for (int j = 0; j < nDestinos; j++){
            v[j] = 0;               
            vEsC[j] = false;
        }
    }
    
    private void inicializar_u_vEsF(int nOrigenes, int nDestinos){ 
        //Procedimiento que permite calcular u y v 
        for (int i = 0; i < nOrigenes; i++){
            if (i == 0){
                uEsF[i] = true;
            }
            else{  
                uEsF[i] = false;
            }                
        }
        for (int j = 0; j < nDestinos; j++){           
            vEsF[j] = false;
        }
    }
    
    private void celdasOcupadas(int[][] matrizPC, int nOrigenes, int nDestinos, int[][] matrizA){    
        //Calcula el valor de u y v utilizando celdas ocupadas
        inicializar_u_vEsC(nOrigenes, nDestinos);
        boolean conValor = false; 
        int pasadaEnFila = 0, cambio = 1, k, regreso = 1;
        boolean regresar = false;
        //Función que cálcula el valor de ui y vj para celdas ocupadas
        	k = 0; // k es el valor de la fila y es dinámico, recorre las filas 2 veces de la primera a la ultima y repite el proceso de la ultima a la primera
            while ( ( ( ((conValor == false) || pasadaEnFila <= 2) ) || (regresar == true) ) && ( (k < nOrigenes) && (k >= 0) )){           	
                conValor = true; //Esta variable se mantiene asi cuando los valores de u y v son válidos
                if (pasadaEnFila > 1) { //Se hace esto por si no se logra resolver u y v recorriendo toda la fila, asi que se pasa a la sig. fila	
                	pasadaEnFila = 0;                               	
                	if ( (k == (nOrigenes - 1)) || (regresar == true)) {
                		k -= regreso;
                		regresar = true;
                	}
                	else if (regresar == false){
                		k += cambio;
                	}
                }
                else { //Se identifican las celdas ocupadas y se calculan sus multiplicadores u y v
                  for (int j = 0; j < nDestinos; j++){
                    if ( (celdaEsOcupada(matrizA, k, j) == true) && ( (uEsC[k] == false) && (vEsC[j] == false) ) ){//Se repite bucle
                        conValor = false;
                    }           
                    else if ( (celdaEsOcupada(matrizA, k, j) == true) && (uEsC[k] == false) ){//Se calcula valor de u[i]
                        calcularU(k, j, matrizPC); 
                        uEsC[k] = true;
                        //JOptionPane.showMessageDialog(null, "u"+k + u[k]); //prueba
                    }           
                    else if ( (celdaEsOcupada(matrizA, k, j) == true) && (vEsC[j] == false) ){//Se calcula valor de v[j]
                        calcularV(k, j, matrizPC);
                        vEsC[j] = true;
                        //JOptionPane.showMessageDialog(null, "v"+j + v[j]); //prueba
                    }
                  } 
                  pasadaEnFila += 1;
                  if ( ((pasadaEnFila == 2) && (k == (nOrigenes - 1))) && (conValor == true)) {
                  	regresar = true;
                  }
                }
                
           }              
    }
    
    private void calcularU(int fila, int columna, int[][] matrizPC){
    	//Calcula multiplicador u
        int cij = matrizPC[fila][columna];      
        u[fila] = cij - v[columna];
    }
    
    private void calcularV(int fila, int columna, int[][] matrizPC){
    	//Calcula multiplicador v
        int cij = matrizPC[fila][columna];
        v[columna] = cij - u[fila];
    }
    
    private int celdasVacias(int[][] matrizPC, int nOrigenes, int nDestinos, int[][] matrizA){
        //Función que devuelve la celda vacia con menor costo marginal
        int cmij = 0; //Costo marginal ij
        int menor = 0;
        int primerCelda = 0; 
        
        for (int i = 0; i < nOrigenes; i++){
            for (int j = 0; j < nDestinos; j++){
                if ( celdaEsOcupada(matrizA, i, j) == false ){
                    cmij = matrizPC[i][j] - u[i] - v[j];
                    if (primerCelda == 0){ //primer celda para poder comparar
                        primerCelda += 1;
                        menor = cmij;
                        fmenorV = i;
                        cmenorV = j;
                    }
                    else{
                        if ( cmij < menor ){
                            menor = cmij;
                            fmenorV = i; //Fila de celda con costo marginal menor
                            cmenorV = j; //Columna de celda con costo marginal menor
                        }
                    }
                }
            }    
        }        
        return menor;
    }
    
    private int recorridoLazo(int nOrigenes, int nDestinos, int matrizA[][]){
        //Procedimiento que define el recorrido del lazo para el método MODI
        filaEsquinaLazo = new int[nCeldasOcupadas + 1];
        columnaEsquinaLazo = new int[nCeldasOcupadas + 1];
        int nOcupaciones = 0;
        int[] k = new int[nCeldasOcupadas + 1];
        boolean[][] matrizAEV = new boolean[nOrigenes][nDestinos]; //Matriz asignación de esquina de lazo valida
        int fInicio = fmenorV, cInicio = cmenorV;
        
        for (int i = 0; i < nOrigenes; i++) {
        	for (int j = 0; j < nDestinos; j++) {
        		matrizAEV[i][j] = true;
        	}
        }
        
        int i = fInicio, j = cInicio, columnaActual = cInicio;
        boolean izq, arriba, recorridoFT = false, recorridoCT = false, encontrarSigEsquina = false, fin = false;
        filaEsquinaLazo[nOcupaciones] = fInicio;
        columnaEsquinaLazo[nOcupaciones] = cInicio;
        nOcupaciones += 1;
        do {       
        //while ( (i < nOrigenes) && (i >= 0) ){ //Recorre filas                     
            //Recorrido hacia izquierda o derecha en columnas de la matriz
            if ( j == (nDestinos - 1) ){
                izq = true;  //izq = true es recorrido hacia la izquierda
                recorridoCT = true;
                columnaActual = j;
                j-=1;
            }
            else if(j == 0){
                izq = false; //izq = false es recorrido hacia la derecha
                recorridoCT = true;
                columnaActual = j;
                j+=1;
            }
            else{
                izq = false;
                recorridoCT = false;
                columnaActual = j; //Columna de inicio
                j+=1;
            }
            filaEsquinaLazo[nOcupaciones] = i;
            encontrarSigEsquina = false;
            
            while ( (j < nDestinos) && (j >= 0) ){ //Recorre columnas
                
                //Celda actual esta ocupada
                if ( (celdaEsOcupada(matrizA, i, j) == true) && (matrizAEV[i][j] == true) ){
                    columnaEsquinaLazo[nOcupaciones] = j;  
                    encontrarSigEsquina = true;
                }            
                else{
                    matrizAEV[i][j] = false; //se puede optimizar
                }
              //Da reversa al recorrido en la columna
                if ( (j == (nDestinos - 1)) && (recorridoCT == false) ){
                    izq = true;
                    recorridoCT = true;
                    j = columnaActual;
                }
                if (izq == true){
                    j -= 1;
                }
                else{
                    j += 1;
                }
                
            }
            //System.out.println(nOcupaciones + " nO"); //Prueba
            if (encontrarSigEsquina == false) {
            	j = columnaActual;
                matrizAEV[i][j] = false;
                nOcupaciones -= 2;
                i = filaEsquinaLazo[nOcupaciones];             
                j = columnaEsquinaLazo[nOcupaciones];
                nOcupaciones += 1;	                                              
            }
            else {
            	j = columnaEsquinaLazo[nOcupaciones];           	
                nOcupaciones += 1;
            }
            //System.out.println(j); //prueba
            if( ((nOcupaciones > 2) && (j == cInicio)) && ( (filaEsquinaLazo[0] == fInicio) &&  (columnaEsquinaLazo[0] == cInicio) ) ) { //Identifica si celda inicial se encuentra al principio del lazo
            	fin = true; //Si la celda inicial esta al principio se deja de iterar
            	nOcupaciones -= 1; //Se regresa a su valor anterior en nOcupaciones
            	ultimo = false; //Dice que la celda inicial no se encuentra al final
            }
            else if ( ((nOcupaciones > 1) && (j == cInicio)) && ( (filaEsquinaLazo[0] != fInicio) &&  (columnaEsquinaLazo[0] != cInicio) ) ) { //Verifica si en la columna actual se puede acceder a la celda de inicio
            	columnaEsquinaLazo[nOcupaciones] = j; //columna de celda inicial
            	i = fInicio;
            	filaEsquinaLazo[nOcupaciones] = i; //Fila de celda inicial
            	fin = true; //Si la celda inicial esta al final se deja de iterar
            	ultimo = true; //Dice que la celda inicial se encuentra al final
            }
            else { //Si no se sigue con el proceso normal
            	//Recorrido hacia arriba o abajo en filas de la matriz
                if ( i == (nOrigenes - 1) ){
                    arriba = true; // arriba = true es recorrido hacia arriba en fila
                    recorridoFT = true; //Es que ya se recorrio toda la fila
                }
                else if (i == 0){
                    arriba = false; // arriba = false es recorrido hacia abajo
                    recorridoFT = true; //Es que ya se recorrio toda la fila
                }
                else{
                    arriba = false; //arriba = false es recorrido hacia abajo
                    recorridoFT = false;
                }
                
              k[nOcupaciones]  = i; //Se puede mejorar
              if (arriba == true){
                k[nOcupaciones] -= 1;               
              }
              else{
                k[nOcupaciones] += 1;
              } 
              encontrarSigEsquina = false; //Se reinicia a su valor por defecto
              columnaEsquinaLazo[nOcupaciones] = j; //Columna es la misma que se obtuvo en el anterior proceso
              
              while ( (k[nOcupaciones] < nOrigenes) && (k[nOcupaciones] >= 0) ){ //Recorre filas
                if ( (celdaEsOcupada(matrizA, (k[nOcupaciones]), j) == true) && (matrizAEV[k[nOcupaciones]][j] == true) ){ //Encuentra celdas ocupadas para construir el lazo 
                    filaEsquinaLazo[nOcupaciones] = k[nOcupaciones];   
                    encontrarSigEsquina = true;
                    //System.out.println("entro" + k[nOcupaciones]); //prueba
                }
                else{
                    matrizAEV[k[nOcupaciones]][j] = false;
                } 
                
                if ( (k[nOcupaciones] == (nOrigenes - 1) ) && (recorridoFT == false) ){ //Da reversa al recorrido de la fila
                    arriba = true;
                    recorridoFT = true;
                    k[nOcupaciones] = i;
                }

                if (arriba == true){
                    k[nOcupaciones] -= 1;               
                }
                else{
                    k[nOcupaciones] += 1;
                } 
                
                //Se recorrio hacia arriba y hacia abajo                    
              }
              if (encontrarSigEsquina == false) {
                matrizAEV[i][j] = false;
                nOcupaciones -= 2;
                j = columnaEsquinaLazo[nOcupaciones];
                i = filaEsquinaLazo[nOcupaciones];
                nOcupaciones += 1;                               
              }
              else {
            	i = filaEsquinaLazo[nOcupaciones];  
                nOcupaciones += 1;
              }
              //System.out.println("nOcu i"+ nOcupaciones +" "  +i); //prueba
            }
       // }
        }while( (fin == false) || (nOcupaciones == 0) );
                 
        return nOcupaciones;
    }
    
    private boolean esDegenerado(int nOrigenes, int nDestinos) {
    	//Funcion que devuelve si un problema esta degenerado
    	nEpsilon = 0;
    	boolean pDegenerado = false;
    	if (nCeldasOcupadas < (nOrigenes + nDestinos - 1)) {
    		nEpsilon = (nOrigenes + nDestinos - 1) - nCeldasOcupadas;
    		pDegenerado = true;
    	}
    	
    	return pDegenerado;
    }
    
    private void posicionesFactibles(int matrizA[][], int matrizPC[][], int nOrigenes, int nDestinos) {
    	//if ( esPDegenerado == true ) {
    	    inicializar_u_vEsF(nOrigenes, nDestinos); //inicializar valores booleanos de se puede calcular fila o no
    	    int pasadaEnFila = 0, cambio = 1, k, regreso = 1;
    	    boolean regresar = false;    		        
            //Función que identifica celdas factibles para asignar celdas ocupadas  		        
    		k = 0;
    	    while (  ( (regresar == true) || (pasadaEnFila <= 2) ) && ( (k < nOrigenes) && (k >= 0) ) ){           	
    		    if (pasadaEnFila > 1) { //Se hace esto por si no se logra resolver u y v recorriendo toda la fila, asi que se pasa a la sig. fila	
    		    	pasadaEnFila = 0;                               	
    		        if ( (k == (nOrigenes - 1)) || (regresar == true)) {
    		            k -= regreso;
    		            regresar = true;
    		        }
    		        else if (regresar == false){
    		            k += cambio;
    		        }
    		    }
    		    else { //Se identifican las celdas ocupadas y se calculan sus multiplicadores u y v
    		        for (int j = 0; j < nDestinos; j++) {  
    		        	if ( ( celdaEsOcupada(matrizA, k, j) == true ) && ( (uEsF[k] == false) && (vEsF[j] == false) ) ) {
    		        		
    		        	}
    		        	else if ( (celdaEsOcupada(matrizA, k, j) == true) && (uEsF[k] == false) ){//Se calcula valor de u[i]
    		                uEsF[k] = true; 
    		                //JOptionPane.showMessageDialog(null, "u"+k + " " + uEsF[k] ); //Prueba
    		            }           
    		            else if ( (celdaEsOcupada(matrizA, k, j) == true) && (vEsF[j] == false) ){//Se calcula valor de v[j]
    		                vEsF[j] = true;
    		                //JOptionPane.showMessageDialog(null, "v"+j + " " + vEsF[j] ); //prueba
    		             }    		                  
    		         }
    		        pasadaEnFila += 1;
		             if ( ( (pasadaEnFila == 2) && (k == (nOrigenes - 1)) ) ) {
		                 regresar = true;
		             }
    		     }   		                
    	     } //Fin del ciclo while

    	/*}
    	else {
    				
        }*/
    }
    
    private void inicializarI(int nOrigenes, int nDestinos, boolean esPDegenerado, int[][] matrizA) {
    	//Procedimiento con el que se inicializan variables de para reasignar asignaciones igual a 0 a celdas vacias
    	if (esPDegenerado == true) { //Problema esta degenerado
    		nCeldasOC = nEpsilon;
    		nCeldaOC = 0;
    	    intentoCO = new boolean[nCeldasOC][nOrigenes][nDestinos];
    	    filaCeroRU = new int[nCeldasOC];
    	    columnaCeroRU = new int[nCeldasOC];
    	    for (int h = 0; h < nCeldasOC; h++) {
    	        for (int i = 0; i < nOrigenes; i++) {
    		        for (int j = 0; j < nDestinos; j++) {
    			        intentoCO[h][i][j] = false;
    		        }
    	        }
    	    }
    	    
    	    int h = 0;
    	    for (int i = 0; i < nOrigenes; i++) {
    		    for (int j = 0; j < nDestinos; j++) {
    		        if ( (celOcupada[i][j] == true) && (matrizA[i][j] == 0) && (h < nCeldasOC) ) { //Numero de celdas ocupadas con asignacion 0
    		        	columnaCeroRU[h] = j;
        			    filaCeroRU[h] = i;
        			    intentoCO[h][i][j] = true;
        			    h++;
        			}
    		    }
    	    }
    	}
    	else { //Problema no esta degenerado
    		nCeldasOC = 0;
    		nCeldaOC = 0;
    		for (int i = 0; i < nOrigenes; i++) { //Calcula numero de celdas ocupadas con asignacion 0
    		    for (int j = 0; j < nDestinos; j++) {
    			    if ( (celOcupada[i][j] == true) && (matrizA[i][j] == 0) ) { //Numero de celdas ocupadas con asignacion 0
    			    	nCeldasOC += 1;
    			    }
    		    }
    	    }
    		intentoCO = new boolean[nCeldasOC][nOrigenes][nDestinos];
    		filaCeroRU = new int[nCeldasOC];
    		columnaCeroRU = new int[nCeldasOC];
    		for (int h = 0; h < nCeldasOC; h++) {
    	        for (int i = 0; i < nOrigenes; i++) {
    		        for (int j = 0; j < nDestinos; j++) {
    			        intentoCO[h][i][j] = false;
    		        }
    	        }
    	    }
    		
    	    int h = 0;
    	    for (int i = 0; i < nOrigenes; i++) {
    		    for (int j = 0; j < nDestinos; j++) {
    		        if ( (celOcupada[i][j] == true) && (matrizA[i][j] == 0) && (h < nCeldasOC) ) { //Numero de celdas ocupadas con asignacion 0
    		        	columnaCeroRU[h] = j;
        			    filaCeroRU[h] = i;
        			    intentoCO[h][i][j] = true;
        			    h++;
        			}
    		    }
    	    }
    	}
    }
    
    private void reasignarCeldaOcupada(int[][] matrizA, int[][] matrizPC ,int nOrigenes, int nDestinos, boolean esPDegenerado) {
    	//Reasigna posicion de celda cuando se encuentra en un lugar desfavorable
    	int nQuitado = 1;
    	for (int i = 0; i < nOrigenes; i++) {
    		for (int j = 0; j < nDestinos; j++) {
    			if ( (i == filaCeroRU[nCeldaOC]) && (j == columnaCeroRU[nCeldaOC]) && (nQuitado != 0) ) {  
    				 if ((nIntento == 0) || (esPDegenerado == true)) { //Calcula cuales celdas son factibles para ser ocupadas por una asignacion igual a 0
     		            
                     }
    				nCeldasOcupadas -= 1;
    				celOcupada[i][j] = false;
    				matrizA[i][j] = 0;
                    if ((nIntento == 0) || (esPDegenerado == true)) {
                    	intentoCO[nCeldaOC][i][j] = true;
                    	posicionesFactibles(matrizA, matrizPC, nOrigenes, nDestinos);
                    	nCeldasFactibles = 0; //Cantidad de celdas factibles (para ser ocupadas por los valores 0)
     		    	    for (int k = 0; k < nOrigenes; k++) {
     		    	    	for (int l = 0; l < nDestinos; l++) {
     		    	    		/*int uC = 0;
     		    	    		for (int m = 0; m < nOrigenes; m++) {
     		        				if (celOcupada[m][l] == true) { //Recorre la columna
     		        					uC += 1;
     		        				}
     		        			}*/
     		        			//if (uC < (nDestinos - 1)) {
     		    	    		    if ( ( ((uEsF[k] == true) && (vEsF[l] == false)) || ((uEsF[k] == false ) && (vEsF[l] == true)) ) && (matrizA[k][l] == 0) && (celOcupada[k][l] == false)) {
     		    	    			    nCeldasFactibles += 1;
     		    	    		    }
     		    	    		//}
     		    	    	}
     		    	    }
     		    	    nCeldaFactible = 0; //numero de celda factible (posicion)
     		    	    nCeldasFactibles -= 1;
                    }
    				nQuitado -= 1;   			
    				//JOptionPane.showMessageDialog(null, "rCceldaDO " + i + " " + j); //prueba
    			}   			
    		}
    	}  	
    	nQuitado = 1;
    	
    	for (int i = 0; i < nOrigenes; i++){
    		for (int j = 0; j < nDestinos; j++) {
    			/*int uC = 0; //Ubicaciones columna
    			for (int k = 0; k < nOrigenes; k++) {
    				if (celOcupada[k][j] == true) { //Recorre la columna
    					uC += 1;
    				}
    			}*/
    			//if(uC < (nDestinos - 1)) {
    				if ( ( ( (uEsF[i] == true) && (vEsF[j] == false) ) || ( (uEsF[i] == false ) && (vEsF[j] == true) ) ) && ( (nQuitado != 0) && (celOcupada[i][j] == false) && (intentoCO[nCeldaOC][i][j] == false) ) ) {
    			        nQuitado -= 1;
    			        nCeldasOcupadas += 1;
    			        matrizA[i][j] = 0;
    			        celOcupada[i][j] = true;
        				intentoCO[nCeldaOC][i][j] = true;
        				filaCeroRU[nCeldaOC] = i;
        				columnaCeroRU[nCeldaOC] = j;
        				//JOptionPane.showMessageDialog(null, "rCceldaO " + i + " " + j); //prueba de escritorio
    		        }
    			//}
    		}
    	}
    	if (nCeldaFactible == nCeldasFactibles) {
    		nCeldaFactible = 0;
    		nCeldaOC += 1;    		
    	}
    	else {
    		nCeldaFactible++;
    	}
    	
    }

    private void asignarCeldaOcupada(int[][] matrizA, int nOrigenes, int nDestinos) {
    	int h = 0;
    	for (int i = 0; i < nOrigenes; i++) {
    		for (int j = 0; j < nDestinos; j++) {
    			/*int uC = 0;
	    		for (int m = 0; m < nOrigenes; m++) {
    				if (celOcupada[m][j] == true) { //Recorre la columna
    					uC += 1;
    				}
    			}*/
    			//if(uC < (nDestinos - 1)) {
    			if ( ( ( ((uEsF[i] == true) && (vEsF[j] == false)) || ((uEsF[i] == false ) && (vEsF[j] == true)) ) && (nEpsilon != 0) ) && (celOcupada[i][j] == false) ) {
    				nEpsilon -= 1;
    				nCeldasOcupadas += 1;
    				matrizA[i][j] = 0;
    				celOcupada[i][j] = true;
    				
    				intentoCO[h][i][j] = true; //celdas ocupadas con asignacion 0
    				columnaCeroRU[h] = j;
    			    filaCeroRU[h] = i;
    			    h++;       			  
    				//JOptionPane.showMessageDialog(null, "cO" + i + " " + j); //Prueba
    			}
    			//}
    		}
    	}   
    }
    
    private int ubicacionCeldaI (int nCeldasOcupadasR, int[][] matrizA, char[] simbolo){                    
            boolean m = false; //Almacena si menor tiene asignación 
            int menor = 0;
            
            for (int i = 0; i <= nCeldasOcupadasR; i++) {
                if (ultimo == true) { //Celda inicial se encuentra al final
                    if ( ((i % 2) == 0) || (i == 0) ){ //Simbolo de operacion
                        simbolo[i] = '-';
                    }
                    else {
                        simbolo[i] = '+';           	
                    }
                }
                else { //Celda inicial se encuentra al principio
                    if ( ( (i % 2) == 0) || (i == 0) ){
            	        simbolo[i] = '+';
                    }
                    else {
            	        simbolo[i] = '-';           	
                    }
                }
                if (simbolo[i] == '-') { //Se calcula el valor minimo que se sumara y restara
                    if ( m == false ) { //Hace la primera asignacion
                            menor = matrizA[filaEsquinaLazo[i]][columnaEsquinaLazo[i]];
                            m = true;
                    }
                    else { //Hace demas comparaciones
                        if ( matrizA[filaEsquinaLazo[i]][columnaEsquinaLazo[i]] < menor ) {
                            menor = matrizA[filaEsquinaLazo[i]][columnaEsquinaLazo[i]];
                            //System.out.println(menor + " " + filaEsquinaLazo[i] + " " + columnaEsquinaLazo[i]); //prueba
                        }
                    }
                }               
            }
            return menor;
    }
    
    public void operacionesLazo(int[][] matrizPC, int nOrigenes, int nDestinos, int[][] matrizA){
    	boolean esPDegenerado = esDegenerado(nOrigenes, nDestinos);
    	if ( esPDegenerado == true ) { //Enviar epsilon  		
            inicializarI(nOrigenes, nDestinos, esPDegenerado, matrizA);
            posicionesFactibles(matrizA, matrizPC, nOrigenes, nDestinos);
            asignarCeldaOcupada(matrizA, nOrigenes, nDestinos);
            nIntento += 1;
    	}
        celdasOcupadas(matrizPC, nOrigenes, nDestinos, matrizA);
        int costoMmenor = celdasVacias(matrizPC, nOrigenes, nDestinos, matrizA);
        //System.out.println(costoMmenor); //prueba
        if ( costoMmenor < 0 ) {
            parar = false;
            /*for (int i = 0; i < nOrigenes; i++) {
                for (int j = 0; j< nDestinos; j++) {
                    if (celOcupada[i][j] == true) {
                    //System.out.println("CelOcupada " + i + " "+j); //prueba
                    }
                }
            }*/
            int nCeldasOcupadasR = recorridoLazo(nOrigenes, nDestinos, matrizA);
            char[] simbolo = new char[nCeldasOcupadasR + 1]; 
            int menor = ubicacionCeldaI(nCeldasOcupadasR, matrizA, simbolo);
        
            //Se realizan operaciones para reasignar valores
            for (int i = 0; i <= nCeldasOcupadasR; i++) {
            	if (menor != 0) { 
                //Si simbolo menor es diferente a 0 entonces el lazo sufre modificacion           		
                    nIntento = 0;
                    if (simbolo[i] == '+') { //Se hacen modificaciones a esquinas del lazo
                        matrizA[filaEsquinaLazo[i]][columnaEsquinaLazo[i]] += menor;
                    }
                    else {
                        matrizA[filaEsquinaLazo[i]][columnaEsquinaLazo[i]] -= menor;
                    }
                    if (celOcupada[filaEsquinaLazo[i]][columnaEsquinaLazo[i]] == false) { 
                        //Celda desocupada se vuelve ocupada
                        celOcupada[filaEsquinaLazo[i]][columnaEsquinaLazo[i]] = true;
                        nCeldasOcupadas += 1;	
                    }
            	}
            }
            //Se desocupan celdas que no se necesitan
            if ( (nOrigenes + nDestinos - 1) < nCeldasOcupadas ) { //Se realiza un ajuste
                int nAjustes = (nCeldasOcupadas - (nOrigenes + nDestinos - 1)); //epsilon no
                int c = 0;
                while ((c <= nCeldasOcupadasR) && (nAjustes != 0)) {// aqui se haran ajustes      	    	
                    if ( (matrizA[filaEsquinaLazo[c]][columnaEsquinaLazo[c]] == 0) 
                    && (celOcupada[filaEsquinaLazo[c]][columnaEsquinaLazo[c]] == true) ) {
                        celOcupada[filaEsquinaLazo[c]][columnaEsquinaLazo[c]] = false;
                        //JOptionPane.showMessageDialog(null, "Ajuste: " + nAjustes + " " + filaEsquinaLazo[c] + " "+columnaEsquinaLazo[c]); //prueba
                        nAjustes -= 1;
                        nCeldasOcupadas -=1;      	    	    
                    }
                    c++;    	    
                }
                if (menor == 0) { //menor == 0 lazo no sufre modificacion
                    if (nIntento == 0) {
                        inicializarI(nOrigenes, nDestinos, false, matrizA);    		    	    
                    }
                    reasignarCeldaOcupada(matrizA, matrizPC, nOrigenes, nDestinos, esPDegenerado); 
                    //Celda ocupada == 0 se reasigna
                    nIntento += 1;
            	}            
            } 
            else { //Si nCeldasOcupadas == (nOrigenes + nDestinos - 1) 
                if (menor == 0) { //menor == 0 lazo no sufre modificacion
                    if (nIntento == 0) {
                        inicializarI(nOrigenes, nDestinos, false, matrizA);    		    	    
                    }
                    reasignarCeldaOcupada(matrizA, matrizPC, nOrigenes, nDestinos, esPDegenerado); 
                    //Celda ocupada == 0 se reasigna
                    nIntento += 1;
            	}
            }       
        }
        else { //Fin de iteraciones
        	parar = true;
        }
    }    
}
