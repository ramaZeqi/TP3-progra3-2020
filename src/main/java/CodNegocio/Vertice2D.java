package CodNegocio;

public class Vertice2D<T> extends Vertice<T> {
	private int x,y;
	
	public Vertice2D(T etiq) {	
		super(etiq);
		this.x=0;
		this.y=0;
		
	}
	
	public Vertice2D(int ident,T etiq) {	
		super(ident,etiq);
		this.x=0;
		this.y=0;
		
	}
	
	public Vertice2D(int x,int y,T etiq) {	
		super(etiq);
		this.x=x;
		this.y=y;
		
	}
	
	public Vertice2D(int x,int y,int ident,T etiq) {	
		super(ident,etiq);
		this.x=x;
		this.y=y;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	

}
