/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

/**
 *
 * @author skot
 */
public class ArtMaker {

    static int sleepInterval = 10;
    
    public static void main(String[] args) {
        // TODO code application logic here
       
        ArtFrame artFrame = new ArtFrame(); 
        artFrame.setVisible(true);
        GridMorph gridMorph = new GridMorph(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());
        artFrame.drawPanel.setImage(gridMorph.image);
        
        //PAUSE
        try
        {
            java.lang.Thread.sleep(1000);
        }
        catch (java.lang.InterruptedException e)
        {

        }
        
        
        while (true)
        //for (int i = 0; i < 5; i++)
        {    
            if (artFrame.drawPanel.increaseValue)
                gridMorph.morph();
            
            artFrame.drawPanel.setImage(gridMorph.image);
            artFrame.drawPanel.repaint();
            
            try
            {
                java.lang.Thread.sleep(sleepInterval);
            }
            catch (java.lang.InterruptedException e)
            {
                
            }
     
                    
        }    
  
    }
    
}

