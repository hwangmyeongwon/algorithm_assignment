import java.util.LinkedList;
import java.util.Queue;


class Node{
    Node link;
    int vertex;
}

class Graph {
    Node[] header;
    Queue<Integer> ZeroPredQ;
    int[] sortedList;
    int sortedNum;
    int[] indegree;
    int n;

    public Graph(int vertices) {
        n = vertices;
        header = new Node[n];
        ZeroPredQ = new LinkedList<>();
        sortedList = new int[n];
        sortedNum = 0;
        indegree = new int[n];
    }

    public void insertEdge(int i, int j) {
        Node newNode = new Node();
        newNode.vertex = j;
        newNode.link = header[i];
        header[i] = newNode;

        indegree[j]++;
    }

    public void topologicalSort() {
        int v;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                ZeroPredQ.add(i);
        }
        if (ZeroPredQ.isEmpty()) {
            System.out.println("network has a cycle");
            return;
        }
        while (!ZeroPredQ.isEmpty()) {
            v = ZeroPredQ.remove();
            sortedList[sortedNum] = v;
            sortedNum++;

            Node p = header[v];
            while (p != null) {
                indegree[p.vertex]--;
                if (indegree[p.vertex] == 0)
                    ZeroPredQ.add(p.vertex);
                p = p.link;
            }
        }
        System.out.println("Topological Order is : ");
        for (int k = 0; k < sortedList.length; k++)
            System.out.print(sortedList[k] + " ");
        System.out.println();
        System.out.println("End.");
    }
}


class AOV_Topological_Sort_test {
    public static void main(String[] args) {
        Graph AOV = new Graph(6);

        AOV.insertEdge(0,2);
        AOV.insertEdge(0,1); // 정점 0의 간선들을 삽입

        AOV.insertEdge(1,4);
        AOV.insertEdge(1,3); // 정점 1의 간선들을 삽입

        AOV.insertEdge(2,4);
        AOV.insertEdge(2,3); // 정점 2의 간선들을 삽입


        AOV.insertEdge(3,5); // 정점 3의 간선들을 삽입
        AOV.insertEdge(4,5); // 정점 4의 간선들을 삽입


        AOV.topologicalSort(); //위상 정렬 함수 호출

    }
}
