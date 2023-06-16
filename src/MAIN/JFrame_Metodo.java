package MAIN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

//clases del proyecto
import GUI.JFrame_2Fases;
import GUI.JFrame_EN;
import GUI.JFrame_Grafico;
import GUI.JFrame_Hungaro;
import GUI.JFrame_Simplex;
import GUI.JFrame_Vogel;
import GUI.JPanel_Start;
import GUI.JButton_transparent;
import validaciones.variables_independientes;




/** 
 *  Author: Gijon Vazquez Elias Domenick
 */

public class JFrame_Metodo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	//Orden de distribucion
	private JPanel JP_BG_IMG;
	private JPanel JP_Contenido;
	private JPanel JP_Titulo;
	
	private JPanel JP_Formulario;
	private JPanel JP_Metodos;
	private JPanel JP_Margen;

	//Botones
	//private ArrayList<JButton> Botones;
	private JButton Btn_ProgramacionLineal;
	private JButton Btn_MetodosTransporte;
	
	//background
	final Color c;
	
	
	//Validaciones de datos para procesos
	variables_independientes varind;
	
	//Variables de Metodos
	boolean destinoF=false, origenF=false;
	boolean oFicticio = false, dFicticio = false;
	int totalO = 0, totalD = 0;
	int nOrigenes, nDestinos;
	int caso=0;
	
	int NR,VAR;
	
	int[] oferta;
    int[] demanda;
    int[][] costo;

	/**
	 * CONSTRUCTOR
	 */
	public JFrame_Metodo() {
		//Objetos de Interfaz globales
		c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");
		varind=new variables_independientes();
		
		//1. Creacion y parametros de la interfaz
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_Metodo.class.getResource("/Imagenes/mathematic-calculator.png")));
		setTitle("Matematicas para la Toma de Decisiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		setMinimumSize(new Dimension(850,550));
		JP_BG_IMG = new JPanel_Start();
		JP_BG_IMG.setBorder(new EmptyBorder(5, 5, 5, 5));
		JP_BG_IMG.setLayout(new BorderLayout(0, 0));
		setContentPane(JP_BG_IMG);
		
		
		//2. Creacion de Paneles y sus propiedades 
		
		JP_Contenido = new JPanel();
		JP_Titulo = new JPanel();
		
		
		JP_Formulario = new JPanel();
		JP_Metodos = new JPanel();
		JP_Margen = new JPanel();
		
		
		JP_Contenido.setOpaque(false);
		JP_Titulo.setOpaque(false);
		
		
		JP_Formulario.setOpaque(false);
		JP_Margen.setOpaque(false);
		JP_Metodos.setOpaque(false);

		
		//3. Creacion de Etiquetas y sus propiedades		
		
		// Diseño de padding
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;

		
		// Calcular el valor del padding superior en función del tamaño de la pantalla
		int topPadding = Math.max(10, (int) (screenHeight * 0.03));  // 10px o el 3% del alto de la pantalla, lo que sea mayor
        int leftPadding = 20;
        int bottomPadding = 10;
        int rightPadding = 20;

        
        JLabel lbl_blankspace = new JLabel("<html><br></br><br></br></html>");
		JLabel lbl_Titulo_Bienvenida = new JLabel("\u00A1Bienvenido! Eliga la Opcion Deseada");
		JLabel lbl_Titulo_Instruccion = new JLabel("Seleccione el Metodo a Realizar");

		
		lbl_Titulo_Bienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Titulo_Bienvenida.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_Titulo_Bienvenida.setFont(new Font("Arial", Font.BOLD, 28));
        lbl_Titulo_Bienvenida.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        
		lbl_Titulo_Instruccion.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_Titulo_Instruccion.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Titulo_Instruccion.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		lbl_Titulo_Instruccion.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
		
		
		//4. Creacion de contenedores de margen dentro del grid JP_Margen
		
		JTextField txt_undeploy_grid;
		JTextField txt_undisplay_grid;
		
		
		txt_undeploy_grid = new JTextField();
		txt_undeploy_grid.setColumns(10);
		txt_undeploy_grid.setVisible(false);
		
		
		txt_undisplay_grid = new JTextField();
		txt_undisplay_grid.setColumns(10);
		txt_undisplay_grid.setVisible(false);
		
		
		//5. Creacion de Botones de Accion y propiedades
		
		Btn_ProgramacionLineal = new JButton_transparent("Tema 1: Programacion Lineal");
		Btn_MetodosTransporte = new JButton_transparent("<html>Tema 2: Metodos de Transporte<br>&emsp;&emsp;&emsp;&emsp;y Asignacion</br></html>");

		
		//6.Agregamos eventos a los botones

		Btn_ProgramacionLineal.addActionListener(this);
		Btn_MetodosTransporte.addActionListener(this);
		
				
		//7. Distribucion de la interfaz
		
		JP_Contenido.setLayout(new BorderLayout(0, 0));
		JP_Contenido.setPreferredSize(new Dimension(800,460));
		JP_Contenido.add(JP_Titulo, BorderLayout.NORTH);
		JP_Contenido.add(JP_Formulario, BorderLayout.CENTER);

		
		JP_Titulo.setLayout(new GridLayout(0, 1, 0, 0));
		JP_Titulo.add(lbl_blankspace);
		JP_Titulo.add(lbl_Titulo_Bienvenida);
		JP_Titulo.add(lbl_Titulo_Instruccion);

		
		
		JP_Formulario.setLayout(new BorderLayout(0, 0));
		JP_Formulario.add(JP_Metodos, BorderLayout.CENTER);
		JP_Formulario.add(JP_Margen, BorderLayout.SOUTH);
		
		
		JP_Metodos.setLayout(new GridLayout(0, 1, 0, 0));
		JP_Metodos.add(Btn_ProgramacionLineal);
		JP_Metodos.add(Btn_MetodosTransporte);
		
		
		JP_Margen.setLayout(new GridLayout(0, 1, 0, 0));
		JP_Margen.add(txt_undeploy_grid);
		JP_Margen.add(txt_undisplay_grid);
		
		
		//8.Mostramos interfaz
		
		JP_BG_IMG.add(JP_Contenido, BorderLayout.CENTER);
	}
	
	
	//Actualizacion de Interfaz
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Btn_ProgramacionLineal) {
        	 Btn_MetodosTransporte.setVisible(false);
             Btn_ProgramacionLineal.setVisible(false);
             JP_Metodos.remove(Btn_MetodosTransporte);
             JP_Metodos.remove(Btn_ProgramacionLineal);
             updateUI_Unit1();
             JOptionPane.showMessageDialog(null,"Tema 1: Programacion Lineal","Seleccion de Metodo",JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource()==Btn_MetodosTransporte) {
        	Btn_MetodosTransporte.setVisible(false);
            Btn_ProgramacionLineal.setVisible(false);
            JP_Metodos.remove(Btn_MetodosTransporte);
            JP_Metodos.remove(Btn_ProgramacionLineal);
            updateUI_Unit2();
            JOptionPane.showMessageDialog(null,"Tema 2: Metodos de Transporte y Asignacion","Seleccion de Metodo",JOptionPane.INFORMATION_MESSAGE);
       }
    }
	 
    public void updateUI_Unit1() {
        //Sub Botones Tema 1
        for(int i=0;i<3;i++) {
                JButton Btn_MetodosPersonalizados = null;
                switch(i) {
                    case 0:		
                        Btn_MetodosPersonalizados= new JButton_transparent("Metodo Grafico");
                        Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                        	Metodo_Grafico();
                        }});
                        break;
                    case 1:		
                        Btn_MetodosPersonalizados = new JButton_transparent("Metodo Simplex");
                        Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            Metodo_Simplex();
                        }});
                        break;
                    case 2:		
                        Btn_MetodosPersonalizados = new JButton_transparent("Metodo Dos Fases");
                        Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            Metodo_2Fases();
                        }});
                        break;
                }

                JP_Metodos.add(Btn_MetodosPersonalizados);
        }
    }
    public void updateUI_Unit2() {
        //Sub Botones
        for(int i=0;i<3;i++) {
            JButton Btn_MetodosPersonalizados = null;
            switch(i) {
                case 0:		
                    Btn_MetodosPersonalizados = new JButton_transparent("Metodo Vogel");
                    Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Metodo_Vogel();
                    }});
                    break;
	            case 1:		
                    Btn_MetodosPersonalizados = new JButton_transparent("Metodo MODI");
                    Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Metodo_MODI();
                    }});
                    break;
	            case 2:		
                    Btn_MetodosPersonalizados = new JButton_transparent("Metodo Hungaro");
                    Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Metodo_Hungaro();
                    }});
                    break;
            }
          
            JP_Metodos.add(Btn_MetodosPersonalizados);
        }
    }
	
    //Obtencion de Variables Independientes del Tema 1
	public void initUnit1() {
		NR=varind.LeerRes();
        VAR=varind.LeerVar();
    }
	
	//Despliege de Interfaces Tema 1
	public void Metodo_Grafico() {
		dispose();							
        JFrame_Grafico startMethod_Grafico=new JFrame_Grafico();
        startMethod_Grafico.setResizable(true);
        startMethod_Grafico.setLocationRelativeTo(null);
        startMethod_Grafico.setVisible(true);
        
    }
	
	public void Metodo_Simplex() {
		initUnit1();
        dispose();
        JFrame_Simplex startMethod_Simplex=new JFrame_Simplex(NR,VAR);
        startMethod_Simplex.setResizable(true);
        startMethod_Simplex.setLocationRelativeTo(null);
        startMethod_Simplex.setVisible(true);
    }
	
	public void Metodo_2Fases() {
		initUnit1();
		JPanel JP_Metodo_2Fases=new JFrame_2Fases(NR,VAR);
		JP_BG_IMG.removeAll();
        JP_BG_IMG.add(JP_Metodo_2Fases, BorderLayout.CENTER);
        
        JP_BG_IMG.revalidate();
        JP_BG_IMG.repaint();
        
        JOptionPane.showMessageDialog(null,"Tema 2: Metodos de Transporte y Asignacion","Seleccion de Metodo",JOptionPane.INFORMATION_MESSAGE);

        /*dispose();
        JFrame_2Fases startMethod_2Fases=new JFrame_2Fases(NR,VAR);
        startMethod_2Fases.setResizable(true);
        startMethod_2Fases.setLocationRelativeTo(null);
        startMethod_2Fases.setVisible(true);*/
    }
	
    //Obtencion de Variables Independientes Tema 2
	public void initUnit2() {
    	
        nOrigenes=varind.LeerOrigenes();
        nDestinos=varind.LeerDestinos();

        oferta = varind.oferta(nOrigenes);
        demanda=varind.demanda(nDestinos);
        
        //Se verifica que sean igual si no se crea ficticio
        if (totalO != totalD){
            costo=varind.balancear(oferta,demanda);
        }
        else {
                costo=new int[nOrigenes][nDestinos];
        }
    }
	
	
	//Despliege de Interfaces del Tema 1
	public void Metodo_Vogel() {
        initUnit2();
		dispose();

        JFrame_Vogel startMethod_Vogel=new JFrame_Vogel(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
        startMethod_Vogel.setResizable(true);
        startMethod_Vogel.setLocationRelativeTo(null);
        startMethod_Vogel.setVisible(true);
	}
	
	
    public void Metodo_MODI(){
        initUnit2();
        dispose();

    	caso = varind.optionMODI();
        
		switch (caso) {
		case 1:
                //Metodo Esquina noroeste - MODI  
                JFrame_EN startMethod_EN=new JFrame_EN(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                startMethod_EN.setResizable(true);
                startMethod_EN.setLocationRelativeTo(null);
                startMethod_EN.setVisible(true);
                break;
		case 2:  
                //Metodo Vogel - MODI
                JFrame_Vogel startMethod_MV=new JFrame_Vogel(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                startMethod_MV.setResizable(true);
                startMethod_MV.setLocationRelativeTo(null);
                startMethod_MV.setVisible(true);
                break;
		}
    }
    public void Metodo_Hungaro() {    	
        int Origenes=varind.LeerOrigenes();
        int Destinos=varind.LeerDestinos();
        //Se verifica que sean igual si no se crea ficticio
        int Costos[][]=varind.balancearOfertaD(Origenes,Destinos);
        int Oferta = 1;
        int Demanda = 1;
    	
        dispose();

        //Interfaz Hungaro
        JFrame_Hungaro startMethod_Hungaro=new JFrame_Hungaro(Costos,Oferta,Demanda,Origenes,Destinos,destinoF, origenF);
        startMethod_Hungaro.setResizable(true);
        startMethod_Hungaro.setLocationRelativeTo(null);
        startMethod_Hungaro.setVisible(true);
    }
}
