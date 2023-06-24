package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//Paquete.clase
import objects.JPanel_transparent;
import objects.txt_unseen;
import objects.JScrollPane_transparent;
import objects.JViewport_transparent;
import objects.JTabbedPane_create_tabs;
import validaciones.campos_interfaz;
import metodo2Fases.Datos2F;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JViewport;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/** 
 *  Author: Gijón Vazquez Elias Domenick
 */
public class JFrame_2Fases extends JPanel implements ActionListener {
	int nomVariable_indepent,numberRestriction;
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JTextField> Coeficiente;
	private ArrayList<JTextField> Objetivo;
	private ArrayList<JComboBox<String>> Simbolo;
	public JComboBox<String> comboBox_option;

/*	
	private ArrayList<JButton> Botones;
	private ArrayList<JLabel> Xn;
	private ArrayList<JPanel> Nuevo;
	private ArrayList<JPanel> NuevoPanel2;
	private ArrayList<JPanel> G;
	
	private JPanel contentPane;
	private txt_unseen txt_undisplay;
	boolean dato;
*/
	
	JButton Btn_Summit;
	JButton Btn_createTabs;
	
	JTabbedPane tabbedPane;
	final Color c;
	
	
	/**
	 * Create the frame.
	 */
	public JFrame_2Fases( int numberRestriction,  int nomVariable_indepent) {
		this.numberRestriction=numberRestriction;
		this.nomVariable_indepent=nomVariable_indepent;
/*		
		Xn=new ArrayList<>();
		Coeficiente=new ArrayList<JTextField>();
		Objetivo=new ArrayList<JTextField>();
		Simbolo=new ArrayList<JComboBox>();
		Botones=new ArrayList<JButton>();
		Nuevo=new ArrayList<JPanel>();
		NuevoPanel2=new ArrayList<JPanel>();
		G=new ArrayList<JPanel>();
*/
		c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");

		//1. Creacion y parametros de la interfaz
		setBounds(100, 100, 850, 550);
		setMinimumSize(new Dimension(850,550));
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		
		//2. Creacion de Paneles y sus propiedades 
		JPanel Panel_Central = new JPanel_transparent();
		JPanel Panel_Titulo = new JPanel_transparent();
		JPanel Panel_Formulario = new JPanel_transparent();
		
		JPanel Centro = new JPanel_transparent();
		
		JPanel LlenarCampos = new JPanel_transparent();
		JPanel Confirmar = new JPanel_transparent();
		
		JPanel Izquierda = new JPanel_transparent();
		JPanel Derecho = new JPanel_transparent();
		JPanel Inferior = new JPanel_transparent();


		Panel_Central.setOpaque(false);
		Panel_Central.setPreferredSize(new Dimension(800,460));
		
		LlenarCampos.setRequestFocusEnabled(false);
		LlenarCampos.setLayout(new BorderLayout(0, 0));
		
		
		//3. Creacion de Etiquetas y sus propiedades		
		
		// Diseño de padding
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;

		
		// Calcular el valor del padding superior en función del tamaño de la pantalla
		int topPadding = Math.max(10, (int) (screenHeight * 0.03));  // 10px o el 3% del alto de la pantalla, lo que sea mayor
        int leftPadding = 20;
        int bottomPadding = 10;
        int rightPadding = 20;	        

		JLabel Titulo = new JLabel("Metodo Dos Fases");
		JLabel Titulo_1 = new JLabel("Porfavor Complete la Siguiente Informacion");

		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 28));
		Titulo.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));

		Titulo_1.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));

		
		//4. Creacion de contenedor
		//Contenedor de Pestañas
		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		
		//Dont take color can be moved to constructor gui
		tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
			protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
		});
		
		//ComboBox
		comboBox_option=new JComboBox<String>();  
	    comboBox_option.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_option.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione Un Objetivo:", "Maximizar", "Minimizar"}));
		
	
		//5. Creacion de elementos de Accion y propiedades
		//Botones
		Btn_Summit = new JButton("Enviar Formulario");
		Btn_createTabs = new JButton("<html><p align=\"center\">Agregar<br/>Restriccion</p></html>");
		
		
		//6.Agregamos eventos a los botones
		Btn_Summit.addActionListener(this);
		Btn_createTabs.addActionListener(this);
		
		//inicializamos pestañas
		Btn_createTabs.doClick();
		
		
		//7. Distribucion de la interfaz
		Panel_Central.setLayout(new BorderLayout(0, 0));
		Panel_Central.add(Panel_Titulo, BorderLayout.NORTH);
		Panel_Central.add(Panel_Formulario, BorderLayout.CENTER);
		
		Panel_Titulo.setLayout(new GridLayout(0, 1, 0, 0));
		Panel_Titulo.add(new txt_unseen());
		Panel_Titulo.add(Titulo);
		Panel_Titulo.add(Titulo_1);

		
		Panel_Formulario.setLayout(new BorderLayout(0, 0));
		Panel_Formulario.add(Centro, BorderLayout.CENTER);
		Panel_Formulario.add(Izquierda, BorderLayout.WEST);
		Panel_Formulario.add(Derecho, BorderLayout.EAST);
		Panel_Formulario.add(Inferior, BorderLayout.SOUTH);

		
		Centro.setLayout(new BorderLayout(0, 0));
		Centro.add(LlenarCampos, BorderLayout.CENTER);
		Centro.add(Confirmar, BorderLayout.SOUTH);
		
		LlenarCampos.add(tabbedPane);
		
		Confirmar.setLayout(new GridLayout(2, 0, 0, 0));
		Confirmar.add(Btn_Summit);
		
		
		Izquierda.setLayout(new GridLayout(0, 1, 0, 0));
		Izquierda.add(new txt_unseen());

		Derecho.setLayout(new GridLayout(0, 1, 0, 0));
		Derecho.add(new txt_unseen());	
		
		Inferior.setLayout(new GridLayout(3, 0, 0, 0));
		Inferior.add(new txt_unseen());

		
		//8.Mostramos interfaz
		JScrollPane scrollPane = new JScrollPane_transparent();
		JViewport viewport = new JViewport_transparent();
		
		//Component that need to be added in Scroll pane//
	    viewport.setView(Panel_Central);
	    scrollPane.setViewport(viewport);
	    scrollPane.getViewport().setOpaque(false);
	    add(scrollPane, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Btn_Summit) {
			campos_interfaz validacionCampos= new campos_interfaz();
			
			//Funcion Objetivo Campos
			double [] nomVariable_indepentdecision = validacionCampos.FuncionObjetivo(nomVariable_indepent,Objetivo);
			
			//Objetivo: Maximizar o Minimizar
			char OBJETIVO = validacionCampos.objetivo(comboBox_option.getSelectedIndex());
			
			//Pasar Arraylist de Xn y Resultado a Arreglo Bidimensional
			double[][] restriccionI = validacionCampos.RecorrerArray(numberRestriction,nomVariable_indepent,Coeficiente,Simbolo);
			
			//Simbolo logico por Restriccion
			int[] simbolo= validacionCampos.simbolo(numberRestriction, Simbolo);

			
			boolean isDataValid = ((nomVariable_indepentdecision != null) && (restriccionI != null) && (simbolo != null));
			if (isDataValid){
				//SOLO ENTRA SI YA CONSIGUIO LOS DATOS QUE REQUERIA, DE AQUI SIGUE LA TABLA
				String [] SimbolosFN=validacionCampos.SimbolosFN(numberRestriction, simbolo);
	
				
                restriccionI = validacionCampos.ladoDerechoNN(restriccionI, nomVariable_indepent, numberRestriction, SimbolosFN);
				int Sigma=numberRestriction+nomVariable_indepent;
				
				Datos2F siguiente=new Datos2F(nomVariable_indepentdecision,OBJETIVO,restriccionI,SimbolosFN,Sigma,numberRestriction,nomVariable_indepent);
				siguiente.varHolguras();
                siguiente.despejarZ();
                siguiente.tabla2F();
			}
		}
		if(e.getSource()==Btn_createTabs) {
			JTabbedPane_create_tabs init_dinamic_tabs=new JTabbedPane_create_tabs(numberRestriction, nomVariable_indepent);	
			
			tabbedPane=init_dinamic_tabs.create_tabs(tabbedPane, comboBox_option);
			
			Coeficiente=init_dinamic_tabs.Coeficiente;
			Simbolo=init_dinamic_tabs.Simbolo;
			Objetivo=init_dinamic_tabs.Objetivo;
		}
	}
}