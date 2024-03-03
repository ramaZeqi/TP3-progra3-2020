package AuxDIbujoGrafo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Circulo implements Figura {
	
	private Point coord;
    private int radio;
    private Color color;
    

    public Circulo() {
        this(0, 0, 1, Color.BLACK);
    }

    public Circulo(int x, int y, int radio, Color color) {
        this(new Point(x, y), radio, color);
    }

	public Circulo(Point punto, int radio, Color color) {
		 this.coord = punto;
	     this.radio = radio;
	     this.color = color;
	}

	
	public Point getCoordenadas() {
		return this.coord;
	}


	public void setCoordenadas(Point coord) {
		this.coord=coord;
		
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setColor(Color color) {
		this.color=color;
		
	}

	@Override
	public void paintComponent(Graphics g) {
		   int diametro = (int) (this.radio * 2);
	        int x = (int) (coord.x - this.radio);
	        int y = (int) (coord.y - this.radio);
	                
	        g.setColor(this.color);        
	        g.fillOval(x, y, diametro, diametro);
	}
}
