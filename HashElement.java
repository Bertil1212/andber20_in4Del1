public class HashElement<Key> implements Comparable<HashElement<Key>>{

    private Key key;
    private int count;


    public HashElement(Key key){
        this.key = key;
        count = 1;
    }

    public HashElement(Key key, int counter){
        this.key = key;
        count = counter;
    }

    public void increment() { count++; } 

    public void decrement() { count--; }

    public int getFrequencey() { return count; }

    public Key getKey(){ return key; }

    public void setKey(Key key) { this.key = key; }


    public int compareTo(HashElement<Key> he){
        if(this.count > he.count)
            return 1;
        if(this.count < he.count)
            return -1;
        return 0;
    }

}