/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day16;

import java.util.ArrayList;

/**
 *
 * @author jkester
 */
public class Day16 {

    String myInput = "10001110011110000";
    
    ArrayList<String> a = new ArrayList();
    
    public void expand(){
        int size = a.size();
        
        while (size < 272){
            a.add("0");
            
            for(int i = size-1; i >= 0; i--){
                String c = new String(a.get(i));
                
                switch(c){
                    case "0":
                        c = "1";
                        break;
                    case "1":
                        c = "0";
                        break;
                    default: break;
                }
                
                a.add(c);
                size = a.size();
                if(size == 272) break;
            }
            /*
            for(int i = 0; i < size; i++)
                System.out.print(a.get(i));
            System.out.print("\n");
                    */
        }
    }
    
    public ArrayList<String> checksum(ArrayList<String> list){
        ArrayList<String> newList = new ArrayList();
        
        int size = list.size();
        for(int i = 0; i < size; i += 2){
            String p1 = new String(list.get(i));
            String p2 = new String(list.get(i+1));
            String pair = p1 + p2;
            
            switch(pair){
                case "11":
                case "00":
                    newList.add("1");
                    break;
                case "01":
                case "10":
                    newList.add("0");
                    break;
            }
        }
        
        if (newList.size()%2 == 0) return checksum(newList);
        else return newList;
        
    }
    
    public static void main(String[] args) {
        Day16 d16 = new Day16();
        
        int length = d16.myInput.length();
        for(int i = 0; i < length; i++)
            d16.a.add(d16.myInput.substring(i, i+1));
        
        d16.expand();
        ArrayList<String> checksum = d16.checksum(d16.a);
        for(int i = 0; i < checksum.size(); i++){
            System.out.print(checksum.get(i));
        }
        System.out.print("\n");
    }
    
}
