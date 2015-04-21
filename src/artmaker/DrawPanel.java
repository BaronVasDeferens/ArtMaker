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
import java.awt.event.*;


/**
 *
 * @author skot
 */
public class DrawPanel extends JPanel implements KeyListener{

    BufferedImage img = null;
    Thread t;
    boolean alive = false;
    Graphics g;
    
    boolean increaseValue = false;
    
    DrawPanel()
    {
        super();
        this.setFocusable(true);
        addKeyListener(this);
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
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        increaseValue = true;
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        increaseValue = false;
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
}
