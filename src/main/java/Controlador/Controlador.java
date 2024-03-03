package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import CodNegocio.Negocio;
import CodNegocio.SolucionNegocio;
import CodNegocio.Vertice2D;
import Vista.GuiApp;

public class Controlador implements ActionListener,MouseListener{
	
	private Negocio negocio;
	private GuiApp vista;
	private Vertice2D<BigDecimal> extremo_sel_1,extremo_sel_2;
	
	
	public Controlador(){
		this.negocio=null;
		this.vista=null;
		extremo_sel_1=extremo_sel_2= null;
						
	}
	public Controlador(GuiApp view,Negocio neg){
		this.negocio=neg;
		this.vista=view;
		extremo_sel_1=extremo_sel_2= null;
		
	}

//-------------gettes y setters------------------------------		
	public Negocio getNegocio() {
		return negocio;
	}
	
	public GuiApp getVista() {
		return vista;
	}

//---------------_---------------_---------------_---------------_
	
	@Override
	public void actionPerformed(ActionEvent listener) {
		//seleccionador de vertice1
		if(listener.getSource()==this.getVista().getBtnSelectVert1()) {
			try {
				int index=getVista().getListVertices().getSelectedIndex();
				extremo_sel_1 = (Vertice2D<BigDecimal>) getVista().getListModelVertice().getElementAt(index);
				this.getVista().getLblEtiquetaV1().setText (Integer.toString(extremo_sel_1.getEtiqueta()));
				this.getVista().getLblPesoV1().setText(extremo_sel_1.getDato().toString());
			
			}
			catch(IndexOutOfBoundsException e) {
				this.getVista().MostrarVentanaEmergente("Seleccione un Vertice");
			}
		}
		//seleccionador de vertice 2
		if(listener.getSource()==this.getVista().getBtnSelectVert2()) {
			try {
				int index=getVista().getListVertices().getSelectedIndex();
				extremo_sel_2 = (Vertice2D<BigDecimal>) getVista().getListModelVertice().getElementAt(index);
				this.getVista().getLblEtiquetaV2().setText (Integer.toString(extremo_sel_2.getEtiqueta()));
				this.getVista().getLblPesoV2().setText(extremo_sel_2.getDato().toString());
			
			}
			catch(IndexOutOfBoundsException e) {
				this.getVista().MostrarVentanaEmergente("Seleccione un Vertice");
			}
	
		}
		if(listener.getSource()==this.getVista().getBtnCrearArista()) {
			crear_arista();
		}	
		
		if(listener.getSource()==this.getVista().getBtnCalculoClique()) {
			calcular_clique();
			
		}
	
		if(listener.getSource()==this.getVista().getBtnLimpiarCarga()) {
			limpiar_carga();
		}

		if(listener.getSource()==this.getVista().getBtnMostrarClique()) {
			pintar_clique();
			
		}	
				
	}

	@Override
	public void mouseClicked(MouseEvent listener) {
		if(listener.getSource()==this.getVista().getPnlGrafo()) {
			crear_vertice(listener);
		}
	
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		}

	@Override
	public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

	@Override
	public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

//------------------------------------------------------------------------------------------------
		
	private void crear_vertice(MouseEvent listener) {
		try {
				String peso=this.getVista().VentanaIngresoPeso();
				this.getNegocio().IngresarVertice2D(listener.getX(),listener.getY(),peso);
				//	tomo la referencia del vertice agregado
				Vertice2D<BigDecimal> aux=(Vertice2D<BigDecimal>) this.getNegocio().getGrafo().getVertices().get(this.getNegocio().getGrafo().getVertices().size()-1);
				this.getVista().agregarVertice2DALista(aux);
				this.getVista().getPnlGrafo().agregarDibujoVertice2D(aux);
				this.getVista().getPnlGrafo().RestablecerGrafo();
						
			}
			catch( NumberFormatException e) {
				this.getVista().MostrarVentanaEmergente(e.getMessage());
			}
			catch(IllegalArgumentException e) {
				this.getVista().MostrarVentanaEmergente(e.getMessage());
			}
			catch(NullPointerException e) {
				//Captura la cancelacion del algun joptionpane
			}
	}
	
	private void crear_arista() {
		try {
			this.getNegocio().IngresarArista(this.extremo_sel_1,this.extremo_sel_2);
			this.getVista().borrarLblPestañaCargaDatos();
			this.getVista().agregarAristaLista("("+this.extremo_sel_1.getEtiqueta()+","+this.extremo_sel_2.getEtiqueta()+")");
			this.getVista().getPnlGrafo().agregarDibujoArista(this.extremo_sel_1,this.extremo_sel_2);
			this.extremo_sel_1=this.extremo_sel_2=null;
			this.getVista().getPnlGrafo().RestablecerGrafo();
			
		}
		catch( NumberFormatException e) {
			this.getVista().MostrarVentanaEmergente(e.getMessage());
		}
		catch(IllegalArgumentException e) {
			this.getVista().MostrarVentanaEmergente(e.getMessage());
		}
	}

	private void pintar_clique() {
		try {
			this.getVista().getPnlGrafo().PintarClique(this.getNegocio().CliquePorHeuristica());
		}
		catch(NullPointerException e) {
			this.getVista().MostrarVentanaEmergente(e.getMessage());
		}
	}

	private void limpiar_carga() {
		this.extremo_sel_1=this.extremo_sel_2=null;
		this.getVista().borrarLblPestañaCargaDatos();
		this.getNegocio().limpiarCodNegocio();
		this.getVista().getListModelArista().removeAllElements();
		this.getVista().getListModelVertice().clear();
		this.getVista().getPnlGrafo().limpiarPanel();
		this.getVista().getPnlGrafo().repaint();
	}

	private void calcular_clique() {
		try {
			SolucionNegocio sol=new SolucionNegocio();	
			sol.setClique(this.getNegocio().CliquePorHeuristica());
			this.getVista().MostrarVentanaEmergente(sol.toString());
		}
		catch(NullPointerException e) {
			this.getVista().MostrarVentanaEmergente(e.getMessage());
		}
	}
	
		
	
}
