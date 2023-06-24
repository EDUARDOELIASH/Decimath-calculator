/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MAIN.JFrame_Metodo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

//Paquete.clase
import objects.JPanel_transparent;
import objects.txt_unseen;
import objects.JScrollPane_transparent;
import objects.JViewport_transparent;
import objects.TextPrompt;
import objects.JTabbedPane_create_tabs;
import validaciones.campos_interfaz;
import metodo2Fases.Datos2F;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JViewport;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class JFrame_2Fases extends JPanel implements ActionListener {
	int nomVariable_indepent,numberRestriction;
	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> Botones;
	private ArrayList<JLabel> Xn;
	private ArrayList<JTextField> Coeficiente;
	private ArrayList<JTextField> Objetivo;
	private ArrayList<JComboBox> Simbolo;
	private ArrayList<JPanel> Nuevo;
	private ArrayList<JPanel> NuevoPanel2;
	private ArrayList<JPanel> G;
	public JComboBox comboBox_option;
	
//	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	boolean dato;
	
	
	
	
	
	JButton Btn_Summit;
	JButton Btn_Res;
	
	JTabbedPane tabbedPane;
	final Color c;
	
	
	/**
	 * Create the frame.
	 */
	public JFrame_2Fases(final int numberRestriction, final int nomVariable_indepent) {
		this.numberRestriction=numberRestriction;
		this.nomVariable_indepent=nomVariable_indepent;
		
		Botones=new ArrayList<JButton>();
		//Xn=new ArrayList<>();
		//Coeficiente=new ArrayList<JTextField>();
		//Objetivo=new ArrayList<JTextField>();
		//Simbolo=new ArrayList<JComboBox>();
		Nuevo=new ArrayList<JPanel>();
		NuevoPanel2=new ArrayList<JPanel>();
		G=new ArrayList<JPanel>();

		
		

		
		
		comboBox_option=new JComboBox();

		c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");

		//1. Creacion y parametros de la interfaz


		//setIconImage(Toolkit.getDefaultToolkit().getImage(JFrame_Metodo.class.getResource("/Imagenes/mathematic-calculator.png")));
		//setTitle("Matematicas para la Toma de Decisiones");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		setMinimumSize(new Dimension(850,550));
		setOpaque(false);
		//contentPane = new JPanel_Start();
		
		setLayout(new BorderLayout(0, 0));
		//add(contentPane);

		
		//2. Creacion de Paneles y sus propiedades 
		
		
		JPanel Panel_Central = new JPanel_transparent();
		JPanel Panel_Titulo = new JPanel_transparent();
		JPanel panel = new JPanel_transparent();
		JPanel Panel_Formulario = new JPanel_transparent();
		JPanel Inferior = new JPanel_transparent();
		JPanel Izquierda = new JPanel_transparent();
		JPanel Derecho = new JPanel_transparent();
		JPanel Centro = new JPanel_transparent();
		JPanel Confirmar = new JPanel_transparent();
		JPanel LlenarCampos = new JPanel_transparent();

		Panel_Central.setOpaque(false);
		Panel_Central.setPreferredSize(new Dimension(800,460));
		//contentPane.add(Panel_Central, BorderLayout.CENTER);
		
		
		/* USE OBJECT JPANEL CLASS
		
		Panel_Titulo.setOpaque(false);
		panel.setOpaque(false);
		Panel_Formulario.setOpaque(false);
		Inferior.setOpaque(false);
		Centro.setOpaque(false);
		Derecho.setOpaque(false);
		Izquierda.setOpaque(false);
		Confirmar.setOpaque(false);
		LlenarCampos.setOpaque(false);

		LlenarCampos.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

		*/

		
		
		
		
		LlenarCampos.setRequestFocusEnabled(false);
		LlenarCampos.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lblNewLabel = new JLabel("<html><br></br><br></br></html>");
		

		
		JLabel Titulo = new JLabel("Metodo Dos Fases");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 28));
		
		JLabel Titulo_1 = new JLabel("Porfavor Complete la Siguiente Informacion");
		Titulo_1.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		
		
		
		
		textField = new txt_unseen();
		textField_3 = new txt_unseen();
		textField_1 = new txt_unseen();
		textField_2  = new txt_unseen();
		
		
		
		Btn_Summit = new JButton("Enviar Formulario");
		Btn_Summit.addActionListener(this);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		//Dont take color can be moved to constructor gui
		tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
			protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
		});
		
		
		Btn_Res = new JButton("<html><p align=\"center\">Agregar<br/>Restriccion</p></html>");
		Btn_Res.setBorder(null);
		Btn_Res.setBorderPainted(false);
		Btn_Res.setHorizontalTextPosition(SwingConstants.CENTER);
		Btn_Res.setAlignmentX(Component.CENTER_ALIGNMENT);
		Btn_Res.setActionCommand("<html><p align=\"center\">Agregar  <br/>Restriccion</p></html>");
		Btn_Res.setBounds(0, 0, 53, 29);
		
		Btn_Res.addActionListener(this);
		Btn_Res.doClick();
		
		
		
		
		
		Panel_Central.setLayout(new BorderLayout(0, 0));
		Panel_Central.add(Panel_Titulo, BorderLayout.NORTH);
		Panel_Central.add(Panel_Formulario, BorderLayout.CENTER);
		
		Panel_Titulo.setLayout(new GridLayout(0, 1, 0, 0));
		Panel_Titulo.add(lblNewLabel);

		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(13);
		Panel_Titulo.add(panel);
		panel.add(Titulo);
		Panel_Titulo.add(Titulo_1);

		
		Panel_Formulario.setLayout(new BorderLayout(0, 0));
		Panel_Formulario.add(Centro, BorderLayout.CENTER);
		Panel_Formulario.add(Izquierda, BorderLayout.WEST);
		Panel_Formulario.add(Derecho, BorderLayout.EAST);
		Panel_Formulario.add(Inferior, BorderLayout.SOUTH);

		
		Centro.setLayout(new BorderLayout(0, 0));
		Centro.add(LlenarCampos, BorderLayout.CENTER);
		Centro.add(Confirmar, BorderLayout.SOUTH);
		
		
		
		Izquierda.setLayout(new GridLayout(0, 1, 0, 0));
		Izquierda.add(textField_1);

		
		Derecho.setLayout(new GridLayout(0, 1, 0, 0));
		Derecho.add(textField_2);
		
		
		Inferior.setLayout(new GridLayout(0, 1, 0, 0));
		Inferior.add(textField);
		Inferior.add(textField_3);
		
		
		
		
		Confirmar.add(Btn_Summit);
		LlenarCampos.add(tabbedPane);
		
		
		JScrollPane scrollPane = new JScrollPane_transparent();
		/*scrollPane.setBorder(null);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.setFocusable(false);
		scrollPane.setOpaque(false);
		*/
		
		JViewport viewport = new JViewport_transparent();
/*		viewport.setFocusable(false);
		viewport.setRequestFocusEnabled(false);
	    viewport.setOpaque(false);
*/
		//Component that need to be added in Scroll pane//
	    viewport.setView(Panel_Central);
	    scrollPane.setViewport(viewport);
	    scrollPane.getViewport().setOpaque(false);
	    add(scrollPane, BorderLayout.CENTER);
	    
	    
	    
	  
	    comboBox_option.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_option.setModel(new DefaultComboBoxModel(new String[] {"Seleccione Un Objetivo:", "Maximizar", "Minimizar"}));
		
	    
	    //ALL THIS WAS NECESESARY?
  		//Para ver la GUI en la pestaña Diseño Documentar toda la parte del setIU
  		/*tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
  			  protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
  			});*/
  		
  		
  		/*JPanel UY = new JPanel();
  		UY.setRequestFocusEnabled(false);
  		UY.setDoubleBuffered(false);
  		UY.setFocusable(false);
  		UY.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
  		UY.setOpaque(false);
  		UY.setLayout(new BorderLayout(0, 0));
  		int tabCount = tabbedPane.getTabCount();
  		//UY.add(new JLabel("I'm tab " + tabCount));
  		//tabbedPane.addTab("Tab " +tabCount, UY);
  		//tabbedPane.setEnabledAt(0, true);*/
	    
	    
	    /* DONT USED
	     * 
		Btn_Res.setVisible(false);
		add(Btn_Res);
		contentPane.add(Btn_Res);*/
		/*
		
		JScrollPane scrollPane = new JScrollPane(Panel_Central);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(null);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Ajusta la velocidad de desplazamiento vertical

		add(scrollPane, BorderLayout.CENTER);

		
		*/
		
		
		//contentPane.add(scrollPane, BorderLayout.CENTER);

		/*
		JScrollPane scrollPane = new JScrollPane(Panel_Central);
		scrollPane.setBorder(null);
		scrollPane.getViewport().setOpaque(false);

		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPane.setPreferredSize(new Dimension(Panel_Central.getPreferredSize().width, 300));

		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        // Configurar los colores de la barra de desplazamiento vertical
		        this.thumbColor = new Color(200, 200, 200, 100);
		        this.thumbDarkShadowColor = new Color(200, 200, 200, 100);
		        this.thumbHighlightColor = new Color(200, 200, 200, 100);
		        this.thumbLightShadowColor = new Color(200, 200, 200, 100);
		    }
		});

		Panel_Central.setPreferredSize(new Dimension(Panel_Central.getPreferredSize().width, 0));

		add(scrollPane, BorderLayout.CENTER);*/
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Btn_Summit) {
			campos_interfaz validacionCampos= new campos_interfaz();

			
			int n = 0;
			
			
			//Funcion Objetivo Campos
			double [] nomVariable_indepentdecision = validacionCampos.FuncionObjetivo(nomVariable_indepent,Objetivo);
			
			//Objetivo: Maximizar o Minimizar
			char OBJETIVO = validacionCampos.objetivo(comboBox_option.getSelectedIndex());
			
			//Pasar Arraylist de Xn y Resultado a Arreglo Bidimensional
			double[][] restriccionI = validacionCampos.RecorrerArray(numberRestriction,nomVariable_indepent,Coeficiente,Simbolo);
			
			//Simbolo logico por Restriccion
			int[] simbolo= validacionCampos.simbolo(numberRestriction, Simbolo);

			
			
			
			
			
			
			//dato=true;
			
			
			
			
			
			
			
		/*	
			int[] simbolo = new int [numberRestriction];
			boolean simb=true;
			for(int i=0;i<numberRestriction;i++) {
				JComboBox Sim = Simbolo.get(i);
				if(Sim.getSelectedIndex()!=0) {
					simbolo[i]=Sim.getSelectedIndex();
				}
				else {
					simb=false;
					//System.out.println("En la Restriccion "+(i+1)+" se encuentra un simbolo logico dentro de comboBox sin seleccionar");
				}
			}
		*/	
			
			
			
			/*
			
			double [] nomVariable_indepentdecision=new double [nomVariable_indepent+1];
			boolean FO=true;
			for(int i=0;i<nomVariable_indepent;i++){
				JTextField ob = Objetivo.get(i);
				try {
					double verificacion=Double.parseDouble(ob.getText());
					nomVariable_indepentdecision[i]=verificacion;
					//System.out.println(nomVariable_indepentdecision[i]);
				}
				catch(Exception error) {
					//System.out.println("Coeficiente no valido");
					FO=false;
					break;
				}
			}
			*/
			
			
			
			
			
			
			/*
			char OBJETIVO=' ';
			if(comboBox_option.getSelectedIndex()!=0) {
				n=comboBox_option.getSelectedIndex();
				switch(n) {
					case 1: 	OBJETIVO='+';
						break;
					case 2: 	OBJETIVO='-';
						break; 
				}
			}
			else {
				//System.out.println("En la Funcion Objetivo no se selecciono un Objetivo");
			
			}*/
			//(n>0)&&(FO==true)&&
			if(((nomVariable_indepentdecision!=null)&&(restriccionI!=null)&&(simbolo!=null))){
				//SOLO ENTRA SI YA CONSIGUIO LOS DATOS QUE REQUERIA, DE AQUI SIGUE LA TABLA
				
				String [] SimbolosFN=validacionCampos.SimbolosFN(numberRestriction, simbolo);
				
				/*String [] SimbolosFN=new String [numberRestriction];
				for(int i=0;i<numberRestriction;i++) {
					int A=simbolo[i];
					switch(A) {
						case 1: 	SimbolosFN[i]="<=";
							break;
						case 2: 	SimbolosFN[i]=">=";
							break;
						case 3: 	SimbolosFN[i]="=";
							break;
					}
				}*/
				
				
				
                restriccionI = validacionCampos.ladoDerechoNN(restriccionI, nomVariable_indepent, numberRestriction, SimbolosFN);
				//xd  dispose();
				int Sigma=numberRestriction+nomVariable_indepent;
			                        //System.out.println(numberRestriction + " " + nomVariable_indepent);
				//double nomVariable_indepentdecision[], char OBJETIVO, double restriccionI[][],String SimboloFN[]
				Datos2F siguiente=new Datos2F(nomVariable_indepentdecision,OBJETIVO,restriccionI,SimbolosFN,Sigma,numberRestriction,nomVariable_indepent);
				siguiente.varHolguras();
				//metod
                siguiente.despejarZ();
                siguiente.tabla2F();
				//siguiente.leer();
			}
			/*else {
				if(nomVariable_indepentdecision==null) {
					JOptionPane.showMessageDialog(null,"Un campo no es de tipo NumericoXD","Error",JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(dato==false) {
						JOptionPane.showMessageDialog(null,"Un campo no es de tipo Numerico","Error",JOptionPane.WARNING_MESSAGE);
					}
					else {
						if(n==0) {
							JOptionPane.showMessageDialog(null,"En la Funcion Objetivo no se selecciono un Objetivo","Error",JOptionPane.WARNING_MESSAGE);
						}
						else {
							if(simb==false) {
								JOptionPane.showMessageDialog(null,"En una Restriccion no se selecciono un simbolo logico","Error",JOptionPane.WARNING_MESSAGE);
							}
							else {
									JOptionPane.showMessageDialog(null,"Faltan campos por Rellenar","Error",JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
			}*/
			
			 // Arreglo Bidimensional para Datos Simplex
			
			/*for(int i=0;i<numberRestriction;i++) {
				for(int j=0;j<=nomVariable_indepent;j++) {
				}
			 }*/
		}
		if(e.getSource()==Btn_Res) {
			JTabbedPane_create_tabs init_dinamic_tabs=new JTabbedPane_create_tabs(numberRestriction, nomVariable_indepent);	

			
			tabbedPane=init_dinamic_tabs.create_tabs(tabbedPane, comboBox_option);
			
			Coeficiente=init_dinamic_tabs.Coeficiente;
			Simbolo=init_dinamic_tabs.Simbolo;
			Objetivo=init_dinamic_tabs.Objetivo;
			
			
			/*Doc*/	//int numberRestriction=5;
		//	for(int i=0;i<=numberRestriction;i++) {
				//Dont take color can be moved to constructor gui
				
				
				
				//Posiciones
			//	JPanel UY = new JPanel_transparent();
				/*UY.setRequestFocusEnabled(false);
				UY.setDoubleBuffered(false);
				UY.setFocusable(false);
				UY.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
				UY.setOpaque(false);*/
				/*UY.setLayout(new BorderLayout(0, 0));
				
				//Panel de nomVariable_indepentIABLES
				JPanel newTabComponent = new JPanel_transparent();
				//newTabComponent.setSize(new Dimension (50,50));
				newTabComponent.setLayout(new GridLayout(0,2,0,0));
				//newTabComponent.setMaximumSize(new Dimension(300,200));
				//newTabComponent.setPreferredSize(new Dimension(20,20));
				//newTabComponent.setOpaque(false);
				//newTabComponent.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
				Nuevo.add(newTabComponent);
				
				//Resultado y Simbolo
				JPanel Res = new JPanel();
				//Res.setLayout(new GridLayout(0, 2, 0, 0));
				Res.setOpaque(false);
				Res.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));    */
				/*tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
                                            @Override
					protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
				});*/
		/*		NuevoPanel2.add(Res);
				
				int tabCount = tabbedPane.getTabCount();
				//NP.setLayout(new GridLayout(0, 5, 0, 0));
				//JScrollPane R = new JScrollPane();
				//R.add(NP);
				//NP.add(new JTextField("I'm tab " + tabCount));
				//newTabComponent.add(R, getContentPane());
				
				//for(int j=0;j<nomVariable_indepent;j++) {
				//	newTabComponent.add(L);
				//	Xn.add(L);
				//	newTabComponent.add(J);
				//	Coeficiente.add(J);
				//}
				
				//Scroll Para n nomVariable_indepent
				
				
				nomVariable_indepentiables(newTabComponent,UY,Res,i);
				
				
				
				//btnNewButton.doClick();
	            //newTabComponent.add(J);
				//newTabComponent.add(L);
	            //newTabComponent.add(new JLabel("I'm tab " + tabCount));
	            if(tabCount==0){
	            	//JComboBox z=new JComboBox();
					
					Res.add(comboBox_option);
					
	            	tabbedPane.addTab("Funcion Objetivo", UY);
	            	//tabbedPane.addTab("Funcion Objetivo2.0", R);
	            }
	            else {
	            	//UY.add(newTabComponent,BorderLayout.CENTER);
	            	tabbedPane.addTab("Restricciones " +tabCount, UY);
	            }
	            G.add(UY);
			}   */
			//tabbedPane.addTab("Restricciones"+i, R);
	      //xdd  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	     //   setVisible(true);
		}
	}
	

	
	//nomVariable_indepent
	private void nomVariable_indepentiables(JPanel newTabComponent,JPanel JP_VARIND,JPanel Res,int numberRestriction) {
		/*Doc	//int nomVariable_indepent=2;
		for(int i=0;i<nomVariable_indepent;i++) {
			JTextField txt_coeficiente=new JTextField(12);
			TextPrompt placeholder = new TextPrompt("Ingrese el Coeficiente", txt_coeficiente);
		    placeholder.changeAlpha(0.75f);
		    placeholder.changeStyle(Font.ITALIC);
			JLabel lbl_Xn;
			if(i==(nomVariable_indepent-1)) {
				lbl_Xn=new JLabel("<html>X<sub>"+(i+1)+"</sub></html>");	
			}
			else {
				lbl_Xn=new JLabel("<html>X<sub>"+(i+1)+"</sub>&emsp;+</html>");
			}
			
			if(numberRestriction==0) {
				newTabComponent.add(txt_coeficiente);
				Objetivo.add(txt_coeficiente);
				
			}
			else {
				newTabComponent.add(txt_coeficiente);
				Coeficiente.add(txt_coeficiente);
				
			}
			newTabComponent.add(lbl_Xn);
			Xn.add(lbl_Xn);
			newTabComponent.updateUI();	
		}
		
		
		JComboBox x=new JComboBox();
		x.setFont(new Font("Tahoma", Font.PLAIN, 18));
		//Se crea un Array del ComboBox que relaciona, Si el Usuario tiene Autorizacion de Acceder Al Programa
		x.setModel(new DefaultComboBoxModel(new String[] {"Seleccione Un Simbolo:", "<=", ">=", "="}));
		//newTabComponent.add(x);
		JTextField J=new JTextField(12);
		TextPrompt placeholder2 = new TextPrompt("Ingrese el Resultado", J);
	    placeholder2.changeAlpha(0.75f);
	    placeholder2.changeStyle(Font.ITALIC);
		
		if(numberRestriction!=0) {
			Coeficiente.add(J);
			Res.add(x);
			Res.add(J);
			Simbolo.add(x);
		}
		
		
		//Restructura
        JScrollPane scrollPane = new JScrollPane_transparent();
        
		JViewport viewport = new JViewport_transparent();
		viewport.setView(newTabComponent);

		scrollPane.setViewport(viewport);
		scrollPane.getViewport().setOpaque(false);
		
		JP_VARIND.add(Res,BorderLayout.EAST);
		JP_VARIND.add(scrollPane,BorderLayout.CENTER);
		
        // Crear el JScrollPane
		//newTabComponent.setPreferredSize(new Dimension(200, getHeight()));
		//newTabComponent.setMaximumSize(new Dimension(200, 3 * newTabComponent.getPreferredSize().height));

		//newTabComponent.setPreferredSize(new Dimension(200, getHeight());
		//newTabComponent.setMinimumSize(new Dimension(200, newTabComponent.getPreferredSize().height));


		/* bueno
		JScrollPane scrollPane = new JScrollPane(newTabComponent);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		UY.add(Res, BorderLayout.EAST);
		
		UY.add(scrollPane, BorderLayout.CENTER);
		*/

		/*
        JScrollPane scrollPane = new JScrollPane(newTabComponent);
        //scrollPane.setPreferredSize(new Dimension(11, 100));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPane.setOpaque(false);
		//scrollPane.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

        
        
		UY.add(Res,BorderLayout.EAST);

        UY.add(scrollPane,BorderLayout.CENTER);
		*/
		/* original cambio flowlayout y jpanel a clase transparente
		//newTabComponent.setPreferredSize(new Dimension(200,(getSize().height*nomVariable_indepent)));
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setRequestFocusEnabled(false);
		scrollPane.setFocusable(false);
		scrollPane.setOpaque(false);
		JViewport viewport = new JViewport();
		viewport.setFocusable(false);
		viewport.setRequestFocusEnabled(false);
		 //Component that need to be added in Scroll pane//
	    viewport.setView(newTabComponent);
	    viewport.setOpaque(false);
	    scrollPane.setViewport(viewport);
	    scrollPane.getViewport().setOpaque(false);
	    
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setViewportView(newTabComponent);
		
		UY.add(Res,BorderLayout.EAST);
		//UY.add(J,BorderLayout.EAST);
		UY.setPreferredSize(new Dimension(100,100));
		UY.add(scrollPane,BorderLayout.CENTER);*/
		
		
	}
	

}