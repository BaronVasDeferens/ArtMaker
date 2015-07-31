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
    boolean movingUp, movingDown, movingRight, movingLeft;
    Graphics g;
    
    boolean increaseValue = false;
    
    public DrawPanel()
    {
        super();
        this.setFocusable(true);
        addKeyListener(this);
        movingUp = false;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
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
        
        switch (e.getKeyChar())
        {
            case 'w':
                movingUp = true;
                break;
            case 's':
                movingDown = true;
                break;
            case 'a':
                movingLeft = true;
                break;
            case 'd':
                movingRight = true;
                break;
            default:
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        increaseValue = false;
        
                switch (e.getKeyChar())
        {
            case 'w':
                movingUp = false;
                break;
            case 's':
                movingDown = false;
                break;
            case 'a':
                movingLeft = false;
                break;
            case 'd':
                movingRight = false;
                break;
            default:
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
}
