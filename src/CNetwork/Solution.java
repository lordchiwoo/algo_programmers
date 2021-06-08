package CNetwork;

public class Solution {
    public int[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;

        //visited[0~n]를 0으로 초기화
        visited= new int[n]; 
        for(int mainIndex = 0;mainIndex < n; mainIndex++) //0~n까지 모든 노드를  체크
        {
            // 방문한 컴퓨터는 바로 리턴 0되고 방문되지 않은 컴퓨터는 네트워크를 순회하여 방문처리 하고 1을 리턴
            answer += DFS(mainIndex, n, computers); //네트워크 상의 모든 컴퓨터를 체크 
        }

        return answer;
    }

    private int DFS(int mainIndex, int nComputer, int[][] computers) {
        //연결 상태를 체크한 컴퓨터는 다시 체크하지 않고 넘어간다.
        if(visited[mainIndex]==1) return 0;
        
        visited[mainIndex] = 1;
        for(int networkIndex = 0; networkIndex < nComputer ; networkIndex++)
        {
            //나와 연결된 컴퓨터를 모두 방문한다.
            if(computers[mainIndex][networkIndex]==1)
                DFS(networkIndex, nComputer, computers); //이 때 리턴값은 동일네트워크 안에 있는 것이므로 사용하지 않고 버림.
        }

        //여기까지 왔으면 네트워크는 하나 찾은거임.
        return 1;
    }
}