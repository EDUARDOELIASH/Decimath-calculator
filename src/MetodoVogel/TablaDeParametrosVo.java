/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoVogel;

import javax.swing.JOptionPane;

public class TablaDeParametrosVo {		    	    
	    int[][] matrizPC, matrizAG; //Matriz de parametros - costos, matriz asignacion get
	    String[] origen;
	    String[] destino;
	    
	    public void matrizP(int nOrigenes, int nDestinos, int[][] costo, int[] oferta, int[] demanda, boolean oFicticio, boolean dFicticio){
	        final short OD = 1; //Columna y fila de la oferta y la demanda
	        matrizPC = new int[nOrigenes + OD][nDestinos + OD];
	        origen = new String[nOrigenes + OD];
	        destino = new String[nDestinos + OD];
	        
	        for (int i = 0; i <= nOrigenes; i++){
	            for (int j = 0; j <= nDestinos; j++){
	                if ( (i == nOrigenes) && (j != nDestinos) ){
	                    matrizPC[i][j] = demanda[j]; //Se asigna el valor de la demanda               
	                }
	                else if( (i != nOrigenes) && (j == nDestinos) ){
	                    matrizPC[i][j] = oferta[i]; //Se asigna el valor de la oferta                
	                }
	                else if ( (i != nOrigenes) && (j != nDestinos)){
	                    matrizPC[i][j] = costo[i][j];               
	                }
	                else{
	                    matrizPC[i][j] = 0;
	                }
	            }        
	        }
	        origenes(nOrigenes, oFicticio);
	        destinos(nDestinos, dFicticio);
	    }
	    
	    private void origenes(int nOrigenes, boolean oFicticio){
	        //Este procedimiento asigna identificadores a los origenes
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
	    
	    public void resultado(int nOrigenes, int nDestinos, int[] oferta, int[] demanda){
	    	IteracionesVo iteracion = new IteracionesVo(nOrigenes, nDestinos, oferta, demanda);
	    	int total = 0;
	        imprimir(nOrigenes, nDestinos, matrizPC); //Matriz de costos
	        while (iteracion.pararI(nOrigenes, nDestinos) == false){ //Proceso de iteracion
	            iteracion.asignarO(matrizPC, nOrigenes, nDestinos);
	            imprimir(nOrigenes, nDestinos, iteracion.matrizA);
	            System.out.println();
	        }
	        for (int i = 0; i < nOrigenes; i++){ //Proceso de obtener costo
	            for (int j = 0; j < nDestinos; j++){
	                total += (iteracion.matrizA[i][j] * matrizPC[i][j]);
	            }        
	        }
	        matrizAG = iteracion.matrizA;
	        JOptionPane.showMessageDialog(null, "ZVogel " + total);
	    }
	    
	  //Para método MODI
	    public int[][] matrizParametros(){
	        return matrizPC;
	    }
	    
	    public int[][] matrizTransporte(){
	        return matrizAG;
	    }
	    
	    public String[] getdestino() {
	    	return destino;
	    }
	    
	    public String[] getorigen() {
	    	return origen;
	    }
}
