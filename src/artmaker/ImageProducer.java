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

/**
 *
 * @author skot
 */
public abstract class ImageProducer {
    
    protected int width, height;
    
    protected int rows = 25;
    protected int cols = 15;
    protected int initialSpacing = 50;

    java.util.Random rando;
    
    public BufferedImage image;
    
    ImageProducer(int wid, int hei)
    {
        width = wid;
        height = hei;
        
        image = new BufferedImage(width,height, BufferedImage.OPAQUE);
        Graphics g = image.createGraphics();

        g.setColor(Color.RED);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        g.setColor(Color.RED);

        rando = new java.util.Random();

    }//constructor
    
    
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
        
        transform.rotate(1, image.getWidth()/2, image.getHeight()/2);
        //transform.rotate(1);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        tmp = op.filter(image, null);
        image = tmp;
    }
    
}
