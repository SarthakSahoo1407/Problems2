package Unknown;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
class Sets {
    int parent;
    int rank;

    Sets(int p, int r) {
        parent = p;
        rank = r;
    }
}
public class Graph {
    int count;
    private LinkedList<LinkedList<Edge>> Adj;
    int INF = 9999;

    private static class Edge implements Comparable<Edge> {
        int src, dest, cost;

        public Edge(int s, int d, int c) {
            src = s;
            dest = d;
            cost = c;
        }

        public int compareTo(Edge compareEdge) {
            return this.cost - compareEdge.cost;
        }
    }

    public Graph(int cnt) {
        count = cnt;
        Adj = new LinkedList<LinkedList<Edge>>();
        for (int i = 0; i < cnt; i++) {
            Adj.add(new LinkedList<Edge>());
        }
    }

    private void addDirectedEdge(int source, int dest, int cost) {
        Edge edge = new Edge(source, dest, cost);
        Adj.get(source).add(edge);
    }

    public void addDirectedEdge(int source, int dest) {
        addDirectedEdge(source, dest, 1);
    }

    public void addUndirectedEdge(int source, int dest, int cost) {
        addDirectedEdge(source, dest, cost);
        addDirectedEdge(dest, source, cost);
    }

    public void addUndirectedEdge(int source, int dest) {
        addUndirectedEdge(source, dest, 1);
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            LinkedList<Edge> ad = Adj.get(i);
            System.out.print("Vertex " + i + " is connected to : ");
            for (Edge adn : ad) {
                System.out.print(adn.dest + "(cost: " + adn.cost + ") ");
            }
            System.out.println();
        }
    }
    public boolean dfs(int source, int target) {
        boolean[] visited = new boolean[count];
        dfsUtil(source, visited);
        return visited[target];
    }

    public void dfsUtil(int index, boolean[] visited) {
        visited[index] = true;
        LinkedList<Edge> adl = Adj.get(index);
        for (Edge adn : adl) {
            if (visited[adn.dest] == false)
                dfsUtil(adn.dest, visited);
        }
    }
    public boolean bfs(int source, int target) {
        boolean[] visited = new boolean[count];
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.add(source);
        visited[source] = true;

        while (que.isEmpty() == false) {
            int curr = que.remove();
            LinkedList<Edge> adl = Adj.get(curr);
            for (Edge adn : adl) {
                if (visited[adn.dest] == false) {
                    visited[adn.dest] = true;
                    que.add(adn.dest);
                }
            }
        }
        return visited[target];
    }

    public boolean isCyclePresentUndirectedDFS(int index, int parentIndex, boolean[] visited) {
        visited[index] = true;
        int dest;
        LinkedList<Edge> adl = Adj.get(index);
        for (Edge adn : adl) {
            dest = adn.dest;
            if (visited[dest] == false) {
                if (isCyclePresentUndirectedDFS(dest, index, visited))
                    return true;
            } else if (parentIndex != dest)
                return true;
        }
        return false;
    }
    public void primsMST() {
        int[] previous = new int[count];
        int[] dist = new int[count];
        boolean[] visited = new boolean[count];

        Arrays.fill(previous, -1);
        Arrays.fill(dist, Integer.MAX_VALUE);// infinite

        int source = 0;
        dist[source] = 0;
        previous[source] = source;

        PriorityQueue<Edge> queue = new PriorityQueue<Edge>(100);
        Edge node = new Edge(source, source, 0);
        queue.add(node);

        while (queue.isEmpty() != true) {
            node = queue.peek();
            queue.remove();
            visited[source] = true;
            source = node.dest;
            LinkedList<Edge> adl = Adj.get(source);
            for (Edge adn : adl) {
                int dest = adn.dest;
                int alt = adn.cost;
                if (dist[dest] > alt && visited[dest] == false) {
                    dist[dest] = alt;
                    previous[dest] = source;
                    node = new Edge(source, dest, alt);
                    queue.add(node);
                }
            }
        }

        // printing result.
        int sum = 0;
        boolean isMst = true;
        String output = "Edges are ";
        for (int i = 0; i < count; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                output += ("(" + i + ", Unreachable) ");
                isMst = false;
            } else if (previous[i] != i) {
                output += ("(" + previous[i] + " -> " + i + " @ " + dist[i] + ") ");
                sum += dist[i];
            }
        }

        if (isMst) {
            System.out.println(output);
            System.out.println("Total MST cost: " + sum);
        } else
            System.out.println("Can't get a Spanning Tree");

    }
    public int find(Sets[] sets, int index) {
        int p = sets[index].parent;
        while (p != index) {
            index = p;
            p = sets[index].parent;
        }
        return index;
    }

    // consider x and y are roots of sets.
    public void union(Sets[] sets, int x, int y) {
        if (sets[x].rank < sets[y].rank)
            sets[x].parent = y;
        else if (sets[y].rank < sets[x].rank)
            sets[y].parent = x;
        else {
            sets[x].parent = y;
            sets[y].rank++;
        }
    }
    public void kruskalMST() {
        //Different subsets are created.
        Sets[] sets = new Sets[count];
        for (int i = 0; i < count; i++)
            sets[i] = new Sets(i, 0);

        // Edges are added to array and sorted.
        int E = 0;
        Edge edge[] = new Edge[100];
        for (int i = 0; i < count; i++) {
            LinkedList<Edge> ad = Adj.get(i);
            for (Edge adn : ad) {
                edge[E++] = adn;
            }
        }
        Arrays.sort(edge, 0, E - 1);

        int sum = 0;
        String output = "Edges are ";
        for (int i = 0; i < E; i++) {
            int x = find(sets, edge[i].src);
            int y = find(sets, edge[i].dest);
            if (x != y) {
                output += ("(" + edge[i].src + " -> " + edge[i].dest + " @ " + edge[i].cost + ") ");
                sum += edge[i].cost;
                union(sets, x, y);
            }
        }
        System.out.println(output);
        System.out.println("Total MST cost: " + sum);
    }
    public void dijkstra(int source) {
        int[] previous = new int[count];
        int[] dist = new int[count];
        boolean[] visited = new boolean[count];

        Arrays.fill(previous, -1);
        Arrays.fill(dist, 99999); // infinite

        dist[source] = 0;
        previous[source] = source;

        PriorityQueue<Edge> queue = new PriorityQueue<Edge>(100);
        Edge edge = new Edge(source, source, 0);
        queue.add(edge);
        int curr;

        while (queue.isEmpty() != true) {
            edge = queue.peek();
            queue.remove();
            curr = edge.dest;
            visited[curr] = true;
            LinkedList<Edge> adl = Adj.get(curr);

            for (Edge adn : adl) {
                int dest = adn.dest;
                int alt = adn.cost + dist[curr];

                if (alt < dist[dest] && visited[dest] == false) {
                    dist[dest] = alt;
                    previous[dest] = curr;
                    edge = new Edge(curr, dest, alt);
                    queue.add(edge);
                }
            }
        }
        printPath(previous, dist, count, source);
    }
    public void printPath(int[] previous, int[] dist, int count, int source) {
        String output = "Shortest Paths: ";
        for (int i = 0; i < count; i++) {
            if (dist[i] == 99999)
                output += ("(" + source + " -> " + i + " @ Unreachable) ");
            else if (i != previous[i]) {
                output += "(";
                output += printPathUtil(previous, source, i);
                output += (" @ " + dist[i] + ") ");
            }
        }
        System.out.println(output);
    }
    String printPathUtil(int[] previous, int source, int dest) {
        String path = "";
        if (dest == source)
            path += source;
        else {
            path += printPathUtil(previous, source, previous[dest]);
            path += (" -> " + dest);
        }
        return path;
    }

        public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addUndirectedEdge(0, 1, 1);
        graph.addUndirectedEdge(0, 2, 1);
        graph.addUndirectedEdge(1, 2, 1);
        graph.addUndirectedEdge(2, 3, 1);
        graph.print();
        System.out.println(graph.dfs(0, 3));
        System.out.println(graph.bfs(0, 3));
        System.out.println(graph.isCyclePresentUndirectedDFS(0, -1, new boolean[4]));
        graph.primsMST();
        graph.kruskalMST();
        graph.dijkstra(1);
    }
}