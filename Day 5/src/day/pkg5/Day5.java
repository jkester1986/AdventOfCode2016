/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg5;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jkester
 */
public class Day5 {

    /**
     * @param args the command line arguments
     */
    char[] pw = new char[8];
    ArrayList<Integer> alreadyIndexed = new ArrayList<>();
    
    StringBuilder pw1 = new StringBuilder();
    
    public String lowestNumber(String input) throws NoSuchAlgorithmException{
        String sKey = input;
        String hashString = "";
        boolean tooLow = true;
        long lowNumber = 0;
        String combined = "";
        
        int count = 8;
        
       while(contains(pw, '\u0000')){
            combined = input + lowNumber;
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(combined.getBytes(),0,combined.length());
            byte[] mdbytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
              sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashString = sb.toString();
            if(hashString.charAt(0) == '0' && 
                    hashString.charAt(1) == '0' &&
                    hashString.charAt(2) == '0' &&
                    hashString.charAt(3) == '0' &&
                    hashString.charAt(4) == '0' ){
                //System.out.println(combined);
                //tooLow = false;
                int index = Character.getNumericValue(hashString.charAt(5));
                //System.out.println("index is " + index);
                if(count > 0){
                    pw1.append(hashString.charAt(5));
                    count--;
                }
                //System.out.println("Next char is " + hashString.charAt(6));
                if(index < 8 && ! alreadyIndexed.contains(index)) {
                    pw[index] = hashString.charAt(6);
                    alreadyIndexed.add(index);
                    //count--;
                    //System.out.println("New count is " + count);
                    //System.out.println("");
                }
                
            }
            
            lowNumber ++;
            
        }
        return hashString;
    }
    
    
    public Boolean contains(char[] array, char theChar){
        
        for(char c: array){
            //System.out.println("there's an empty space");
            if(c == theChar)
                return true;
        }
        
        return false;
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        Day5 d5 = new Day5();
        String testString = "reyedfim";
        d5.lowestNumber(testString);
        
        System.out.println("The pw for part 1 is " + d5.pw1.toString());
        
        System.out.print("The pw for part 2 is ");
        for(char c: d5.pw){
            System.out.print(c);
        }
        System.out.println("");
    }
    
}
