package CodNegocio;

import java.util.ArrayList;
import java.util.List;
 

public class Grafo<T> {
	
	private ArrayList<Vertice<T>> Vertices;
			
	public Grafo() {
		
		Vertices=new ArrayList<Vertice<T>>();
	}	
	
//-------------Getters y Setters-------------------------------------------------------------
	public List<Vertice<T>> getVertices() {
		return Vertices;
	}
//--------------------------------------------------------------------------------------------	
//-------------Metodos-----------------------------------------------------------------------
	
	public  void agregarVertice(Vertice<T> vert) {
		if(!contieneVertice(vert)) {
			getVertices().add(vert);		
		}
	}
	
	public boolean contieneVertice(Vertice<T> vert) {
		return this.getVertices().contains(vert);
	}
	
	public void agregarArista(Vertice<T> vert1,Vertice<T> vert2) {
		
		verificarNulidadInstanca(vert1);
		verificarNulidadInstanca(vert2);
				
		agregarVertice(vert1);
		agregarVertice(vert2);
		
		vert1.agregarVecino(vert2);
		vert2.agregarVecino(vert1);
		
	}
	
	public boolean contieneArista(Vertice<T> vert1,Vertice<T> vert2) {
		verificarNulidadInstanca(vert1);
		verificarNulidadInstanca(vert2);
		if(contieneVertice(vert1))
			return vert1.contieneVecino(vert2);
		return false;
	
	
	}

	private void verificarNulidadInstanca(Vertice<T> vert1) {
		if(vert1==null)
			throw new NullPointerException();
	}

	public boolean vacio() {
		return  getVertices().isEmpty();
	}
	
	
}