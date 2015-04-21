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

/**
 *
 * @author skot
 */
public class GridMorph {
    
    final int rows = 25;
    final int cols = 15;
    final int initialSpacing = 50;
    
    final int morphValue = 2;
    
    Point pointGrid[][];
    Polygon polygonGrid[][];
    Color colorGrid[][];
    
    public BufferedImage image;
    
    GridMorph(int wid, int hei)
    {
        java.util.Random rando = new java.util.Random();
        
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

        image = new BufferedImage(wid,hei, BufferedImage.OPAQUE);
        Graphics g = image.createGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        g.setColor(Color.RED);


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
                colorGrid[x][y] = new Color(rando.nextInt(255), rando.nextInt(255), rando.nextInt(255));
                //colorGrid[x][y] = new Color(255, 255, rando.nextInt(255));
                g.setColor(colorGrid[x][y]);
                
                g.fillPolygon(polygonGrid[x][y]);

            }
        }
    }//constructor
    
    
    public void morph()
    {
        java.util.Random rando = new java.util.Random();
        
        //Adjust each point in a (semi) random direction
        for (int x = 0; x < rows+1; x++)
        {
            for (int y = 0; y < cols+1; y++)
            {
                pointGrid[x][y].x += (rando.nextInt(morphValue) - rando.nextInt(morphValue));
                pointGrid[x][y].y += (rando.nextInt(morphValue) - rando.nextInt(morphValue));
            }
        }
        
        Graphics g = image.getGraphics();
        
        g.setColor(Color.BLACK);
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
    }

    private void blur()
    {
        
        float[] matrix = 
        {
            0.111f, 0.111f, 0.111f, 
            0.111f, 0.111f, 0.111f, 
            0.111f, 0.111f, 0.111f, 
        };
        
        /*
        float[] matrix = 
        {
            0.211f, 0.211f, 0.211f, 
            0.211f, 0.211f, 0.211f, 
            0.211f, 0.211f, 0.211f,
        };
        */
        
        java.awt.image.BufferedImageOp op = new java.awt.image.ConvolveOp( new Kernel(3, 3, matrix) );
	BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.OPAQUE);
        BufferedImage blurredImage = op.filter(image, temp);
        image = blurredImage;
        
    }
    
    //Broken
    private void spin()
    {
        AffineTransform transform = new AffineTransform();
        BufferedImage tmp;
        
        transform.rotate(1, image.getWidth()/2, image.getHeight()/2);
        //transform.rotate(1);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        tmp = op.filter(image, null);
        image = tmp;
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
