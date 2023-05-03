public class Sort_Test {
    public static void main(String[] args) {
        int[] a={1,4,6,2,3};

        for(int i=0 ; i<a.length ; i++)
            System.out.print(a[i]);
        bubbleSort(a);
        System.out.println();
        for(int i=0 ; i<a.length ; i++)
            System.out.print(a[i]);

    }
    public static void selectionSort(int[] a) {
        int i, j, min;
        for (i = 0; i < a.length-1 ; i++){
            for (j = i + 1, min = i; j < a.length; j++) {     // a[j] ··· a[a.length] 사이에
                // 가장 작은 원소의 인덱스(min)을 찾음
                if (a[j] < a[min]) min = j;
            }
            swap(a, min, i);     // a[i]와 a[min]을 교환
        }
    }
    public static void bubbleSort(int[] a)  {
        int i, j;
        for (i = a.length-1 ;  i >= 0;  --i){
            for (j = 0; j < i; j++)
                if (a[j] > a[j + 1])
                    swap(a, j, j + 1);
        }
    }

    public static void swap(int[] a, int j, int k)  {     // a[j]와 a[k]를 교환
            int temp = a[j];
            a[j] = a[k];
            a[k] = temp;
        }
}
