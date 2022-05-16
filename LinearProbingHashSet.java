import java.util.Iterator;

public class LinearProbingHashSet<Key extends Comparable<Key>> {

    private int defaultM = 3;   //default array lenght
    private HashElement<Key>[] keyarr;
    private int m;  //array lenght
    private int n = 0;  //element count

    
    @SuppressWarnings("unchecked")   //To get rid of the unchecked cast warning
    public LinearProbingHashSet(int m){
        this.m = m;
        keyarr = new HashElement[m];
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashSet(){
        m = defaultM;
        keyarr = new HashElement[m];
    }


    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public int getCapaticy(){ return m; }
    
    
    @SuppressWarnings("unchecked")
    private void doubleSize(){
        m=m*2;
        HashElement<Key>[] temp = new HashElement[m];

        for(int i = 0; i< keyarr.length; i++){
            temp[i] = keyarr[i];
        }

        keyarr = new HashElement[m];


        for(int i = 0; i < temp.length; i++){
            if(temp[i] != null){
                Key key= temp[i].getKey();
                int y = hash(key);
                while(true){

                    
                    if(keyarr[y] == null){
                        keyarr[y] = new HashElement<Key>(key, temp[i].getFrequencey());
                        
                        
                        break;
                    }

                    if(keyarr[y].getKey() == key){
                        keyarr[y].increment();
                        break;
                    }

                    y++;
                    y = y % m;
                }
                
            }
        }
    }

    public void insert(Key key){
        
        if(n/m >= 0.4f)
            doubleSize();
        
        int i = hash(key);
        
        while(true){
            
            
            if(keyarr[i] == null){
                keyarr[i] = new HashElement<Key>(key);
                n++;
                return;
            }

            if(keyarr[i].getKey() == key){
                keyarr[i].increment();
                return;
            }
            i++;
            i = i % m;

            

            
        }
    }

    public boolean contains(Key key){
        int i = hash(key);

        int count = 0;
        while(true){
            if(keyarr[i] != null && keyarr[i].getKey() == key)
                return true;

            if(keyarr[i] == null)
                return false;

            count++;
            if(count > m){
                return false;
            }
            i++;
            i = i % m;
        
        }
    }

    public void delete(Key key){
        int i = hash(key);

        int count = 0;
        while(true){
            

            if(keyarr[i].getKey() == key){
                deletep(i);
                return;
            }

            if(keyarr[i] == null)
                return;

            count++;
            if(count > m){
                return;
            }

            i++;
            i = i % m;
        
        }
    }

    private void deletep(int place){
        if(keyarr[place] == null)
            return;
        
        keyarr[place] = null;
        n--;
    }


    public void decrease(Key key){
        int i = hash(key);
        int step = 0;
        if(!contains(key))
            return;
        while(true){

            if(keyarr[i].getKey() == key){
                if(keyarr[i].getFrequencey() == 1){
                    deletep(i);
                    return;
                }else{
                    keyarr[i].decrement();
                }

                return;
            }

            if(keyarr[i] == null)
                return;
            step++;
            if(step > m){
                return;
            }
            i++;
            i = i % m;
        
        }


    }



    public Iterable<Key> keys()
    {

        
        MaxPQ<HashElement<Key>> mpq = new MaxPQ<>();
        
        for(int i = 0; i < m; i++){
            if(keyarr[i] != null){
                mpq.insert(keyarr[i]);
            }
        }

        DoublyLinkedList<Key> sortedlist = new DoublyLinkedList<>();
        for(int i = 0; i < n; i++){
            sortedlist.add(mpq.delMax().getKey());
        }

        
        
        return sortedlist;


        
    }


    //For testing
    public void printarr(){
        System.out.println("M is : " + m);
        System.out.println("N is : " + n);

        for(int i = 0; i < m; i++){
            if(keyarr[i] != null){
                System.out.print(keyarr[i].getKey());
                System.out.println("   Count:"+keyarr[i].getFrequencey());
            }else
                System.out.println("Null");
        }
        System.out.println("");
    }

    public void e(Key key){
        insert(key);
        printarr();
    }



    public static void main(String[] args) {
        LinearProbingHashSet<Integer> Lp = new LinearProbingHashSet<>(3);

        Lp.e(1);
        Lp.e(2);
        Lp.e(2); 
        Lp.e(3); 
        Lp.e(3); 
        Lp.e(3); 
        Lp.e(4); 
        Lp.e(4); 
        Lp.e(4); 
        Lp.e(4); 
        Lp.decrease(4);
        Lp.decrease(4);
        Lp.decrease(4);

        Lp.decrease(4);
        Lp.decrease(4);



        Lp.decrease(2);
       

        Lp.printarr();
        

        //System.out.println(Lp.contains(4));






        Iterator<Integer> it = Lp.keys().iterator();
        System.out.println("Iterator next");
        while(it.hasNext()){
            System.out.println(it.next());
        }
        

       


    }
       
    

}