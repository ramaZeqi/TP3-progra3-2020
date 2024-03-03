package CodNegocio;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SolucionNegocio {

	List<Vertice<BigDecimal>> Clique;
	
	public void setClique(List<Vertice<BigDecimal>> clique) {
		Clique = clique;
	}
	
	@Override
	public String toString()
	{
		if(Clique.size()==1)
			return "La clique de peso máximo en este grafo es {"+ Clique.get(0).getEtiqueta()+"}, y tiene un peso de "+Clique.get(0).getDato().toString();
		else
			return "La clique de peso máximo en este grafo es "+ VerticesPorIdentificador()+" y tiene un peso de "+CalcularPeso().toString();
	}

	//Declaro con acceso default, a fin de utilizarlo en los test para verificar el funcionamiento de la funcion Clique_ValMax() de la clase negocio.
	public BigDecimal CalcularPeso() {
		BigDecimal aux=new BigDecimal(0.0);
		for(Vertice<BigDecimal> vert: Clique) {
			aux=aux.add(vert.getDato());
			
		}
		return aux;
	}
	
	//Clique ordenado de menor a mayor en base al atributo Identificador
	private String VerticesPorIdentificador() {
			Collections.sort(Clique,new Comparator<Vertice<BigDecimal>>() {
			@Override
			public int compare(Vertice<BigDecimal> vert1, Vertice<BigDecimal> vert2) {
				if(vert1.getEtiqueta()>vert2.getEtiqueta())
					return 1;
				else if(vert1.getEtiqueta()<vert2.getEtiqueta())
					return -1;
				else
					return 0;
			}
		});
		
		StringBuilder aux=new StringBuilder();
		aux.append("{");
		for(Vertice<BigDecimal> vert: Clique) {
			aux.append(vert.getEtiqueta()+",");
		}
		
		aux.replace(aux.length()-1,aux.length()-1,"}");
		
		return aux.toString();
		
		
	}
	


}