package MetodoGrafico;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class principalGrafico {
	private JFrame frame;
	private JScrollPane scrollPane;
    
	ListaPuntosPoligono P; //puntero de lista que almacena puntos factibles
	ListaPuntosPoligono Q = new ListaPuntosPoligono();
	String[] restriccion; //cadena que almacena restricciones
	int altoV, anchoV;	//ancho y alto de la ventana
	int xPDF, yPDF; //variable que ajusta a la ventana coordenadas de las lineas
	int nrest; //numero restricciones
	double[] vardecision; //almacena coeficientes de variables de decision
	char objetivo; //objetivo + (maximizar) - (minimizar)
	
	//Datos para dibujar las lineas del plano cartesiano
	int[] x, y, xA, yA;
	boolean [] xNegativo, yNegativo;
	
	double[] xF; 
	double[] yF;
	int n;
	
	public void main() {
		
	}	
	//JFrame_Grafico datos = new JFrame_Grafico();	
	/**
	 * Create the application.
     * @param restriccion
     * @param altoV
     * @param anchoV
     * @param xPDF
     * @param yPDF
     * @param x
     * @param y
     * @param xA
     * @param yA
     * @param xNegativo
     * @param yNegativo
     * @param nrest
     * @param vardecision
     * @param objetivo
     * @param P
     * @param n
     * @param xF
     * @param yF
	 */
	public principalGrafico(String[] restriccion, int altoV, int anchoV, int xPDF, int yPDF, int[] x, int[] y, int[] xA, int[] yA,
	    boolean[] xNegativo, boolean[] yNegativo, int nrest, double[] vardecision, char objetivo, ListaPuntosPoligono P, int n, 
            double[] xF, double[] yF) { 
		//inicializarVar();		
		this.restriccion = restriccion;
		this.altoV = altoV;
		this.anchoV = anchoV;
		this.xPDF = xPDF;
		this.yPDF = yPDF;
		this.x = x;
		this.y = y;
		this.xA = xA;
		this.yA = yA;
		this.xNegativo = xNegativo;
		this.yNegativo = yNegativo;
		this.nrest = nrest;
		this.vardecision = vardecision;
		this.objetivo = objetivo;
		this.P = P;
		this.xF = xF;
		this.yF = yF;
		this.n = n;
	    initialize();
	    frame.setVisible(true);	
	}
	
	//-3x1 + 4x2 <= 12
        
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Metodo Grafico");
		//posicion -- tamanio
		frame.setBounds(400, 200, anchoV, altoV);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel grafico = new nodoGrafico();
		
		
		((nodoGrafico) grafico).dibujar(nrest, xPDF, yPDF, x, y, xA, yA, restriccion, xNegativo, yNegativo, n, 0, xF, yF);
		grafico.repaint();
		
		JPanel panel1 = new JPanel();

		final JPanel panelPrincipal = new JPanel();
		//JPanel panel2 = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(0,0));
		
		panelPrincipal.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new GridLayout(0, 4, 0, 0));
		panelPrincipal.add(grafico, BorderLayout.CENTER);
		scrollPane = new JScrollPane();
		//scrollPane.setBounds(0,0,anchoV - 10,altoV-35);
		panelPrincipal.setPreferredSize(new Dimension(anchoV, anchoV));
		scrollPane.setViewportView(panelPrincipal);		
		frame.add(scrollPane);
		final int cambio = 80;
		JButton btnMin = new JButton("-");
		//necesito el valor de n
		btnMin.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent arg0) {	
				grafico.removeAll();
				((nodoGrafico) grafico).dibujar(nrest, xPDF, yPDF, x, y, xA, yA, restriccion, xNegativo, yNegativo, n, 0, xF, yF);
				panelPrincipal.setPreferredSize(new Dimension(anchoV, anchoV));
				grafico.repaint();
				JOptionPane.showMessageDialog(null, "El zoom ha disminuido");
			}	
		});
		panel1.add(btnMin);
		
		JButton btnMax = new JButton("+");
		btnMax.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent arg0) {
				grafico.removeAll();
				((nodoGrafico) grafico).dibujar(nrest, xPDF, yPDF, x, y, xA, yA, restriccion, xNegativo, yNegativo, n, cambio, xF, yF);
				panelPrincipal.setPreferredSize(new Dimension(anchoV + cambio, anchoV + cambio));
				grafico.repaint();
				JOptionPane.showMessageDialog(null, "El zoom ha aumentado");
			}
		});
		panel1.add(btnMax);
		
		JButton btnPuntos = new JButton("Puntos de area factible");
		btnPuntos.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent arg0) {
				if (P == null) {
					JOptionPane.showMessageDialog(null, "lista Vacia");
				}
				else {			    
				    JOptionPane.showMessageDialog(null, Q.puntosFactibles(P, vardecision[0], vardecision[1]), "Puntos de area factible", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel1.add(btnPuntos);
		
		JButton btnOptimo = new JButton("Punto Optimo");
		btnOptimo.addActionListener(new ActionListener() {		
                        @Override
			public void actionPerformed(ActionEvent arg0) {
				if (P == null) {
					JOptionPane.showMessageDialog(null, "lista Vacia");
				}
				else {
					Q.puntoOptimo(P, vardecision[0], vardecision[1], objetivo);
				}
				
			}
		});
		panel1.add(btnOptimo);
	}
}


class nodoGrafico extends JPanel{
    //Variables necesarias
	int nRestricciones;
	int xPDF, yPDF;
	int[] x, y, xA, yA;
	final int separacionI = 0, separacionA = 0;
	int zoom, n;
	String[] restriccion;
	boolean[] xNegativo, yNegativo;
	double[] xF, yF;
	public void dibujar(int nRestricciones, int xPDF, int yPDF, int[] x, int[] y, int[] xA, int[] yA, 
        String[] restriccion, boolean[] xNegativo, boolean[] yNegativo, int n, int zoom, double[] xF, double[] yF) { //Constructor
		
            this.nRestricciones = nRestricciones; //Valores necesarios para realizar la grafica
            this.xPDF = xPDF;
            this.yPDF = yPDF;
            this.x = x;
            this.y = y;
            this.xA = xA;
            this.yA = yA;
            this.restriccion = restriccion;
            this.xNegativo = xNegativo;
            this.yNegativo = yNegativo;
            this.zoom = zoom;
            this.n = n;
            this.xF = xF;
            this.yF = yF;
	}
	private static final long serialVersionUID = 1L;		
        @Override
	public void paintComponent(Graphics g) {
            int separacion = 0;
            int tamanio = 1;
            super.paintComponent(g);   	
            //Eje de las y
            g.setColor(Color.RED);
            g.drawLine(xPDF - zoom, separacionA, xPDF - zoom, (yPDF  + zoom )* 1 * 2);
            g.drawLine(xPDF - zoom, separacionA, xPDF - zoom, (yPDF  + zoom )* 1 * 2);
            g.drawLine(xPDF - zoom, separacionA, xPDF - zoom, (yPDF  + zoom )* 1 * 2);
            //Eje de las x
            g.drawLine(separacionI, yPDF + (zoom / 2), (xPDF + zoom) * 1 * 2, yPDF + (zoom / 2));
            g.drawLine(separacionI, yPDF + (zoom / 2), (xPDF  + zoom)*1 * 2, yPDF + (zoom / 2));
            g.drawLine(separacionI, yPDF + (zoom / 2), (xPDF  + zoom)*1 * 2, yPDF + (zoom / 2));
            for (int i = 0; i < nRestricciones; i++) {		  		  
                    if (x[i] == xPDF) { //Caso especial funcion constante en X
                        //x2 <= 6, punto 1 (x1 original + 250, y = 2t0 - 0)=(0 + 250, 250 - 0), punto2 (x = 0 + 250, 250 - x2original)
                        g.setColor(Color.GREEN);
                        g.drawLine(xA[i] - (tamanio *zoom), y[i] - (tamanio * zoom), x[i] + (tamanio * zoom) + (xPDF - zoom), y[i] - (tamanio * zoom));
                        g.setColor(Color.BLACK);
                        g.drawString(restriccion[i] ,(xA[i] + separacion) - (tamanio *zoom), (y[i] - separacion) - (tamanio * zoom));
                        separacion += 10;
                    }
                    else if (y[i] == yPDF) { //Caso especial funcion constante en Y
                                g.setColor(Color.blue);
                                g.drawLine(x[i], y[i] - (tamanio * zoom) - (yPDF + zoom), x[i], yA[i] + (tamanio *zoom) / 2);
                                g.setColor(Color.BLACK);
                                g.drawString(restriccion[i] ,x[i] + separacion, yA[i] + separacion + (tamanio *zoom) / 2);
                                separacion += 10;
                    }
                    else { //Caso normal 
                        //if y = -, x = -
                        if (xNegativo[i] == true) {
                            g.setColor(Color.gray);
                            g.drawLine(xA[i] + (tamanio *zoom), y[i] - (tamanio *zoom), x[i] - (tamanio * zoom), yA[i] + (tamanio *zoom)/2);
                            g.setColor(Color.BLACK);
                            g.drawString(restriccion[i] ,x[i] - (tamanio *zoom) - separacion, (yA[i] + separacion) + tamanio *zoom);
                            separacion += 10;
                        }
                        else if (yNegativo[i] == true) {
                            g.setColor(Color.ORANGE);
                            g.drawLine(xA[i] - (tamanio *zoom), y[i] + (tamanio * zoom) /2, x[i] + (tamanio *zoom), yA[i] - (tamanio *zoom));
                            g.setColor(Color.BLACK);
                            g.drawString(restriccion[i] , (xA[i]+separacion) - (tamanio *zoom), (y[i] + separacion) + (tamanio* zoom) );
                            separacion += 10;
                        }
                        else {
                            g.setColor(Color.BLACK);
                            g.drawLine(xA[i] - (tamanio* zoom), y[i] - (tamanio* zoom), x[i] + (tamanio*zoom), yA[i] + (tamanio* zoom)/2);
                            g.drawString(restriccion[i] ,(xA[i]+separacion) - (tamanio* zoom),  y[i] - ( tamanio *zoom) - separacion);
                            separacion += 10;
                        }			  
                    }
            }	
            //valor de puntos factibles
            rellenarPoligono(g, tamanio);
		 
    }
	//no funciona bien al hacer zoom
	public void rellenarPoligono(Graphics g, int tamanio) {
		int[] x = new int[n];
		int[] y = new int[n];
		for (int i = 0; i<n; i++) {			
			x[i] = (int) xF[i];
			y[i] = (int) yF[i];
		}		
		
		for (int i = 0; i<n; i++) {
			if ((x[i] == xPDF) && (y[i] == yPDF)) {
				x[i] = x[i] - (zoom * tamanio);
				y[i] = y[i] + (zoom * tamanio) / 2;
			}
			else {
				 x[i] = x[i] - (zoom * tamanio);
				 y[i] = y[i] + (zoom * tamanio) / 2;
			}
		}
		g.fillPolygon(x , y, n);
    }	
	
}
