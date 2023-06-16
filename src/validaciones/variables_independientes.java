package validaciones;

import javax.swing.JOptionPane;

public class variables_independientes {

	boolean destinoF=false, origenF=false;
	boolean oFicticio = false, dFicticio = false;
	int totalO = 0, totalD = 0;
	int nOrigenes, nDestinos;
	int caso=0;
	
	public int optionMODI(){
	    int option=0;
	    //Leer numero de variables de decision
	    
	    boolean esNumero = false;
		while (esNumero == false) {
                try { //Manejo de excepciones
                    String res = JOptionPane.showInputDialog(null, "Metodo MODI con\nOpcion 1: Esquina Noroeste \nOpcion 2: Vogel\n","Metodo MODI",JOptionPane.QUESTION_MESSAGE);
                    option = Integer.parseInt(res);
                }
                catch(Exception er) {
                    esNumero = false;
                    JOptionPane.showMessageDialog(null, "Dato ingresado debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                boolean cumplerango= option>0 && option<3;
        		if(cumplerango == false){
            		JOptionPane.showMessageDialog(null, "Numero de variables debe ser 1 o 2", "Error", JOptionPane.WARNING_MESSAGE);
            	}
        		else {
					esNumero = true;
        		}
		}
	    return option;
	}
	
	public int LeerVar(){
	    boolean nvarV = false, excepcion;
	    int nvar = 0;
	    //Leer numero de variables de decision
	    while (nvarV == false){
	        try{ //Manejo de excepciones
	            excepcion = false;
	            String nVar = JOptionPane.showInputDialog(null, "Digite el número de variables de decision: ", "Variables de Decision", JOptionPane.QUESTION_MESSAGE);
	            nvar = Integer.parseInt(nVar);
	        }
	        catch (Exception error){
	            JOptionPane.showMessageDialog(null, "Tipo de dato debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE); 
	            excepcion = true;
	        }
	        if (nvar >= 2){
	            nvarV = true;
	        }
	        else{ 
	        	if(excepcion == false){
	        		JOptionPane.showMessageDialog(null, "Numero de variables debe ser >= 2", "Error", JOptionPane.WARNING_MESSAGE);
	        	} 
	        }
	    }
	    return nvar;
	}
	public int LeerRes() {
		boolean nrestV = false, excepcion;
		int nrest = 0;
		//Leer numero de restricciones
	    while (nrestV == false){
	        try{ //Manejo de excepciones
	            excepcion = false;
	            String nVar = JOptionPane.showInputDialog(null, "Digite el número de Restricciones", "Variables de Decision", JOptionPane.QUESTION_MESSAGE);
	            nrest = Integer.parseInt(nVar);
	        }
	       /* catch (InputMismatchException error){
	            s.print("Tipo de dato debe ser numerico porfavor, "); 
	            entrada.nextLine();
	            excepcion = true; //No entra a else if
	        }*/ 
	        catch (Exception error){
	            JOptionPane.showMessageDialog(null, "Tipo de dato debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE); 
	            excepcion = true;
	        }
	        if (nrest >= 1){
	            nrestV = true;
	        }
	        else{ 
	        	if(excepcion == false){
	        		JOptionPane.showMessageDialog(null, "Numero de variables debe ser >= 1", "Error", JOptionPane.WARNING_MESSAGE);
	        	}
	        } 
	    }
	    return nrest;
	}
	public int LeerOrigenes() {
		//numero de origenes
		boolean respV = false,excepcion = false;
		int nOrigenes = 0;
		while (respV == false) {
			try {
		        String res = JOptionPane.showInputDialog(null,"Digite numero de origenes","Ingrese Origenes",JOptionPane.QUESTION_MESSAGE);
		        nOrigenes = Integer.parseInt(res);
		    }   
			catch (Exception error) {
				JOptionPane.showMessageDialog(null, "Dato debe ser tipo entero","Error",JOptionPane.ERROR_MESSAGE);
				excepcion = true;
			}
			 if (nOrigenes >= 1){
	             respV = true;
	         }
	         else{ 
	         	if(excepcion == false){
	         		JOptionPane.showMessageDialog(null, "El numero de Origenes debe ser >= 1", "Error", JOptionPane.WARNING_MESSAGE);
	         	}
	         } 
		}
		return nOrigenes;
	}
	public int LeerDestinos() {
		//numero de destinos
		boolean respV = false,excepcion = false;
		int nDestinos=0;
		while (respV == false) {
			try {
		        String res = JOptionPane.showInputDialog(null,"Digite numero de destinos","Ingrese Destinos",JOptionPane.QUESTION_MESSAGE); 
		        nDestinos = Integer.parseInt(res);
			}
			catch (Exception r) {
				JOptionPane.showMessageDialog(null, "Dato debe ser tipo entero","Error",JOptionPane.ERROR_MESSAGE);
				excepcion = true;
			}
			if (nDestinos >= 1){
	            respV = true;
	        }
	        else{ 
	        	if(excepcion == false){
	        		JOptionPane.showMessageDialog(null, "El numero de Destinos debe ser >= 1", "Error", JOptionPane.WARNING_MESSAGE);
	        	}
	        } 
		}
		return nDestinos;
	}
	public int [][] balancearOfertaD (int nOrigenes, int nDestinos) {
	    destinoF = false;
	    origenF = false;
		if (nOrigenes != nDestinos) {
	        if (nDestinos < nOrigenes) {
	            int diferencia =nOrigenes -nDestinos;
	            nDestinos += diferencia;
	            destinoF = true;
	        }
	        else {
	            int diferencia =nDestinos - nOrigenes;
	            nOrigenes += diferencia;
	            origenF = true;
	        }
		}    
		int[][] costos = new int[nOrigenes][nDestinos];
		return costos;
	}
	public int[] oferta(int nOrigenes){
	    //Procedimiento que permite leer el valor de la oferta
		int[] oferta;
	    oferta = new int[nOrigenes+1]; //Se crea un espacio de memoria extra por si es necesario un origen ficticio
	    boolean esN = false;
	    for (int i = 0; i < nOrigenes; i++){
	        while (esN == false){
	            try{
	            	String c=JOptionPane.showInputDialog(null,"Digite oferta Origen " + (i + 1) + ": ","Oferta",JOptionPane.QUESTION_MESSAGE);
	                oferta[i] =Integer.parseInt(c);
	                esN = true;
	            } 
	            /*catch(java.util.InputMismatchException err){
	                s.print("Dato ingresado debe ser tipo numero entero porfavor, ");
	                esN = false;
	                entrada.nextLine();       
	            }*/
	            catch(Exception error){
	                JOptionPane.showMessageDialog(null, "Dato debe ser tipo entero", "Error", JOptionPane.ERROR_MESSAGE);
	                esN = false;
	            }
	            if (esN == true){ //Verificación de número válido
	                if (!(oferta[i] >= 0)){
	                    JOptionPane.showMessageDialog(null, "La demanda debe ser >= 0", "Error", JOptionPane.WARNING_MESSAGE);
	                    esN = false;
	                }
	            }
	        }
	        esN = false;
	        totalO+= oferta[i];              
	   } //oferta >= 0
	   // if oferta y demanda igual 0, if nDestinos y nOrigenes igual a 1
	   return oferta;
	}
	public int[] demanda(int nDestinos){
	    //Procedimiento que permite leer el valor de la demanda de determinado destino
		int [] demanda;
	    demanda = new int[nDestinos+1]; //Se crea un espacio de memoria extra por si es necesario crear un destino ficticio
	
	    boolean esN = false;
	    
	    for (int j = 0; j < nDestinos; j++){
	        while (esN == false){
	            try{
	                String c=JOptionPane.showInputDialog(null,"Digite demanda Destino " + (j + 1) + ": ","Demanda",JOptionPane.QUESTION_MESSAGE);
	                demanda[j] =Integer.parseInt(c);
	                esN = true;
	            } 
	            /*catch(java.util.InputMismatchException err){
	                s.print("Dato ingresado debe ser tipo numero entero porfavor, ");
	                esN = false;
	                entrada.nextLine();       
	            }*/
	            catch(Exception error){
	                JOptionPane.showMessageDialog(null, "Dato debe ser tipo entero", "Error", JOptionPane.ERROR_MESSAGE);
	                esN = false;
	            //  entrada.nextLine();
	            }
	            if (esN == true){ //Valor ingresado valido
	                if (!(demanda[j] >= 0)){
	                    JOptionPane.showMessageDialog(null, "La demanda debe ser >= 0", "Error", JOptionPane.WARNING_MESSAGE);
	                    esN = false;
	                }
	            }
	        }
	        esN = false;
	        totalD+= demanda[j];                     
	    }//demanda >= 0  
	    return demanda;
	}
	public int [][] balancear(int [] oferta, int [] demanda){
	    //Si la oferta y la demanda son diferentes, entonces se crea ya sea un origen o destino ficticio
	    if (totalO < totalD){ //Se crea origen ficticio
	        oferta[nOrigenes] = (totalD - totalO);
	        nOrigenes += 1;
	        oFicticio = true;
	    }
	    else{ //Se crea destino ficticio
	        demanda[nDestinos] = (totalO - totalD);
	        nDestinos += 1;  
	        dFicticio = true;
	    }
	    int [][] costo = new int[nOrigenes][nDestinos];
	    return costo;
	}
}
