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
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author skot
 */
public abstract class ImageProducer {
    
    protected int width, height;
    
    protected int rows = 15;
    protected int cols = 15;
    protected int initialSpacing = 75;
    java.util.Random rando;
    public BufferedImage image;
    Color colorGrid[][];
    
    
    
    ImageProducer(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        while (cols * initialSpacing < height) {
            cols += 2;
        }
        
        while (rows * initialSpacing < width) {
            rows += 2;
        }
        
        System.out.println("imgProd width: " + width);
        System.out.println("imgProd height: " + height);
        System.out.println("final rows: " + rows);
        System.out.println("final cols: " + cols);
        
        image = new BufferedImage(width,height, BufferedImage.OPAQUE);
        Graphics g = image.createGraphics();

        g.setColor(Color.RED);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        g.setColor(Color.RED);

        rando = new java.util.Random();

    }//constructor
    
    
    
    public void setColorGrid(ColorScheme scheme, int minValue, float factor, Color[][] colorGrid) {
        
        int maxValue = 255 - minValue;

        
        switch (scheme) {
            case REDS:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(r, 0, b);
                    }
                }
                break;
            case GREENS:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(0, r, b);
                    }
                }
                break;
            case BLUES:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(b, 0, r);
                    }
                }
                break;
            case RANDOM:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        colorGrid[x][y] = new Color(rando.nextInt(255), rando.nextInt(255), rando.nextInt(255));
                    }
                }
                break;
        }
        
    }
    
    
    public abstract void update();

    
    
    
    
    
    protected void blur()
    {
        
//        float[] matrix = 
//        {
//            0.111f, 0.111f, 0.111f, 
//            0.111f, 0.111f, 0.111f, 
//            0.111f, 0.111f, 0.111f, 
//        };
        
        
        float[] matrix = 
        {
            0.211f, 0.211f, 0.211f, 
            0.211f, 0.211f, 0.211f, 
            0.211f, 0.211f, 0.211f,
        };
        
        
        java.awt.image.BufferedImageOp op = new java.awt.image.ConvolveOp( new Kernel(3, 3, matrix) );
	BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.OPAQUE);
        BufferedImage blurredImage = op.filter(image, temp);
        image = blurredImage;
        
    }
    
    //Broken
    protected void spin()
    {
        AffineTransform transform = new AffineTransform();
        BufferedImage tmp;
        
        transform.rotate(rando.nextFloat(), 50, 50);
        //transform.rotate(1);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        tmp = op.filter(image, null);
        image = tmp;
    }
    

    
    
}
