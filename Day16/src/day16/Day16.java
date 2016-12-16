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
    
    ArrayList<Boolean> a = new ArrayList();
    
    public void expand(){
        int size = a.size();
        
        while (size < 35651584){
            a.add(false);
            
            for(int i = size-1; i >= 0; i--){
                Boolean c = a.get(i);
                a.add(!c);
                size = a.size();
                if(size == 35651584) break;
            }
            System.out.println("size: " + size);
            
            /*
            for(int i = 0; i < size; i++){
                if(a.get(i)) System.out.print(1);
                else System.out.print(0);
            }
            System.out.print("\n");
                    */
        }
    }
    
    public ArrayList<Boolean> checksum(ArrayList<Boolean> list){
        ArrayList<Boolean> newList = new ArrayList();
        
        int size = list.size();
        for(int i = 0; i < size; i += 2){
            Boolean p1 = list.get(i);
            Boolean p2 = list.get(i+1);
            
            if(p1&&p2 || !p1&&!p2){
                newList.add(true);
            }
            else{
                newList.add(false);
            }
        }
        
        if (newList.size()%2 == 0) return checksum(newList);
        else return newList;
        
    }
    
    public static void main(String[] args) {
        Day16 d16 = new Day16();
        
        int length = d16.myInput.length();
        for(int i = 0; i < length; i++){
            if(d16.myInput.charAt(i) == '1') d16.a.add(true);
            else d16.a.add(false);
        }
        
        d16.expand();
        ArrayList<Boolean> checksum = d16.checksum(d16.a);
        for(int i = 0; i < checksum.size(); i++){
            if(checksum.get(i)) System.out.print(1);
            else System.out.print(0);
        }
        System.out.print("\n");
    }
    
}
