import java.util.*;


public class MaxPQ<Key extends Comparable<Key>> 
implements Iterable<Key>
{
    Key[] a;
    int size;
    
    
    
    public Iterable<Key> sortedOrder()
    {
        return new Iterable<Key>()
        {
            public Iterator<Key> iterator()
            {
                return new Iterator<Key>()
                {
                    MaxPQ<Key> mpqTemp;
                    
                    {
                     for (int i = 1; i <= size; i++)
                            mpqTemp.insert(a[i]);
                    }
                        
                    public boolean hasNext()
                    {
                        return !mpqTemp.isEmpty();
                    }
                    
                    public Key next()
                    {
                        return mpqTemp.delMax();
                    }
                };
            }
        };
    }
    
    public Iterator<Key> iterator()
    {
        return new Iterator<Key>()
        {
            int index = 1;
            public boolean hasNext()
            {
                return index <= size;
            }
            public Key next()
            {
                return a[index++];
            }
            
        };
    }
    
    
    
    public MaxPQ()
    {
        this(10);
    }
    @SuppressWarnings("unchecked")
    public MaxPQ(int max)
    {
        
        a = (Key[]) new Comparable[max];
        size = 0;
    }
    @SuppressWarnings("unchecked")
    public MaxPQ(Key[] a){
        //size = a.length;
        this.a = (Key[]) new Comparable[a.length+1];
        int n = a.length;
        for(int i = 0; i < a.length; i++){
            this.a[i+1] = a[i];
            size++;
        }
        //System.out.println(size);
        for(int i = n/2; i > 0; i--){
            //System.out.println(i);
            sink(i);
        }

    }




    int size()
    {
        return size;
    }

    boolean isEmpty()
    {
        return size == 0;
    }
    
    public void insert(Key t)
    {
        if (size >= a.length-1)
            a = Arrays.copyOf(a, 2*a.length);
            
        a[++size] = t;
        
        swim(size);
        
    }
    
    public Key max()
    {
        if (isEmpty()) return null;
        
        return a[1];
    } 
    
    public Key delMax()
    {
        if (isEmpty()) return null;
        
        exch(1,size);
        
        Key temp = a[size];
        a[size--] = null;
        
        if (!isEmpty()) sink(1);
       
        
        return temp;
    }
    
    
    
    private void swim(int k)
    {
        // S?? l??nge f??r??ldranoden existerar och ??r mindre ??n
        // barnnoden.
        while (k/2 >= 1 && less(a[k/2], a[k])) 
        {
            // byt plats p?? f??r??lder och barn...
            exch(k, k/2);
            
            // och hitta den nya f??r??ldern.
            k /= 2;
        }
    }
    
    public void sink(int k)
    {
        
        while (2*k <= size)
        {
            // i ??r de f??rsta barnnoden
            int i = 2*k;
            
            // v??lj den "st??rsta" av de tv?? barnnoderna
            if (i < size && less(a[i], a[i+1])) i++;
            
            // Om den "st??rsta" av de tv?? barnnoderna ??r
            // mindre ??n f??r??ldranoden: avbryt
            if (less(a[i],a[k])) break;
            
            // Vi byter vi plats p?? f??r??ldranoden och den st??rsta
            // barnnoden...
            exch(i, k);
            
            // ...och s??tter den nya f??r??ldranoden till den st??rsta
            // barnnoden.
            k = i;
            
            

        }
    }
    
    
    private boolean less(Key v, Key w)
    {
        return v.compareTo(w) < 0;
    }
    
    private void exch(int i, int j)
    {
        Key temp = a[i]; a[i] = a[j]; a[j] = temp;
    }
   
    public static void main(String[] cmdLn)
    {
        int n = 10;
        Integer[] a = new Integer[n];
        Random r = new Random();
        
        for (int i = 0; i  < n; i++)
            a[i] = r.nextInt(10);
            
        System.out.println(Arrays.toString(a));
        
        MaxPQ<Integer> mpq = new MaxPQ<>(a);
        
        /*
        for (int i = 0; i < n; i++)
            mpq.insert(a[i]);
          */
        //for (int i = 0; i < n; i++)
        //    System.out.println(mpq.delMax());
        
        /* 
        for (Integer i : mpq.sortedOrder())
            System.out.println(i);
          */  
        System.out.println(Arrays.toString(mpq.a));
        
        
            
            
        
    }
}
