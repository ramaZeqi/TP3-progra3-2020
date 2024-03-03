package Vista;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import AuxDIbujoGrafo.Circulo;
import AuxDIbujoGrafo.Figura;
import AuxDIbujoGrafo.Linea;
import CodNegocio.Vertice;
import CodNegocio.Vertice2D;



public class PanelGrafo extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Figura> Vertices;
	private List<Figura> Aristas;
	
	
	/**
	 * Create the panel.
	 */
	public PanelGrafo() {	 
		  
		        this.Vertices = new ArrayList<Figura>();
		        this.Aristas = new ArrayList<Figura>();
		        setBackground(Color.lightGray);
		       
		    
	    
	}
	
	public void limpiarPanel() {
		this.Vertices.clear();
		this.Aristas.clear();
		repaint();
		
	}
	
	
	public void agregarDibujoVertice2D(Vertice2D<BigDecimal> vert) {
		Vertices.add(new Circulo(new Point(vert.getX(),vert.getY()),10,Color.BLUE));
		repaint();
	}
	
	public void agregarDibujoArista(Vertice2D<BigDecimal> vert1,Vertice2D<BigDecimal> vert2) {
		Aristas.add(new Linea(new Point(vert1.getX(),vert1.getY()),new Point(vert2.getX(),vert2.getY()), Color.blue));
	}
	
	public void RestablecerGrafo() {
		for(Figura vert: this.Vertices)
			vert.setColor(Color.BLUE);
		for(Figura arst: this.Aristas)
			arst.setColor(Color.BLUE);
	}	
	
	public void PintarClique(List<Vertice<BigDecimal>> clique) {
		for(Vertice<BigDecimal> aux:clique)
		{
			Vertice2D<BigDecimal> vert1=(Vertice2D<BigDecimal>) aux;
			Vertices.add(new Circulo(new Point(vert1.getX(),vert1.getY()),10,Color.BLACK));
			
			for(Vertice<BigDecimal> aux2:clique) {
				Vertice2D<BigDecimal> vert2=(Vertice2D<BigDecimal>) aux2;
				Aristas.add(new Linea(new Point(vert1.getX(),vert1.getY()),new Point(vert2.getX(),vert2.getY()), Color.black));
			}
			
		}
		repaint();
	}
			
	@Override
	public void paintComponent(Graphics g) {
		 super.paintComponent(g);    
		
	 
	        
	     for (Figura linea : Aristas) {
	    	 linea.paintComponent(g);
	     }
	     for (Figura circulo : Vertices) {
			 circulo.paintComponent(g);
         }
	        
		 

	}

 

	
}
