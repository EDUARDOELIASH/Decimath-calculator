/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodoEsquinaNoroeste;

import MetodoVogel.PrincipalVo;

public class PrincipalEN extends PrincipalVo{
    public TablaDeParametrosEN tabla = new TablaDeParametrosEN();
    
    public PrincipalEN(int [][] Costos, int [] oferta, int [] demanda, int Origenes, int Destinos, int totalO, int totalD, boolean oFicticio, boolean dFicticio) {
        super(Costos, oferta, demanda, Origenes, Destinos, totalO, totalD, oFicticio, dFicticio);
    }
    
    @Override
    public void main(){ //Metodo Principal        
        tabla.matrizP(nOrigenes, nDestinos, costo, oferta, demanda, oFicticio, dFicticio);
        tabla.resultado(nOrigenes, nDestinos, oferta, demanda); 
    } 
    //Variables necesarias para metodo MODI	
    @Override
    public int[][] getmatrizPC() {
            return tabla.matrizParametros();
    }
    @Override
    public int[][] getmatrizA() {
            return tabla.matrizTransporte();
    }
    @Override
    public String[] getdestino() {
            return tabla.getdestino();
    }
    @Override
    public String[] getorigen() {
            return tabla.getorigen();
    }
    @Override
    public int getnOrigenes() {
            return nOrigenes;
    }
    @Override
    public int getnDestinos() {
            return nDestinos;
    }
}
