package MetodoVogel;

public class PrincipalVo {
	
    protected int nOrigenes= 0;
    protected int nDestinos = 0;
    protected int[] oferta;
    protected int[] demanda;
    protected int[][] costo;
    protected boolean oFicticio, dFicticio;
    
    protected int totalO = 0, totalD = 0;
    protected TablaDeParametrosVo tabla = new TablaDeParametrosVo();
    
    public PrincipalVo(int [][] Costos, int [] oferta, int [] demanda, int Origenes, int Destinos, int totalO, int totalD, boolean oFicticio, boolean dFicticio) {       
    	this.costo=Costos;
    	this.oferta=oferta;
    	this.demanda=demanda;
    	this.nOrigenes=Origenes;
    	this.nDestinos=Destinos;
    	this.totalO=totalO;
    	this.totalD=totalD;
    	this.oFicticio=oFicticio;
    	this.dFicticio=dFicticio;
    }
    
    public void main(){ //Metodo Principal        
       // inicializarVo();
        
        tabla.matrizP(nOrigenes, nDestinos, costo, oferta, demanda, oFicticio, dFicticio);
        tabla.resultado(nOrigenes, nDestinos, oferta, demanda); 
    } 
	
    /*public void inicializarVo() {
        DatosVo datovo = new DatosVo(); //Objeto de clase datos del metodo Vogel		
        //datovo.leer();
        //Se obtienen valores necesarios
        nOrigenes = datovo.getnOrigenes(); 
        nDestinos = datovo.getnDestinos();
        oferta = datovo.getoferta();
        demanda = datovo.getdemanda();
        costo = datovo.getcosto();
        oFicticio = datovo.getoFicticio();
        dFicticio = datovo.getdFicticio();   	
    }*/
	
    //Variables necesarias para metodo MODI	
    public int[][] getmatrizPC() {
            return tabla.matrizParametros();
    }
     
    public int[][] getmatrizA() {
            return tabla.matrizTransporte();
    }

    public String[] getdestino() {
            return tabla.getdestino();
    }

    public String[] getorigen() {
            return tabla.getorigen();
    }

    public int getnOrigenes() {
            return nOrigenes;
    }

    public int getnDestinos() {
            return nDestinos;
    }
}
