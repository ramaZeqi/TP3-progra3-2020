package AuxDIbujoGrafo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Linea implements Figura{
	Point P1;
	Point P2;
	Color color;

	public Linea(Point extremo_1, Point extremo_2,Color col) {
		this.P1 = extremo_1;
		this.P2 = extremo_2;
		this.color=col;
	}

	
	
	public Point getP1() {
		return P1;
	}

	
	public void setP1(Point coord) {
		this.P1=coord;
		
	}
	
	
	public Point getP2() {
		return P2;
	}

	
	public void setP2(Point coord) {
		this.P2=coord;
		
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color=color;
		
	}

	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2= (Graphics2D) g;		
		g2.setStroke(new BasicStroke(2));
		g2.setColor(this.color);
		g2.drawLine(P1.x,P1.y,P2.x,P2.y);		
		g2.setStroke(new BasicStroke(0));
		
		
	}



}
