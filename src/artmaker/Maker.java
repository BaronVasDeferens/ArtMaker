

package artmaker;


import java.awt.event.KeyEvent;

/**
 * @author skot
 */
public class Maker {

    static int sleepInterval = 15;
    public boolean isPaused = false;

    ImageProducer drawer;
    ArtFrame artFrame;
    DrawPanel drawPanel;

    public Maker() {

        artFrame = new ArtFrame(this);

        goFullscreen(artFrame);

        // Wait for fullscreen to fully expand
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        drawPanel = new DrawPanel();
        drawPanel.setSize(artFrame.getWidth(), artFrame.getHeight());
        drawPanel.setDoubleBuffered(true);
        artFrame.add(drawPanel);


        drawer =
                //new Triangles(drawPanel.getWidth(), drawPanel.getHeight());
                new GridMorph(drawPanel.getWidth(), drawPanel.getHeight());
        //new MazeMaker(drawPanel.getWidth(), drawPanel.getHeight());
        //new GridRunner(artFrame.drawPanel.getWidth(), artFrame.drawPanel.getHeight());


        drawPanel.setImage(drawer.image);

        artFrame.requestFocus();
        artFrame.setVisible(true);


        while (true) {

            if (isPaused == false) {
                drawer.update();
                drawPanel.setImage(drawer.image);
                drawPanel.repaint();
            }

            try {
                java.lang.Thread.sleep(sleepInterval);
            } catch (java.lang.InterruptedException e) {

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


    public void keyPressed (KeyEvent e) { drawer.keyPressed(e); }
    public void keyReleased (KeyEvent e) { drawer.keyReleased(e);}
    public void keyTyped (KeyEvent e) { drawer.keyTyped(e);}


    public static void println(int num) {
        println(Integer.toString(num));
    }

    public static void println(String str) {
        System.out.println(str);
    }


}
