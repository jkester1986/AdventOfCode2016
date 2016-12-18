/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg18;

import java.util.Arrays;

/**
 *
 * @author jkester
 */
public class Day18 {

    boolean[][] floor = new boolean[100][400000];
    
    
    //true = trap
    public void fillFloors(){
        for(int y = 1; y < 400000; y++){
            for(int x = 0; x < 100; x++){
                boolean l = true;
                boolean c = true;
                boolean r = true;
                
                //get left, right, center
                if(x-1 < 0)
                    l = false;
                else
                    l = floor[x-1][y-1];
                if(x+1 == 100)
                    r = false;
                else{
                    r = floor[x+1][y-1];
                }
                c = floor[x][y-1];
                
                //figure out traps
                if(l && c && !r)
                    floor[x][y] = true;
                else if(r && c && !l)
                    floor[x][y] = true;
                else if(l && !r && !c)
                    floor[x][y] = true;
                else if(r && !l && !c)
                    floor[x][y] = true;
                else
                    floor[x][y] = false;
                
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day18 d18 = new Day18();
        String row1 = ".^.^..^......^^^^^...^^^...^...^....^^.^...^.^^^^....^...^^.^^^...^^^^.^^.^.^^..^.^^^..^^^^^^.^^^..^";
        
        for (int x = 0; x < 100; x++){
            char c = row1.charAt(x);
            switch(c){
                case '.':
                    d18.floor[x][0] = false;
                    break;
                case '^':
                    d18.floor[x][0] = true;
                    break;
                default: d18.floor[x][0] = false;
            }
        }
        for(int x = 0; x < 100; x++){
            if(d18.floor[x][0]){
                    System.out.print("^");
                }
                else System.out.print(".");
            
        }
        System.out.print("\n");
        
        d18.fillFloors();
        
        int count = 0;
        for(int y = 0; y < 400000; y++){
            for(int x = 0; x < 100; x++){
                /*
                if(d18.floor[x][y]){
                    System.out.print("^");
                }
                else System.out.print(".");
*/
                if(!d18.floor[x][y])
                    count++;
            }

            //System.out.print("\n");
        }
        System.out.println("There are " + count + " safe tiles");
    }
    
}
