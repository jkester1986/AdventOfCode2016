/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg15;

/**
 *
 * @author jkester
 */
public class Day15 {

    /**
     * @param args the command line arguments
     */
    
    public int whenStart(Disk[] disks){
        int time = 0;
        int size = disks.length;
        
        boolean match = false;
        
        while(!match){
            for(int i = 0; i < size; i++){
                if((i+1+time+disks[i].starting())%disks[i].positions() == 0){
                    match = true;
                }
                else{
                    match = false;
                    break;
                }
            }
            
            if(match){
                break;
            }
            else time++;
        }
        
        
        
        return time;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day15 d15 = new Day15();
        
        Disk d1 = new Disk(17, 1);
        Disk d2 = new Disk(7, 0);
        Disk d3 = new Disk(19, 2);
        Disk d4 = new Disk(5, 0);
        Disk d5 = new Disk(6, 0);
        Disk d6 = new Disk(13, 5);
        Disk d7 = new Disk(11, 0);
        
        Disk[] allDisks = {d1, d2, d3, d4, d5, d6, d7};
        /*        
        Disk d1 = new Disk(5, 4);
        Disk d2 = new Disk(2, 1);
        
        Disk[] allDisks = {d1, d2};
                */
        
        System.out.println("The button should be pushed at " + d15.whenStart(allDisks) + " seconds");
    }
    
}
