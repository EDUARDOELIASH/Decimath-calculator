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
import validaciones.variables_independientes;




/** 
 *  Author: Gijon Vazquez Elias Domenick
 */

public class JFrame_Metodo extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	//Orden de distribucion
	private JPanel contentPane;
	private JPanel Panel_Central;
	private JPanel Panel_Titulo;
	
	private JPanel Panel_Formulario;
	private JPanel Centro;
	private JPanel Inferior;

	//Botones
	private ArrayList<JButton> Botones;
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
		Botones=new ArrayList<JButton>();
		c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");
		varind=new variables_independientes();
		
		//1. Creacion y parametros de la interfaz
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_Metodo.class.getResource("/Imagenes/mathematic-calculator.png")));
		setTitle("Matematicas para la Toma de Decisiones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		setMinimumSize(new Dimension(850,550));
		contentPane = new JPanel_Start();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//2. Creacion de Paneles y sus propiedades 
		
		Panel_Central = new JPanel();
		Panel_Titulo = new JPanel();
		
		
		Panel_Formulario = new JPanel();
		Centro = new JPanel();
		Inferior = new JPanel();
		
		
		Panel_Central.setOpaque(false);
		Panel_Titulo.setOpaque(false);
		
		
		Panel_Formulario.setOpaque(false);
		Inferior.setOpaque(false);
		Centro.setOpaque(false);

		
		//3. Creacion de Etiquetas y sus propiedades		
		
		// Diseño de padding
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;

		
		// Calcular el valor del padding superior en función del tamaño de la pantalla
		int topPadding = Math.max(10, (int) (screenHeight * 0.03));  // 10px o el 3% del alto de la pantalla, lo que sea mayor
        int leftPadding = 20;
        int bottomPadding = 10;
        int rightPadding = 20;

        
        JLabel lblNewLabel = new JLabel("<html><br></br><br></br></html>");
		JLabel Titulo_Bienvenida = new JLabel("\u00A1Bienvenido! Eliga la Opcion Deseada");
		JLabel Titulo_Instruccion = new JLabel("Seleccione el Metodo a Realizar");

		
		Titulo_Bienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo_Bienvenida.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo_Bienvenida.setFont(new Font("Arial", Font.BOLD, 28));
        Titulo_Bienvenida.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

        
		Titulo_Instruccion.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo_Instruccion.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo_Instruccion.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		Titulo_Instruccion.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
		
		
		//4. Creacion de contenedores de margen dentro del grid inferior
		
		JTextField txt_undeploy_grid;
		JTextField txt_undisplay_grid;
		
		
		txt_undeploy_grid = new JTextField();
		txt_undeploy_grid.setColumns(10);
		txt_undeploy_grid.setVisible(false);
		
		
		txt_undisplay_grid = new JTextField();
		txt_undisplay_grid.setColumns(10);
		txt_undisplay_grid.setVisible(false);
		
		
		//5. Creacion de Botones de Accion y propiedades
		
		Btn_ProgramacionLineal = new JButton("Tema 1: Programacion Lineal");
		Btn_MetodosTransporte = new JButton("<html>Tema 2: Metodos de Transporte<br>&emsp;&emsp;&emsp;&emsp;y Asignacion</br></html>");

		
		Btn_ProgramacionLineal.setHorizontalTextPosition(SwingConstants.CENTER);
		Btn_ProgramacionLineal.setFocusPainted(false);
		Btn_ProgramacionLineal.setContentAreaFilled(false);
		Btn_ProgramacionLineal.setBorderPainted(false);
		Btn_ProgramacionLineal.setOpaque(false);
		Btn_ProgramacionLineal.setFont(new Font("Arial", Font.ITALIC, 21));
		Btn_ProgramacionLineal.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		
		
		Btn_MetodosTransporte.setHorizontalTextPosition(SwingConstants.CENTER);
		Btn_MetodosTransporte.setFocusPainted(false);
		Btn_MetodosTransporte.setBorderPainted(false);
		Btn_MetodosTransporte.setContentAreaFilled(false);
		Btn_MetodosTransporte.setOpaque(false);
		Btn_MetodosTransporte.setFont(new Font("Arial", Font.ITALIC, 21));
		Btn_MetodosTransporte.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

		
		//6.Agregamos eventos a los botones

		Btn_ProgramacionLineal.addActionListener(this);
		Btn_MetodosTransporte.addActionListener(this);
		
				
		//7. Distribucion de la interfaz
		
		Panel_Central.setLayout(new BorderLayout(0, 0));
		Panel_Central.setPreferredSize(new Dimension(800,460));
		Panel_Central.add(Panel_Titulo, BorderLayout.NORTH);
		Panel_Central.add(Panel_Formulario, BorderLayout.CENTER);

		
		Panel_Titulo.setLayout(new GridLayout(0, 1, 0, 0));
		Panel_Titulo.add(lblNewLabel);
		Panel_Titulo.add(Titulo_Bienvenida);
		Panel_Titulo.add(Titulo_Instruccion);

		
		
		Panel_Formulario.setLayout(new BorderLayout(0, 0));
		Panel_Formulario.add(Centro, BorderLayout.CENTER);
		Panel_Formulario.add(Inferior, BorderLayout.SOUTH);
		
		
		Centro.setLayout(new GridLayout(0, 1, 0, 0));
		Centro.add(Btn_ProgramacionLineal);
		Centro.add(Btn_MetodosTransporte);
		
		
		Inferior.setLayout(new GridLayout(0, 1, 0, 0));
		Inferior.add(txt_undeploy_grid);
		Inferior.add(txt_undisplay_grid);
		
		
		//8.Mostramos interfaz
		
		contentPane.add(Panel_Central, BorderLayout.CENTER);
	}
	
	
	//Actualizacion de Interfaz
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Btn_ProgramacionLineal) {
        	 Btn_MetodosTransporte.setVisible(false);
             Btn_ProgramacionLineal.setVisible(false);
             Centro.remove(Btn_MetodosTransporte);
             Centro.remove(Btn_ProgramacionLineal);
             updateUI_Unit1();
             JOptionPane.showMessageDialog(null,"Tema 1: Programacion Lineal","Seleccion de Metodo",JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource()==Btn_MetodosTransporte) {
        	Btn_MetodosTransporte.setVisible(false);
            Btn_ProgramacionLineal.setVisible(false);
            Centro.remove(Btn_MetodosTransporte);
            Centro.remove(Btn_ProgramacionLineal);
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
                        Btn_MetodosPersonalizados = new JButton("Metodo Grafico");
                        Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                        	Metodo_Grafico();
                        }});
                        break;
                    case 1:		
                        Btn_MetodosPersonalizados = new JButton("Metodo Simplex");
                        Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            Metodo_Simplex();
                        }});
                        break;
                    case 2:		
                        Btn_MetodosPersonalizados = new JButton("Metodo Dos Fases");
                        Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            Metodo_2Fases();
                        }});
                        break;
                }
                Btn_MetodosPersonalizados.setHorizontalTextPosition(SwingConstants.CENTER);

                Btn_MetodosPersonalizados.setFocusPainted(false);
                Btn_MetodosPersonalizados.setBorderPainted(false);
                Btn_MetodosPersonalizados.setContentAreaFilled(false);
                Btn_MetodosPersonalizados.setOpaque(false);
                Btn_MetodosPersonalizados.setFont(new Font("Arial", Font.ITALIC, 21));
                Btn_MetodosPersonalizados.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

                Botones.add(Btn_MetodosPersonalizados);
                Centro.add(Btn_MetodosPersonalizados);
        }
    }
    public void updateUI_Unit2() {
        //Sub Botones
        for(int i=0;i<3;i++) {
            JButton Btn_MetodosPersonalizados = null;
            switch(i) {
                case 0:		
                    Btn_MetodosPersonalizados = new JButton("Metodo Vogel");
                    Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Metodo_Vogel();
                    }});
                    break;
	            case 1:		
                    Btn_MetodosPersonalizados = new JButton("Metodo MODI");
                    Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Metodo_MODI();
                    }});
                    break;
	            case 2:		
                    Btn_MetodosPersonalizados = new JButton("Metodo Hungaro");
                    Btn_MetodosPersonalizados.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        Metodo_Hungaro();
                    }});
                    break;
            }
            Btn_MetodosPersonalizados.setHorizontalTextPosition(SwingConstants.CENTER);					
            Btn_MetodosPersonalizados.setFocusPainted(false);
            Btn_MetodosPersonalizados.setBorderPainted(false);
            Btn_MetodosPersonalizados.setContentAreaFilled(false);
            Btn_MetodosPersonalizados.setOpaque(false);
            Btn_MetodosPersonalizados.setFont(new Font("Arial", Font.ITALIC, 21));
            Btn_MetodosPersonalizados.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

            Botones.add(Btn_MetodosPersonalizados);
            Centro.add(Btn_MetodosPersonalizados);
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
        JFrame_Grafico AS=new JFrame_Grafico();
        AS.setResizable(true);
        AS.setLocationRelativeTo(null);
        AS.setVisible(true);
        
    }
	
	public void Metodo_Simplex() {
		initUnit1();
        dispose();
        JFrame_Simplex AS=new JFrame_Simplex(NR,VAR);
        AS.setResizable(true);
        AS.setLocationRelativeTo(null);
        AS.setVisible(true);
    }
	
	public void Metodo_2Fases() {
		initUnit1();
        dispose();
        JFrame_2Fases AS=new JFrame_2Fases(NR,VAR);
        AS.setResizable(true);
        AS.setLocationRelativeTo(null);
        AS.setVisible(true);
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
		dispose();
        initUnit2();

        JFrame_Vogel AM=new JFrame_Vogel(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
        AM.setResizable(true);
        AM.setLocationRelativeTo(null);
        AM.setVisible(true);
	}
	
	
    public void Metodo_MODI(){
        dispose();
        initUnit2();
        
    	int opcion = varind.optionMODI();
        
		switch (opcion) {
		case 1:
                caso=opcion;
                //Metodo Esquina noroeste - MODI  
                JFrame_EN AD=new JFrame_EN(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                AD.setResizable(true);
                AD.setLocationRelativeTo(null);
                AD.setVisible(true);
                opcion=0;
                break;
		case 2:  
                //Metodo Vogel - MODI
                caso=opcion;
                JFrame_Vogel AM=new JFrame_Vogel(costo,oferta,demanda,nOrigenes,nDestinos,totalO,totalD,oFicticio,dFicticio,caso);
                AM.setResizable(true);
                AM.setLocationRelativeTo(null);
                AM.setVisible(true);
                opcion=0;
                break;
		}
    }
    public void Metodo_Hungaro() {
    	dispose();
    	
        int Origenes=varind.LeerOrigenes();
        int Destinos=varind.LeerDestinos();
        //Se verifica que sean igual si no se crea ficticio
        int Costos[][]=varind.balancearOfertaD(Origenes,Destinos);
        int Oferta = 1;
        int Demanda = 1;

        //Interfaz Hungaro
        JFrame_Hungaro AS=new JFrame_Hungaro(Costos,Oferta,Demanda,Origenes,Destinos,destinoF, origenF);
        AS.setResizable(true);
        AS.setLocationRelativeTo(null);
        AS.setVisible(true);
    }
}
