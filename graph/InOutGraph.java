import java.util.*;

// Directed graph with in and out edges

public class InOutGraph {
    private int vertex;
    private LinkedList<Integer> inEdge[];
    private LinkedList<Integer> outEdge[];

    public InOutGraph(int vertex) {
        this.vertex = vertex;
        inEdge = new LinkedList[vertex];
        outEdge = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++) {
            inEdge[i] = new LinkedList<Integer>();
            outEdge[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int src, int dest) {
        outEdge[src].add(dest);
        inEdge[dest].add(src);
    }

    public void dfs(int v) {
        HashSet<Integer> visited = new HashSet<>();
        dfsHelper(v, visited);

    }

    private void dfsHelper(int v, HashSet<Integer> visited) {
        System.out.printf("%d ", v);
        visited.add(v);

        Iterator<Integer> i = outEdge[v].listIterator();
        while (i.hasNext()) {
            v = i.next();
            if (!visited.contains(v)) {
                dfsHelper(v, visited);
            }
        }
    }

    public void printOutEdges() {
        for (int i = 0; i < vertex; i++) {
            System.out.printf("Vertex %d is connected to:", i);
            for (int j = 0; j < outEdge[i].size(); j++) {
                System.out.printf(" %d", outEdge[i].get(j));
            }
            System.out.println();
        }
    }

    public void printInEdges() {
        for (int i = 0; i < vertex; i++) {
            System.out.printf("Vertex %d in edges:", i);
            for (int j = 0; j < inEdge[i].size(); j++) {
                System.out.printf(" %d", inEdge[i].get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        InOutGraph g = new InOutGraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(2, 0);
        g.addEdge(4, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 4);

        g.dfs(0);
    }
}
