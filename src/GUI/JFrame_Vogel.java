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

//Paquete.clase
import MetodoVogel.PrincipalVo;
import metodoModi.TablaDeParametrosMODI;

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

public class JFrame_Vogel extends JFrame {
	int VAR,NR;
	private static final long serialVersionUID = 1L;
	private ArrayList<JButton> Botones;
	private ArrayList<JLabel> Xn;
	private ArrayList<JTextField> Coeficiente;
	private ArrayList<JTextField> Objetivo;
	private ArrayList<JComboBox> Simbolo;
	private ArrayList<JPanel> Nuevo;
	private ArrayList<JPanel> NuevoPanel2;
	private ArrayList<JPanel> G;
	public JComboBox z;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	boolean dato;
	int [][] costos;
	/**
	 * Create the frame.
	 */
	public JFrame_Vogel(int [][] Costos, final int [] oferta, final int [] demanda, int Origenes, int Destinos, final int totalO, final int totalD, final boolean oFicticio, final boolean dFicticio,final int caso) {
		this.NR=Costos.length;
		this.VAR=Costos[0].length;
		this.costos=Costos;
		
		Botones=new ArrayList<JButton>();
		Xn=new ArrayList<>();
		Coeficiente=new ArrayList<JTextField>();
		Objetivo=new ArrayList<JTextField>();
		Simbolo=new ArrayList<JComboBox>();
		Nuevo=new ArrayList<JPanel>();
		G=new ArrayList<JPanel>();
		NuevoPanel2=new ArrayList<JPanel>();
		
		z=new JComboBox();

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
		
		JLabel Titulo = new JLabel("Metodo Voguel");
		panel.add(Titulo);
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setHorizontalTextPosition(SwingConstants.CENTER);
		Titulo.setFont(new Font("Arial", Font.BOLD, 28));
		
		JLabel Titulo_1 = new JLabel("Porfavor Complete la Siguiente Informacion");
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
		Izquierda.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField_1 = new JTextField();
		Izquierda.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setVisible(false);

		
		JPanel Derecho = new JPanel();
		Derecho.setOpaque(false);
		Panel_Formulario.add(Derecho, BorderLayout.EAST);
		Derecho.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField_2 = new JTextField();
		Derecho.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		
		JPanel Centro = new JPanel();
		Centro.setOpaque(false);
		Panel_Formulario.add(Centro, BorderLayout.CENTER);
		Centro.setLayout(new BorderLayout(0, 0));
		
		JPanel Confirmar = new JPanel();
		Confirmar.setOpaque(false);
		Centro.add(Confirmar, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Enviar Formulario");
		btnNewButton.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent arg0) {
				//int n = 0;
				dato=true;
				//Pasar Arraylist de Xn y Resultado a Arreglo Bidimensional
				//double[][] restriccionI = new double[NR][VAR+1];
				
				costos=RecorrerArray(Coeficiente,Simbolo);						
				if(dato==true){
                                    dispose();
                                    PrincipalVo AS=new PrincipalVo(costos,oferta,demanda,NR,VAR,totalO,totalD,oFicticio,dFicticio);
                                    AS.main();

                                    //Metodo Vogel - MODI
                                    if(caso!=0) {
                                        int[][] matrizPC, matrizA;
                                        String[] destino, origen;
                                        TablaDeParametrosMODI tablaMO = new TablaDeParametrosMODI();

                                        //Se obtienen matrices la de costos y asignaciones
                                        matrizPC = AS.getmatrizPC(); //Matriz de parametros dados
                                        matrizA = AS.getmatrizA(); //Cantidad de transporte
                                        destino = AS.getdestino();
                                        origen = AS.getorigen();
                                        //nOrigenes = AS.getnOrigenes();
                                        //nDestinos = AS.getnDestinos();

                                        tablaMO.resultado(matrizPC, NR, VAR, matrizA, destino, origen);
                                    }                                 
				}
				else {
				/*	if(FO==false) {
						JOptionPane.showMessageDialog(null,"Un campo no es de tipo Numerico","Error",JOptionPane.WARNING_MESSAGE);
					}
					else{
				*/		if(dato==false) {
							JOptionPane.showMessageDialog(null,"Un campo no es de tipo Numerico","Error",JOptionPane.WARNING_MESSAGE);
						}
						else {
				/*			if(n==0) {
								JOptionPane.showMessageDialog(null,"En la Funcion Objetivo no se selecciono un Objetivo","Error",JOptionPane.WARNING_MESSAGE);
							}
							else {
								if(simb==false) {
									JOptionPane.showMessageDialog(null,"En una Restriccion no se selecciono un simbolo logico","Error",JOptionPane.WARNING_MESSAGE);
								}
								else {
				*/						JOptionPane.showMessageDialog(null,"Faltan campos por Rellenar","Error",JOptionPane.WARNING_MESSAGE);
								}
							}
				/*		}
					}
				}
				*/
				 // Arreglo Bidimensional para Datos Simplex
				
				/*for(int i=0;i<NR;i++) {
					for(int j=0;j<=VAR;j++) {
					}
				 }*/
				
				
			}

			private int[][] RecorrerArray(ArrayList<JTextField> coeficiente, ArrayList<JComboBox> simbolo) {
				int[][] restriccionI = new int[NR][VAR];
				int j=0;
				//JOptionPane.showMessageDialog(null,coeficiente.size(),"",JOptionPane.INFORMATION_MESSAGE);
				int i=0,k=0;
				//for(int i=0;i<NR;i++) {
					while(j<coeficiente.size()&&i<NR) {
						JTextField x = coeficiente.get(j);
						//
						//while(dato==false) {
							try {
								int verificacion=Integer.parseInt(x.getText());
								if(k==(VAR-1)) {
									restriccionI[i][k]=verificacion;
									i++;
									k=0;
								}
								else {
									restriccionI[i][k]=verificacion;
									k++;
								}
								//dato=true;
							}
							catch(Exception e) {
								//System.out.println("Coeficiente no valido en restriccion"+j);
								dato=false;
								break;
								//
							}
						j++;
					};
				return restriccionI;
			}
		});
		Confirmar.add(btnNewButton);
		final Color c = UIManager.getLookAndFeel().getDefaults().getColor( "Panel.background");
		
		JPanel LlenarCampos = new JPanel();
		LlenarCampos.setRequestFocusEnabled(false);
		LlenarCampos.setOpaque(false);
		Centro.add(LlenarCampos, BorderLayout.CENTER);
		LlenarCampos.setLayout(new BorderLayout(0, 0));
		LlenarCampos.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		LlenarCampos.add(tabbedPane);
		
		
		JButton Btn_Res = new JButton("<html><p align=\"center\">Agregar<br/>Restriccion</p></html>");
		Btn_Res.setVisible(false);
		Btn_Res.setBorder(null);
		Btn_Res.setBorderPainted(false);
		Btn_Res.setHorizontalTextPosition(SwingConstants.CENTER);
		Btn_Res.setAlignmentX(Component.CENTER_ALIGNMENT);
		Btn_Res.setActionCommand("<html><p align=\"center\">Agregar  <br/>Restriccion</p></html>");
		Btn_Res.setBounds(0, 0, 53, 29);
		contentPane.add(Btn_Res);
		Btn_Res.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent arg0) {
		/*Doc*/	//int NR=5;
				for(int i=0;i<NR;i++) {
					//Posiciones
					JPanel UY = new JPanel();
					UY.setRequestFocusEnabled(false);
					UY.setDoubleBuffered(false);
					UY.setFocusable(false);
					UY.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
					UY.setOpaque(false);
					UY.setLayout(new BorderLayout(0, 0));
					
					//Panel de VARIABLES
					JPanel newTabComponent = new JPanel();
					//newTabComponent.setSize(new Dimension (50,50));
					newTabComponent.setLayout(new FlowLayout());
					//newTabComponent.setMaximumSize(new Dimension(300,200));
					//newTabComponent.setPreferredSize(new Dimension(20,20));
					newTabComponent.setOpaque(false);
					newTabComponent.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
					tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
                                                @Override
						protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
					});
					Nuevo.add(newTabComponent);
					
					//Resultado y Simbolo
					JPanel Res = new JPanel();
					//Res.setLayout(new GridLayout(0, 2, 0, 0));
					Res.setOpaque(false);
					Res.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
					tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
                                                @Override
						protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
					});
					NuevoPanel2.add(Res);
					
					int tabCount = tabbedPane.getTabCount();
					
					Variables(newTabComponent,UY,Res,i);
					
		            if((oFicticio == true)&&(i==(NR-1))){
		            	tabbedPane.addTab("Origen Ficticio", UY);
		            }
		            else {
		            	//UY.add(newTabComponent,BorderLayout.CENTER);
		            	tabbedPane.addTab("Origen " +(tabCount+1), UY);
		            }
		            G.add(UY);
				}
				//tabbedPane.addTab("Restricciones"+i, R);
		        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        setVisible(true);
			}
			//VAR
			private void Variables(JPanel newTabComponent,JPanel UY,JPanel Res,int NR) {
		/*Doc*/	//int VAR=2;
				for(int i=0;i<VAR;i++) {
					if((dFicticio == true)&&(i==(VAR-1))) {
						JTextField J=new JTextField(15);
						TextPrompt placeholder = new TextPrompt("Ingrese Costo Destino Ficticio", J);
					    placeholder.changeAlpha(0.75f);
					    placeholder.changeStyle(Font.ITALIC);
						newTabComponent.add(J);
						Coeficiente.add(J);
						newTabComponent.updateUI();
					}
					else {
						JTextField J=new JTextField(15);
						TextPrompt placeholder = new TextPrompt("Ingrese Costo Destino"+(i+1), J);
					    placeholder.changeAlpha(0.75f);
					    placeholder.changeStyle(Font.ITALIC);
						newTabComponent.add(J);
						Coeficiente.add(J);
						newTabComponent.updateUI();	
					}
				}
				/*JComboBox x=new JComboBox();
				x.setFont(new Font("Tahoma", Font.PLAIN, 18));
				//Se crea un Array del ComboBox que relaciona, Si el Usuario tiene Autorizacion de Acceder Al Programa
				x.setModel(new DefaultComboBoxModel(new String[] {"Seleccione Un Simbolo:", "<=", ">=", "="}));
				//newTabComponent.add(x);
				JTextField J=new JTextField(12);
				TextPrompt placeholder2 = new TextPrompt("Ingrese el Resultado", J);
			    placeholder2.changeAlpha(0.75f);
			    placeholder2.changeStyle(Font.ITALIC);
				*/
				/*if(NR!=0) {
					Coeficiente.add(J);
					Res.add(x);
					Res.add(J);
					Simbolo.add(x);
				}*/
				
				newTabComponent.setPreferredSize(new Dimension(200,(getSize().height*VAR)));
				
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
				
				//UY.add(Res,BorderLayout.EAST);
				//UY.add(J,BorderLayout.EAST);
				UY.setPreferredSize(new Dimension(100,100));
				UY.add(scrollPane,BorderLayout.CENTER);
			}
		});
		Btn_Res.doClick();
		
		
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
}

