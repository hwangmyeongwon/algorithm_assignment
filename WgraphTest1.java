import java.util.*;
import java.util.Arrays;

class Edge implements Comparable<Edge>{
    int head;
    int tail;
    int weight;

    public Edge(int h, int t, int w) {
        head = h;
        tail = t;
        weight = w;
    }

    public int compareTo(Edge e) {
        int a = this.weight;
        int b = e.weight;
        return a - b;
    }
}
class UnionFind {
    int [] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    private int root(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        parent[i] = j;
    }
}
class minHeap {
    private int count;
    private int size;
    private Edge[] itemArray;

    public minHeap() {
        count = 0;
        size = 10;
        itemArray = new Edge[size];
        itemArray[0]=new Edge(0,0,0);
    }


    private void heapFull(Edge[] arr,int size){
        itemArray = new Edge[size];
        for (int i = 0; i < arr.length; i++) {
            itemArray[i] = arr[i];
        }
    }
    public void insert(Edge newItem) {

        count+=1;
        if((count)==size){
            size=size*2;
            heapFull(itemArray,size);
        }
        int i=count;
        while(true){
            if(i==1)
                break;
            if(newItem.weight>=itemArray[i/2].weight)
                break;
            itemArray[i]=itemArray[i/2];
            i=i/2;
        }
        itemArray[i]=newItem;
    }

    public Edge delete() {
        if(count==0)
            return null;
        Edge item=itemArray[1];
        Edge temp=itemArray[count];
        count-=1;
        int i=1;
        int j=2;

        while(j<=count){
            if(j<count){
                if(itemArray[j].weight>itemArray[j+1].weight)
                    j=j+1;
            }
            if(temp.weight<=itemArray[j].weight)
                break;
            itemArray[i]=itemArray[j];
            i=j;
            j=j*2;
        }
        itemArray[i]=temp;
        return item;
    }
}

class Wgraph { //Matrix

    int n; //Number of vertices
    int e; // Number of edges
    int[][] weight;

    public Wgraph(int noOfVertices) {
        n = noOfVertices;
        e = 0;
        weight = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) weight[i][j] = 0;
                else weight[i][j] = 9999;
            }
        }
    }
    public Wgraph() {
        n = 0;
        e = 0;
    }

    public void insertEdge(int i, int j, int w) {
        this.weight[i][j] = w;
        e++;

    }

    public void removeEdge(int i, int j) {
        this.weight[i][j] = 0;
        e--;
    }

    public Edge[] spanningTree() { // Kruskal
        Edge[] edgelist = new Edge[e];
        UnionFind unionFind = new UnionFind(n);

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (weight[i][j] == 9999 || weight[i][j] == 0)
                    continue;
                else {
                    edgelist[count] = new Edge(i, j, weight[i][j]);
                    count++;
                }
            }
        }

        Arrays.sort(edgelist);

        Edge[] e = new Edge[n-1];
        int count2 = 0;
        for (int i = 0; i < edgelist.length; i++) {
            if (unionFind.find(edgelist[i].tail, edgelist[i].head) == false)
            {
                unionFind.union(edgelist[i].tail, edgelist[i].head);
                e[count2] = edgelist[i];
                count2++;
                System.out.println();
            }
        }

        return e;

    }

    public Edge[] prim(int t) { // Prim
        Edge[] edgelist = new Edge[n-1];
        UnionFind unionFind = new UnionFind(n);
        minHeap mh = new minHeap();

        for(int i=0 ; i<n ; i++) {
            if (weight[t][i] != 0 && weight[t][i] != 9999)
                mh.insert(new Edge(t, i, weight[t][i]));
        }

        int count = 0;

        while (count<n-1){
            Edge e=mh.delete();
            if(unionFind.find(e.head,e.tail)==false) {
                edgelist[count] = e;
                count++;
                unionFind.union(e.head,e.tail);
                for(int i=0 ; i<n ; i++) {
                    if (weight[e.tail][i] != 0 && weight[e.tail][i] != 9999)
                        mh.insert(new Edge(e.tail, i, weight[e.tail][i]));
                    if(weight[i][e.tail] != 0 && weight[i][e.tail] != 9999)
                        mh.insert(new Edge(e.tail, i, weight[i][e.tail]));
                }
            }
        }
        return edgelist;
    }
    //sollin 은 재미로

}

public class WgraphTest1 {
    public static void main(String args[]) {
        Wgraph gr = new Wgraph(6);

        gr.insertEdge(0, 1, 5);
        gr.insertEdge(0, 2, 4);
        gr.insertEdge(1, 2, 2);
        gr.insertEdge(1, 3, 7);
        gr.insertEdge(2, 3, 6);
        gr.insertEdge(2, 4, 11);
        gr.insertEdge(3, 4, 3);
        gr.insertEdge(3, 5, 8);
        gr.insertEdge(4, 5, 8);

        Edge[] edgelist = gr.spanningTree();
        System.out.println("Kruskal");
        for (int i = 0; i < edgelist.length; i++) {
            System.out.print("(" + edgelist[i].head + " " + edgelist[i].tail + ") ");
        }


        System.out.println();
        System.out.println("Prim");

        edgelist = gr.prim(0);
        for (int i = 0; i < edgelist.length; i++) {
            System.out.print("(" + edgelist[i].head + " " + edgelist[i].tail + ") ");
        }

        System.out.println();

    }
}

