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
        
        ImageProducer drawer;
        
        //drawer = new Triangles(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());
        //drawer = new GridMorph(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());
        
        drawer = new MazeMaker2(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());
        
        //drawer = new GridRunner(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());
        
        artFrame.drawPanel.setImage(drawer.image);
        
        //GridRunner gRun 
        //artFrame.drawPanel.setImage(gRun.image);
        
        artFrame.setVisible(true);
        
        //PAUSE
        try
        {
            java.lang.Thread.sleep(50);
        }
        catch (java.lang.InterruptedException e)
        {

        }
        
        
        while (true)
        //for (int i = 0; i < 5; i++)
        {    
            //gRun.update(artFrame.drawPanel);
            drawer.update();
            
            //artFrame.drawPanel.setImage(gRun.image);
            artFrame.drawPanel.setImage(drawer.image);
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

