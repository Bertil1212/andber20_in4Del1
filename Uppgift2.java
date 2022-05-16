import java.io.File;
import java.util.Scanner;
import java.util.Iterator;
import java.awt.Color;

public class Uppgift2{
    static int count = 0;
    static DoublyLinkedList<antenn> antennalist = new DoublyLinkedList<>();
    static DoublyLinkedList<network> networks = new DoublyLinkedList<>();

    static void p(String s){
        System.out.println(s);
    }

    static void p(int s){
        System.out.println(s);
    }

    static void p(Float s){
        System.out.println(s);
    }

    static void p(Double s){
        System.out.println(s);
    }
    
    
    static void createAntennas(Scanner sc){
        
        
        if (sc.hasNextLine() == false)
            return;

        sc.nextLine();
        String x = sc.nextLine();
        x = x.substring(3);
        String y = sc.nextLine();
        y = y.substring(3);

        String r = sc.nextLine();
        r = r.substring(3);
        antennalist.add(new antenn(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(r)));
        createAntennas(sc);
    } 


    //Draw a antenna
    static void drawAntennas(){
        
        
        StdDraw.setPenRadius(0.008);

        Iterator<antenn> it = antennalist.iterator();
        while(it.hasNext()){
            
            antenn ant = it.next();
            if(ant.getNetwork() == null){
                StdDraw.setPenColor(0,0,0);
            }else{
                StdDraw.setPenColor(ant.getNetwork().getColor());
            }
            StdDraw.circle(ant.getx(), ant.gety(), ant.getr());
            
        }

    }


    //check if two antennas are connected
    static void connect(antenn ant1, antenn ant2){
        int maxdis = ant1.getr() + ant2.getr();
        double distance = antennaDistance(ant1, ant2);

        if(maxdis >= distance){
            StdDraw.line(ant1.getx(), ant1.gety(), ant2.getx(), ant2.gety());
            ant1.addConnectedAntenna(ant2);
            ant2.addConnectedAntenna(ant1);
        }
        

    }

    
    //Create network or add antenna to existing network
    static void createNetworks(){
        boolean done = false;
        int count = antennalist.size();
        while(!done){
            
            count++;
            Iterator<antenn> it = antennalist.iterator();
            done = true;
            while(it.hasNext()){
                antenn ant = it.next();
                
                if(!ant.addToNetwork(networks))
                    done = false;    
            }
        }
    }

    static void connections(){
        StdDraw.setPenColor(new Color(30, 20, 255));
        StdDraw.setPenRadius(0.003);
        ListNode<antenn> front = antennalist.head;
        ListNode<antenn> back = antennalist.head.getafter();

        while(true){

            connect(front.get(), back.get());



            if(back.getafter() == null){
                if(front.getafter() == back)
                    return;

                front = front.getafter();
                back = front.getafter();
            }else{
                back = back.getafter();
            }
        }
        


    }

    


    static void run(String filename){
        networks.add(new network()); //the first network is not used 
        File file1 = new File(filename);
        Scanner sc;
        try {
            sc = new Scanner(file1);
            
        } catch (Exception e) {
            System.out.println("Input File not found");
            return;
        }
        
        createAntennas(sc);

        //Setup screen
        //StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0,1000);


        
        

        //Make connections and draw the connections
        connections();

        //create the networks and add the antennas to the corresponding network
        createNetworks();
        //Show the graph
        




        //Draw antennas
        drawAntennas();
    }


    //highlighta en antenn för att se om metoderna fungerar
    public static void highlight(antenn ant){
        StdDraw.setPenColor(new Color(255,0,0));
        StdDraw.setPenRadius(0.01);
       // StdDraw.circle(ant.getx(), ant.gety(), ant.getr());
        StdDraw.rectangle(ant.getx(), ant.gety(), ant.getr()*1.5, ant.getr()*1.5);
    }





    public static int networkCount(){
        //Because it contains a dummy network at place 0
        //Dont remember why i did it that way. But it works. If it aint broken dont fix it
        return networks.size()-1;
    }

    public static boolean antennaIsConnected(antenn ant1, antenn ant2){
        return ant1.getNetwork() == ant2.getNetwork();
    }

    public static double antennaDistance(antenn ant1, antenn ant2){
        return Math.sqrt(Math.pow(Math.abs(ant1.getx() - ant2.getx()), 2) + Math.pow(Math.abs(ant1.gety() - ant2.gety()), 2));
    }

    public static antenn getFurthestAntennafrom(antenn ant1){
        double maxdis = 0;
        antenn furthest = null;
        network net = ant1.getNetwork();
        for(antenn ant2 : net.antennas){
            if(antennaDistance(ant1, ant2) > maxdis){
                maxdis = antennaDistance(ant1, ant2);
                furthest = ant2;
            }
        }
        return furthest;
    }

    public DoublyLinkedList<antenn> antennasToPassBetweene(antenn ant1, antenn ant2){
        DoublyLinkedList<antenn> path = new DoublyLinkedList<>();



        return path;
    }

    public static void main(String[] args){

            run("karta1.txt");

            System.out.println(networkCount());

            highlight(antennalist.getLast());
            //highlight(getFurthestAntennafrom(antennalist.getLast()));
    }




}
