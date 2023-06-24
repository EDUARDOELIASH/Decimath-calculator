package validaciones;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class campos_interfaz {
	
	public char objetivo(int comboBox_option){
		char objetive=' ';
        boolean is_select_objetive= comboBox_option!=0;

    	if(is_select_objetive==true) {
			switch(comboBox_option) {
				case 1: 	objetive='+';
					break;
				case 2: 	objetive='-';
					break; 
			}
		}       
    	else{
			JOptionPane.showMessageDialog(null,"En la Funcion Objetivo no se selecciono un Objetivo","Error en Funcion Objetivo",JOptionPane.WARNING_MESSAGE);
		}
	    return objetive;
	}
	
	public double [] FuncionObjetivo(int nomVariable_indepent,ArrayList<JTextField> Objetivo){
		double [] nomVariable_indepentdecision=new double [nomVariable_indepent+1];
		//boolean FO=true;
		for(int i=0;i<nomVariable_indepent;i++){
			JTextField ob = Objetivo.get(i);
			try {
				double verificacion=Double.parseDouble(ob.getText());
				nomVariable_indepentdecision[i]=verificacion;
				//System.out.println(nomVariable_indepentdecision[i]);
			}
			catch(Exception error) {
				//System.out.println("Coeficiente no valido");
				//FO=false;
				JOptionPane.showMessageDialog(null,"Un campo no es de tipo Numerico","Error en Funcion Objetivo",JOptionPane.WARNING_MESSAGE);
				nomVariable_indepentdecision= null;
				break;
			}
		}
	    return nomVariable_indepentdecision;
	}
	
	public double[][] RecorrerArray(int nomVariable_indepent,int numberRestriction,ArrayList<JTextField> coeficiente, ArrayList<JComboBox> simbolo) {
		double[][] restriccionI = new double[numberRestriction][nomVariable_indepent+1];
		int j=0;
		//JOptionPane.showMessageDialog(null,coeficiente.size(),"",JOptionPane.INFORMATION_MESSAGE);
		int i=0,k=0;
		//for(int i=0;i<numberRestriction;i++) {
			while(j<coeficiente.size()&&i<numberRestriction) {
				JTextField x = coeficiente.get(j);
				//
				//while(dato==false) {
					try {
						double verificacion=Double.parseDouble(x.getText());
						if(k==nomVariable_indepent) {
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
						//dato=false;
						boolean errorcampoResultado=k==2;
						if(errorcampoResultado==true) {
							JOptionPane.showMessageDialog(null,"Un campo Resultado no es de tipo Numerico","Error en Restriccion "+(i+1),JOptionPane.WARNING_MESSAGE);

						}
						else {
							JOptionPane.showMessageDialog(null,"Un campo no es de tipo Numerico","Error en Restriccion "+(i+1),JOptionPane.WARNING_MESSAGE);
						}
						restriccionI=null;
						break;
						//
					}
				j++;
			};
		return restriccionI;
	}
	
	public int [] simbolo(int numberRestriction,ArrayList<JComboBox> Simbolo) {
		int[] simbolo = new int [numberRestriction];
		//boolean simb=true;
		for(int i=0;i<numberRestriction;i++) {
			JComboBox Sim = Simbolo.get(i);
			if(Sim.getSelectedIndex()!=0) {
				simbolo[i]=Sim.getSelectedIndex();
			}
			else {
				//simb=false;
				JOptionPane.showMessageDialog(null,"En una Restriccion no se selecciono un simbolo logico","Error en Restriccion "+(i+1),JOptionPane.WARNING_MESSAGE);
				simbolo=null;
				//System.out.println("En la Restriccion "+(i+1)+" se encuentra un simbolo logico dentro de comboBox sin seleccionar");
			}
		}
		return simbolo;
	}
	
	public String [] SimbolosFN(int numberRestriction, int[] simbolo) {
		String [] SimbolosFN=new String [numberRestriction];
		for(int i=0;i<numberRestriction;i++) {
			int signo=simbolo[i];
			switch(signo) {
				case 1: 	SimbolosFN[i]="<=";
					break;
				case 2: 	SimbolosFN[i]=">=";
					break;
				case 3: 	SimbolosFN[i]="=";
					break;
			}
		}
		return SimbolosFN;
	}
	
	public double[][] ladoDerechoNN(double[][] restriccionI, int nnomVariable_indepent, int numberRestrictionest, String[] simbolo){
        for (int i = 0; i<numberRestrictionest; i++){
            if (restriccionI[i][nnomVariable_indepent] < 0){
            for (int k = 0; k <=nnomVariable_indepent; k++){ //Lado izquierdo
                restriccionI[i][k] *= (-1);
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
        }
        return restriccionI;
    }
}
