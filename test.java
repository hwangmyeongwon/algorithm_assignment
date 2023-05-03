import java.util.*;


class Sorting { //Matrix
    public static void selectionSort(int[] a) {
        int i, j, min;
        for (i = 0; i < a.length - 1; i++) {
            for (j = i + 1, min = i; j < a.length; j++) {
                if (a[j] < a[min])
                    min = j;
            }
            swap(a, min, i);
        }
    }

    public static void swap(int[] a, int j, int k)  {
        int temp = a[j];
        a[j] = a[k];
        a[k] = temp;
    }

    public static void bubbleSort(int[] a)  {
        int i, j;
        for (i = a.length-1;  i >= 0; --i) {
            for (j = 0; j < i; j++) {
                if (a[j] > a[j + 1])
                    swap(a, j, j + 1);
            }

        }
    }

    public static void insertionSort(int a[]) {

        for(int i = 1; i < a.length; i++) {
            int target = a[i];
            int j = i;

            while(j > 0 && target < a[j-1]) {
                a[j] = a[j-1];
                j--;
            }
            a[j] = target;
        }
    }

    public static void quickSort(int [] a) {
        theQuickSort(a, 0, a.length-1);
    }
    private static void theQuickSort(int [] a, int left, int right) {
        int p;
        if (left > right)
            return;
        p = partition(a, left, right);
        theQuickSort(a, left, p-1);
        theQuickSort(a, p+1, right);

    }
    private static int partition(int[] a, int left, int right) {
        int middle=(left+right)/2;

        int lc = left;
        int rc = right;
        int pivot = a[middle];

        while(lc < rc){
            while(a[rc] > pivot && rc > lc) {
                rc--;
            }
            while(a[lc] < pivot && lc < rc) {
                lc++;
            }
            if(lc<rc)
                swap(a, lc, rc);

        }

        return rc;

    }



    public static void heapSort(int [] a) {

        int temp;
        int n = a.length - 1;
        for (int i= n/2;i >= 1; i--) {
            heapify(a, i, n);
        }
        for (int i =n-1 ; i >= 1 ; i--) {
            temp =a[1];
            a[1] =a[i + 1];
            a[i + 1] =temp;
            heapify(a, 1, i);
        }
    }

    private static void heapify(int[] a, int h, int m) {

        int ah = a[h];
        int j;
        for (j = 2*h;  j <= m;  j = 2*j) {
            if (j < m) {
                if (a[j] < a[j+1])
                    j = j+1;
            }
            if (ah >= a[j])
                return;
            else a[j/2] = a[j];
        }
        a[j/2] = ah;

    }


    public static void mergeSort(int[] a) {
        int[] temp = new int[a.length];
        theMergeSort(a, temp, 0, a.length-1);
    }

    private static void theMergeSort(int[] a, int[] temp, int left, int right) {
        if (left < right)  {
            int middle = (left+right) / 2;
            theMergeSort(a, temp, left, middle);
            theMergeSort(a, temp, middle+1, right);
            merge(a, temp, left, middle, middle+1, right);
        }

    }
    private static void merge(int[] a, int[] temp, int left, int middle, int middle1, int right)  {
        int t = left;
        int numElements = right-left+1;
        while (left <= middle && middle1 <= right)
        {
            if (a[left] < a[middle1])
                temp[t++] = a[left++];
            else temp[t++] = a[middle1++];
        }
        while (left <= middle)
            temp[t++] = a[left++];
        while (middle1 <= right)
            temp[t++] = a[middle1++];
        for (int j = 0 ; j < numElements ; j++, right--)
            a[right] = temp[right];

    }

}

class test {
    public static void main(String args[]) {

        int test1[] = {0, 2, 4, 5, 7, 3, 10, 8, 1, 9, 6};
        Sorting.selectionSort(test1);

        System.out.print("test1 : ");
        for (int i = 0; i < test1.length; i++) {
            System.out.print(test1[i] + " ");
        }

        System.out.println();

        int test2[] = {0, 2, 4, 5, 7, 3, 10, 8, 1, 9, 6};
        Sorting.bubbleSort(test2);

        System.out.print("test2 : ");
        for (int i = 0; i < test2.length; i++) {
            System.out.print(test2[i] + " ");
        }

        System.out.println();

        int test3[] = {0, 2, 4, 5, 7, 3, 10, 8, 1, 9, 6};
        Sorting.insertionSort(test3);

        System.out.print("test3 : ");
        for (int i = 0; i < test3.length; i++) {
            System.out.print(test3[i] + " ");
        }


        System.out.println();

        int test4[] = {0, 2, 4, 5, 7, 3, 10, 8, 1, 9, 6};
        Sorting.quickSort(test4);

        System.out.print("test4 : ");
        for (int i = 0; i < test4.length; i++) {
            System.out.print(test4[i] + " ");
        }

        System.out.println();

        int test5[] = {0, 2, 4, 5, 7, 3, 10, 8, 1, 9, 6};
        Sorting.heapSort(test5);

        System.out.print("test5 : ");
        for (int i = 0; i < test5.length; i++) {
            System.out.print(test5[i] + " ");
        }

        System.out.println();
        int test6[] = {0, 2, 4, 5, 7, 3, 10, 8, 1, 9, 6};
        Sorting.mergeSort(test6);

        System.out.print("test6 : ");
        for (int i = 0; i < test6.length; i++) {
            System.out.print(test6[i] + " ");
        }


    }
}

