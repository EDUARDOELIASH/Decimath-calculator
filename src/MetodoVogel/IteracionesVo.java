/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodoVogel;

public class IteracionesVo {
    int[] penalizacionF;
    int[] penalizacionC;
    short fila;
    int[][] matrizA;
    int ofertaS, demandaS;
    
    public IteracionesVo(int nOrigenes, int nDestinos, int[] oferta, int[] demanda) { //Constructor
    	fila = 0; //Se inicializan variables
    	ofertaS = 0;
    	demandaS = 0;
    	
        penalizacionF = new int[nOrigenes]; //Se inicializan tamaños de arreglos
        penalizacionC = new int[nDestinos];  
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
       
    private void penalizacion(int[][] matrizPC, int nOrigenes, int nDestinos){
        //Procedimiento que calcula la penalización de las columnas y fila
    	
    	//Calcula la penalizacion de fila
        for (int i = 0; i < nOrigenes; i++){
            if (matrizA[i][nDestinos] != 0){
                penalizacionFila(matrizPC, i, nDestinos, nOrigenes); //Este
            }
            else{
                penalizacionF[i] = 0; //Si el origen tiene 0 oferta
            }
        }
        
        //Calcula penalizacion de columna
        for (int j = 0; j < nDestinos; j++){
            if (matrizA[nOrigenes][j] != 0){
                penalizacionColumna(matrizPC, j, nDestinos, nOrigenes);  
            }
            else{
                penalizacionC[j] = 0; //Si el destino tiene 0 demanda
            }
        }
    }
    
    private void penalizacionFila(int[][] matrizPC, int fila, int nDestinos, int nOrigenes){
        //Calcula la penalización de fila (i)
        int menor1 = -1, menor2 = -1;
        //Se calculan valores menores de la fila
        for (int j = 0; j < nDestinos; j++){
            if ( (menor1 == -1) && (matrizA[nOrigenes][j] != 0) ){ //Se obtiene primer valor menor
                menor1 = matrizPC[fila][j];
            }
            else if ( (menor2 == -1) && (matrizA[nOrigenes][j] != 0) ){ //Se obtiene segundo valor menor
                menor2 = matrizPC[fila][j]; 
            }
            else if ( ((menor1 != -1) && (menor2 != -1)) && (matrizA[nOrigenes][j] != 0) ){ 
                //Se ejecuta si hay 2 valores menores
                if ((matrizPC[fila][j] < menor1) && (menor1 > menor2)){ //Se obtiene nuevo valor menor1
                    menor1 = matrizPC[fila][j];
                }
                else if (matrizPC[fila][j] < menor2){ //Se obtiene nuevo valor menor2
                    menor2 = matrizPC[fila][j];
                }
            }
        } 
        
        if (menor2 == -1){ //Si solo hay un valor menor 1
            menor2 = 0;
        }
        if (menor1 >= menor2){ //Se calcula penalizacion, restando los 2 valores menores
            penalizacionF[fila] = menor1 - menor2;
        }
        else{
            penalizacionF[fila] = menor2 - menor1;
        }
        //System.out.println(penalizacionF[fila] + " f" + fila);
    }
    
    private void penalizacionColumna(int[][] matrizPC, int columna, int nDestinos, int nOrigenes){
        //Calcula la penalización de columna (j)
        int menor1 = -1, menor2 = -1;

        //Se calculan valores menores de la columna
        for (int i = 0; i < nOrigenes; i++){
            if ( (menor1 == -1) && (matrizA[i][nDestinos] != 0) ){
                menor1 = matrizPC[i][columna];
            }
            else if ( (menor2 == -1) && (matrizA[i][nDestinos] != 0) ){
                menor2 = matrizPC[i][columna];
            }
            else if ( ((menor1 != -1) && (menor2 != -1)) && (matrizA[i][nDestinos] != 0) ){
                if ((matrizPC[i][columna] < menor1) && ((menor1) > (menor2))){
                    menor1 = matrizPC[i][columna];
                }
                else if (matrizPC[i][columna] < menor2){
                    menor2 = matrizPC[i][columna];
                }
            }
        }
        if (menor2 == -1){ //Si solo hay un valor menor 1
            menor2 = 0;
        }
        if (menor1 >= menor2){ //Se calcula penalizacion, restando los 2 valores menores
            penalizacionC[columna] = menor1 - menor2;
        }
        else{
            penalizacionC[columna] = menor2 - menor1;
        }
        //System.out.println(penalizacionC[columna] + " c" + columna);
    } 
    
    private int penalizacionM(int nOrigenes, int nDestinos){
        //Funcion que identifica la columna o fila con penalización mayor
        int mayorF = 0, mayorC = 0, localizacionF = 0, localizacionC = 0, localizacionM;
                
        for (int i = 0; i < nOrigenes; i++){
            if ((penalizacionF[i] >= mayorF) && (matrizA[i][nDestinos] != 0)){
                mayorF = penalizacionF[i];
                localizacionF = i;
            }
        }
        for (int j = 0; j < nDestinos; j++){
            if ((penalizacionC[j] >= mayorC) && (matrizA[nOrigenes][j] != 0)){
                mayorC = penalizacionC[j];
                localizacionC = j;                
            }    
        }
        
        if (mayorC >= mayorF){ //Penalizacion mayor se encuentra en columna
            localizacionM = localizacionC;
            //System.out.println(localizacionM +" MColumna" );
        }
        else{ //Penalizacion mayor se encuentra en fila
            fila = 1;
            localizacionM = localizacionF;
           // System.out.println(localizacionM +" Mfila" );
        }
        return localizacionM; //Localizacion de penalizacion Mayor
    }
    
    private void ofertaODemanda(int indice, int indice2, int nOrigenes, int nDestinos){
        //Se calcula cual es menor, oferta o demanda
            //Oferta ----- Demanda
            if (matrizA[indice][nDestinos] <= matrizA[nOrigenes][indice2]){ //oferta <= demanda
                matrizA[indice][indice2] = matrizA[indice][nDestinos]; //Celda con menor costo
                matrizA[indice][nDestinos] -= matrizA[indice][indice2]; //Oferta
                matrizA[nOrigenes][indice2] -= matrizA[indice][indice2]; //Demanda
                if (matrizA[indice][nDestinos] == 0){
                    ofertaS += 1;
                }
                if (matrizA[nOrigenes][indice2] == 0){
                    demandaS += 1;
                }
            }  
            else{ //oferta > demanda
                matrizA[indice][indice2] = matrizA[nOrigenes][indice2]; //Celda con menor costo
                matrizA[indice][nDestinos] -= matrizA[indice][indice2]; //Oferta
                matrizA[nOrigenes][indice2] -= matrizA[indice][indice2]; //Demanda
                if (matrizA[indice][nDestinos] == 0){
                    ofertaS += 1;
                }
                if (matrizA[nOrigenes][indice2] == 0){
                    demandaS += 1;
                }
            }                           
    }
        
    public void asignarO(int[][] matrizPC, int nOrigenes, int nDestinos){ 
        //Procedimiento que asigna de que origen a que destino transportar una determinada cantidad de oferta
        int menor = -1, indice, indice2 = 0;
        fila = 0;
        
        penalizacion(matrizPC, nOrigenes, nDestinos);
        indice = penalizacionM(nOrigenes, nDestinos);
        if (fila == 1){ //Penalizacion mayor se encuentra en fila
            //Se calcula celda de fila con costo menor
            for (int j = 0; j < nDestinos; j++){
                if ( (menor == -1) && (matrizA[nOrigenes][j] != 0) ){
                    menor = matrizPC[indice][j];
                    indice2 = j;
                }
                else if ( (menor != -1) && (matrizA[nOrigenes][j] != 0) ){
                    if (matrizPC[indice][j] < menor){
                        menor = matrizPC[indice][j];
                        indice2 = j; //Columna de celda con costo menor
                    }
                }
            }
            //Se calcula cual es mayor oferta o demanda
            ofertaODemanda(indice, indice2, nOrigenes, nDestinos);
                    
        }
        else{ //Penalizacion mayor se encuentra en columna
            //Se calcula celda de columna con costo menor 
            for (int i = 0; i < nOrigenes; i++){
                if ( (menor == -1) && (matrizA[i][nDestinos] != 0) ){
                    menor = matrizPC[i][indice];
                    indice2 = i;
                }
                else if ( (menor != -1) && (matrizA[i][nDestinos] != 0) ){
                    if (matrizPC[i][indice] < menor){
                        menor = matrizPC[i][indice];
                        indice2 = i;
                    }
                }    
            }
            //Se calcula cual es menor, oferta o demanda
            //Oferta ----- Demanda Se modificia oferta y demanda
            ofertaODemanda(indice2, indice, nOrigenes, nDestinos);           
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
