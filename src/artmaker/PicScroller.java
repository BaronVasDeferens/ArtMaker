package artmaker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.InputStream;

/**
 * Created by skot on 9/18/16.
 */
public class PicScroller extends ImageProducer {

    private int currentMasterPosX = 0, currentMasterPosY = 0;
    private int priorMouseX = 0, priorMouseY = 0;
    BufferedImage originalImage;

    public PicScroller(int width, int height) {
        super(width, height);

        try (InputStream fin = getClass().getResourceAsStream("images/img02.jpg")) {
            originalImage = ImageIO.read(fin);
            fin.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void update() {

        java.awt.PointerInfo pInfo = java.awt.MouseInfo.getPointerInfo();

        int x = pInfo.getLocation().x;
        int y = pInfo.getLocation().y;

        // LEFT - TO - RIGHT
        // Scroll region : right
        if (x >= image.getWidth()-5) {
            currentMasterPosX = currentMasterPosX + Math.abs(priorMouseX - x);

            if (currentMasterPosX >= originalImage.getWidth() - image.getWidth())
                currentMasterPosX = originalImage.getWidth() - image.getWidth();

        }

        // scroll region : left
        else if (x <= 5) {
            currentMasterPosX = currentMasterPosX - Math.abs(priorMouseX - x);

            if (currentMasterPosX <= 0)
                currentMasterPosX = 0;
        }

        // normal
        else if (x > priorMouseX)  {
            currentMasterPosX = currentMasterPosX + (x - priorMouseX);
            priorMouseX = pInfo.getLocation().x;
        }

        else if ( (x < priorMouseX) || (x <= 0)) {
            currentMasterPosX = currentMasterPosX - (priorMouseX - x);
            priorMouseX = pInfo.getLocation().x;
        }

        // UP - AND - DOWN
        // Scroll region : UP
        if (y <= 5) {
            currentMasterPosY = currentMasterPosY - Math.abs(priorMouseY - y);

            if (currentMasterPosY <= 0)
                currentMasterPosY = 0;

        }
        // Scroll region: down
        else if (y >= image.getHeight()-5) {
            currentMasterPosY = currentMasterPosY + Math.abs(priorMouseY - y);

            if (currentMasterPosY >= originalImage.getHeight() - image.getHeight())
                currentMasterPosY = originalImage.getHeight() - image.getHeight();
        }

        else if (y > priorMouseY)  {
            currentMasterPosY = currentMasterPosY + (y - priorMouseY);
            priorMouseY = pInfo.getLocation().y;
        }

        else if ( (y < priorMouseY) || (y <= 0)) {
            currentMasterPosY = currentMasterPosY - (priorMouseY - y);
            priorMouseY = pInfo.getLocation().y;
        }

        try {

            if (currentMasterPosX > originalImage.getWidth())
                currentMasterPosX = originalImage.getWidth() - image.getWidth();
            else if (currentMasterPosX <= 0)
                currentMasterPosX = 0;

            if (currentMasterPosY > originalImage.getHeight())
                currentMasterPosY = originalImage.getHeight() - image.getHeight();
            else if (currentMasterPosY <= 0)
                currentMasterPosY = 0;

            BufferedImage subImage = originalImage.getSubimage(currentMasterPosX, currentMasterPosY, image.getWidth(), image.getHeight());
            image = subImage;
        }
        catch (RasterFormatException e) {
            System.out.println(e.toString());
            System.out.println("currentMasterPosX : " + currentMasterPosX);
            System.out.println("currentMasterPosY : " + currentMasterPosY);
        }
    }

    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }
}
