/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoModi;

import javax.swing.JOptionPane;

public class TablaDeParametrosMODI {
           
    private void imprimir(int nOrigenes, int nDestinos, int [][] matriz, String[] destino, String[] origen){
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
       
    public void resultado(int[][] matrizPC, int nOrigenes, int nDestinos, int[][] matrizA, String[] destino, String[] origen){
    	IteracionesMODI iteracion = new IteracionesMODI(nOrigenes, nDestinos, matrizA);
    	//Imprime el resultado
    	int total = 0;
        System.out.println("Metodo MODI");
    	while (iteracion.parar == false) {
            iteracion.operacionesLazo(matrizPC, nOrigenes, nDestinos, matrizA);
            if (iteracion.parar == false) {
                imprimir(nOrigenes, nDestinos, matrizA, destino, origen);
            }
        }
    	
    	for (int i = 0; i < nOrigenes; i++){
            for (int j = 0; j < nDestinos; j++){
                total += (matrizA[i][j] * matrizPC[i][j]);
            }        
        }
        JOptionPane.showMessageDialog(null, "Z obtenido con método MODI = " + total);
    }
}

