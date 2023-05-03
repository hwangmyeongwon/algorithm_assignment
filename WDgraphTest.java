class WDgraph { //Matrix

    int n; //Number of vertices
    int e; // Number of edges
    int [][] weight;

    public WDgraph(int noOfVertices) {
        n = noOfVertices;
        e = 0;
        weight = new int[n][n];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (i == j) weight[i][j] = 0;
                else weight[i][j] = 9999; // 9999?
            }
        }
    }

    public WDgraph() {
        n = 0;
        e = 0;
    }

    public void insertEdge(int i, int j, int w) {
        weight[i][j]=w;
        e++;
    }

    public void removeEdge(int i, int j) {
        weight[i][j]=9999;
        e--;
    }


    public int[] shortestPath(int v) {
        // Dijkstra
        int[] Dist=new int[n];
        boolean[] S=new boolean[n];

        for(int i=0 ; i<n ; i++){
            S[i]=false;
            Dist[i]=weight[v][i];
        }

        S[v]=true;
        Dist[v]=0;

        for(int i=0 ;i<n-2 ; i++){
            int min=999999;
            int min_index = -1;
            for(int j=0;j<n;j++){
                if(S[j]==false){
                    if(Dist[j]<min){
                        min=Dist[j];
                        min_index = j;
                    }
                }
            }
            S[min_index] = true;
            for(int w=0 ;w<n ;w++){
                if(S[w]==false){
                    if(Dist[w]>(Dist[min_index]+weight[min_index][w]))
                        Dist[w]=Dist[min_index]+weight[min_index][w];
                }
            }
        }
        return Dist;
    }

    public int[] negativePath(int v) {
        // 음의 정수 포함
        int[] Dist=new int[n];
        int[] prevDist=new int[n];
        boolean[] indgree=new boolean[n];

        for(int i=0 ; i<n ; i++)
            indgree[i]=false;
        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<n ; j++){
                if(weight[j][i]!=9999 && weight[j][i]!=0){
                    indgree[i]=true;

                }
            }
        }

        for(int i=0 ; i<n ;i++)
            Dist[i]=weight[v][i];

        for(int k=2 ; k<n ;k++) {
            for (int u = 0; u < n; u++)
                prevDist[u] = Dist[u];
            for (int u = 0; u < n; u++) {
                if (u != v && indgree[u]) {
                    for (int i = 0; i < n; i++) {
                        if (weight[i][u] < 9999) {
                            if (Dist[u] > prevDist[i] + weight[i][u])
                                Dist[u] = prevDist[i] + weight[i][u];
                        }
                    }
                }
            }
        }



        return Dist;
    }

    public int[][] allShortestPath() {//이행적폐쇄 만드는데 이용
        // 모든 정점 쌍 간 최단 경로
        int[][] D=new int[n][n];

        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<n ; j++)
                D[i][j]=weight[i][j];
        }
        for(int k=0; k<n ; k++){
            for(int i=0 ; i<n ; i++){
                for(int j=0 ; j<n ; j++){
                    if(D[i][j]>(D[i][k]+D[k][j]))
                        D[i][j]=D[i][k]+D[k][j];
                }
            }
        }
        return D;
    }
}


class WDgraphTest {

    public static void main(String args[]) {
        WDgraph gr = new WDgraph(5);

        gr.insertEdge(0, 1, 2);
        gr.insertEdge(0, 2, 5);
        gr.insertEdge(0, 4, 3);
        gr.insertEdge(1, 3, 4);
        gr.insertEdge(1, 4, 10);
        gr.insertEdge(2, 3, 6);
        gr.insertEdge(2, 4, 2);
        gr.insertEdge(4, 2, 1);
        gr.insertEdge(4, 3, 2);

        int[] sp = gr.shortestPath(0);

        System.out.println("(shortestPath)");
        for (int i = 0; i < sp.length; i++) {
            System.out.println("0 -> " + i + ": " + sp[i]);
        }

        System.out.println();

        gr = new WDgraph(6);

        gr.insertEdge(0, 1, 6);
        gr.insertEdge(0, 2, 5);
        gr.insertEdge(0, 3, 5);
        gr.insertEdge(1, 4, -1);
        gr.insertEdge(2, 1, -2);
        gr.insertEdge(2, 4, 1);
        gr.insertEdge(3, 2, -3);
        gr.insertEdge(3, 5, -1);
        gr.insertEdge(4, 5, 3);

        sp = gr.negativePath(0);
        System.out.println("(negativePath)");
        for (int i = 0; i < sp.length; i++) {
            System.out.println("0 -> " + i + ": " + sp[i]);
        }


        gr = new WDgraph(6);

        gr.insertEdge(0, 1, 5);
        gr.insertEdge(0, 2, 4);
        gr.insertEdge(1, 2, 2);
        gr.insertEdge(1, 3, 7);
        gr.insertEdge(2, 3, 6);
        gr.insertEdge(2, 4, 11);
        gr.insertEdge(3, 4, 3);
        gr.insertEdge(3, 5, 8);
        gr.insertEdge(4, 5, 8);

        System.out.println();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(gr.weight[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();


        int shPath[][] = gr.allShortestPath();
        System.out.println("(allShortestPath)");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(shPath[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();


        gr = new WDgraph(4);

        gr.insertEdge(0, 1, 2);
        gr.insertEdge(0, 2, 9);
        gr.insertEdge(1, 0, 5);
        gr.insertEdge(1, 2, 4);
        gr.insertEdge(1, 3, 3);
        gr.insertEdge(2, 0, -1);
        gr.insertEdge(2, 3, 4);
        gr.insertEdge(3, 1, 1);
        gr.insertEdge(3, 2, 7);

        System.out.println();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(gr.weight[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        shPath = gr.allShortestPath();

        System.out.println("(allShortestPath)");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(shPath[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();


    }
}

