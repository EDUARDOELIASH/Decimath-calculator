/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoEsquinaNoroeste;

public class IteracionesEN {
	int[][] matrizA;
    int ofertaS, demandaS, columna, fila;
    
    public IteracionesEN (int nOrigenes, int nDestinos, int[] oferta, int[] demanda) {
    	//Variables 
    	ofertaS = 0;
    	demandaS = 0;
    	columna = 0;
    	fila = 0;
    	//Este procedimiento inicializa valores necesarios
        matrizA = new int[nOrigenes + 1][nDestinos + 1];
        //Se crea la matriz asignacion
        for (int i = 0; i <= nOrigenes; i++){
            for (int j = 0; j <= nDestinos; j++){
                if (i == nOrigenes){ //Demanda
                    if (j < nDestinos){
                        matrizA[i][j] = demanda[j];
                        if (demanda[j] == 0){
                            demandaS +=1; //Demanda satisfecha
                        }
                    }
                    
                }
                else if (j == nDestinos){ //Oferta
                    matrizA[i][j] = oferta[i];
                    if (oferta[i] == 0){
                        ofertaS += 1; //Oferta satisfecha
                    }
                }
                else{
                    matrizA[i][j] = 0;
                }
            } //Fin del for
        }   
    }
    
    private void esquinaNO(int nOrigenes, int nDestinos){
        //Esta funcion identifica la celda mas al noroeste
        int i = 0, j;
        boolean esquinaV = false;
        
        while ( (i < nOrigenes) && (esquinaV == false) ){
            j = 0;
            while ( j < nDestinos && (esquinaV == false) ){
                if ( (matrizA[i][nDestinos] != 0) && (matrizA[nOrigenes][j] != 0) ){
                    fila = i;
                    columna = j;
                    esquinaV = true;
                }
                j++;
            }
            i++;
        }
    }   
    
    public void asignarO(int[][] matrizPC, int nOrigenes, int nDestinos){ 
        //Procedimiento que asigna de que origen a que destino transportar una determinada cantidad de oferta
        fila = 0;
        columna = 0;
        esquinaNO(nOrigenes, nDestinos);
        //Se calcula cual es menor, oferta o demanda
        //Oferta ----- Demanda
        if (matrizA[fila][nDestinos] <= matrizA[nOrigenes][columna]){
            matrizA[fila][columna] = matrizA[fila][nDestinos]; //Celda con menor costo
            matrizA[fila][nDestinos] -= matrizA[fila][columna]; //Oferta
            matrizA[nOrigenes][columna] -= matrizA[fila][columna]; //Demanda
            if (matrizA[fila][nDestinos] == 0){
                ofertaS += 1;
            }
            if (matrizA[nOrigenes][columna] == 0){
                demandaS += 1;
            }
        }  
        else{
            matrizA[fila][columna] = matrizA[nOrigenes][columna]; //Celda con menor costo
            matrizA[fila][nDestinos] -= matrizA[fila][columna]; //Oferta
            matrizA[nOrigenes][columna] -= matrizA[fila][columna]; //Demanda
            if (matrizA[fila][nDestinos] == 0){
                ofertaS += 1;
            }
            if (matrizA[nOrigenes][columna] == 0){
                demandaS += 1;
            }
        }
    }
    
    public boolean pararI(int nOrigenes, int nDestinos){
        //Funcion que devuelve si el programa deja de iterar
        boolean parar = true;
        //Ofertas
        if ((ofertaS != nOrigenes) && (demandaS != nDestinos)){
            parar = false;    
        }
        return parar; //Devuelve si iteracion debe parar o no
    }
    
}
