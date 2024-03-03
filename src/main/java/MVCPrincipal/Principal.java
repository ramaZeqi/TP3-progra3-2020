package MVCPrincipal;

import java.math.BigDecimal;
import java.util.Comparator;

import CodNegocio.Negocio;
import CodNegocio.Vertice;
import Controlador.Controlador;
import Vista.GuiApp;

public class Principal {


	public static void main(String []args) {
		
		GuiApp vista=new GuiApp();
		Negocio modelo=new Negocio(new Comparator<Vertice<BigDecimal>>() {

			@Override
			public int compare(Vertice<BigDecimal> vert1, Vertice<BigDecimal> vert2) {
				
				return vert2.getDato().compareTo(vert1.getDato());
			}
			
		});
		
		Controlador controlador=new Controlador(vista,modelo);
		vista.setControlador(controlador);
		vista.creaGUI();
	}
	
}
