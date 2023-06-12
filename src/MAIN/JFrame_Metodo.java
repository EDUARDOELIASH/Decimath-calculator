/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MAIN;
import GUI.JFrame_2Fases;
import GUI.JFrame_EN;
import GUI.JFrame_Grafico;
import GUI.JFrame_Hungaro;
import GUI.JFrame_Simplex;
import GUI.JFrame_Vogel;
import GUI.JPanel_Start;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//Metodos

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JViewport;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class JFrame_Metodo extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> Botones;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	boolean destinoF=false, origenF=false;
	boolean oFicticio = false, dFicticio = false;
	int totalO = 0, totalD = 0;
	int nOrigenes, nDestinos;
	int caso=0;
	
	
	/**
	 * Create the frame.
	 */
	public JFrame_Metodo() {
		Botones=new ArrayList<JButton>();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_Metodo.class.getResource("/Imagenes/mathematic-calculator.png")));
		setTitle("Matematicas para la Toma de Decisiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		setMinimumSize(new Dimension(850,550));
		contentPane = new JPanel_Start();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel Panel_Central = new JPanel();
		Panel_Central.setOpaque(false);
		Panel_Central.setPreferredSize(new Dimension(800,460));
		//contentPane.add(Panel_Central, BorderLayout.CENTER);
		Panel_Central.setLayout(new BorderLayout(0, 0));
		
		JPanel Panel_Titulo = new JPanel();
		Panel_Titulo.setOpaque(false);
		Panel_Central.add(Panel_Titulo, BorderLayout.NORTH);
		Panel_Titulo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("<html><br></br><br></br></html>");
		Panel_Titulo.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(13);
		panel.setOpaque(false);
		Panel_Titulo.add(panel);
		
		JLabel Titulo = new JLabel("\u00A1Bienvenido! Eliga la Opcion Deseada");
		panel.add(Titulo);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 28));
		
		JLabel Titulo_1 = new JLabel("Seleccione el Metodo a Realizar");
		Panel_Titulo.add(Titulo_1);
		Titulo_1.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		
		JPanel Panel_Formulario = new JPanel();
		Panel_Formulario.setOpaque(false);
		Panel_Central.add(Panel_Formulario, BorderLayout.CENTER);
		Panel_Formulario.setLayout(new BorderLayout(0, 0));
		
		JPanel Inferior = new JPanel();
		Inferior.setOpaque(false);
		Panel_Formulario.add(Inferior, BorderLayout.SOUTH);
		Inferior.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField = new JTextField();
		Inferior.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		textField_3 = new JTextField();
		Inferior.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setVisible(false);
		
		JPanel Izquierda = new JPanel();
		Izquierda.setOpaque(false);
		Panel_Formulario.add(Izquierda, BorderLayout.WEST);
		
		textField_1 = new JTextField();
		Izquierda.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);

		
		JPanel Derecho = new JPanel();
		Derecho.setOpaque(false);
		Panel_Formulario.add(Derecho, BorderLayout.EAST);
		
		textField_2 = new JTextField();
		Derecho.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		
		final JPanel Centro = new JPanel();
		Centro.setOpaque(false);
		Panel_Formulario.add(Centro, BorderLayout.CENTER);
		Centro.setLayout(new GridLayout(0, 1, 0, 0));
		
		final JButton Btn_ProgramacionLineal = new JButton("Tema 1: Programacion Lineal");
		Btn_ProgramacionLineal.setHorizontalTextPosition(SwingConstants.CENTER);
		Btn_ProgramacionLineal.setFocusPainted(false);
		Btn_ProgramacionLineal.setContentAreaFilled(false);
		
		Btn_ProgramacionLineal.setBorderPainted(false);
		Btn_ProgramacionLineal.setOpaque(false);
		Btn_ProgramacionLineal.setFont(new Font("Arial", Font.ITALIC, 21));
		final Color c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");
		Btn_ProgramacionLineal.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		
		
		Centro.add(Btn_ProgramacionLineal);
		
		final JButton Btn_MetodosTransporte = new JButton("<html>Tema 2: Metodos de Transporte<br>&emsp;&emsp;&emsp;&emsp;y Asignacion</br></html>");
		Btn_MetodosTransporte.setHorizontalTextPosition(SwingConstants.CENTER);
		
		Btn_MetodosTransporte.setFocusPainted(false);
		Btn_MetodosTransporte.setBorderPainted(false);
		Btn_MetodosTransporte.setContentAreaFilled(false);
		Btn_MetodosTransporte.setOpaque(false);
		Btn_MetodosTransporte.setFont(new Font("Arial", Font.ITALIC, 21));
		Btn_MetodosTransporte.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		Centro.add(Btn_MetodosTransporte);
		
		//Actualizar Interfaz
		Btn_ProgramacionLineal.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent arg0) {
                            Btn_MetodosTransporte.setVisible(false);
                            Btn_ProgramacionLineal.setVisible(false);
                            Centro.remove(Btn_MetodosTransporte);
                            Centro.remove(Btn_ProgramacionLineal);
                            Btn();
                            JOptionPane.showMessageDialog(null,"Tema 1: Programacion Lineal","Seleccion de Metodo",JOptionPane.INFORMATION_MESSAGE);
			}
			private void Btn() {
                            //Sub Botones Tema 1
                            for(int i=0;i<3;i++) {
                                    JButton Btn_Grafico = null;
                                    switch(i) {
                                            case 0:		
                                                Btn_Grafico = new JButton("Metodo Grafico");
                                                Btn_Grafico.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent arg0) {
                                                    dispose();							
                                                    JFrame_Grafico AS=new JFrame_Grafico();
                                                    AS.setResizable(true);
                                                    AS.setLocationRelativeTo(null);
                                                    AS.setVisible(true);
                                                }
                                                });
                                                break;
                                            case 1:		
                                                Btn_Grafico = new JButton("Metodo Simplex");
                                                Btn_Grafico.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent arg0) {
                                                    int NR=LeerRes();
                                                    int VAR=LeerVar();
                                                    dispose();
                                                    JFrame_Simplex AS=new JFrame_Simplex(NR,VAR);
                                                    AS.setResizable(true);
                                                    AS.setLocationRelativeTo(null);
                                                    AS.setVisible(true);
                                                }
                                                });
                                                break;
                                            case 2:		
                                                Btn_Grafico = new JButton("Metodo Dos Fases");
                                                Btn_Grafico.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent arg0) {
                                                    int NR=LeerRes();
                                                    int VAR=LeerVar();
                                                    dispose();
                                                    JFrame_2Fases AS=new JFrame_2Fases(NR,VAR);
                                                    AS.setResizable(true);
                                                    AS.setLocationRelativeTo(null);
                                                    AS.setVisible(true);
                                                }
                                                });
                                                break;
                                    }
                                    Btn_Grafico.setHorizontalTextPosition(SwingConstants.CENTER);

                                    Btn_Grafico.setFocusPainted(false);
                                    Btn_Grafico.setBorderPainted(false);
                                    Btn_Grafico.setContentAreaFilled(false);
                                    Btn_Grafico.setOpaque(false);
                                    Btn_Grafico.setFont(new Font("Arial", Font.ITALIC, 21));
                                    Btn_Grafico.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

                                    Botones.add(Btn_Grafico);
                                    Centro.add(Btn_Grafico);
                            }
                    }
		});
		Btn_MetodosTransporte.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                        Btn_MetodosTransporte.setVisible(false);
                        Btn_ProgramacionLineal.setVisible(false);
                        Centro.remove(Btn_MetodosTransporte);
                        Centro.remove(Btn_ProgramacionLineal);
                        Btn2();
                        JOptionPane.showMessageDialog(null,"Tema 2: Metodos de Transporte y Asignacion","Seleccion de Metodo",JOptionPane.INFORMATION_MESSAGE);
                }
                    private void Btn2() {
                        //Sub Botones
                        for(int i=0;i<3;i++) {
                            JButton Btn_Grafico = null;
                            switch(i) {
                                case 0:		
                                    Btn_Grafico = new JButton("Metodo Vogel");
                                    Btn_Grafico.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        dispose();
                                        int[] oferta;
                                        int[] demanda;
                                        int[][] costo;

                                        nOrigenes=LeerOrigenes();
                                        nDestinos=LeerDestinos();

                                        oferta = oferta(nOrigenes);
                                        demanda=demanda(nDestinos);

                                        //Se verifica que sean igual si no se crea ficticio
                                        if (totalO != totalD){
                                            costo=balancear(oferta,demanda);
                                        }
                                        else {
                                            costo=new int[nOrigenes][nDestinos];
                                        }

                                        JFrame_Vogel AM=new JFrame_Vogel(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                                        AM.setResizable(true);
                                        AM.setLocationRelativeTo(null);
                                        AM.setVisible(true);

                                        /*	PrincipalVo vogel = new PrincipalVo();
                                        vogel.main();
                                        */
                                        }
                                    });
                                    break;
                            case 1:		
                                    Btn_Grafico = new JButton("Metodo MODI");
                                    Btn_Grafico.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent arg0) {
                                        dispose();
                                        MODI();
                                        /*
                                                PrincipalMODI modi = new PrincipalMODI();
                                        modi.main();
                                    */
                                    }
                                    });
                                    break;
                                    case 2:		
                                        Btn_Grafico = new JButton("Metodo Hungaro");
                                        Btn_Grafico.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent arg0) {
                                            dispose();
                                            int Origenes=LeerOrigenes();
                                            int Destinos=LeerDestinos();
                                            //Se verifica que sean igual si no se crea ficticio
                                            int Costos[][]=balancearOfertaD(Origenes,Destinos);
                                            int Oferta = 1;
                                            int Demanda = 1;

                                            //Interfaz Hungaro
                                            JFrame_Hungaro AS=new JFrame_Hungaro(Costos,Oferta,Demanda,Origenes,Destinos,destinoF, origenF);
                                            AS.setResizable(true);
                                            AS.setLocationRelativeTo(null);
                                            AS.setVisible(true);
                                            /*PrincipalHungaro hungaro = new PrincipalHungaro();
                                            hungaro.main();*/
                                        }
                                        });
                                        break;
                            }
                            Btn_Grafico.setHorizontalTextPosition(SwingConstants.CENTER);					
                            Btn_Grafico.setFocusPainted(false);
                            Btn_Grafico.setBorderPainted(false);
                            Btn_Grafico.setContentAreaFilled(false);
                            Btn_Grafico.setOpaque(false);
                            Btn_Grafico.setFont(new Font("Arial", Font.ITALIC, 21));
                            Btn_Grafico.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

                            Botones.add(Btn_Grafico);
                            Centro.add(Btn_Grafico);
                            }
                    }
            });
		
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBorder(null);
            scrollPane.setRequestFocusEnabled(false);
            scrollPane.setFocusable(false);
            scrollPane.setOpaque(false);
            JViewport viewport = new JViewport();
            viewport.setFocusable(false);
            viewport.setRequestFocusEnabled(false);
             //Component that need to be added in Scroll pane//
	    viewport.setView(Panel_Central);
	    viewport.setOpaque(false);
	    scrollPane.setViewport(viewport);
	    scrollPane.getViewport().setOpaque(false);
		contentPane.add(scrollPane, BorderLayout.CENTER);
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
    private int [][] balancearOfertaD (int nOrigenes, int nDestinos) {
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
    private int[] oferta(int nOrigenes){
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
    private int[] demanda(int nDestinos){
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
    private int [][] balancear(int [] oferta, int [] demanda){
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
    public void MODI(){
        int opcion = 1;
    	while (opcion != 0) {
    		boolean esNumero = false;
    		while (esNumero == false) {
                    try { //Manejo de excepciones
                        String res = JOptionPane.showInputDialog(null, "Metodo MODI con\nOpcion 1: Esquina Noroeste \nOpcion 2: Vogel\nOpcion 0: Salir","Metodo MODI",JOptionPane.QUESTION_MESSAGE);
                        opcion = Integer.parseInt(res);
                        esNumero = true;
                    }
                    catch(Exception er) {
                        esNumero = false;
                        JOptionPane.showMessageDialog(null, "Dato ingresado debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
                    }
    		}
    		
    		int[] oferta;
                int[] demanda;
                int[][] costo;
		    									
                nOrigenes=LeerOrigenes();
                nDestinos=LeerDestinos();

                oferta = oferta(nOrigenes);
	        demanda=demanda(nDestinos);
	        
	        //Se verifica que sean igual si no se crea ficticio
	        if (totalO != totalD){
	            costo=balancear(oferta,demanda);
	        }
	        else {
                    costo=new int[nOrigenes][nDestinos];
	        }
	        
    		switch (opcion) {
    		case 0:
                    System.out.println("Saliendo...");
                    break;
    		case 1:
                    caso=opcion;
                    //Metodo Esquina noroeste - MODI  
                    JFrame_EN AD=new JFrame_EN(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                    AD.setResizable(true);
                    AD.setLocationRelativeTo(null);
                    AD.setVisible(true);
                    opcion=0;
                    //inicializarEN();                                      
                    //Resultado de tabla MODI
                    //tablaMO.resultado(matrizPC, nOrigenes, nDestinos, matrizA, destino, origen);
                break;
    		case 2:  
                    //Metodo Vogel - MODI
                    caso=opcion;
                    JFrame_Vogel AM=new JFrame_Vogel(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                    AM.setResizable(true);
                    AM.setLocationRelativeTo(null);
                    AM.setVisible(true);
                    opcion=0;
                    //inicializarVo();
                    //Resultado de tabla MODI
                    //tablaMO.resultado(matrizPC, nOrigenes, nDestinos, matrizA, destino, origen);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Digite opcion valida", "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                
    		}
    	}
    	
    }
}
