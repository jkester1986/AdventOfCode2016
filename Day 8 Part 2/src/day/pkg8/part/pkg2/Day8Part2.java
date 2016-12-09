/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg8.part.pkg2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author jkester
 */
public class Day8Part2 {

    
    public void parseInput(Scanner sc){
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("exit")){
               break; 
            }
            else{
                System.out.println("the size of the file is: " + getCount(line));
            }
        }
    }
    
    public long getCount(String s){
        long count = 0;
        int length = s.length();
        
        Pattern p = Pattern.compile("^\\((\\d+)x(\\d+)\\)(.*)$");
        
        
        if(s.charAt(0) == '('){
            Matcher m = p.matcher(s);
            m.find();
            
            int charCount = Integer.parseInt(m.group(1));
            int multiplier = Integer.parseInt(m.group(2));
            String leftOver = m.group(3);
            
            String sub1 = leftOver.substring(0,charCount);//the amount that needs to be parsed
            String sub2 = "";
            
            
            if(sub1.length() != leftOver.length()){
                sub2 = leftOver.substring(charCount);
                return multiplier*getCount(sub1) + getCount(sub2);
            }
            else return multiplier*getCount(sub1);
        }
        else{
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) != '('){//if we have a letter character
                    count ++;
                }
                else
                    return count + getCount(s.substring(i));
            }
        }
        return count;
    }
    
    public static void main(String[] args) {

        Day8Part2 d8 = new Day8Part2();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your input?");
        d8.parseInput(sc);
    }
    
}
