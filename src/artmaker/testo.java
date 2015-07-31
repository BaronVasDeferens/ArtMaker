/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.util.Random;

/**
 *
 * @author skot
 */
public class testo {
    
    
    public static void main(String ... args) {
       
        int mazeHeight = 15;
        int mazeWidth = 25;
        Random rando = new Random();
        
        System.out.println("mazeWidth: " + (mazeWidth-2));
        
        for (int i = 0; i < 14; i++) {
            System.out.println(rando.nextInt((mazeWidth-2)) +1);
        }
    }
}
