package metodohungaro;

import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class DatosHungaro {
	int nOrigenes, nDestinos;
	int oferta, demanda;
	int costos[][];
	boolean destinoF, origenF;
	
	public DatosHungaro(int [][] Costos, int Oferta, int Demanda, int Origenes,int Destinos, boolean destino, boolean origen) {
		this.destinoF = destino;
		this.origenF = origen;
		this.costos=Costos;
		this.oferta=Oferta;
		this.demanda=Demanda;
		this.nOrigenes=Origenes;
		this.nDestinos=Destinos;
	}
	
	Scanner entrada = new Scanner(System.in);
	PrintStream s = System.out;
	
    public void leer() {
    	//numero de origenes
    	boolean respV = false;
    	while (respV == false) {
    		try {
    	        String res = JOptionPane.showInputDialog("Digite numero de origenes");
    	        nOrigenes = Integer.parseInt(res);
    	        respV = true;
    		}   
    		catch (Exception r) {
    			respV = false;
    			JOptionPane.showMessageDialog(null, "Dato debe ser tipo entero");
    		}
    	}
    	respV = false;
    	//numero de destinos
    	while (respV == false) {
    		try {
    	        String res = JOptionPane.showInputDialog("Digite numero de destinos"); 
    	        nDestinos = Integer.parseInt(res);
    	        respV = true;
    		}
    		catch (Exception r) {
    			respV = false;
    			JOptionPane.showMessageDialog(null, "Dato debe ser tipo entero");
    		}
    	}
    	//Se verifica que sean igual si no se crea ficticio
    	balancearOfertaD();
    	//se asigna oferta y demanda igual a 1
	    oferta = 1;
	    demanda = 1;
    	//Se leen costos
	    costos = new int[nOrigenes][nDestinos];
    	leerCostos();
    	
    }
    
    private void leerCostos() {
    	for (int i = 0; i < nOrigenes; i++) {
    		for(int j = 0; j<nDestinos; j++) {
    			System.out.println("Digite costo " + (i + 1) + " " + (j + 1));
    			costos[i][j] = entrada.nextInt();
    		}
    	}
    }
    
    private void balancearOfertaD () {
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
    }
   
    public void TablaParametrosH () {
    	TablaDeParametrosHungaro Tabla = new TablaDeParametrosHungaro();
    	Tabla.matrizParametros(nOrigenes, nDestinos, oferta, demanda, costos, origenF, destinoF);
    	Tabla.resultado(nOrigenes, nDestinos, oferta, demanda, costos);    	
    }
}
