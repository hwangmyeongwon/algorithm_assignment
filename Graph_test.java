class StackNode{
    int data;
    StackNode link;
}
class LinkedStack {
    StackNode top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int data) {
        StackNode newNode = new StackNode();
        newNode.data = data;
        newNode.link = top;
        top = newNode;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("LinkedStack is empty");
            return 0;
        } else {
            int popNode = top.data;
            top = top.link;
            return popNode;
        }
    }
}

class QNode {
    int data;
    QNode link;
}

class LinkedQueue {
    QNode front;
    QNode rear;

    public LinkedQueue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enQueue(int data) {
        QNode newNode = new QNode();
        newNode.data = data;
        newNode.link = null;

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.link = newNode;
//            rear = newNode;
        }
    }

    public int deQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return 0;
        } else {
            int deQueuedData = front.data;
            front = front.link;
            if (front == null) {
                rear = null;
            }
            return deQueuedData;
        }
    }
}

class GraphNodeForFS {
    int vertex;
    GraphNodeForFS link;
}

class AdjList {
    GraphNodeForFS head[] = new GraphNodeForFS[10];
    private int totalVertex = 0;

    // vertex 값을 입력하지 않는다.
    // vertex 갯수 카운트용 메소드

    public void insertVertex(int vertex) {
        totalVertex++;
    }

    // vertex2 값을 내림차순으로 입력해야 함.
    public void insertEdge(int vertex1, int vertex2) {
        if (vertex1 >= totalVertex || vertex2 >= totalVertex) {
            System.out.println("Vertex is out of range");
        } else {
            GraphNodeForFS newNode = new GraphNodeForFS();
            newNode.vertex = vertex2;
            newNode.link = head[vertex1];
            head[vertex1] = newNode;
        }
    }

    public void printAdjList() {
        for (int i = 0; i <= totalVertex; i++) {
            System.out.printf("\n정점 " + i + "의 인접리스트 ");
            GraphNodeForFS gNode = head[i];

            while (gNode != null) {
                System.out.printf("-> " + gNode.vertex);
                gNode = gNode.link;
            }
        }
    }
    public void DFS(int vertex) {
        GraphNodeForFS gNode = new GraphNodeForFS();
        LinkedStack stack = new LinkedStack();
        boolean visited[] = new boolean[10];


        stack.push(vertex);
        visited[vertex] = true;
        System.out.printf(vertex + " ");
        while (stack.top != null) {
            gNode = head[vertex];
            while (gNode != null) {
                if (visited[gNode.vertex]==false) {
                    visited[gNode.vertex] = true;
                    System.out.printf(gNode.vertex + " ");
                    stack.push(gNode.vertex);
                    gNode = head[gNode.vertex];
                } else {
                    gNode = gNode.link;
                }
            }
            vertex = stack.pop();
        }

    }

    public void BFS(int vertex) {
        GraphNodeForFS gNode = new GraphNodeForFS();
        LinkedQueue queue = new LinkedQueue();
        boolean visited[] = new boolean[10];

        queue.enQueue(vertex);
        visited[vertex] = true;
        System.out.printf(" %d", vertex);

        while (queue.isEmpty()==false) {
            vertex = queue.deQueue();
            for (gNode = head[vertex]; gNode != null; gNode = gNode.link) {
                if (visited[gNode.vertex]==false) {
                    visited[gNode.vertex] = true;
                    System.out.printf(" %d", gNode.vertex);
                    queue.enQueue(gNode.vertex);
                }
            }
        }
    }
}

public class Graph_test {

    public static void main(String[] args) {
        AdjList G = new AdjList();
        for (int i = 0; i < 6; i++) {
            G.insertVertex(i);
        }
        G.insertEdge(0,2);
        G.insertEdge(0,1);
        G.insertEdge(1,4);
        G.insertEdge(1,3);
        G.insertEdge(2,5);


        G.printAdjList();

        System.out.printf("\nDFS >>> ");
        G.DFS(0);

        System.out.printf("\nBFS >>> ");
        G.BFS(0);
    }
}
