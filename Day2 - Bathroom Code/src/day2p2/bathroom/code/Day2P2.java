/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2p2.bathroom.code;

import java.util.*;

/**
 *
 * @author jkester
 */
public class Day2P2 {

    /**
     * @param args the command line arguments
     */
    int x = 2;
    int y = 0;
    String[][] keypad = new String[5][5];
    
    ArrayList<String> theCode = new ArrayList<>();
    
    public String[][] fillKeypad(){
        keypad[0][2] = "1";
        keypad[1][1] = "2";
        keypad[1][2] = "3";
        keypad[1][3] = "4";
        keypad[2][0] = "5";
        keypad[2][1] = "6";
        keypad[2][2] = "7";
        keypad[2][3] = "8";
        keypad[2][4] = "9";
        keypad[3][1] = "A";
        keypad[3][2] = "B";
        keypad[3][3] = "C";
        keypad[4][2] = "D";
        return keypad;
    }
    
    public void getDirections(Scanner sc){
        while(sc.hasNext()){
           String dirs = sc.next();
           if(dirs.equals("exit")){
               break;
           }
           else{
               /*take a line of directions, iterate through it, 
                *and add the result to the end of the bathroom code
               */
               theCode.add(analyzeDirections(dirs));
           }
        }
        
        //output the full bathroom code
        System.out.print("The code is: ");
        for(String code : theCode){
            System.out.print(code);
        }
    }
    
    public String analyzeDirections(String dirs){
        
        int length = dirs.length()-1;
        int index = 0;
        
        //iterate through all chars in the direction String
        while(length >= 0){
            char direction = dirs.charAt(index);
            move(direction);
            index++;
            length--;
        }
        
        //once we reach the end, return the value in the resulting part of the array
        System.out.println("Next key in code is: " + keypad[x][y] + "\n");
        return keypad[x][y];
    }
    
    //handle the movement given by the directions
    public void move(char direction){
        //System.out.println("Current index is: " + x + "," + y);
        System.out.println("Direction to move is " + direction);
        switch(direction){
            case 'U':
                if (x != 0){//as long as we don't hit bound of array
                    x--;
                    //System.out.println(keypad[x][y]);
                    if (keypad[x][y] == null) x++;//don't want to go over the null areas, there are no buttons here
                }
                break;
            case 'D':
                if (x != 4){
                    x++;
                    if (keypad[x][y] == null) x--;
                }
                break;
            case 'L':
                if (y != 0){
                    y--;
                    if (keypad[x][y] == null) y++;
                }
                break;
            case 'R':
                if (y != 4){
                    y++;
                    if (keypad[x][y] == null) y--;
                }
                break;
            default:
                break;
        }
        System.out.println("The current key is " + keypad[x][y]);
    }
        
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day2P2 bc = new Day2P2();
        bc.fillKeypad();
        Scanner sc = new Scanner(System.in);
        System.out.println("What are the directions?");
        
        bc.getDirections(sc);
    }
    
}
