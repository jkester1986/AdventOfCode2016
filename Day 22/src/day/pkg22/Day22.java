/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day.pkg22;

import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 *
 * @author jkester
 */
public class Day22 {

    Node[][] compNodes = new Node[34][30];//do I need this????
    ArrayList<Node> nodeList = new ArrayList();
    
    int lowest = 0;
    
    Multimap<Node[], ArrayList<Node>> all = HashMultimap.create();
    
    Multimap<Node[], ArrayList<Node>> current = HashMultimap.create();
    Multimap<Node[], ArrayList<Node>> future = HashMultimap.create();
    
    public void fillNodes(Scanner sc){
        Pattern p = Pattern.compile(".*\\s(\\d+)T.*\\s(\\d+)T.*\\s(\\d+)T.*\\s(\\d+)%$");
        for(int x = 0; x < 34; x++){
            for(int y = 0; y < 30; y++){
                String data = sc.nextLine();
                Matcher m = p.matcher(data);
                m.find();
                
                //System.out.println(x + ", " + y);
                
                Node n = new Node(x, y,
                        Integer.parseInt(m.group(1)), 
                        Integer.parseInt(m.group(2)), 
                        Integer.parseInt(m.group(3)), 
                        Integer.parseInt(m.group(4)));
                
                if(x == 0 && y == 29) n.hasData = true;
                
                compNodes[x][y] = n;
                nodeList.add(n);
            }
        }
    }
    
    public ArrayList<Node[]> createPairs(){
        int pairs = 0;
        
        ArrayList<Node[]> nodePairs = new ArrayList();
        
        for(Node n: nodeList){
            if(n.used != 0){
                for(Node c: nodeList){
                    if(!n.equals(c) && n.used <= c.available){
                        pairs++;
                        Node[] node = {n, c};
                        nodePairs.add(node);
                    }
                }
            }
        }
        //System.out.println("There are " + pairs + " pairs now");
        return nodePairs;
    }
    
    public ArrayList<Node[]> borderPairs(ArrayList<Node> list){
        int pairs = 0;
        
        ArrayList<Node[]> nodePairs = new ArrayList();
        
        for(Node n: list){
            if(n.used != 0){
                for(Node c: list){
                    if(!n.equals(c) && n.used <= c.available){
                        if((n.x + 1 == c.x && n.y == c.y) ||
                           (n.x - 1 == c.x && n.y == c.y) ||
                           (n.x == c.x && n.y - 1 == c.y) ||
                           (n.x == c.x && n.y + 1 == c.y)){
                                pairs++;
                                Node tempN = new Node(n);
                                Node tempC = new Node(c);
                                Node[] node = {n, c};
                                nodePairs.add(node);
                        }
                        
                    }
                }
            }
        }
        //System.out.println("There are " + pairs + " adjacent pairs now");
        return nodePairs;
    }
    
    public void countSteps(){
        int count = 0;
        
        System.out.println("starting with " + current.size() + " sets");
        
        while(!current.isEmpty()){
            count ++;
            
            for(Node[] node: current.keySet()){
                
                Collection<ArrayList<Node>> nodes = current.get(node);
                System.out.println("Collection size: " + nodes.size());
                
                /* somewhere in here the colections are getting messed up
                for(ArrayList<Node> entry: nodes){
                    
                    Node a = new Node(node[0]);
                    Node b = new Node(node[1]);
                    
                    ArrayList<Node> theList = new ArrayList(entry);
                    
                    int indA = theList.indexOf(a);
                    int indB = theList.indexOf(b);
                    System.out.println("index A " + indA);
                    System.out.println("index B " + indB);

                    if(a.hasData) System.out.println("A is moving");

                    b.used += a.used;
                    b.available -= a.used;
                    b.hasData = a.hasData;

                    a.available = a.size;
                    a.used = 0;
                    a.hasData = false;

                    if(b.x == 33 && b.y == 0 && b.hasData == true){
                        System.out.println("it took " + count + " steps to move the data to the right spot");
                        break;
                    }

                    theList.set(indA, a);
                    theList.set(indB, b);

                    //set up next set
                    ArrayList<Node[]> adjacentPairs = borderPairs(theList);
                    for(Node[] pair: adjacentPairs){
                        future.put(node, theList);
                    }

                    
                }
                current = ArrayListMultimap.create(future);
                future = ArrayListMultimap.create();
                */
            }
            return;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Day22 d22 = new Day22();
        Scanner sc = new Scanner(System.in);
        System.out.println("what is your input?");
        
        d22.fillNodes(sc);
        ArrayList<Node[]> firstSet = d22.createPairs();
        
        ArrayList<Node[]> adjacentPairs = d22.borderPairs(d22.nodeList);
        for(Node[] node: adjacentPairs){
            System.out.println("Node A: " + node[0] + " Node B: " + node[1]);
            d22.current.put(node, d22.nodeList);
        }
        
        for(Node[] node: d22.current.keySet()){
                
                Collection<ArrayList<Node>> nodes = d22.current.get(node);
                System.out.println("Initial collection size: " + nodes.size());
        }
        
        
        /*
        for(int y = 0; y < 30; y++){
            for (int x = 0; x < 34; x++){
                System.out.print(d22.compNodes[x][y].used + "/" + d22.compNodes[x][y].size + "     ");
                
            }
        }
*/
        //if(d22.compNodes[0][29].hasData) System.out.println("there's a node with data");
        
        
        d22.countSteps();

        
    }
    
}
