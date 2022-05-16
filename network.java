import java.awt.Color;
public class network implements Comparable<network>{
    public DoublyLinkedList<antenn> antennas = new DoublyLinkedList<>();
    double priorityOffset;


    private Color networkColor;

    public network(){
        priorityOffset = Math.random();
        networkColor = new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255));
    }

    public int compareTo(network net){
        return 0;
    }


    public Color getColor(){
        return networkColor;
    }

    public void addAntenna(antenn ant){
        antennas.add(ant);
    }

    public void removeAntenna(antenn ant){
        antennas.remove(ant);
    }

    public DoublyLinkedList<antenn> getAntennas(){
        return antennas;
    }
    public double getPriority(){
        return priorityOffset;
    }


}
