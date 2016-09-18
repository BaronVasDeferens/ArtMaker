/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 *
 * @author skot
 */
public class DrawPanel extends JPanel { //implements KeyListener{

    BufferedImage img = null;
    Thread t;
    Graphics g;
    
    boolean increaseValue = false;
    
    public DrawPanel()
    {
        super();
    }
    
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
