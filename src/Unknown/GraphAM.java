package Unknown;

public class GraphAM {
    int count;
    int[][] adj;
    GraphAM(int cnt) {
        count = cnt;
        adj = new int[count][count];
    }
    public void addDirectedEdge(int src, int dst, int cost) {
        adj[src][dst] = cost;
    }
    public void addUndirectedEdge(int src, int dst, int cost) {
        addDirectedEdge(src, dst, cost);
        addDirectedEdge(dst, src, cost);
    }
    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print("Node index " + i + " is connected with : ");
            for (int j = 0; j < count; j++) {
                if (adj[i][j] != 0)
                    System.out.print(j + " ");
            }
            System.out.println(""); }
    }
    public static void main(String[] args) {
        GraphAM graph = new GraphAM(4);
        graph.addUndirectedEdge(0, 1, 1);
        graph.addUndirectedEdge(0, 2, 1);
        graph.addUndirectedEdge(1, 2, 1);
        graph.addUndirectedEdge(2, 3, 1);
        graph.print();
    }
}
