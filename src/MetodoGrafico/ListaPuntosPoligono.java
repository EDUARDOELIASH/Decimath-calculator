package MetodoGrafico;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

//import ZONA_DE_PRUEBA_1.ListaPuntosPoligono;


public class ListaPuntosPoligono {
    public double x;
    public double y;
    public ListaPuntosPoligono next;


    public ListaPuntosPoligono(){
        x= 0;
        y = 0;
        next=null;
    }

    public ListaPuntosPoligono creanodo(double x, double y, ListaPuntosPoligono P, ListaPuntosPoligono Q){
        Q.x =x;
        Q.y = y;
        Q.next=P;	         
        P=Q;
        System.out.println("valor del nodo: " + Q.x + " " + Q.y);
        System.out.println("direccion del nodo actual : " + Q);
        System.out.println("nodo actual apunta a: " + Q.next);
        System.out.println("                               "  );
        return P;
    }

    /*public ListaPuntosPoligono eliminanodo(ListaPuntosPoligono P, ListaPuntosPoligono Q){	         
        if (Q.next != null)  
                    P=Q.next;    
            else
                    P=null;
        System.out.println("EL NODO...." + Q.x + " " + Q.y + "     HA SIDO ELIMINADO" );
        System.out.println("                               "  );
        Q=null;

         return P;
    }*/

    public String puntosFactibles(ListaPuntosPoligono nodos, double xVarDes, double yVarDes){
        //Guarda en un String todos los puntos factibles
        double total = 0;
        boolean repetir = false; 
        ListaPuntosPoligono puntosF = new ListaPuntosPoligono();
        ListaPuntosPoligono aux = puntosF;
        String puntos = "";

        total = (xVarDes*nodos.x) + (yVarDes * nodos.y);
        puntos += xVarDes+"*(" + nodos.x +") + " + yVarDes + "*(" + nodos.y+ ") = " + total + "\n";
        ListaPuntosPoligono Q = new ListaPuntosPoligono();
        puntosF = Q.creanodo(nodos.x, nodos.y, puntosF, Q);
        while (nodos.next!=null){
                nodos=nodos.next;
               // System.out.println(nodos.x +" == " +  puntosF.x + " && " + nodos.y+  " == " + puntosF.y);
                if ((nodos.x == puntosF.x) && (nodos.y == puntosF.y)) { //revisa que valores no se repitan
                        repetir = true;
                }
                else {
                        aux = puntosF;
                    while (aux.next != null){
                    aux = aux.next;
                    //System.out.println(nodos.x +" == " +  aux.x + " && " + nodos.y+  " == " + aux.y);
                    if ((nodos.x == aux.x) && (nodos.y == aux.y)) { //revisa que valores no se repitan
                                repetir = true;
                        }
                        }
                }
                if (repetir == false) {
                        total = (xVarDes*nodos.x) + (yVarDes * nodos.y);
                        puntos+=xVarDes+"*(" + nodos.x +") + " + yVarDes + "*(" + nodos.y+ ") = " + total + "\n";
                        ListaPuntosPoligono Q1 = new ListaPuntosPoligono();
                        puntosF = Q1.creanodo(nodos.x, nodos.y, puntosF, Q1);
                }	                
                repetir = false;	    	        
         }
         return puntos;          
     }

     public int nPuntosFactibles(ListaPuntosPoligono nodos){
        //Guarda en un String todos los puntos factible'
        int n = 1;
        boolean repetir = false; 
        ListaPuntosPoligono puntosF = new ListaPuntosPoligono();
        ListaPuntosPoligono aux = puntosF;

        ListaPuntosPoligono Q = new ListaPuntosPoligono();
        puntosF = Q.creanodo(nodos.x, nodos.y, puntosF, Q);
        while (nodos.next!=null){
                nodos=nodos.next;
                if ((nodos.x == puntosF.x) && (nodos.y == puntosF.y)) { //revisa que valores no se repitan
                        repetir = true;
                }
                else {
                        aux = puntosF;
                    while (aux.next != null){
                    aux = aux.next;
                    if ((nodos.x == aux.x) && (nodos.y == aux.y)) { //revisa que valores no se repitan
                                repetir = true;
                        }
                        }
                }
                if ((repetir == false)) {
                        n++;
                        ListaPuntosPoligono Q1 = new ListaPuntosPoligono();
                        puntosF = Q1.creanodo(nodos.x, nodos.y, puntosF, Q1);
                }	                
                repetir = false;	    	        
         }
         return n;          
     }
     
     private double maximo (ListaPuntosPoligono nodos, double pO, double total, double[] xYyOptimo){
         if (total > pO) {
            pO = total;
            xYyOptimo[0] = nodos.x;
            xYyOptimo[1] = nodos.y;
        } 
        return pO;
     }
     
     private double minimo (ListaPuntosPoligono nodos, double pO, double total, double[] xYyOptimo){
        if (total < pO) {
            pO = total;
            xYyOptimo[0] = nodos.x;
            xYyOptimo[1] = nodos.y;
        }
        return pO;
     }

     public void puntoOptimo(ListaPuntosPoligono nodos, double xVarDes, double yVarDes, char objetivo){
         //Procedimento que imprime en un JOptionPane el punto optimo 
        double total = 0;
        double pO = 0;
        double[] xYyOptimo = new double[2];
        
        total = (xVarDes*nodos.x) + (yVarDes * nodos.y);
        pO = total;
        xYyOptimo[0] = nodos.x;
        xYyOptimo[1] = nodos.y;
        
        while (nodos.next!=null){ //recorrido de la lista
            nodos=nodos.next;
            total = (xVarDes*nodos.x) + (yVarDes * nodos.y);
            switch(objetivo) {
                case '+': //maximizar    
                    pO = maximo(nodos, pO, total, xYyOptimo);
                    break;
                case '-': //minimizar
                    pO = minimo(nodos, pO, total, xYyOptimo);
                    break;
            }
        }
        
        switch(objetivo) {
            case '+': //maximizar    
                JOptionPane.showMessageDialog(null, "punto optimo maximo es "+ xVarDes+"*(" + xYyOptimo[0] +") + " + yVarDes + "*(" + xYyOptimo[1] + ") = " + pO);
                break;
            case '-': //minimizar
                JOptionPane.showMessageDialog(null, "punto optimo minimo es "+ xVarDes+"*(" + xYyOptimo[0] +") + " + yVarDes + "*(" + xYyOptimo[1]+ ") = " + pO);
                break;
        }      
     }	     	     
}
