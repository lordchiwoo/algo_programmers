package DWordTransform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int[] visited;

    static Queue<Integer> bfsQueueCurrent;
    static Queue<Integer> bfsQueueNext;
    static Queue<Integer> bfsQueueTemp;

    public int solution(String begin, String target, String[] words){

        //거리가 1인애들만으로 인접노드 그래프를 구성한다음에 BFS로 최단거리 탐색을 하면 될 것 같음
        int answer = 0;
        bfsQueueCurrent = new LinkedList<>(); bfsQueueNext = new LinkedList<>();
        List<String> wordList =  Arrays.asList(words);

        //TODO 거리가 1인애들만으로 인접노드 그래프를 구성(N^2??)
        ArrayList<ArrayList<Integer>> length1GraphNode = new ArrayList<ArrayList<Integer>>();

        //시작/끝 노드 인덱스를 찾고
        int startIndex = wordList.indexOf(begin);
        int endIndex = wordList.indexOf(target);
        bfsQueueCurrent.offer(startIndex);

        //최단거리 탐색 with visited
        while( ! bfsQueueCurrent.isEmpty() )
        {
            //check Node has endIndex
            int currentNode = bfsQueueCurrent.poll();

            ArrayList<Integer> linkedNodeList = length1GraphNode.get(currentNode);
            if(linkedNodeList.indexOf(endIndex) != -1)
                return answer;  // +1??
            else{
                linkedNodeList.forEach(     (linkedNode)    ->  bfsQueueNext.offer(linkedNode)  );
            }
            //End of Queue
            //swap bfsQueueCurrent / bfsQueueNext
            if(bfsQueueCurrent.isEmpty())
            {
                bfsQueueTemp = bfsQueueCurrent; 
                bfsQueueCurrent = bfsQueueNext; 
                bfsQueueNext = bfsQueueTemp; 
            }
        }

        //다 뒤졌는데 못 찾음
        return 0;
    }
}