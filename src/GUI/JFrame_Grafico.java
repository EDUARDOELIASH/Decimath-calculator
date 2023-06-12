/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import MAIN.JFrame_Metodo;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import MetodoGrafico.ListaPuntosPoligono;
import MetodoGrafico.PuntosGr;
import metodoSimplex.DatosSimplex;
import MetodoGrafico.principalGrafico;

public class JFrame_Grafico extends JFrame{
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
	/**
	 * Create the frame.
	 */
	public JFrame_Grafico() {
		
		this.NR= LeerRes();
		this.VAR= LeerVar();
		
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
		
		JLabel Titulo = new JLabel("Metodo Grafico");
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
				int n = 0;
				dato=true;
				//Pasar Arraylist de Xn y Resultado a Arreglo Bidimensional
				int[][] restriccionI = new int[NR][VAR+1];
				restriccionI=RecorrerArray(Coeficiente,Simbolo); //restricciones
				
				//Simbolo logico por Restriccion
				int[] simbolo = new int [NR]; 
				boolean simb=true;
				for(int i=0;i<NR;i++) {
					JComboBox Sim = Simbolo.get(i);
					if(Sim.getSelectedIndex()!=0) {
						simbolo[i]=Sim.getSelectedIndex();
					}
					else {
						simb=false;
						/*System.out.println("En la Restriccion "+(i+1)+" 
                                                se encuentra un simbolo logico dentro de comboBox sin seleccionar");*/
					}
				}
				
				//Funcion Objetivo Campos
				double [] vardecision=new double [VAR+1]; //Variables de decision
				boolean FO=true;
				for(int i=0;i<VAR;i++){
					JTextField ob = Objetivo.get(i);
					try {
						double verificacion=Double.parseDouble(ob.getText());
						vardecision[i]=verificacion;
						//System.out.println(vardecision[i]);
					}
					catch(Exception e) {
						//System.out.println("Coeficiente no valido");
						FO=false;
						break;
					}
				}
				
				//Objetivo: Maximizar o Minimizar
				char OBJETIVO=' ';
				if(z.getSelectedIndex()!=0) {
					n=z.getSelectedIndex();
					switch(n) {
						case 1: 	OBJETIVO='+';
							break;
						case 2: 	OBJETIVO='-';
							break; 
					}
				}
				else {
					//System.out.println("En la Funcion Objetivo no se selecciono un Objetivo");

				}
				
				if(((n>0)&&(FO==true)&&(dato==true)&&(simb==true))){
					//SOLO ENTRA SI YA CONSIGUIO LOS DATOS QUE REQUERIA, DE AQUI SIGUE LA TABLA
					String [] SimbolosFN=new String [NR];
					for(int i=0;i<NR;i++) {
						int A=simbolo[i];
						switch(A) {
							case 1: 	SimbolosFN[i]="<=";
								break;
							case 2: 	SimbolosFN[i]=">=";
								break;
							case 3: 	SimbolosFN[i]="=";
								break;
						}
					}
					dispose();
					int Sigma=NR+VAR;
					nvar= VAR;
					nrest = NR;
					totalVar = Sigma;
					restriccionY = new int[nrest];
					restriccionX = new int[nrest];
					limiteR = new int[nrest];
					restriccion = new String[nrest];
					simbolo1 = SimbolosFN;
					for (int i = 0; i < nrest; i++) {//Se obtienen las restricciones
						for(int j = 0; j <= nvar; j++) {
							if (j == 0) {
								restriccionX[i] = restriccionI[i][j];
							}
							else if (j == 1) {
								restriccionY[i] = restriccionI[i][j];
							}
							if (j == nvar) {
								limiteR[i] = restriccionI[i][j]; 
							}
						}
						
						if (limiteR[i] < 0){ //Si lado derecho de la restriccion es negativo
                                                   for (int k = 0; k <=nvar; k++){ //Lado izquierdo
                                                       limiteR[i] *= (-1);
                                                       restriccionX[i] *= (-1);
                                                       restriccionY[i] *= (-1);
                                                   }
                                                   switch (simbolo1[i]){
                                                       case ">=": 
                                                          simbolo1[i] = "<=";
                                                       break;
                                                       case "<=": 
                                                          simbolo1[i] = ">=";
                                                       break;
                                                       case "=": 
                                                          simbolo1[i] = "=";
                                                       break;    
                                                   }
				                }
						restriccion[i] = "";
						restriccion[i] += restriccionX[i] + "x1\n+ " + restriccionY[i] + "x2\n" + simbolo1[i] + "\n" + limiteR[i];
					}				    				    			    
				    vardecision1 = vardecision;
				    objetivo = OBJETIVO;
				    puntos();
				    int n1 = P.nPuntosFactibles(P); //Se calcula numero de puntos factibles
					double[] xF = new double[n1]; 
					double[] yF = new double[n1];
				    puntosFactiblesAr(P, xF, yF); //Se crea una lista de los puntos factibles
				    principalGrafico sig = new principalGrafico(restriccion, altoV, anchoV, xPDF, yPDF, x, y, xA, yA, 
                                    xNegativo, yNegativo, nrest, vardecision, objetivo, P, n1, xF, yF);
				    
					//double vardecision[], char OBJETIVO, double restriccionI[][],String SimboloFN[]
					//DatosSimplex siguiente=new DatosSimplex(vardecision,OBJETIVO,restriccionI,SimbolosFN,Sigma,NR,VAR);
					/*siguiente.varHolguras();
					siguiente.despejarZ();
					siguiente.tablaSimplex();*/
					//siguiente.leer();
				}
				else {
					if(FO==false) {
						JOptionPane.showMessageDialog(null,"Un campo no es de tipo Numerico","Error",JOptionPane.WARNING_MESSAGE);
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
				}
				
				 // Arreglo Bidimensional para Datos Simplex
				
				/*for(int i=0;i<NR;i++) {
					for(int j=0;j<=VAR;j++) {
					}
				 }*/
				
				
			}

			private int[][] RecorrerArray(ArrayList<JTextField> coeficiente, ArrayList<JComboBox> simbolo) {
				int[][] restriccionI = new int[NR][VAR+1];
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
								if(k==VAR) {
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
		
		//Para ver la GUI en la pestaña Diseño Documentar toda la parte del setIU
		/*tabbedPane.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI(){
			  protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
			});*/
		
		tabbedPane.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));
		
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
				for(int i=0;i<=NR;i++) {
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
						protected void paintContentBorder(Graphics g,int tabPlacement,int selectedIndex){}
					});
					NuevoPanel2.add(Res);
					
					int tabCount = tabbedPane.getTabCount();
					//NP.setLayout(new GridLayout(0, 5, 0, 0));
					//JScrollPane R = new JScrollPane();
					//R.add(NP);
					//NP.add(new JTextField("I'm tab " + tabCount));
					//newTabComponent.add(R, getContentPane());
					
					//for(int j=0;j<VAR;j++) {
					//	newTabComponent.add(L);
					//	Xn.add(L);
					//	newTabComponent.add(J);
					//	Coeficiente.add(J);
					//}
					
					//Scroll Para n var
					
					
					Variables(newTabComponent,UY,Res,i);
					
					
					
					//btnNewButton.doClick();
		            //newTabComponent.add(J);
					//newTabComponent.add(L);
		            //newTabComponent.add(new JLabel("I'm tab " + tabCount));
		            if(tabCount==0){
		            	//JComboBox z=new JComboBox();
						z.setFont(new Font("Tahoma", Font.PLAIN, 18));
						//Se crea un Array del ComboBox que relaciona, Si el Usuario tiene Autorizacion de Acceder Al Programa
						z.setModel(new DefaultComboBoxModel(new String[] {"Seleccione Un Objetivo:", "Maximizar", "Minimizar"}));
						Res.add(z);
						
		            	tabbedPane.addTab("Funcion Objetivo", UY);
		            	//tabbedPane.addTab("Funcion Objetivo2.0", R);
		            }
		            else {
		            	//UY.add(newTabComponent,BorderLayout.CENTER);
		            	tabbedPane.addTab("Restricciones " +tabCount, UY);
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
					JTextField J=new JTextField(12);
					TextPrompt placeholder = new TextPrompt("Ingrese el Coeficiente", J);
				    placeholder.changeAlpha(0.75f);
				    placeholder.changeStyle(Font.ITALIC);
					JLabel L;
					if(i==(VAR-1)) {
						L=new JLabel("<html>X<sub>"+(i+1)+"</sub></html>");	
					}
					else {
						L=new JLabel("<html>X<sub>"+(i+1)+"</sub>&emsp;+</html>");
					}
					
					if(NR==0) {
						newTabComponent.add(J);
						Objetivo.add(J);
						
					}
					else {
						newTabComponent.add(J);
						Coeficiente.add(J);
						
					}
					newTabComponent.add(L);
					Xn.add(L);
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
				
				if(NR!=0) {
					Coeficiente.add(J);
					Res.add(x);
					Res.add(J);
					Simbolo.add(x);
				}
				
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
				
				UY.add(Res,BorderLayout.EAST);
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
	//Aqui empieza codigo de leer datos
			//Datos que son leidos
		    int nvar, nrest, totalVar;	    	    
		    private int[] restriccionY, restriccionX;
			private int[] limiteR;
		    private String[] simbolo1;
		    String[] restriccion;
		    double[] vardecision1;
		    char objetivo = ' ';
		    
		    //Datos de la ventana y el plano cartesiano
		    int altoV = 500, anchoV = 800;	
			int xPDF =anchoV /2, yPDF = anchoV /2;
			
			//Datos para dibujar las lineas del plano cartesiano
			int[] x, y, xA, yA;
			boolean [] xNegativo, yNegativo;
			
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
		                
		            if (nvar == 2){
		                nvarV = true;
		            }
		            else if (excepcion == false){
		                JOptionPane.showMessageDialog(null, "Numero de variables debe ser == 2", "Error", JOptionPane.WARNING_MESSAGE);
		            }     
		        }
		        s.println();
		        //Leer numero de restricciones
		        while (nrestV == false){
		            try{ //Manejo de excepciones
		                excepcion = false;
		                //s.print("digite número de restricciones: ");
		                nrest = entrada.nextInt();
		            }
		            catch (InputMismatchException error){
		                //s.print("Tipo de dato debe ser numerico porfavor, "); 
		                entrada.nextLine();
		                excepcion = true; //No entra a else if
		            } 
		            catch (Exception error){
		                //s.print("Ha ocurrido un error, porfavor, "); 
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
		                vardecision[j] = entrada.nextInt();
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
		        restriccion = new String[nrest];
		        restriccionX = new int[nrest]; //Variables de restriccion
		        restriccionY = new int[nrest];
		        limiteR = new int[nrest];
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
		                        limiteR[i] = entrada.nextInt();
		                    }
		                    else{
		                    	if ( j == 0 ) {
		                            s.print("x" + (j+1) + " ");
		                            restriccionX[i] = entrada.nextInt();
		                    	}
		                    	else if (j == 1){
		                    		s.print("x" + (j+1) + " ");
		                            restriccionY[i] = entrada.nextInt();
		                    	}
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
		            if (limiteR[i] < 0){
		               for (int k = 0; k <=nvar; k++){ //Lado izquierdo
		                   limiteR[i] *= (-1);
		                   restriccionX[i] *= (-1);
		                   restriccionY[i] *= (-1);
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
		    
		    //Aqui termina codigo de leer datos
		    ListaPuntosPoligono P;
		    public void puntos() {	    	
		    	PuntosGr puntos1 = new PuntosGr(nrest, restriccionX, restriccionY, limiteR, simbolo1, xPDF, yPDF, vardecision1);
		    	puntos1.datosx(nrest, restriccionX, limiteR);
                        puntos1.datosy(nrest, restriccionY, limiteR, restriccionX);
                        puntos1.intersecciones(nrest);
                        x = puntos1.x;
                        y = puntos1.y;
                        xA = puntos1.xA;
                        yA = puntos1.yA;
                        xNegativo = puntos1.xNegativo;
                        yNegativo = puntos1.yNegativo;
                        P = puntos1.P;
		    }
		    
		    public void puntosFactiblesAr (ListaPuntosPoligono nodos, double[] x, double[] y) {
		       	//Guarda en 2 arreglos puntos factibles (en interfaz) para hacer fill
		    	        boolean repetir = false; 
		    	        ListaPuntosPoligono puntosF = new ListaPuntosPoligono();
		    	        ListaPuntosPoligono aux = puntosF;
		               int i = 0;
		               //Recorrido a lista en busca de puntos factibles
		    	        ListaPuntosPoligono Q = new ListaPuntosPoligono();
		    	        puntosF = Q.creanodo(nodos.x, nodos.y, puntosF, Q);
		    	        x[i] = nodos.x;
		    	        y[i] = nodos.y;
		    	        x[i] += xPDF;
		    	        y[i] = yPDF - y[i]; //Se resta porque en JPanel ir hacia arriba es tener menos cantidad
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
		    	             if (repetir == false) { // si no es un valor repetido
		    	                 ListaPuntosPoligono Q1 = new ListaPuntosPoligono();
		    	        	     puntosF = Q1.creanodo(nodos.x, nodos.y, puntosF, Q1);
		    	        	     i++;
		    	        	     x[i] = nodos.x;
		    	        	     y[i] = nodos.y;
		    	        	     x[i] += xPDF;
		    	     	         y[i] = yPDF - y[i]; //Se resta porque en JPanel ir hacia arriba es tener menos cantidad
		    	             }	                
		    	             repetir = false;	    	        
		    	        }
		        }
		    public int LeerVar(){
		        boolean nvarV = false, excepcion;
		        int nvar = 0;
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
			                
			            if (nvar == 2){
			                nvarV = true;
			            }
			            else if (excepcion == false){
			                JOptionPane.showMessageDialog(null, "Numero de variables debe ser == 2", "Error", JOptionPane.WARNING_MESSAGE);
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
}
