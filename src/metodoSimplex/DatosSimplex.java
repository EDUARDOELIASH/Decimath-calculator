/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoSimplex;

import java.util.Scanner;
import java.io.PrintStream;
import java.util.InputMismatchException;

public class DatosSimplex {
    private int nvar, nrest, totalVar;
    private double[] vardecision;
    private double[][] restriccionI;
    private String[] simbolo;
    private int[] holguras;
    private char objetivo = ' ';
    
    public DatosSimplex(double vardecision[], char OBJETIVO, double restriccionI[][],String SimboloFN[],int Sigma,int NR,int VAR){
    	this.vardecision= vardecision;
    	this.objetivo=OBJETIVO;
    	this.restriccionI=restriccionI;
    	this.simbolo=SimboloFN;
    	this.totalVar=Sigma;
    	this.nvar=VAR;
    	this.nrest=NR;
    }
    
    PrintStream s = System.out;
    Scanner entrada = new Scanner(System.in);
    
    /*public void leer(){
        boolean nvarV = false, nrestV = false, excepcion;
        
        //Leer numero de variables de decision
        while (nvarV == false){
            try{ //Manejo de excepciones
                excepcion = false;
                String nVar = JOptionPane.showInputDialog(null, "digite número de variables de decision: ", "Variables de Decision", JOptionPane.INFORMATION_MESSAGE);
                nvar = Integer.parseInt(nVar);
            }
            catch (Exception error){
                JOptionPane.showMessageDialog(null, "Tipo de dato debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE); 
                excepcion = true;
            }
                
            if (nvar >= 2){
                nvarV = true;
            }
            else if (excepcion == false){
                JOptionPane.showMessageDialog(null, "Numero de variables debe ser >= 2", "Error", JOptionPane.WARNING_MESSAGE);
            }     
        }
        s.println();
        //Leer numero de restricciones
        while (nrestV == false){
            try{ //Manejo de excepciones
                excepcion = false;
                s.print("digite número de restricciones: ");
                nrest = entrada.nextInt();
            }
            catch (InputMismatchException error){
                s.print("Tipo de dato debe ser numerico porfavor, "); 
                entrada.nextLine();
                excepcion = true; //No entra a else if
            } 
            catch (Exception error){
                s.print("Ha ocurrido un error, porfavor, "); 
                entrada.nextLine();
                excepcion = true;
            }
            if (nrest >= 1){
                nrestV = true;
            }
            else if (excepcion == false){
                s.print("Numero de restricciones debe ser >= 1 porfavor, ");
            } 
            
        }   
        
        //Objetivo
        fObjetivo();
    
        //Var.Decision
        varDecision();
        
        //Restricciones
        restricciones();
        totalVar = nrest + nvar;
    }
    
    private void fObjetivo(){
        boolean simboloV = false;
        s.println("Opcion +: Maximizar");
        s.println("Opcion -: Minimizar");
        entrada.nextLine();
        while (simboloV == false){       
            objetivo = entrada.nextLine().charAt(0);
            switch (objetivo){
            case '+': 
                simboloV = true;
            break;
            case '-':
                simboloV = true;
            break;    
            default:
                s.println("Digite objetivo valido");
            break;    
          }
        }
    }
    
    private void varDecision(){
        boolean excepcion;
        int j = 0;
        vardecision = new double[nvar+1]; //Variables de decision
        //j columna
        s.print("Z");
        switch (objetivo){
            case '+': 
                s.print("Max ");
            break;
            case '-':
                s.print("Min ");
            break;        
        }
         
        s.println("Digite variables de decision");
        do {
            try{
                excepcion = false;
                s.print("x" + (j+1) + " ");
                vardecision[j] = entrada.nextDouble();
            }
            catch (InputMismatchException error){
                excepcion = true;
                s.print("Tipo de dato debe ser numerico porfavor, "); 
                entrada.nextLine();
            }
            catch (Exception error){
                excepcion = true;
                s.print("Ha ocurrido un error porfavor, ");
                entrada.nextLine();
            }
            if (excepcion == false){
                j++;
            }
         } while (j < nvar);     

    }
    
    private void restricciones(){
        int j;
        boolean excepcion, simboloR = false; //simboloR = simbolo Leido
        
        restriccionI = new double[nrest][nvar+1]; //Variables de restriccion
        simbolo = new String[nrest]; //Simbolos logicos
        
        s.println("\nDigite restricciones");
        //i fila
        for (int i = 0; i < nrest; i++){
            j = 0;
            do { //Lado izquierdo
                try{ //Manejo de Excepciones
                    excepcion = false;                    
                    if (j == nvar){
                        //Simbolo
                        if (simboloR == false){
                            simboloL(simbolo, i);
                            simboloR = true;
                        }
                        //Lado derecho
                        s.print("r: ");
                        restriccionI[i][nvar] = entrada.nextDouble();
                    }
                    else{
                        s.print("x" + (j+1) + " ");
                        restriccionI[i][j] = entrada.nextDouble();
                    }
                }
                catch (InputMismatchException error){
                    excepcion = true;
                    s.print("Tipo de dato debe ser numerico porfavor, "); 
                    entrada.nextLine();
                }
                catch (Exception error){
                    excepcion = true;
                    s.print("Ha ocurrido un error porfavor, ");
                    entrada.nextLine();
                }
                if (excepcion == false){ //Aumenta iteración siempre y cuando sea
                    j++;
                }   
            } while (j <= nvar);
            simboloR = false;
            //Se verifica que lado derecho de restriccion sea no negativo
            if (restriccionI[i][nvar] < 0){
               for (int k = 0; k <=nvar; k++){ //Lado izquierdo
                   restriccionI[i][k] *= (-1);
               }
               switch (simbolo[i]){
                   case ">=": 
                      simbolo[i] = "<=";
                   break;
                   case "<=": 
                      simbolo[i] = ">=";
                   break;
                   case "=": 
                      simbolo[i] = "=";
                   break;    
               }
            }
            s.println();
        }
    }
    
    private void simboloL(String[] simbolo, int i){
        boolean simboloV = false;
        entrada.nextLine();
        while (simboloV == false){
            simbolo[i] = entrada.nextLine();
            switch (simbolo[i]) { //Se leen simbolos validos
                case "<=":
                  simboloV = true;
                break;
                case ">=":
                  simboloV = true;
                break;
                case "=":
                  simboloV = true;
                break;
                default:
                  s.print("Digite simbolo logico valido: ");
                break;
            }
        }
    }*/
    
    public void varHolguras(){
    	//Procedimiento que calcula las variables holgura
        holguras = new int[nrest];
        for (int i = 0; i < nrest; i++){
            if (simbolo[i].equals("<=")){
               holguras[i] = 1;
            }
            else if (simbolo[i].equals(">=")){
               holguras[i] = -1;
            }     
            else if (simbolo[i].equals("=")){
               holguras[i] = 0;
            }
        } 
    }
    
    public void despejarZ(){
    	//Se despeja la funcion Z
        for (int j = 0; j <= nvar; j++){
            if (j == nvar){ //Lado derecho del igual
               vardecision[j] = 0;
            }
            else{ //Lado izquierdo del igual
               vardecision[j] *= (-1);
            }
        }           
    }
    
    public void tablaSimplex() {
        TablaSimplex tabla = new TablaSimplex(nrest, totalVar);
        //System.out.println(tabla.co);
        if (tabla.solBasicaI(nrest, holguras) == true){ //Tiene solución básica
            tabla.matrizI(nrest, totalVar, vardecision, restriccionI, holguras, nvar);//
            tabla.resultado(totalVar, objetivo, nrest, nvar);
        }
        else{
            s.println("Problema no tiene solucion basica inicial asi que no se puede resolver por el metodo simplex");      
        }            

    }
}
