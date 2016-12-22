/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg22;

/**
 *
 * @author jkester
 */
public class Node {
    
    int x; 
    int y;
    
    int size;
    int used;
    int available;
    int percentage;
    
    boolean hasData = false;
    
    public Node(int indexX, int indexY, int nodeSize, int spaceUsed, int spaceAvailable, int percentageUsed){
        x = indexX;
        y = indexY;
        
        size = nodeSize;
        used = spaceUsed;
        available = spaceAvailable;
        percentage = percentageUsed;
    }
    
    public Node(Node copy){
        x = copy.x;
        y = copy.y;
        
        size = copy.size;
        used = copy.used;
        available = copy.available;
        percentage = copy.percentage;
    }
    
    public void print(){
        System.out.println("size: " + size +
                ", used: " + used +
                ", available: " + available +
                ", percentage: " + percentage);
    }
    
    @Override
    public boolean equals(Object o){
        
        if (o == this) return true;
        if (!(o instanceof Node)) {
            return false;
        }
        
        Node n = (Node) o;
        
        return(size == n.size &&
                used == n.used &&
                available == n.available &&
                percentage == n.percentage &&
                x == n.x &&
                y == n.y);
    }
}
