/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.awt.Color;
import java.awt.Graphics;

import javax.sound.sampled.*;

/**
 *
 * @author skot
 */
public class GridRunner extends ImageProducer {
 
    private int posX, 
                posY, 
                pixelsPerFrame = 2,
                runnerSize = 20;
    
    private Mixer mixer;
    
    GridRunner(int wid, int hei)
    {
        super(wid, hei);
        posX = 0;
        posY = 0;
        
        Mixer.Info mixers[] = AudioSystem.getMixerInfo();
        
        for (int i = 0; i < mixers.length; i++)
        {
            System.out.println("*********");
            System.out.println(mixers[i].getName());
            System.out.println(mixers[i].getDescription());
            System.out.println(mixers[i].getVersion());
        }
        
        mixer = AudioSystem.getMixer(mixers[0]);
        
        
        
    }

    public void update() {
        
    }
    
    public void update(DrawPanel dPanel)
    {
        if (dPanel.movingUp == true)
        {
            if ((posY - pixelsPerFrame) >= 0)
                posY = posY - pixelsPerFrame;
        }
        
        else if (dPanel.movingDown == true)
        {
            if ((posY + runnerSize) <= height)
                posY = posY + pixelsPerFrame;
        }
        
        if (dPanel.movingLeft == true)
        {
            if ((posX - pixelsPerFrame) >= 0)
                posX = posX - pixelsPerFrame;
        }
        
        else if (dPanel.movingRight == true)
        {
            if ((posX + runnerSize) <= width)
                posX = posX + pixelsPerFrame;
        }
        
        
        Graphics g = image.getGraphics();
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.setColor(Color.RED);
        g.fillRect(posX, posY, (runnerSize), ( runnerSize));
            
    }
}
