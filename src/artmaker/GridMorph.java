/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.*;

/**
 *
 * @author skot
 */
public class GridMorph extends ImageProducer {
         
    int morphValue = 2;
    
    
    Point pointGrid[][];
    Polygon polygonGrid[][];

    

    GridMorph(int width, int height)
    {
        super(width, height);

        pointGrid = new Point[rows+1][cols+1];
        polygonGrid = new Polygon[rows][cols];
        colorGrid = new Color[rows][cols];

        for (int x = 0; x < rows+1; x++)
        {
            for (int y = 0; y < cols+1; y++)
            {
                pointGrid[x][y] = new Point();
                pointGrid[x][y].x = x * initialSpacing;
                pointGrid[x][y].y = y * initialSpacing;
            }
        }
       
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.RED);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());


        for (int x = 0; x < rows; x++)
        {
            for (int y = 0; y < cols; y++)
            {
                polygonGrid[x][y] = new Polygon();

                polygonGrid[x][y].addPoint(pointGrid[x][y].x, pointGrid[x][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y].x, pointGrid[x+1][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y+1].x, pointGrid[x+1][y+1].y);
                polygonGrid[x][y].addPoint(pointGrid[x][y+1].x, pointGrid[x][y+1].y);

                //Establish a color associated with a specific poly
                //colorGrid[x][y] = new Color(rando.nextInt(255), rando.nextInt(255), rando.nextInt(255));
                
                //colorGrid[x][y] = new Color((rando.nextInt(235))+20,0,0);
                
                g.setColor(colorGrid[x][y]);
                
                g.fillPolygon(polygonGrid[x][y]);

            }
        }
        
        setColorGrid(ColorScheme.REDS, 55, .80f, colorGrid);
        
    }//constructor
    
    
    
    
    public void update()
    {
        
        //Adjust each point in a (semi) random direction
        for (int x = 0; x < rows+1; x++)
        {
            for (int y = 0; y < cols+1; y++)
            {
                pointGrid[x][y].x += (rando.nextInt(morphValue) - rando.nextInt(morphValue));
                pointGrid[x][y].y += (rando.nextInt(morphValue) - rando.nextInt(morphValue));
            }
        }
        
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        g.setColor(colorGrid[0][0]);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (int x = 0; x < rows; x++)
        {
            for (int y = 0; y < cols; y++)
            {
                polygonGrid[x][y] = new Polygon();

                polygonGrid[x][y].addPoint(pointGrid[x][y].x, pointGrid[x][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y].x, pointGrid[x+1][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y+1].x, pointGrid[x+1][y+1].y);
                polygonGrid[x][y].addPoint(pointGrid[x][y+1].x, pointGrid[x][y+1].y);
                
                g.setColor(colorGrid[x][y]);
                //g.setColor(new Color(rando.nextInt(255),rando.nextInt(255),rando.nextInt(255)));
                
                g.fillPolygon(polygonGrid[x][y]);
                
                //g.setColor(Color.RED);
                //g.drawPolygon(polygonGrid[x][y]);
                    
            }
        }
        
        //APPLY EFFECTS
        
        //blur();
        //spin();
        
        g.dispose();
    }  
}


class Point
{
    int x, y;
    
    Point()
    {
        x = 0;
        y = 0;
    }
    
    Point(int xx, int yy)
    {
        x = xx;
        y = yy;
    }
}
