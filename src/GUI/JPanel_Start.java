/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanel_Start extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public JPanel_Start() {
		
	}
        @Override
	public void paintComponent(Graphics g){
            //Creamos Variable para el Tamaño del JFrame
            Dimension tamaño=getSize();
            //Creamos Variable de la Imagen a Poner dentro del Area del JPanel
	    ImageIcon imagen=new ImageIcon(new  ImageIcon(getClass().getResource("/Imagenes/white.jpg")).getImage());
	    //Pintamos la Imagen deacuerdo al tamaño del JFrame
	    g.drawImage(imagen.getImage(),0,0,tamaño.width,tamaño.height,null);
	}
}
