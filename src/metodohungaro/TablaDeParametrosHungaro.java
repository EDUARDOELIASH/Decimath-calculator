/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodohungaro;

import javax.swing.JOptionPane;

public class TablaDeParametrosHungaro {
	int[][] matrizC;
	String[] origen, destino;
    
    public void matrizParametros(int nOrigenes, int nDestinos, int oferta, int demanda, int[][] costos, boolean origenF, boolean destinoF) {
    	matrizC = new int[nOrigenes + 1][nDestinos + 1];
    	for (int i = 0; i <= nOrigenes; i++) {
    		for (int j = 0; j<= nDestinos; j++) {
    			if ((i == nOrigenes) && (j != nDestinos)) {
    				matrizC[i][j] = oferta;
    			}
    			else if ((j == nDestinos) && (i != nOrigenes)) {
    				matrizC[i][j] = demanda;
    			}
    			else if ((j != nDestinos) && (i != nOrigenes)) {
    				matrizC[i][j] = costos[i][j];
    			}    			
    			else {
    				matrizC[i][j] = 0;
    			}
    		}
    	}
    	
    	origenes(nOrigenes, origenF);
    	destinos(nDestinos, destinoF);
    	
    }
	
    private void origenes(int nOrigenes, boolean oFicticio){
        //Este procedimiento asigna identificadores a los origenes
        origen = new String[nOrigenes + 1];
        for (int i = 0; i <= nOrigenes; i++){           
            if (i == nOrigenes){
                origen[i] = "Demanda";
            }
            else if ( (i == (nOrigenes - 1)) && (oFicticio == true) ){
                origen[i] = "Of";
            }
            else{
                origen[i] = "O" + (i+1);            
            }
        }
    }
    
    private void destinos(int nDestinos, boolean dFicticio){
    	//Este procedimiento asigna identificadores a los origenes
    	destino = new String[nDestinos+1];
        for (int j = 0; j <= nDestinos; j++){
            if (j == nDestinos){
                destino[j] = "Oferta";
            }
            else if( (j == (nDestinos - 1)) && (dFicticio == true) ){
                destino[j] = "Df";
            }
            else{
                destino[j] = "D" + (j+1);
            }
        }    
    }
    
    private void imprimir(int nOrigenes, int nDestinos, int [][] matriz){
        for (int j = 0; j <= nDestinos; j++){
            System.out.print("\t" + destino[j]); 
        }
        System.out.println();
        for (int i = 0; i <= nOrigenes; i++){
            System.out.print(origen[i]);
            for (int j = 0; j <= nDestinos; j++){
                System.out.print("\t" + matriz[i][j]);           
            }
            System.out.println();
        }
    
    }
    
    public void resultado(int nOrigenes, int nDestinos, int oferta, int demanda, int[][] costos) {
    	IteracionesHungaro iteraciones = new IteracionesHungaro(); //Clase iteraciones
    	int[] filaC = new int[nOrigenes]; //Arreglos necesarios donde se localizan costos de celdas igual a 0
    	int[] columnaC = new int[nDestinos];	
    	boolean solucion = false;
    	
    	imprimir(nOrigenes, nDestinos, matrizC);
    	matrizC = iteraciones.AsignacionDeT(nOrigenes, nDestinos, matrizC);
    	imprimir(nOrigenes, nDestinos, matrizC);  
    	System.out.println();
    	
    	solucion = iteraciones.diagonalP(nOrigenes, nDestinos, matrizC, filaC, columnaC, costos, destino);
    	while (solucion == false) {
            //JOptionPane.showMessageDialog(null, "Iteracion");
            matrizC = iteraciones.AsignacionDeT(nOrigenes, nDestinos, matrizC);
            imprimir(nOrigenes, nDestinos, matrizC); 
            //JOptionPane.showMessageDialog(null, "sol");
            solucion = iteraciones.diagonalP(nOrigenes, nDestinos, matrizC, filaC, columnaC, costos, destino);            
    	}
    	    	    	   	
    	int total = 0;
    	//Calcula total
    	if (solucion == true) {
    	  //for (int i = 0; i<nOrigenes; i++) {
        for (int d = 0; d<nDestinos; d++) {
            total += costos[filaC[d]][columnaC[d]];
            //matrizC[filaC[d]][columnaC[d]] -= 1;
            matrizC[filaC[d]][nDestinos] -= 1;
            matrizC[nOrigenes][columnaC[d]] -= 1;
            imprimir(nOrigenes, nDestinos, matrizC);
            System.out.println();
        }
        System.out.println("En donde esta el -1 es donde se encuentra el cero de asignación");
    	  //}
    	JOptionPane.showMessageDialog(null, "Costo total por asignación de trabajo es = " + total);
    	}
    	else {
            JOptionPane.showMessageDialog(null, "No se encontro solucion al problema", null, JOptionPane.INFORMATION_MESSAGE);
    	}    	    	
    }
}
