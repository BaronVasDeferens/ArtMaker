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
public class BigArtFrame extends ArtFrame {
    
    public BigArtFrame() {
        super();
        this.setSize(new java.awt.Dimension(1920,1080));
        //drawPanel.setPreferredSize(new java.awt.Dimension(1920,1280));
        drawPanel.setSize(new java.awt.Dimension(1900,1260));
    }
}
