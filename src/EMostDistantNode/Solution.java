package EMostDistantNode;

import java.util.Map;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//https://programmers.co.kr/learn/courses/30/lessons/49189
/*
문제 설명
n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 
1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 
가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.

노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 
1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.

제한사항
노드의 개수 n은 2 이상 20,000 이하입니다.
간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
입출력 예
n	vertex	return
6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
입출력 예 설명
예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.
*/
public class Solution {
    public int[] visited;

    Queue < Integer > bfsQueueCurrent;
    Queue < Integer > bfsQueueNext;

    public int solution(int n, int[][] edge) {
        //BFS로 1번에서부터 방문체크를 하면서 깊이를 같이 저장하고
        //큐에 있는애들이 모두 방문되면 distances를 sort해서 최상위 값이 몇개인지 센다?...
        //굳이 세야 되나? distance별로 node Count & Reset을 해도 되겠다.

        int answer = 0;
        int distanceNodeCount = 0;

        //Edge 로부터 인접노드 그래프를 생성하고 
        HashMap < Integer, LinkedList < Integer >> mapOfAdjNodeList = buildMapOfAdjNodeList(edge);
        LinkedList < Integer > linkedNodeList;
        Stack < Integer > distanceStack = new Stack < Integer > ();

        visited = new int[mapOfAdjNodeList.size() + 1];

        bfsQueueCurrent = new LinkedList < > ();
        bfsQueueNext = new LinkedList < > ();

        Integer firstNode = Integer.valueOf(1);
        bfsQueueCurrent.offer(firstNode);
        //최초 노드도 방문했다고 처리 안해주면 맛탱이가 갑니다. ㅠㅠ
        visited[firstNode] = 1;

        while (!bfsQueueCurrent.isEmpty()) {
            //인접노드를 꺼내서
            int currentNode = bfsQueueCurrent.poll();

            //인접노드의 인접노드들을 가져와서 탐색 시작
            linkedNodeList = mapOfAdjNodeList.get(currentNode);
            for (int adjNode: linkedNodeList) {
                //방문하지 않은 인접노드를 추가하고 등거리 노드 카운트 증가
                if (visited[adjNode] == 0) {
                    visited[adjNode] = 1;
                    bfsQueueNext.offer(adjNode);
                    distanceNodeCount++;
                }
            }

            //End of Queue // 현재 큐를 다꺼내서 썼으면 현재 Depth탐색이 종료되었으므로 다음 뎁스로 넘어간다.
            if (bfsQueueCurrent.isEmpty()) {
                bfsQueueCurrent = bfsQueueNext;
                bfsQueueNext = new LinkedList < > ();

                //그 다음이 없으면 종료해야되므로 현재 노드 카운트 저장 /  0 이면 더이상 갈곳이 없으므로 저장하지 않는다.
                if (distanceNodeCount > 0)  distanceStack.push(distanceNodeCount);

                //리셋
                distanceNodeCount = 0;
            }
        }

        //연결된 노드가 있었고 카운팅을 했으면 스택에서 꺼내오고 아니면 0을 리턴
        if (!distanceStack.isEmpty()) answer = distanceStack.pop();
        return answer;
    }

    public HashMap < Integer, LinkedList < Integer >> buildMapOfAdjNodeList(int[][] edges) {
        HashMap < Integer, LinkedList < Integer >> mapOfAdjNodeList = new HashMap < Integer, LinkedList < Integer >> ();

        //엣지로 주어진 두개의 노드에 각각 상대 노드를 인접노드로 추가해준다.
        for (int[] nodeEdges: edges) {
            LinkedList < Integer > edgeList;
            for(int i=0;i<2;i++){
                //내가 메인노드 니가 서브노드
                int mainNode = nodeEdges[i];
                int subNode = nodeEdges[ i==1 ? 0 : 1 ];

                //내 노드의 인접노드 리스트가 없으면 생성해준다.
                if (!mapOfAdjNodeList.containsKey(mainNode)) {
                    edgeList = new LinkedList < Integer > ();
                    mapOfAdjNodeList.put(mainNode, edgeList);
                }

                //내 노드의 인접노드 리스트를 가지고 와서 서브노드를 집어 넣는다.
                edgeList = mapOfAdjNodeList.get(mainNode);
                edgeList.add(subNode);
            }
        }

        return mapOfAdjNodeList;
    }

}

/*
테스트 1 〉	통과 (0.50ms, 51.5MB)
테스트 2 〉	통과 (0.61ms, 52.3MB)
테스트 3 〉	통과 (0.69ms, 52.3MB)
테스트 4 〉	통과 (2.09ms, 52.4MB)
테스트 5 〉	통과 (8.68ms, 56MB)
테스트 6 〉	통과 (12.20ms, 54.6MB)
테스트 7 〉	통과 (92.65ms, 77.6MB)
테스트 8 〉	통과 (82.33ms, 78.6MB)
테스트 9 〉	통과 (92.16ms, 74.5MB)
*/