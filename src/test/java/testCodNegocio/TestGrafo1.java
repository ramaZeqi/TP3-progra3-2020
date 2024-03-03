package testCodNegocio;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Comparator;

import org.junit.Test;

import CodNegocio.Grafo;
import CodNegocio.Negocio;
import CodNegocio.SolucionNegocio;
import CodNegocio.Vertice;
import CodNegocio.Vertice2D;

public class TestGrafo1 {



	@Test
	public void verticesIgualesTest()
	{
		Grafo<BigDecimal> grafo = new Grafo<BigDecimal>();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(1,new BigDecimal("24.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.agregarVertice(v1);
		grafo.agregarVertice(v2);
		assertEquals(1,grafo.getVertices().size());
	}

		
	@Test
	public void aristaInexistenteTest()
	{
		Grafo<BigDecimal> grafo = new Grafo<BigDecimal>();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(0,new BigDecimal("23.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.agregarVertice(v1);
		grafo.agregarVertice(v2);
		assertFalse(grafo.contieneArista(v1,v2));
	}

	@Test
	public void aristaInexistenteParametroTest()
	{
		Grafo<BigDecimal> grafo = new Grafo<BigDecimal>();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(0,new BigDecimal("23.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.agregarVertice(v1);
		assertFalse(grafo.contieneArista(v1,v2));
	}
	
	

	@Test
	public void agregarAristaDosVecesTest()
	{
		Grafo<BigDecimal> grafo = new Grafo<BigDecimal>();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(0,new BigDecimal("23.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		
		assertFalse(grafo.contieneArista(v2,v1));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void Vertice2DPesoVacioTest()
	{
		Negocio grafo =init_Negocio_Comparator();
		grafo.IngresarVertice2D(2,3,"");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void Vertice2DNegativoTest()
	{
		Negocio grafo =init_Negocio_Comparator();
		grafo.IngresarVertice2D(1,2,"-1.0");
	}
	
	@Test(expected = NumberFormatException.class)
	public void Vertice2DSinPesoNumericoTest()
	{
		Negocio grafo =init_Negocio_Comparator();
		grafo.IngresarVertice2D(2,2,"a");
	}
	
	@Test
	public void IngresarVertice2DTest()
	{
		Negocio grafo =init_Negocio_Comparator();
		grafo.IngresarVertice2D(3,3,"1.0");
		assertTrue(!grafo.getGrafo().getVertices().isEmpty());
	}
	
	@Test
	public void etiquetaVertice2DTest()
	{
		Negocio grafo = init_Negocio_Comparator();
		grafo.IngresarVertice2D(3,3,"1.0");
		assertEquals(0,grafo.getGrafo().getVertices().get(0).getEtiqueta());
	}

 	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest()
	{
		Negocio grafo =init_Negocio_Comparator();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.IngresarArista(v1,v2);
		
	}
 	
 	@Test(expected = IllegalArgumentException.class)
	public void AristaConVert1NUllTest()
	{
		Negocio grafo = init_Negocio_Comparator();
		Vertice2D<BigDecimal> v= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.IngresarArista(null,v);
		
	}
 	
	@Test(expected = IllegalArgumentException.class)
	public void AristaConVert2NUllTest()
	{
		Negocio grafo = init_Negocio_Comparator();
		Vertice2D<BigDecimal> v= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.IngresarArista(v,null);
		
	}
 	
 	

	@Test
	public void aristaExistenteTest()
	{
		Negocio grafo = init_Negocio_Comparator();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(0,new BigDecimal("23.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.IngresarArista(v1,v2);
		assertTrue(grafo.getGrafo().contieneArista(v1,v2));
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void aristaOpuestaTest()
	{
		Negocio grafo = init_Negocio_Comparator();
		Vertice2D<BigDecimal> v1= new Vertice2D<BigDecimal>(0,new BigDecimal("23.0"));
		Vertice2D<BigDecimal> v2= new Vertice2D<BigDecimal>(1,new BigDecimal("23.0"));
		grafo.IngresarArista(v1,v2);
		grafo.IngresarArista(v1,v2);
		assertTrue( grafo.getGrafo().contieneArista(v2,v1));
	}
		
	@Test
	public void VerticeOrigenTest()
	{
		Negocio grafo = inicializar();
		assertEquals("30.00", grafo.getVert_origen().getDato().toString());
	}
	
	@Test(expected=NullPointerException.class)
	public void CliquevacioTest()
	{
		Negocio grafo =init_Negocio_Comparator();
		SolucionNegocio sol=new SolucionNegocio();
		sol.setClique(grafo.CliquePorHeuristica());
		
	}	
	
	@Test
	public void CliqueMaxTest()
	{
		Negocio grafo = inicializar();
		SolucionNegocio sol=new SolucionNegocio();
		sol.setClique(grafo.CliquePorHeuristica());
		assertEquals("58.00", sol.CalcularPeso().toString());
	}
	
	@Test
	public void CliqueTest()
	{
		Negocio grafo=init_Negocio_Comparator();
		grafo.IngresarVertice2D(1,1, "23.0");
		grafo.IngresarVertice2D(2,2,"22.0");
		grafo.IngresarArista(grafo.getGrafo().getVertices().get(0),grafo.getGrafo().getVertices().get(1));
		SolucionNegocio sol=new SolucionNegocio();
		sol.setClique(grafo.CliquePorHeuristica());
		assertEquals("45.00", sol.CalcularPeso().toString());
	}	
	
	private Negocio init_Negocio_Comparator() {
		Negocio g=new Negocio(new Comparator<Vertice<BigDecimal>>() {

			@Override
			public int compare(Vertice<BigDecimal> vert1, Vertice<BigDecimal> vert2) {
				
				return vert2.getDato().compareTo(vert1.getDato());
			}
			
		});
		return g;
	}
	
	private Negocio inicializar() {
		Negocio g=init_Negocio_Comparator();

	
		g.IngresarVertice2D(3,3, "23.0");
		g.IngresarVertice2D(4,4, "22.0");
		g.IngresarVertice2D(5,5, "21.0");
		g.IngresarVertice2D(6,6, "30.0");
		g.IngresarVertice2D(7,7, "29.0");
		g.IngresarVertice2D(8,8, "5.0");
		
		g.IngresarArista(g.getGrafo().getVertices().get(0),g.getGrafo().getVertices().get(3));
		g.IngresarArista(g.getGrafo().getVertices().get(0),g.getGrafo().getVertices().get(2));
		g.IngresarArista(g.getGrafo().getVertices().get(2),g.getGrafo().getVertices().get(4));
		g.IngresarArista(g.getGrafo().getVertices().get(0),g.getGrafo().getVertices().get(4));
		g.IngresarArista(g.getGrafo().getVertices().get(1),g.getGrafo().getVertices().get(4));
		g.IngresarArista(g.getGrafo().getVertices().get(5),g.getGrafo().getVertices().get(3));
		g.IngresarArista(g.getGrafo().getVertices().get(5),g.getGrafo().getVertices().get(0));
		
				
		return g;
	}

}