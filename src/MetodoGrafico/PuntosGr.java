/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoGrafico;

public class PuntosGr {
	//Datos obtenidos con metodos de esta clase
	public int[] x, y, xA, yA;
	//Datos obtenidos por medio del constructor
	public boolean [] xNegativo, yNegativo;
	int nrest;
	int[] restriccionX, restriccionY;
	int[] limiteR;
	String[] simbolo;
	double vardecision[];
	
	int xPDF, yPDF;
	
	public PuntosGr(int nrest, int[] restriccionX, int[] restriccionY, int[] limiteR, String[] simbolo, int xPDF, int yPDF, double[] vardecision) {
            this.nrest = nrest;
            this.restriccionX = restriccionX;
            this.restriccionY = restriccionY;
            this.limiteR = limiteR;
            this.simbolo = simbolo;
            this.xPDF = xPDF;
            this.yPDF = yPDF;
            this.vardecision = vardecision;
            P = null;
	}
	
	public void puntosFactibles(double newX, double newY) {
            //Verifica si puntos cumplen todas las restricciones
            int restriccionC = 0;
            for (int j = 0; j < nrest; j++) {					
                double limite = newX * restriccionX[j] + newY*restriccionY[j];
                switch(simbolo[j]) {
                case "<=":
                    if (limite <= limiteR[j]) {
                            restriccionC += 1;
                    }
                    break;
                case ">=":
                    if (limite >= limiteR[j]) {
                            restriccionC += 1;
                    }
                    break;
                case "=":
                    if (limite == limiteR[j]) {
                            restriccionC += 1;
                    }
                    break;
                    }					
		}
		double total = vardecision[0] * newX + vardecision[1] * newY;
		if ((restriccionC == nrest) && (total >=0) && (newX >= 0) && (newY >= 0)) {
			Q = new ListaPuntosPoligono();
			P = Q.creanodo(newX, newY, P, Q);
		}
	}
	
	public void xPositivo (int[] x, int[] yA, int i) {		
	    if (restriccionX[i] != 0) { 
	    	x[i] = (limiteR[i]) / restriccionX[i]; //Valor original // -3x1 + 4x2 <= 12, -3x1 = 12, x[i] = 12 / -3, x[i] = -4
		    yA[i] = yPDF;
    	}
    	else {
    		x[i] = 0; //Caso especial funcion constante en X
    		yA[i] = yPDF;
    	}
	}
		
	public void datosx(int nrest, int[] restriccionX, int[] limiteR) { 
		xNegativo= new boolean[nrest];
		x = new int[nrest]; //almacena coordenada x 
		yA = new int[nrest];
		//restriccionX = new int[nRestricciones];
        for (int i = 0; i<nrest; i++) { // y = 0, x[i] se despeja
        	int y = 0;
            xPositivo(x, yA, i);
        	//Si primer punto se encuentra en eje x negativo
        	if (x[i] < 0) {
        		xNegativo[i] = true;
        	}
        	else {
        		puntosFactibles(x[i], y);
        		xNegativo[i] = false;
        	}
        	//Valor modificado para graficar en interfaz
        	x[i] = xPDF + x[i]; //Se suma porque en JPanel ir hacia lado derecho es tener mas cantidad -4 = 250 - 4 = 246 y = 250
		}
	}
	
	public void yNegativo (int[] x, int[] yA, int x2, int i) {
		//restriccionX = new int[nRestricciones];	       
    	if (restriccionX[i] != 0) { 
		    x[i] = ( (limiteR[i]) - (restriccionY[i] * x2)) / restriccionX[i]; //Valor original // -3x1 + 4x2 <= 12, if x2= 2, -3x1 = 12 - 4(2), x[i] = 4 / -3, x[i] = -4/3
		    yA[i] = yPDF - x2;
    	}
    	else { //por dependencia funcional el valor de x original cambia hacia mas a la derecha
    		x[i] = 0; //Caso especial funcion constante en X
    		yA[i] = yPDF - x2;
    	}		        			        
        //Valor modificado para graficar en interfaz
        x[i] = xPDF + x[i]; //Se suma porque en JPanel ir hacia lado derecho es tener mas cantidad -4 = 250 - 4 = 246 y = 250
	}
    
	public void datosy(int nrest, int[] restriccionY, int[] limiteR, int restriccionX[]) {
		int x1; // falta un if
		//-3 + 4x2 = 12, 4x2 = 12 + 3  , 2 puntos, 1 punto 1 coordenadas = (x, y), punto 1 (xoriginal + 250, y = 0 + 250) 
		y = new int[nrest];
		xA = new int[nrest];
		yNegativo = new boolean[nrest];
		//restriccionY = new int [nRestricciones];
		for (int i = 0; i<nrest; i++) { // x = 0
			if (restriccionY[i] != 0) {
				if (xNegativo[i] == false) {
					x1 = 0;
				}
				else {
					x1 = xPDF;					
				}
				if (x1 == 0) { //xNegativo es falso
					y[i] = (limiteR[i]) / restriccionY[i]; //Valor original
					xA[i] = x1 + xPDF;
				} //if primer punto se encuentra en eje x negativo
				else { //Se modifica x auxiliar de punto final hacia mas a la derecha y por dependencia funcional y original va hacia mas arriba
					y[i] = ( (limiteR[i]) - (restriccionX[i]*x1) ) / restriccionY[i];
					xA[i] = x1 + xPDF;		
				}
			}
			else {
				y[i] = 0; //Caso especial funcion constante en Y
			}
			
			int x2;
			double x0 = 0;
			if (y[i] < 0) { //Si punto final esta en eje y negativo
				yNegativo [i] = true;
				x2 = yPDF; //Se modifica y auxiliar del punto de inicio hacia mas arriba
				yNegativo(x, yA, x2, i);
			}
			else {
				puntosFactibles(x0, y[i]);
			}
			//Valor modificado para graficar en interfaz
			y[i] = yPDF - y[i]; //Se resta porque en JPanel ir hacia arriba es tener menos cantidad
	    }		
	}
	
	public ListaPuntosPoligono P = new ListaPuntosPoligono();
	ListaPuntosPoligono Q;
	
	public void intersecciones(int nrest) {	
		//long nCombinaciones = factorial(nrest - 1);
		//double[] vectorAux = new double[nRest];
		//int[] interseccionesF;		
		int i = 0, iIz = 0, iDer = 0, baseiDer = 1, limiteI = nrest;
		double newX = 0, newY = 0, newT, varAux, xAux, yAux, rAux;
		while (i < (nrest - 1)) {
			iDer += baseiDer;
			while (iDer < limiteI) {
				//Se realiza el emparejamiento de x
				//System.out.println(restriccionX[iIz] + "restriccionX[iDer] " + restriccionX[iDer] + "restriccionY[iIz] " +restriccionY[iIz] + " restriccionY[iDer] " + restriccionY[iDer]);
				if (((restriccionX[iIz] != 0) || (restriccionX[iDer]!=0)) && ((restriccionY[iIz] != 0)||(restriccionY[iDer] != 0))) { //funciones constantes en x o y no se intersectan
					//System.out.println(restriccionX[iIz] + "restriccionX[iDer] " + restriccionX[iDer] + "restriccionY[iIz] " +restriccionY[iIz] + " restriccionY[iDer] " + restriccionY[iDer]);
				  if ((restriccionX[iIz] >= restriccionX[iDer]) && (restriccionX[iDer] != 0) && ((restriccionY[iDer] != 0) && (restriccionY[iIz] != 0))) { //Se calcula y				
					varAux = restriccionX[iIz] / restriccionX[iDer];					
					xAux = restriccionX[iDer] * varAux;
					yAux = restriccionY[iDer] * varAux;
					rAux = limiteR[iDer] * varAux;
					if ( ((restriccionX[iIz] <= 0) && (restriccionX[iDer] >= 0)) || ((restriccionX[iIz] >= 0) && (restriccionX[iDer] <= 0)) ) {
						newY = restriccionY[iIz] + yAux;
						newT = limiteR[iIz] + rAux;
						newY = newT / newY;
					}
					else {
						newX = restriccionX[iIz] - xAux; //prueba
						newY = restriccionY[iIz] - yAux;
						newT = limiteR[iIz] - rAux;
						newY = newT / newY;
					}
					newX = (limiteR[iDer] - (newY * restriccionY[iDer])) / restriccionX[iDer];
					//System.out.println(newX + " " + newY);
				}
				else if ((restriccionX[iIz] >= restriccionX[iDer]) && (restriccionX[iDer] == 0) && ((restriccionY[iDer] != 0) && (restriccionY[iIz] != 0))) {
					varAux = 0;					//Caso especial x de primer ecuacion es >= que x de segunda ecuacion y las y de 2 ecuaciones son diferentes a 0
					//xAux = restriccionX[iDer];
					newT = limiteR[iDer];
					newY = newT / restriccionY[iDer];
					newX = (limiteR[iIz] - newY*restriccionY[iIz]) / restriccionX[iIz];
					//System.out.println(newX + " " + newY);
				}
				else if ((restriccionX[iDer] > restriccionX[iIz]) && (restriccionX[iIz] != 0) && ((restriccionY[iDer] != 0) && (restriccionY[iIz] != 0))){ //se calcula y
					varAux = restriccionX[iDer] / restriccionX[iIz];					
					xAux = restriccionX[iIz] * varAux;
					yAux = restriccionY[iIz] * varAux;
					rAux = limiteR[iIz] * varAux;
					//Se realiza la eliminación de x
					if ( ((restriccionX[iIz] <= 0) && (restriccionX[iDer] >= 0)) || ((restriccionX[iIz] >= 0) && (restriccionX[iDer] <= 0)) ) {
						//newX = restriccionX[iDer] + xAux; //prueba
						newY = yAux + restriccionY[iDer];
						newT = rAux + limiteR[iDer];
						newY = newT / newY;
					}
					else {
						//newX = restriccionX[iDer] - xAux; //prueba
						newY = restriccionY[iDer] - yAux;
						newT = limiteR[iDer] - rAux;
						newY = newT / newY;
					}
					newX = (limiteR[iIz] - (newY * restriccionY[iIz])) / restriccionX[iIz];
					//System.out.println(newX + " " + newY);
				}
				else if((restriccionX[iDer] > restriccionX[iIz]) && (restriccionX[iIz] == 0) && ((restriccionY[iDer] != 0) && (restriccionY[iIz] != 0))) { //se calcula y
					varAux = 0;					
					//xAux = restriccionX[iDer];
					newT = limiteR[iIz];
					newY = newT / restriccionY[iIz];
					newX = (limiteR[iDer] - newY*restriccionY[iDer]) / restriccionX[iDer];
					//System.out.println(newX + " " + newY);
				}
				else if ((restriccionY[iIz] == 0) || (restriccionY[iDer] == 0)) {				
					if (restriccionY[iIz] == 0) {
					    newX = limiteR[iIz] / restriccionX[iIz];
					    System.out.println(limiteR[iDer] + "- " + restriccionX[iDer] + "/ " + restriccionY[iDer]);
					    newY = (limiteR[iDer] - newX*restriccionX[iDer]) / restriccionY[iDer];
					    System.out.println("Correcto");
					}
					else {
						newX = limiteR[iDer] / restriccionX[iDer];
					    newY = (limiteR[iIz] - newX*restriccionX[iIz]) / restriccionY[iIz];
					}
					//System.out.println(newX + " " + newY);					
				}
			    puntosFactibles(newX, newY);
				}
				//System.out.println(iIz + " - " + iDer);
				iDer ++;
			}
			iIz ++;
			baseiDer += 1;
			iDer = 0;
			i++;
			//restriccionX[i] + "x1\n+ " + restriccionY[i] + "x2\n" + simbolo[i] + "\n" + limiteR[i];
			
		}
		//int restriccionC = 0;
		puntosFactibles(0, 0);
	}
}

