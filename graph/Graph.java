import java.util.*;

// undirected graph traversal with bfs and dfs traversal

public class Graph {
    private int vertex;
    private ArrayList<Integer> list[];

    public Graph(int vertex) {
        this.vertex = vertex;
        list = new ArrayList[vertex];
        for (int i = 0; i < vertex; i++) {
            list[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int src, int dest) {
        list[src].add(dest);
        list[dest].add(src); // for undirected graph
    }

    public void printGraph() {
        for (int i = 0; i < vertex; i++) {
            if (list[i].size() > 0) {
                System.out.printf("vertex %d is connected to:", i);
                for (int j = 0; j < list[i].size(); j++) {
                    System.out.printf(" %d", list[i].get(j));
                }
                System.out.println();
            }
        }
    }

    public void dfs(int v) {
        HashSet<Integer> visited = new HashSet<>();
        dfsHelper(v, visited);

    }

    private void dfsHelper(int v, HashSet<Integer> visited) {
        System.out.printf("%d ", v);
        visited.add(v);

        Iterator<Integer> i = list[v].listIterator();
        while (i.hasNext()) {
            v = i.next();
            if (!visited.contains(v)) {
                dfsHelper(v, visited);
            }
        }
    }

    public void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(v);
        visited.add(v);

        while (q.size() != 0) {
            v = q.poll();
            System.out.printf("%d ", v);
            Iterator<Integer> i = list[v].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited.contains(n)) {
                    visited.add(n);
                    q.add(n);
                }
            }
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);

        g.bfs(0);
    }
}