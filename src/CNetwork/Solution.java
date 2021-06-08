package CNetwork;

public class Solution {
    public int[] visited;
    public int nComputer;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        nComputer = n;

        //visited[0~n]를 0으로 초기화
        visited= new int[n]; 
        for(int mainIndex = 0;mainIndex < n; mainIndex++) //0~n까지 모든 노드를  체크
        {
            answer += DFS(mainIndex, computers);
        }


        return answer;
    }

    private int DFS(int mainIndex, int[][] computers) {
        //연결 상태를 체크한 컴퓨터는 다시 체크하지 않고 넘어간다.
        if(visited[mainIndex]==1) return 0;
        
        visited[mainIndex] = 1;
        for(int networkIndex = 0; networkIndex < nComputer ; networkIndex++)
        {
            if(computers[mainIndex][networkIndex]==1)
                DFS(networkIndex, computers);
        }
        return 1;
    }
}