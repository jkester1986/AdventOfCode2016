/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg14;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jkester
 */
public class Day14 {

    Pattern p = Pattern.compile(".*(.)\\1\\1.*");
    
    String key = "abc";
    Map<String, Integer> padKeys = new HashMap<String, Integer>();
    
    boolean found64 = false;
    
    int count = 1;
    int totPadKeys = 0;
    
    public void findKeys()throws NoSuchAlgorithmException{
        
        while(!found64){
            
            String combo = key + count;
            String hash = getHash(combo);
            
            Matcher m = p.matcher(hash);
            
            String s = "";
            if(m.matches()){
                s = m.group(1);
            }
            
            int count2 = count+1;
            //System.out.println("count2: " + count2);
            boolean found5 = false;
            
            //System.out.println("looking at " + count);
            
            if(!s.isEmpty()){
                System.out.println("the hash is " + hash);
                System.out.println("The triplet char is " + s);
            }
            while(!found5){ 
                if(count2 <= (count+1000)){
                    //System.out.println("count2 in loop: " + count2);
                    if(!s.isEmpty()){
                        Pattern matchOrig = Pattern.compile(".*" + s + "{5}.*");

                        String combo2 = key + count2;
                        String hash2 = "";
                        try {
                            hash2 = getHash(combo2);
                        } 
                        catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(Day14.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        

                        Matcher match3 = matchOrig.matcher(hash2);
                        if(match3.matches()){
                            
                            System.out.println("found 5 " + s + " in a row here: " + hash2 + " at index " + count2);
                            System.out.println("The key is " + count);
                            padKeys.put(hash, count);
                            totPadKeys++;
                            found5 = true;
                        }
                    } 
                    else break;
                }
                else break;
                count2++;
            }
            if(totPadKeys == 64){
                System.out.println("The index that gives the 64th padkey is " + count);
                found64 = true;
            }
            count++;
        }
    }
    
    public String getHash(String s)throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s.getBytes(),0,s.length());
            byte[] mdbytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
              sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
        Day14 d14 = new Day14();
        
        d14.findKeys();
    }
    
}
