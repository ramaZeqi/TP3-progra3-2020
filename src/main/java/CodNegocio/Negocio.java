package CodNegocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Negocio{
	
	private Vertice2D<BigDecimal> Vert_origen;
	private Grafo<BigDecimal> grafo;
	private Comparator<Vertice<BigDecimal>> comparador;
	private int etiquetaVertice;
	
 

	public Negocio(Comparator<Vertice<BigDecimal>> compa)
	{
		
		Vert_origen=null;
		grafo=new Grafo<BigDecimal>(); 
		//Clase anonima (Comparador por mayor peso)
		comparador = compa;
		etiquetaVertice=0;
		
	}	
	
	
//----------------------------------------------------------------------------------------------

	public Comparator<Vertice<BigDecimal>> getComparador() {
		return comparador;
	}
	
	public void setComparador(Comparator<Vertice<BigDecimal>> comparador) {
		this.comparador = comparador;   
	}	
	
	public Grafo<BigDecimal> getGrafo() {
		return grafo;
	}

	public Vertice2D<BigDecimal> getVert_origen() {
		return Vert_origen;
	}
	
	public void setVert_origen(Vertice2D<BigDecimal> vert) {
		if(getGrafo().vacio())
			Vert_origen=vert;
		else {
			if(getComparador().compare(vert,Vert_origen)==-1)
				Vert_origen=vert;		
		}
	}

 
//---------------------------------------------------------------------------
	
	@SuppressWarnings("deprecation")
	public  void IngresarVertice2D(int x,int y,String valor) {
	
			vertificarEntradaNoVacias(valor);
			VerificarValorEnPeso(valor);		  
			Vertice2D<BigDecimal> aux_vert= new Vertice2D<BigDecimal>(x,y,etiquetaVertice,new BigDecimal(valor).setScale(2,BigDecimal.ROUND_FLOOR));
			setVert_origen(aux_vert);
			getGrafo().agregarVertice(aux_vert);
			etiquetaVertice++;
	}

	private void VerificarValorEnPeso(String valor) {
		try{
			if(Double.parseDouble(valor)<0)
				throw new IllegalArgumentException("No se permiten valores negativos");
		}
		catch(NumberFormatException e) {
			throw new NumberFormatException ("Ingrese un valor numerico en peso");
		}
			
	}
 
	private void vertificarEntradaNoVacias(String valor) {
		if(valor.isEmpty())
			throw new IllegalArgumentException("Complete el ingreso de valores");
		
	}

//---------------------------------------------------------------------------	
	
	public void IngresarArista(Vertice<BigDecimal> vert1,Vertice<BigDecimal> vert2) {
		
		verificarVacio(vert1);
		verificarVacio(vert2);
		verificarAristaMutiple(vert1, vert2);
		verificarLoops(vert1, vert2);	
		
		 grafo.agregarArista(vert1, vert2);
		 
	 
	}
	
	private void verificarVacio(Vertice<BigDecimal> vert){
		if(vert==null)
			throw new IllegalArgumentException("Debe seleccionar dos Vertices");
			
	}

	private void verificarAristaMutiple(Vertice<BigDecimal> vert1, Vertice<BigDecimal> vert2) {
		if(grafo.contieneArista(vert1, vert2))
			throw new IllegalArgumentException("No se permiten aristas multiples");
	}

	private void verificarLoops(Vertice<BigDecimal> vert1, Vertice<BigDecimal> vert2) {
		if(vert1.equals(vert2))
			throw new IllegalArgumentException("No se permiten loops");
	}
	
	
	
//----------------------------------------------------------------------------------
	
	public List<Vertice<BigDecimal>> CliquePorHeuristica(){
		
		if(getGrafo().vacio())
			throw new NullPointerException("ingrese datos al grafo");
		
		ArrayList<Vertice<BigDecimal>> Clique=new ArrayList<Vertice<BigDecimal>>();
		List<Vertice<BigDecimal>> Aux_VecinosOrdenados=new ArrayList<Vertice<BigDecimal>>();
		
		Clique.add(getVert_origen());
		Aux_VecinosOrdenados.addAll(getVert_origen().getVecinos());		
		Collections.sort(Aux_VecinosOrdenados,getComparador());		
	
		for(Vertice<BigDecimal> Candidato: Aux_VecinosOrdenados) {
			
			if(contenidoEnClique(Clique,Candidato)) {
				Clique.add(Candidato);
				Aux_VecinosOrdenados=new ArrayList<Vertice<BigDecimal>>(Clique.get(Clique.size()-1).getVecinos());
				Collections.sort(Aux_VecinosOrdenados,getComparador());	
			}
			
		} 
			
		return Clique;
			
	}

	private boolean contenidoEnClique(List<Vertice<BigDecimal>> Clique,Vertice<BigDecimal> Candidato) {
		
		if(Clique.contains(Candidato))
			return false;
		
		boolean contiene=true;
		for(Vertice<BigDecimal> Vert: Clique) {
			
				contiene=contiene && Candidato.contieneVecino(Vert)  ;
		}
		
		return contiene;
	}

	public void limpiarCodNegocio() {
		etiquetaVertice=0;
		Vert_origen=null;
		grafo=new Grafo<BigDecimal>(); 
		
	}

}
