/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 *
 * @author skot
 */
public class GridMorph extends ImageProducer {
         
    int morphValue = 2;
    
    
    Point pointGrid[][];
    Polygon polygonGrid[][];

    

    GridMorph(int width, int height)
    {
        super(width, height);

        pointGrid = new Point[rows+1][cols+1];
        polygonGrid = new Polygon[rows][cols];
        colorGrid = new Color[rows][cols];

        for (int x = 0; x < rows+1; x++)
        {
            for (int y = 0; y < cols+1; y++)
            {
                pointGrid[x][y] = new Point();
                pointGrid[x][y].x = x * initialSpacing;
                pointGrid[x][y].y = y * initialSpacing;
            }
        }
       
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.RED);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());


        for (int x = 0; x < rows; x++)
        {
            for (int y = 0; y < cols; y++)
            {
                polygonGrid[x][y] = new Polygon();

                polygonGrid[x][y].addPoint(pointGrid[x][y].x, pointGrid[x][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y].x, pointGrid[x+1][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y+1].x, pointGrid[x+1][y+1].y);
                polygonGrid[x][y].addPoint(pointGrid[x][y+1].x, pointGrid[x][y+1].y);

                //Establish a color associated with a specific poly
                //colorGrid[x][y] = new Color(rando.nextInt(255), rando.nextInt(255), rando.nextInt(255));

                //colorGrid[x][y] = new Color((rando.nextInt(235))+20,0,0);
                
                g.setColor(colorGrid[x][y]);
                
                g.fillPolygon(polygonGrid[x][y]);

            }
        }
        
        setColorGrid(ColorScheme.REDS, 55, .80f);
        
    }//constructor
    
    
    
    
    public void update()
    {
        
        //Adjust each point in a (semi) random direction
        for (int x = 0; x < rows+1; x++)
        {
            for (int y = 0; y < cols+1; y++)
            {
                pointGrid[x][y].x += (rando.nextInt(morphValue) - rando.nextInt(morphValue));
                pointGrid[x][y].y += (rando.nextInt(morphValue) - rando.nextInt(morphValue));
            }
        }
        
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        g.setColor(colorGrid[0][0]);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        for (int x = 0; x < rows; x++)
        {
            for (int y = 0; y < cols; y++)
            {
                polygonGrid[x][y] = new Polygon();

                polygonGrid[x][y].addPoint(pointGrid[x][y].x, pointGrid[x][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y].x, pointGrid[x+1][y].y);
                polygonGrid[x][y].addPoint(pointGrid[x+1][y+1].x, pointGrid[x+1][y+1].y);
                polygonGrid[x][y].addPoint(pointGrid[x][y+1].x, pointGrid[x][y+1].y);
                
                g.setColor(colorGrid[x][y]);
                //g.setColor(new Color(rando.nextInt(255),rando.nextInt(255),rando.nextInt(255)));
                
                g.fillPolygon(polygonGrid[x][y]);
                
                //g.setColor(Color.RED);
                //g.drawPolygon(polygonGrid[x][y]);
                    
            }
        }
        
        //APPLY EFFECTS
        
        //blur();
        //spin();
        
        g.dispose();
    }


    public void switchToBlue() {
        setColorGrid(ColorScheme.BLUES, 65, .75f);
    }

    public void switchToRed() {
        setColorGrid(ColorScheme.REDS, 65, .75f);
    }

    public void switchToGreen() {
        setColorGrid(ColorScheme.GREENS, 65, .75f);
    }

    public void switchToYellow() { setColorGrid(ColorScheme.YELLOWS, 65, .75f); }

    public void switchToRandom() { setColorGrid(ColorScheme.RANDOM, 0, 0); }

    public synchronized void setColorGrid(ColorScheme scheme, int minValue, float factor) {

        int maxValue = 255 - minValue;
        Color[][] newGrid;

        switch (scheme) {
            case REDS:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(r, 0, b);
                    }
                }
                break;
            case GREENS:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(0, r, b);
                    }
                }
                break;
            case BLUES:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(b, 0, r);
                    }
                }
                break;
            case YELLOWS:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        int r = minValue + rando.nextInt(maxValue);
                        int b = (int)(r * factor);
                        colorGrid[x][y] = new Color(r, b, 0);
                    }
                }
                break;
            case RANDOM:
                for (int x = 0; x < rows; x++)
                {
                    for (int y = 0; y < cols; y++)
                    {
                        colorGrid[x][y] = new Color(rando.nextInt(255), rando.nextInt(255), rando.nextInt(255));
                    }
                }
                break;
            case MORE_RED:
                newGrid = new Color[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        Color c = colorGrid[i][j];
                        newGrid[i][j] = new Color((c.getRed() + 2) % 255, c.getGreen(), c.getBlue());
                    }
                }
                colorGrid = newGrid;
                break;
            case LESS_RED:
                newGrid = new Color[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        Color c = colorGrid[i][j];
                        newGrid[i][j] = new Color((c.getRed() - 2) % 255, c.getGreen(), c.getBlue());
                    }
                }
                colorGrid = newGrid;
                break;
        }

    }



    public void keyPressed(KeyEvent e) {

       switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;

            case KeyEvent.VK_B:
                switchToBlue();
                break;
            case KeyEvent.VK_G:
                switchToGreen();
                break;
            case KeyEvent.VK_R:
                switchToRed();
                break;
            case KeyEvent.VK_X:
                switchToRandom();
                break;
            case KeyEvent.VK_Y:
                switchToYellow();
                break;
           case KeyEvent.VK_I:
                setColorGrid(ColorScheme.MORE_RED, 0,0f);
               break;
           case KeyEvent.VK_O:
               setColorGrid(ColorScheme.LESS_RED, 0,0f);
               break;
            default:
                break;

        }

    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }

}


class Point
{
    int x, y;
    
    Point()
    {
        x = 0;
        y = 0;
    }
    
    Point(int xx, int yy)
    {
        x = xx;
        y = yy;
    }
}
