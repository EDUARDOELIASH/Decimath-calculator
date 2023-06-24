package objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JViewport;

public class JTabbedPane_create_tabs{
	int numberRestriction , nomVariable_indepent;
	
	public ArrayList<JComboBox> Simbolo = new ArrayList<JComboBox>();
	public ArrayList<JTextField> Objetivo=new ArrayList<JTextField>();
	//public ArrayList<JPanel> Nuevo;
	//public ArrayList<JPanel> NuevoPanel2;
	public ArrayList<JTextField> Coeficiente =new ArrayList<JTextField>();
	
	
	public JTabbedPane_create_tabs( int numberRestriction ,int nomVariable_indepent) {
		this.numberRestriction=numberRestriction;
		this.nomVariable_indepent=nomVariable_indepent;
	}
	
	
	public JTabbedPane create_tabs(JTabbedPane tabbedPane, JComboBox comboBox_option) {
		
		/*Doc*/	//int numberRestriction=5;
		for(int i=0;i<=numberRestriction;i++) {
			//Posiciones
			JPanel UY = new JPanel_transparent();

			UY.setLayout(new BorderLayout(0, 0));
			
			//Panel de nomVariable_indepentIABLES
			JPanel newTabComponent = new JPanel_transparent();
			newTabComponent.setLayout(new GridLayout(0,2,0,0));

			//Nuevo.add(newTabComponent);
			
			//Resultado y Simbolo
			JPanel Res = new JPanel();
			Res.setOpaque(false);
			//Res.setBackground(new Color(c.getRed(), c.getGreen(), c.getBlue()));

			//NuevoPanel2.add(Res);
			
			int tabCount = tabbedPane.getTabCount();
			
			nomVariable_indepentiables(newTabComponent,UY,Res,i);
			
            if(tabCount==0){
				Res.add(comboBox_option);
				
            	tabbedPane.addTab("Funcion Objetivo", UY);
            	//tabbedPane.addTab("Funcion Objetivo2.0", R);
            }
            else {
            	//UY.add(newTabComponent,BorderLayout.CENTER);
            	tabbedPane.addTab("Restricciones " +tabCount, UY);
            }
          //  G.add(UY);
		}
		return tabbedPane;
	}
	
	
	
	//nomVariable_indepent
	private void nomVariable_indepentiables(JPanel newTabComponent,JPanel JP_VARIND,JPanel Res,int numberRestriction) {
		/*Doc*/	//int nomVariable_indepent=2;
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
			//Xn.add(lbl_Xn);
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
	}
}
