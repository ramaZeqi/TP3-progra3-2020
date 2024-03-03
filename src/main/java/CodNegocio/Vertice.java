package CodNegocio;

import java.util.HashSet;
import java.util.Set;

 
public class Vertice<T>{
	private int etiqueta;
	private T dato;
	private HashSet<Vertice<T>> vecinos;

	public Vertice(T dat) {	
		etiqueta=0;		
		dato=dat;
		vecinos=new HashSet<Vertice<T>>();
	}
	
	public Vertice(int ident,T dat) {	
		etiqueta=ident;		
		dato=dat;
		vecinos=new HashSet<Vertice<T>>();
	}
	
	
//-------------------------------------Getters-----------------------------------------------	
	public T getDato() {
		return dato;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Vertice<T>> getVecinos() {
		return (Set<Vertice<T>>) vecinos.clone();
	}
	
	public int getEtiqueta() {
		return etiqueta;
	}
	

	public void setIdentificador(int identificador) {
		etiqueta = identificador;
	}

//------------------------------------------------------------------------------------------	
//-------------------------------------Metodos-----------------------------------------------
	public void agregarVecino(Vertice<T> vec) {
		if(!contieneVecino(vec)){
			vecinos.add(vec);
		}
	}	
	
	public boolean contieneVecino(Vertice<T> vec) {
		return vecinos.contains(vec);
	}
		
	@Override
	public int hashCode() {
		return this.getEtiqueta();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		else {
			if(obj instanceof Vertice) {
				@SuppressWarnings("unchecked")
				Vertice<T> aux = (Vertice<T>) obj;
    			return this.getEtiqueta()==aux.getEtiqueta();
			}
		}
		return false;
	
	
	}
 
	@Override
	public String toString() {
		return "Numero:"+this.getEtiqueta()+"-Peso: "+this.getDato().toString(); 
	}
	
	
}
