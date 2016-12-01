/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1p1;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author jkester
 */
public class Day1P1 {

    /**
     * @param args the command line arguments
     * 
     * 
     */
    public void readString(Scanner input){
        while(input.hasNext()){
            String next = input.next();
            if(next.equals("exit")){
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day1P1 d1 = new Day1P1();
        Scanner user_input = new Scanner( System.in );
        System.out.println("What is the string you'd like to input?");
        
        d1.readString(user_input);
    }
    
}
