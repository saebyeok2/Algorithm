/*
    백준 1916번: 최소비용 구하기
    알고리즘 분류: #그래프 이론 #다익스트라
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int node;
    int weight;

    Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
    // 노드를 가중치 기준으로 정렬하기 위한 오버라이딩
    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }

}

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Node>> list; // 인접리스트
    static int[] dist; // 시작점에서 각 정점으로 가는 최단거리를 저장할 배열
    static boolean[] visited; // 방문 확인

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        // 최단거리 배열의 초기값을 int의 최대값으로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        // 단방향 인접 리스트 구현
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // start에서 end로 가는 weight (가중치)
            list.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(startNode, endNode));
    }

    // 다익스트라 알고리즘 (우선순위 큐 활용)
    public static int dijkstra(int start, int end) {
        // 정점과 시작점에서 정점까지 가는 최단거리를 저장. 우선순위는 거리가 짧을수록 높음
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 노드 설정 (해당 노드를 기준으로 모든 노드까지의 최단거리를 계산)
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        // 우선순위 큐가 비었으면 다익스트라 알고리즘 종료
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.node;
            // 방문하지 않은 노드라면 방문 체크 후, 인접 노드를 살펴봄
            if (!visited[cur]) {
                visited[cur] = true;
                // 현재 노드(cur)를 기준으로 갈 수 있는(간선이 존재하는) 모든 노드를 순회
                for (Node node : list.get(cur)) {
                    // 현재 노드(cur)를 거쳐 해당 노드로 가는 경우가 기존의 최단거리보다 짧다면 최단거리를 갱신
                    if (dist[node.node] > dist[cur] + node.weight) {
                        dist[node.node] = dist[cur] + node.weight;
                        // 해당 노드를 우선순위 큐에 삽입
                        pq.add(new Node(node.node, dist[node.node]));
                    }
                }
            }
        }
        // start에서 end로 가는 최단거리를 리턴
        return dist[end];
    }
}