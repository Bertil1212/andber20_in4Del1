import java.util.Iterator;

public class antenn implements Comparable<antenn> {
    

    private int x;
    private int y;
    private int r;

    private network connectednetwork;
    public DoublyLinkedList<antenn> linkedAntennas = new DoublyLinkedList<>();

    public antenn(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;

    }

    public void addConnectedAntenna(antenn ant) {linkedAntennas.add(ant);}

    public DoublyLinkedList<antenn> getConnections(){
        return linkedAntennas;
    }

    public boolean isConnectedTo(antenn ant){
        return false;
    }


    public int compareTo(antenn a){
        if(r > a.r)
            return 1;

        if(r < a.r)
            return -1;
        return 0;
    }

    public boolean addToNetwork(DoublyLinkedList<network> networklist){
        boolean createNew = true;
        LinearProbingHashSet<network> netlist = new LinearProbingHashSet<>();
        if(linkedAntennas.size() == 0){
            if(connectednetwork != null){
                return true;
            }
            //Lonely node
            
            return true;
        }

        
        for(antenn ant : linkedAntennas){
            if(ant.getNetwork() != null){
                netlist.insert(ant.getNetwork());
                createNew = false;
            }
        }
        if(connectednetwork != null){
            connectednetwork.removeAntenna(this);
            if(connectednetwork.antennas.size() == 0)
                networklist.remove(connectednetwork);
            
            connectednetwork = null;
        }


        if(createNew){
            connectednetwork = new network();
            networklist.add(connectednetwork);
            connectednetwork.addAntenna(this);
            return false;
        }else{
            Iterator<network> changetestit = netlist.keys().iterator();
            if(changetestit.hasNext()){
                changetestit.next();
            }
            boolean allsame = true;
            if(changetestit.hasNext()){
                allsame = false;
            }

            Iterator<network> it = netlist.keys().iterator();
            network net = it.next();
            
            while(it.hasNext()){
                network netnew = it.next();
                if(net.priorityOffset < netnew.priorityOffset)
                    net = netnew;    
            }
            
                


            connectednetwork = net;
            net.addAntenna(this);
            return allsame;
        }


    }

    public int getx() { return x; }
    public int gety() { return y; }
    public int getr() { return r; }
    public network getNetwork(){ return connectednetwork; }
    public void setNetwork(network network){ connectednetwork = network; }


}
