public class LinearProbingHashSet<Key> {

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


    public int hash(Key key) { return (key.hashCode() % m); }

    public int getCapaticy(){ return m; }
    
    
    @SuppressWarnings("unchecked")
    private void doubleSize(){
        HashElement<Key>[] temp;
        temp = new HashElement[m*2];
        for(int i = 0; i < keyarr.length; i++){ temp[i] = keyarr[i]; }
        keyarr = new HashElement[m*2];
        m = m*2;
        for(int i = 0; i < keyarr.length; i++){ keyarr[i] = temp[i]; }

    }

    public void insert(Key key){
        int hashkey = hash(key);
        int i = 0;
        while(true){
            if(i + hashkey > m-1 || n/m >= 0.5f)
                doubleSize();
            if(keyarr[hashkey+i].getKey() == key){
                keyarr[hashkey+i].increment();
                return;
            }

            if(keyarr[hashkey+i] == null){
                keyarr[hashkey+i] = new HashElement<Key>(key);
                n++;
                return;
            }
            i++;
        }
    }

    public boolean contains(Key key){
        int hashkey = hash(key);

        int i = 0;
        while(true){
            if(hashkey + i > m-1)
                return false;

            if(keyarr[hashkey+i].getKey() == key)
                return true;

            if(keyarr[hashkey+i] == null)
                return false;
            i++;
        
        }
    }

    public void delete(Key key){
        int hashkey = hash(key);

        int i = 0;

        while(true){
            if(hashkey + i > m-1)
                return;

            if(keyarr[hashkey+i].getKey() == key){
                delete(hashkey+i);
                return;
            }

            if(keyarr[hashkey+i] == null)
                return;
            i++;
        
        }
    }

    private void delete(int place){
        
        keyarr[place] = null;
        for(int i = place; i < keyarr.length; i++){
            if(keyarr[i+1] == null){
                break;
            }else{
                Key temp = keyarr[i+1].getKey();
                keyarr[i+1] = null;
                insert(temp);
            }
        }
    }


    public void decrease(Key key){
        int hashkey = hash(key);

        int i = 0;

        while(true){
            if(hashkey + i > m-1)
                return;

            if(keyarr[hashkey+i].getKey() == key){
                if(keyarr[hashkey+i].getFrequencey() == 1){
                    delete(hashkey+i);
                }else{
                    keyarr[hashkey+i].decrement();
                }

                return;
            }

            if(keyarr[hashkey+i] == null)
                return;
            i++;
        
        }


    }

    public Iterable<Key> keys(){
        
    }



}