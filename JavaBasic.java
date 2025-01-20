import java.util.*;

public class JavaBasic {
    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }

    public static void bfs(ArrayList<Edge> graph[]) {
        boolean track[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!track[i])
                bfsUtils(graph, track);
        }

    }

    public static void bfsUtils(ArrayList<Edge> graph[], boolean track[]) {
        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (q.isEmpty()) {
            int number = q.remove();
            if (!track[number]) {
                track[number] = true;
                System.out.println(number);
                for (int i = 0; i < graph[number].size(); i++) {
                    Edge ee = graph[number].get(i);
                    q.add(ee.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            dfsUtil(graph, i, vis);
        }
    }

    public static void dfsUtil(ArrayList<Edge> graph[], int curr, boolean vis[]) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[curr]) {
                dfsUtil(graph, e.dest, vis);
            }
        }
    }

    public static boolean hasPath(ArrayList<Edge> graph[], boolean visit[], int src, int dist) {
        if (src == dist) {
            return true;
        }
        // ----- or -------- in for loop
        // if (ee.dest == dist) {
        // return true;
        // }
        visit[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            Edge ee = graph[src].get(i);
            if (ee.dest == dist) {
                return true;
            }
            if (!visit[ee.dest]) {
                boolean check = hasPath(graph, visit, ee.dest, dist);
                if (check) {
                    return check;
                }
            }
        }
        return false;
    }

    public static boolean detectCycle(ArrayList<Edge> graph[]) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (detectCycleUtils(graph, vis, i, -1))
                    return true;
            }
        }
        return false;
    }

    public static boolean detectCycleUtils(ArrayList<Edge>[] graph, boolean vis[], int curr, int par) {
        vis[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge ee = graph[curr].get(i);
            if (!vis[ee.dest] && detectCycleUtils(graph, vis, ee.dest, curr)) {
                return true;
            } else if (vis[ee.dest] && ee.dest != par) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) {
        int col[] = new int[graph.length];
        for (int i = 0; i < col.length; i++) {
            col[i] = -1;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (col[i] == -1) {
                q.add(i);
                col[i] = 0;
                while (!q.isEmpty()) {
                    int curr = q.remove();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(i);
                        if (col[e.dest] == -1) {
                            int nextCol = col[curr] == 0 ? 1 : 0;
                            col[e.dest] = nextCol;
                            q.add(e.dest);
                        } else if (col[e.dest] == col[curr]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isCycle(ArrayList<Edge> graph[]) {
        boolean vis[] = new boolean[graph.length];
        boolean stack[] = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                if (isCycleUtils(graph, i, vis, stack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycleUtils(ArrayList<Edge>[] graph, int curr, boolean vis[], boolean stack[]) {
        vis[curr] = true;
        stack[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (stack[e.dest]) {
                return true;
            }
            if (!vis[e.dest] && isCycleUtils(graph, e.dest, vis, stack)) {
                return true;
            }
        }
        stack[curr] = false;
        return false;
    }

    public static void topSort(ArrayList<Edge> graph[]) {
        boolean vis[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < graph.length; i++) {
            if (!vis[i]) {
                topSortUtils(graph, i, vis, s);
            }
        }
        while (s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
    public static void topSortUtils(ArrayList<Edge> graph[], int curr, boolean vis[], Stack<Integer> s) {
        vis[curr] = true;
        for(int i=0;i<graph.length;i++){
            Edge e=graph[curr].get(i);
            if(!vis[i]){
                topSortUtils(graph, e.dest, vis, s);
            }
        }
        s.push(curr);
    }

    public static void calcIndeg(ArrayList<Edge> graph[], int indeg[]){
        for(int i=0;i<graph.length;i++){
            int v=1;
            for(int j=0;j<graph[v].size();j++){
                Edge e =graph[v].get(j);
                indeg[e.dest]++;
            }
        }
    }
    public static void topSortBfs(ArrayList<Edge> graph[]){
        int indeg[]=new int[graph.length];
        Queue<Integer> q =new LinkedList<>();

        for(int i=0;i<indeg.length;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr=q.remove();
            System.out.println(curr+"");
            for(int i=0;i<graph[curr].size();i++){
                Edge e=graph[curr].get(i);
                indeg[e.dest]--;
                if(indeg[e.dest]==0){
                  q.add(e.dest);
                }
            }
        }
        System.out.println("");
    }
    public static void main(String[] args) {
        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[7];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));
        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));
        graph[2].add(new Edge(2, 4, 1));
        graph[2].add(new Edge(2, 0, 1));
        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 5, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 6, 1));
        graph[6].add(new Edge(6, 5, 1));
        boolean visit[] = new boolean[7];
        hasPath(graph, visit, 0, 5);
    }
}
