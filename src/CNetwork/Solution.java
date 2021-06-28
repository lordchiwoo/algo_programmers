package CNetwork;
//https://programmers.co.kr/learn/courses/30/lessons/43162
/*
문제 설명
네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 
예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 
컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 
컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 
따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.

컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.

제한사항
컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
computer[i][i]는 항상 1입니다.
입출력 예
n	computers	                     return
3	[[1, 1, 0], [1, 1, 0], [0, 0, 1]]	2
3	[[1, 1, 0], [1, 1, 1], [0, 1, 1]]	1
*/
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