package AuxDIbujoGrafo;

import java.awt.Color;
import java.awt.Graphics;
 

public interface Figura {

    Color getColor();
  
    void setColor(Color color);

    void paintComponent(Graphics g);
	
}
