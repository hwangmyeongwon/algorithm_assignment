class transitive {
    int n; //Number of vertices
    int e; // Number of edges
    int[][] weight;

    public transitive(int noOfVertices) {
        n = noOfVertices;
        e = 0;
        weight = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) weight[i][j] = 0;
                else weight[i][j] = 9999; // 9999?
            }
        }
    }

    public transitive() {
        n = 0;
        e = 0;
    }

    public void insertEdge(int i, int j, int w) {
        weight[i][j] = w;
        e++;
    }

    public void removeEdge(int i, int j) {
        weight[i][j] = 9999;
        e--;
    }

    public int[][] allShortestPath() {//이행적폐쇄 만드는데 이용
        // 모든 정점 쌍 간 최단 경로
        int[][] D = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (weight[i][j] != 0 && weight[i][j] != 9999)
                    D[i][j] = 1;
                else {
                    D[i][j] = 9999;
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (D[i][j] > (D[i][k] + D[k][j])){
                        if(D[i][j]==9999)
                            D[i][j]=1;
                    }
                }
            }
        }
        return D;
    }
}
class transitiveTest {
    public static void main(String[] args) {
        transitive gr = new transitive(4);

        gr.insertEdge(0, 1, 2);
        gr.insertEdge(0, 2, 5);
        gr.insertEdge(1, 2, 4);
        gr.insertEdge(1, 3, 10);
        gr.insertEdge(2, 1, 6);
        gr.insertEdge(2, 3, 2);

        int shPath[][] = gr.allShortestPath();
        System.out.println("(allShortestPath)");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(shPath[i][j] + " ");
            }
            System.out.println();
        }




    }
}
