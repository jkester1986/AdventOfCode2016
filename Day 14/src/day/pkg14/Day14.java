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

    Pattern p = Pattern.compile("(.)\\1\\1");
    
    String key = "zpqevtbw";
    Map<String, Integer> padKeys = new HashMap<String, Integer>();
    
    boolean found64 = false;
    
    int count = 0;
    int totPadKeys = 0;
    
    public void findKeys()throws NoSuchAlgorithmException{
        
        while(!found64){
            
            String combo = key + count;
            String hash = getHash(combo);
            
            for(int i = 0; i < 2016; i++){
                hash = getHash(hash);
            }
            
            Matcher m = p.matcher(hash);
            
            String s = "";
            while(m.find()){
                s = m.group(1);
                break;
            }
            
            int count2 = count+1;
            //System.out.println("count2: " + count2);
            boolean found5 = false;
            
            //System.out.println("looking at " + count);
            
            /*
            if(!s.isEmpty()){
                System.out.println("the hash is " + hash + " at index " + count);
                System.out.println("The triplet char is " + s);
            }
*/
            
            while(!found5){ 
                if(count2 <= (count+1000)){
                    
                    if(!s.isEmpty()){
                        Pattern matchOrig = Pattern.compile(".*" + s + "{5}.*");

                        String combo2 = key + count2;
                        String hash2 = "";
                        try {
                            hash2 = getHash(combo2);
                            
                            for(int i = 0; i < 2016; i++){
                                hash2 = getHash(hash2);
                            }
                                    
                        } 
                        
                        //if(count2 = 89) System.out.println("");
                        catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(Day14.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        

                        Matcher match3 = matchOrig.matcher(hash2);
                        if(match3.matches()){
                            System.out.println("The key is " + count + ", hash " + hash);
                            System.out.println("found 5 " + s + " in a row here: " + hash2 + " at index " + count2);
                            
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
            if(padKeys.size() == 64){
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
