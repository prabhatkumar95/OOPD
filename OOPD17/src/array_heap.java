public class array_heap {

    student[] heap;
    int heap_size;
    int capacity;

    array_heap()
    {
        heap=new student[10000];
        heap_size=0;
        capacity=10000;
    }

    array_heap(String a, String b,String c[],int n)
    {
        heap=new student[n];
        heap_size=1;
        heap[0]=new student(a,b,c);
        capacity=n;
    }

    public static int  left(int i)
    {	return 2*i+1;  }

    public static int right(int i)
    {	return 2*i+2;	}

    public static int parent (int i )
    {
        if(i%2==0)
            return (i-1)/2;
        else
            return i/2;
    }

    boolean isempty()
    {
        return this.heap_size==0;
    }

    boolean isfull()
    {
        return this.heap_size==this.capacity;
    }

    public int insertion(String a, String b,String c[])
    {
        if(this.heap_size<this.capacity)
        {
            heap[this.heap_size]=new student(a,b,c);
            this.heap_size++;
            int i = heap_size-1;
            int pos=i;
            while(i!=0&&heap[i].greater(heap[parent(i)]))
            {
                student temp;
                temp=heap[i];
                heap[i]=heap[parent(i)];
                heap[parent(i)]=temp;
                i=parent(i);
                pos=i;
            }
            return pos+1;
        }
        return -1;
    }

    public void maxheapify(int i)
    {
        int left=left(i);
        int right = right(i);
        int largest;
        if(left<this.heap_size && (heap[left].compareTo(heap[i]))>0)
        {
            largest = left;
        }
        else
            largest =i;

        if(right<this.heap_size&&(heap[right].compareTo(heap[largest]))>0)
        {
            largest = right;
        }

        if(largest!=i)
        {
            student temp;
            temp= heap[largest];
            heap[largest]=heap[i];
            heap[i]=temp;
            this.maxheapify(largest);
        }
    }

    public void build_maxheap()
    {
        int t;
        t=this.heap_size-1;
        for(int i = parent(t);i>=0;i--)
            this.maxheapify(i);
    }

    public student find_max()
    {
        if(this.heap_size>0)
        {
            return heap[0];
        }
        else
            return null;
    }

    public student delete(String a, String b)
    {

        int i =0;
        for(i=0;i<heap_size;i++)
        {
            if((heap[i].first.equalsIgnoreCase(a)) && (heap[i].last.equalsIgnoreCase(b)))
                break;
        }
        i=i+1;
        //System.out.println(i);

        if(i<=heap_size&&i>0)
        {
            student temp;
            temp=heap[i-1];
            heap[i-1]=heap[this.heap_size-1];
            this.heap_size--;
            this.maxheapify(i-1);
            return temp;
        }
        else
            return null;
    }

    public student extract_max()
    {
        if(this.heap_size>0)
        {
            student temp;
            temp=heap[0];
            heap[0]=heap[this.heap_size-1];
            this.heap_size--;
            this.maxheapify(0);
            return temp;
        }
        else
            return null;
    }

    public void print_heap()
    {
        for(int i =0;i<this.heap_size;i++)
        {
            System.out.println(heap[i].first+" "+heap[i].last);
            System.out.print("COURSES : ");
            for(int j=0;j<heap[i].courses.length;j++)
                System.out.print(heap[i].courses[j]+"  ");
            System.out.println();
        }
    }


}
