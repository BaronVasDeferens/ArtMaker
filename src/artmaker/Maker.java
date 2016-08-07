

package artmaker;



/**
 *
 * @author skot
 */
public class Maker {
    
    static int sleepInterval = 5;
    public boolean isPaused = false;
    
    public Maker() {
        
        ArtFrame artFrame = new ArtFrame(this);
        goFullscreen(artFrame);

        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setSize(artFrame.getWidth(), artFrame.getHeight());
        artFrame.add(drawPanel);


        ImageProducer drawer = //new Triangles(drawPanel.getWidth(), drawPanel.getHeight());
        new GridMorph(drawPanel.getWidth(), drawPanel.getHeight());
        //new MazeMaker(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());
        //new GridRunner(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());


        drawPanel.setImage(drawer.image);

        artFrame.requestFocus();
        artFrame.setVisible(true);

        //PAUSE
        try
        {
            java.lang.Thread.sleep(sleepInterval);
        }
        catch (java.lang.InterruptedException e)
        {

        }


        while (true) {
            
            if ( isPaused  == false) {
                drawer.update();
                drawPanel.setImage(drawer.image);
                drawPanel.repaint();
            }
            
            try
            {
                java.lang.Thread.sleep(sleepInterval);
            }
            catch (java.lang.InterruptedException e)
            {

            }
            

        }    
    }

    
    private static void goFullscreen(javax.swing.JFrame frame) {
        
        java.awt.GraphicsDevice devices[] = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        
        for (int i = 0; i < devices.length; i++) {
            println(devices[i].toString());
            println(devices[i].getDisplayMode().getWidth() + "x" + devices[i].getDisplayMode().getHeight());
            println("bit depth: " + devices[i].getDisplayMode().getBitDepth());
            println("refresh: " + devices[i].getDisplayMode().getRefreshRate());
            if (devices[i].isFullScreenSupported()) {
                println("Fullscreen: yes");
                devices[i].setFullScreenWindow(frame);

            }
        }
    }
    
    
   
    public static void println(int num) {
        println(Integer.toString(num));
    }
    
    public static void println(String str) {
        System.out.println(str);
    }
    
    
    
}
