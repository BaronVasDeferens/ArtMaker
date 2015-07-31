/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmaker;

import java.util.Random;


public class test {
    
    public static void main(String ... args) {
        
        int arraySize = 15;
        int array[] = new int[arraySize];
        int iOpt, jOpt;
        int currentValue, optimalValue;
        int i = 0, j = 1;
        Random rando = new Random();
        
        //Setup random array
        for (int x = 0; x < arraySize; x++) {
            array[x] = rando.nextInt(9);
            // make all odd ints negative
            if (array[x]%2 != 0) {
                array[x] = array[x] * -1;
            }
        }
        
        array[0] = 9000;
        array[2] = -1500;
        
        
        
        // Compute initial optimal value
        int iTemp = rando.nextInt(arraySize);
        int jTemp = rando.nextInt(arraySize);
        
        while (iTemp == jTemp) {
            jTemp = rando.nextInt(arraySize);
        }
        
        iTemp = 0;
        jTemp = 2;
        
        optimalValue = getWeight(array[iTemp], array[jTemp], iTemp, jTemp);
        iOpt = iTemp;
        jOpt = jTemp;
        
        System.out.println("INITS: ");
        System.out.println("i = " + iOpt);
        System.out.println("j = " + jOpt);
        System.out.println("optVal = " + optimalValue);
        System.out.println();
        
        // find optimal i and j
        for (i = 0; i < arraySize; i++) {
            
            for (j = 0; j < arraySize; j++) {
                
                if (i != j) {
                
                    currentValue = getWeight(array[i], array[j], i , j);
                
                    if (currentValue >= optimalValue) {
                        iOpt = i;
                        jOpt = j;
                        optimalValue = currentValue;
                    }
                }
            }
        }
        
        // print results
        for (int x = 0; x < arraySize; x++) {
            System.out.print(x + "\t");
        }
        
        System.out.println();
        
        for (int x = 0; x < arraySize; x++) {
            System.out.print(array[x] + "\t");
        }
        System.out.println();
        System.out.println("optVal = " + optimalValue);
        System.out.println("optimal i = " + iOpt);
        System.out.println("optimal j = " + jOpt);
        
        myAlgo(array);
        
    }
    
    private static int getWeight(int m, int n, int o, int p) {
        return (Math.abs(m - n) / Math.abs(o - p));
    }
    
    // My implementation
    private static void myAlgo(int[] array) {
        int i = 0, j = 1;
        int iOpt = 0, jOpt = 1;
        int bestDWD = Math.abs(array[0]-array[1]) / Math.abs(0 - 1);
        int currentDWD = bestDWD;
        
       for (i = 0; i < array.length-1; i++) {
           j = i + 1;
           currentDWD = Math.abs(array[i]-array[j]) / Math.abs(i - j);
           
           if (currentDWD >= bestDWD) {
               iOpt = i;
               jOpt = j;
               bestDWD = currentDWD;
           }
       }
       
       System.out.println(" SKOT'S ");
       System.out.println("bestDWD = " + bestDWD);
        System.out.println("iOpt = " + iOpt);
        System.out.println("jOPt = " + jOpt);
    }
    
}
