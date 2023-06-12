/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoModi;

import metodoEsquinaNoroeste.PrincipalEN;
import MetodoVogel.PrincipalVo;

public class PrincipalMODI {
    //Metodo Vogel - MODI
    int nOrigenes, nDestinos;
    int[][] matrizPC, matrizA;
    String[] destino, origen;
    TablaDeParametrosMODI tablaMO = new TablaDeParametrosMODI();
    
     
     
   /* public void main(){
        int opcion = 1;
    	while (opcion != 0) {
    		boolean esNumero = false;
    		while (esNumero == false) {
    			try { //Manejo de excepciones
    			    String res = JOptionPane.showInputDialog(null, "Metodo MODI con \nOpcion 0: Salir \nOpcion 1: Esquina Noroeste \nOpcion 2: Vogel");
    			    opcion = Integer.parseInt(res);
    			    esNumero = true;
    			}
    			catch(Exception er) {
    				esNumero = false;
    				JOptionPane.showMessageDialog(null, "Dato ingresado debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    		switch (opcion) {
    		case 0:
    			System.out.println("Saliendo...");
    			break;
    		case 1:
                //Metodo Esquina noroeste - MODI                                       
                inicializarEN();                                      
                //Resultado de tabla MODI
                tablaMO.resultado(matrizPC, nOrigenes, nDestinos, matrizA, destino, origen);
                break;
    		case 2:  
    			//Metodo Vogel - MODI    			
    			inicializarVo();
                //Resultado de tabla MODI
                tablaMO.resultado(matrizPC, nOrigenes, nDestinos, matrizA, destino, origen);
    			break;
            default:
            	JOptionPane.showMessageDialog(null, "Digite opcion valida", "ERROR", JOptionPane.ERROR_MESSAGE);
            	break;
                
    		}
    	}
    	
    }
    
    public void inicializarEN() {
    	PrincipalEN mainEN = new PrincipalEN();
    	//Se ejecuta metodo principal de Esquina Noroeste
    	mainEN.main();
    	
    	//Se obtienen matrices la de costos y asignaciones
    	matrizPC = mainEN.getmatrizPC(); //Matriz de parametros dados
    	matrizA = mainEN.getmatrizA(); //Cantidad de transporte
    	destino = mainEN.getdestino(); 
    	origen = mainEN.getorigen();
    	nOrigenes = mainEN.getnOrigenes();
    	nDestinos = mainEN.getnDestinos();
    }
    
    public void inicializarVo() {
    	PrincipalVo mainVo = new PrincipalVo();
		//Se ejecuta metodo principal del Vogel
		mainVo.main();
		
		//Se obtienen matrices la de costos y asignaciones
		matrizPC = mainVo.getmatrizPC(); //Matriz de parametros dados
        matrizA = mainVo.getmatrizA(); //Cantidad de transporte
        destino = mainVo.getdestino();
        origen = mainVo.getorigen();
        nOrigenes = mainVo.getnOrigenes();
        nDestinos = mainVo.getnDestinos();
    }*/
}
