/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.MouseInfo;


/**
 *
 * @author skot
 */
public class DrawPanel extends JPanel{

    BufferedImage img = null;
    Thread t;
    boolean alive = false;
    Graphics g;
    
    public void setImage(BufferedImage ig)
    {
        img = ig;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,this.getWidth(), this.getHeight());
        
        
        if (img != null)
        {
            g.drawImage(img, 0, 0, this);
        }
    }
    
}
