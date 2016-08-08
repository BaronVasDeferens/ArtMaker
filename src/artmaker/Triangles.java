/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;
import java.awt.*;
/**
 *
 * @author skot
 */
public class Triangles extends ImageProducer {
    
    Polygon poly;
    Polygon poly2;
    Point one, two, three;
    //final int minLength = 75;
    
    Point pointGrid[][];
    int gridRows = 25;
    int gridCols = 25;
    int squareSize = 25;
    final int spacing = 46;
    
    Color colorGrid[][];
    
    final int morphValue = 3;
    int xMorph = 8;
    int yMorph = 8;
    
    Triangles(int width, int height) {
        
        super(width,height);
        
        while (gridCols * squareSize < width) {
            gridCols += 2;
        }
        
        while (gridRows * squareSize < height) {
            gridRows += 2;
        }
        
        pointGrid = new Point[gridRows][gridCols];
        colorGrid = new Color[gridRows][gridCols];
               
        for (int i = 0; i < gridRows; i++) {
            
            for (int j = 0; j < gridCols; j++) {

                if (j%2 == 0)
                    pointGrid[i][j] = new Point(i * spacing, j * spacing);
                else
                    pointGrid[i][j] = new Point(i * spacing + (spacing/2), j * spacing);
                
                
                // black and white
                //int val = rando.nextInt(2) * 255;
                //colorGrid[i][j] = new Color(val,val,val);
                
                // Shades of red
                colorGrid[i][j] = new Color((rando.nextInt(235))+20,0,0);
                
                // All colors; stained glass?
                //colorGrid[i][j] = new Color( rando.nextInt(255) ,rando.nextInt(255), rando.nextInt(255));
             
                //colorGrid[i][j] = new Color(0,255, rando.nextInt(255));
                
            }
        }
        

    }
    
    public void update() {
        
        Graphics g = image.getGraphics();
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(colorGrid[0][0]);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        
        g2.setColor(Color.RED);
        
        Polygon poly;
        
        int i = 0, j = 0;
        
        for (i = 0; i < gridRows-1; i++) {
            
            for (j = 0; j < gridCols-1; j++) {
                               
                // tweak grid along different x and y values
                pointGrid[i][j].x += (rando.nextInt(xMorph) - rando.nextInt(xMorph));
                pointGrid[i][j].y += (rando.nextInt(yMorph) - rando.nextInt(yMorph));
                
                // UPWARD-POINTING triangles

                poly = new Polygon();

                if (j >= 1) {

                    if (j%2 != 0) {
                        //g.setColor(Color.WHITE);
                        poly.addPoint(pointGrid[i][j].x, pointGrid[i][j].y);
                        poly.addPoint(pointGrid[i+1][j-1].x, pointGrid[i+1][j-1].y);
                        poly.addPoint(pointGrid[i+1][j].x, pointGrid[i+1][j].y);
                    }

                    else if (i >= 2) {
                        //g.setColor(Color.ORANGE);
                        poly.addPoint(pointGrid[i][j].x, pointGrid[i][j].y);
                        poly.addPoint(pointGrid[i-1][j-1].x, pointGrid[i-1][j-1].y);
                        poly.addPoint(pointGrid[i-1][j].x, pointGrid[i-1][j].y);
                    }

                    // cycles through shades of red
                    //colorGrid[i][j] = new Color( ((colorGrid[i][j].getRed() + 1)%255), colorGrid[i][j].getGreen(), colorGrid[i][j].getBlue() );
                    
                    // From red to blue, pops back to black
                    //colorGrid[i][j] = new Color( ((colorGrid[i][j].getRed() + 1)%255), (colorGrid[i][j].getGreen()+1) % 255, (colorGrid[i][j].getBlue()+1)%255 );
                    g2.setColor(colorGrid[i][j]);
                    
                    //g.setColor(new Color( rando.nextInt(235)+20 ,rando.nextInt(235)+20, rando.nextInt(235)+20));
                    g2.fillPolygon(poly);
                    g2.setColor(Color.BLACK);
                    g2.drawPolygon(poly);
                   
                    
                }

                 poly = new Polygon();

                // DOWNWARD-POINTING triangles
                if (j%2 == 0) {
                    poly.addPoint(pointGrid[i][j].x, pointGrid[i][j].y);
                    poly.addPoint(pointGrid[i+1][j].x, pointGrid[i+1][j].y);
                    poly.addPoint(pointGrid[i][j+1].x, pointGrid[i][j+1].y);
                    //g.setColor(colorGrid[i][j]);
                }

                else {
                    poly.addPoint(pointGrid[i][j].x, pointGrid[i][j].y);
                    poly.addPoint(pointGrid[i+1][j].x, pointGrid[i+1][j].y);
                    poly.addPoint(pointGrid[i+1][j+1].x, pointGrid[i+1][j+1].y);
                    //g.setColor(Color.BLUE);
                }

                    g2.setColor(colorGrid[i][j]);
                    //g.setColor(new Color( rando.nextInt(235)+20 ,rando.nextInt(235)+20, rando.nextInt(235)+20));
                    g2.fillPolygon(poly);
                    g2.setColor(Color.BLACK);
                    g2.drawPolygon(poly);
                              
            }
        }
         
    }
    
   
}


