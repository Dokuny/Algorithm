import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 다익스트라 알고리즘 (P[A][B]는 A와 B 사이의 거리라고 가정한다)
 * 1. 출발점으로부터의 최단거리를 저장할 배열 d[v]를 만들고, 출발 노드에는 0을, 출발점을 제외한 다른 노드들에는 매우 큰 값 INF를 채워 넣는다.
 * 2. 현재 노드를 나타내는 변수 A에 출발 노드의 번호를 저장한다.
 * 3. A로부터 갈 수 있는 임의의 노드 B에 대해, d[A] + P[A][B][8]와 d[B][9]의 값을 비교한다.
 * 4. 만약 d[A] + P[A][B]의 값이 더 작다면, 즉 더 짧은 경로라면, d[B]의 값을 이 값으로 갱신시킨다.
 * 5. A의 모든 이웃 노드 B에 대해 이 작업을 수행한다.
 * 6. A의 상태를 "방문 완료"로 바꾼다. 그러면 이제 더 이상 A는 사용하지 않는다.
 * 7. "미방문" 상태인 모든 노드들 중, 출발점으로부터의 거리가 제일 짧은 노드 하나를 골라서 그 노드를 A에 저장한다.
 * 8. 도착 노드가 "방문 완료" 상태가 되거나, 혹은 더 이상 미방문 상태의 노드를 선택할 수 없을 때까지, 3~7의 과정을 반복한다.
 * 9. 이 작업을 마친 뒤, 도착 노드에 저장된 값이 바로 A로부터의 최단 거리이다. 만약 이 값이 INF라면, 중간에 길이 끊긴 것임을 의미한다.
 */

public class Dijkstra {
    static class Node{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 기본 구현 방법
    public static void dijkstra1(int v, int[][] data, int start) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        // 그래프 작성
        // 0번 인덱스는 사용 X
        for (int i = 0; i < v+1 ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < data.length; i++) {
            // 그래프를 구성할 때 단방향이면 한쪽 만 그래프에 추가.
            graph.get(data[i][0]).add(new Node(data[i][1], data[i][2]));
            // 양방향이면 양쪽 다 그래프에 추가.
            graph.get(data[i][1]).add(new Node(data[i][0], data[i][2]));
        }

        // 1번 수행
        // 최단경로 저장할 배열 생성
        int[] dist = new int[v + 1];

        for (int i = 0; i < v + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // 2번 수행
        // 시작점의 최단경로를 0으로
        dist[start] = 0;
        int curIdx = start;

        boolean[] visited = new boolean[v + 1];

        for (int i = 0; i < v; i++) {
            // 3번 ,4번,5번 수행 (인접노드들 가중치 비교)
            for (int j = 0; j < graph.get(curIdx).size(); j++) {
                Node adjNode = graph.get(curIdx).get(j);

               if (dist[adjNode.to] > dist[curIdx] + adjNode.weight) {
                    dist[adjNode.to] = dist[curIdx] + adjNode.weight;
                }
            }
            // 6번 수행 (방문 표시)
            visited[curIdx] = true;

            int minDist = Integer.MAX_VALUE;
            // 7번 수행
            // 방문한 적이 없는 가중치가 가장 작은 노드가 걸러져서 나온다. (다음 노드 찾기)
            for (int j = 1; j < v+1; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    curIdx = j;
                }
            }
        }

        for (int i = 1; i < v + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
    }

    // 우선 순위 큐 이용
    public static void dijkstra2(int v, int[][] data, int start) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        for (int i = 0; i < v+1 ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < data.length; i++) {
            graph.get(data[i][0]).add(new Node(data[i][1], data[i][2]));
            graph.get(data[i][1]).add(new Node(data[i][0], data[i][2]));
        }

        int[] dist = new int[v + 1];

        for (int i = 0; i < v + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<Node>(Comparator.comparingInt(node->node.weight));
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (dist[curNode.to] < curNode.weight) {
                continue;
            }

            for (int i = 0; i < graph.get(curNode.to).size(); i++) {
                Node adjNode = graph.get(curNode.to).get(i);

                if (dist[adjNode.to] > curNode.weight + adjNode.weight) {
                    dist[adjNode.to] = curNode.weight + adjNode.weight;
                    queue.offer(new Node(adjNode.to, dist[adjNode.to]));
                }
            }

        }

        for (int i = 1; i < v + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {{1, 2, 2}, {1, 3, 3}, {2, 3, 4}, {2, 4, 5}, {3, 4, 6}, {5, 1, 1}};
        dijkstra1(5, data, 1);
        System.out.println();
        System.out.println("===============");
        dijkstra2(5, data, 1);
    }
}
