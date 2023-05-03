class Heap {
    private int count;
    private int size;
    private int[] itemArray;

    public Heap() {
        count = 0;
        size = 10;
        itemArray = new int[size];
        itemArray[0]=-10;
    }

    public Heap(int[] origArray) {
        size = 8;
        itemArray = new int[size];
        if(size<origArray.length){
            size=size*2;
            heapFull(itemArray,size);
        }
        for (int i = 0; i < origArray.length; i++) {
            itemArray[i] = origArray[i];
        }
        count = origArray.length - 1;
        int temp;

        for (int i = count / 2; i >= 1; i--) {
            int p = i;
            for (int j = 2 * p; j <= count; j = j * 2) {
                if (j < count) {
                    if (itemArray[j] < itemArray[j + 1])
                        j = j + 1;
                }
                if (itemArray[p] >= itemArray[j])
                    break;
                temp = itemArray[p];
                itemArray[p] = itemArray[j];
                itemArray[j] = temp;
                p = j;
            }
        }
    }
    private void heapFull(int[] arr,int size){
        itemArray = new int[size];
        for (int i = 0; i < arr.length; i++) {
            itemArray[i] = arr[i];
        }
    }

/*
    public static Heap makeHeap(int[] h){
        Heap newHeap=new Heap(h);

        int temp;

        for(int i= newHeap.count/2 ; i>=1 ; i--){
            int p=i;
            for(int j=2*p;j<=newHeap.count;j=j*2){
                if(j< newHeap.count){
                    if(newHeap.itemArray[j]<newHeap.itemArray[j+1])
                        j=j+1;
                }
                if(newHeap.itemArray[p]>=newHeap.itemArray[j])
                    break;
                temp=newHeap.itemArray[p];
                newHeap.itemArray[p]=newHeap.itemArray[j];
                newHeap.itemArray[j]=temp;
                p=j;
            }
        }

        return newHeap;
    }
*/
    public void insert(int newItem) {


        count+=1;
        if((count)==size){
            size=size*2;
            heapFull(itemArray,size);
        }
        int i=count;
        while(true){
            if(i==1)
                break;
            if(newItem>=itemArray[i/2])
                break;
            itemArray[i]=itemArray[i/2];
            i=i/2;
        }
        itemArray[i]=newItem;
    }

    public int delete() {
        if(count==0)
            return -1;
        int item=itemArray[1];
        int temp=itemArray[count];
        count-=1;
        int i=1;
        int j=2;

        while(j<=count){
            if(j<count){
                if(itemArray[j]>itemArray[j+1])
                    j=j+1;
            }
            if(temp<=itemArray[j])
                break;
            itemArray[i]=itemArray[j];
            i=j;
            j=j*2;
        }
        itemArray[i]=temp;
        return item;
    }


    public void printHeap() {
        int i;
        for (i = 1; i <= count; i++)
            System.out.print(itemArray[i] + " ");
        System.out.println();
    }
}


class HeapTest {
    public static void main(String args[]) {
        Heap h = new Heap();
        h.insert(30);
        h.insert(40);
        h.insert(20);
        h.insert(10);
        h.insert(35);
        h.insert(45);
        h.insert(50);
        h.insert(25);
        h.insert(70);
        h.insert(48);
        h.insert(64);
        h.insert(31);
        h.insert(100);
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();
        System.out.println(h.delete());
        h.printHeap();

        h.printHeap();

        System.out.println("************************");

        // -10은 의미없는 숫자임
        int[] origArray = {-10, 50, 55, 60, 30, 70, 90, 25, 80, 40, 45};
        h = new Heap(origArray);
        h.printHeap();

/*
        // -10은 의미없는 숫자임
        int[] origArray1 = {-10, 50, 55, 60, 30, 70, 90, 25, 80, 40, 45};
        Heap h1 = Heap.makeHeap(origArray1);
        h1.printHeap();
*/
        System.out.println(h.delete());
        System.out.println(h.delete());

        h.printHeap();



    }
}