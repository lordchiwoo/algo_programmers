package EMostDistantNode;

import java.util.Map;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] visited;
    public int[] distances;

    Queue < Integer > bfsQueueCurrent;
    Queue < Integer > bfsQueueNext;    
    Queue < Integer > bfsQueueTemp;

    public int solution(int n, int[][] edge)  {
        int answer = 0;
        int distance = 0;

        //Edge 로부터 인접노드 그래프를 생성하고 
        //BFS로 1번에서부터 방문체크를 하면서 깊이를 같이 저장하고
        //큐에 있는애들이 모두 방문되면 distances를 sort해서 최상위 값이 몇개인지 센다?...
        //굳이 세야 되나? distance별로 node Count & Reset을 해도 되겠다.

        return answer;
    }
}
