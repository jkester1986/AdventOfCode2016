/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day2p1.bathroom.code;
import java.util.*;

/**
 *
 * @author jkester
 */
public class Day2BathroomCode {

    /**
     * @param args the command line arguments
     */
    int x = 1;
    int y = 1;
    int[][] keypad = new int[3][3];
    
    ArrayList<Integer> theCode = new ArrayList<>();
    
    public int[][] fillKeypad(){
        int fill = 1;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                keypad[i][j] = fill;
                fill++;
                //System.out.println(fill);
                //System.out.println(i + "," + j);
                //System.out.println(keypad[i][j]);
            }
        }
        return keypad;
    }
    
    public void getDirections(Scanner sc){
        while(sc.hasNext()){
           String dirs = sc.next();
           if(dirs.equals("exit")){
               break;
           }
           else{
               theCode.add(analyzeDirections(dirs));
           }
        }
        System.out.print("The code is: ");
        for(Integer code : theCode){
            System.out.print(code);
        }
    }
    
    public Integer analyzeDirections(String dirs){
        
        int length = dirs.length()-1;
        int index = 0;
        while(length >= 0){
            char direction = dirs.charAt(index);
            move(direction);
            index++;
            length--;
        }
        
        return keypad[x][y];
    }
    
    public void move(char direction){
        System.out.println("Current index is: " + x + "," + y);
        System.out.println("Direction to move is " + direction);
        switch(direction){
            case 'U':
                if (x != 0) x--;
                break;
            case 'D':
                if (x != 2) x++;
                break;
            case 'L':
                if (y != 0) y--;
                break;
            case 'R':
                if (y != 2) y++;
                break;
            default:
                break;
        }
        System.out.println("The new index is " + x + "," + y);
        System.out.println("The location is " + keypad[x][y]);
    }
        
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day2BathroomCode bc = new Day2BathroomCode();
        bc.fillKeypad();
        Scanner sc = new Scanner(System.in);
        System.out.println("What are the directions?");
        
        bc.getDirections(sc);
    }
    
}
