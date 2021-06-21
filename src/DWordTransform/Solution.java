package DWordTransform;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] visited;

    Queue < Integer > bfsQueueCurrent;
    Queue < Integer > bfsQueueNext;    
    Queue < Integer > bfsQueueTemp;

    public int solution(String begin, String target, String[] words) {

        //거리가 1인애들만으로 인접노드 그래프를 구성한다음에 BFS로 최단거리 탐색을 하면 될 것 같음
        int answer = 0;
        int endIndex;

        visited = new int[words.length];
        bfsQueueCurrent = new LinkedList < > ();
        bfsQueueNext = new LinkedList < > ();

       
        Map < String, Integer > wordMap = new HashMap < String, Integer > ();
        for (int idx = 0; idx < words.length; idx++) {
            wordMap.put(words[idx], idx);
        }

        //타겟이 words 안에 있으면 해당 인덱스를 종료인덱스로 지정
        if (wordMap.containsKey(target)) endIndex = wordMap.get(target);
        else    return answer;

         //그래프를 Integer로 만드는 이유는... 인덱스 그대로 사용하면 편해서?;;
        //거리가 1인애들만으로 인접노드 그래프를 구성(N^2??)
        Map < Integer, LinkedList < Integer >> listOfAdjNodeList = buildListOfAdjNodeList(wordMap);

        //시작 스트링의 인접노드 그래프를 그려서 최초 탐색 후보를 지정한다
        LinkedList < Integer > linkedNodeList = buildAdjNodeList(begin, wordMap);

        listOfAdjNodeList.put(listOfAdjNodeList.size(), linkedNodeList);
        bfsQueueCurrent.add(listOfAdjNodeList.size() - 1);

        answer = 1;

        //최단거리 탐색 with visited
        while (!bfsQueueCurrent.isEmpty()) {
            //인접노드를 꺼내서
            int currentNode = bfsQueueCurrent.poll();
 
            //인접노드의 인접노드들을 가져와서 탐색 시작
            linkedNodeList = listOfAdjNodeList.get(currentNode);
            for (int adjNode: linkedNodeList) {
                //종료인덱스를 찾으면 바로 종료
                System.out.println(adjNode);
                if (adjNode == endIndex)
                    return answer; 
                else {
                    //방문한 노드는 하위노드를 체크할 필요가 없다(이미 큐에 있거나 탐색 실패)
                    if (visited[adjNode] == 0) {
                        visited[adjNode] = 1;
                        bfsQueueNext.offer(adjNode);
                    }
                }
            }

            //End of Queue // 현재 큐를 다꺼내서 썼으면 현재 Depth탐색이 종료되었으므로 다음 뎁스로 넘어간다.
            if (bfsQueueCurrent.isEmpty()) {
                //swap bfsQueueCurrent / bfsQueueNext
                bfsQueueTemp = bfsQueueCurrent; bfsQueueCurrent = bfsQueueNext; bfsQueueNext = bfsQueueTemp; 
                answer++;
            }
        }

        //다 뒤졌는데 못 찾음
        answer = 0;
        return answer;
    }

    public Map < Integer, LinkedList < Integer >> buildListOfAdjNodeList(Map <  String, Integer > wordMap) {
        Map < Integer, LinkedList < Integer >> lisfOfAdjNodeList = new HashMap < Integer, LinkedList < Integer >> ();

        //아... 맵을 string integer로 바꾸니까 키(문자열)로 정렬되어서 lisfOfAdjNodeList 인덱스가 맛탱이가 가네요.
        //각 문자열 별로 인접노드를 탐색해온다.
        for (Map.Entry < String, Integer > elem: wordMap.entrySet())        lisfOfAdjNodeList.put( elem.getValue(), buildAdjNodeList(elem.getKey(), wordMap) );

        return lisfOfAdjNodeList;
    }

    public LinkedList < Integer > buildAdjNodeList(String source, Map <  String, Integer > wordMap) {
        LinkedList < Integer > adjacentNodeList = new LinkedList < Integer > ();

        //각 문자열 별로 거리를 구해서 거리가 1인 인접노드를 찾는다.
        for (Map.Entry <  String, Integer > elem: wordMap.entrySet()) 
            if (1 == this.checkDistance(source, elem.getKey()))     // 거리가 1인 것만 인접노드 리스트에 추가
                adjacentNodeList.add(elem.getValue());;

        return adjacentNodeList;
    }
    public int checkDistance(String sourceStr, String targetStr) {
        int result = 0;
       
        //문제 조건에서 오류는 없을거라고 했으니 방어코드는 제끼고
        // String sourceStr, targetStr;
        //if (source.length() == 0 || target.length() == 0) return Math.max(source.length(), target.length());
        //if (source.length() > target.length()) { sourceStr = source; targetStr = target;}
        //else { sourceStr = target; targetStr = source; }
        //sourceStr = source; targetStr = target;

        for (int i = 0; i < targetStr.length(); i++) {
            if (sourceStr.charAt(i) != targetStr.charAt(i))
                result++;
        }

        return result;
    }
}

/*
테스트 1 〉	통과 (0.54ms, 53.1MB)
테스트 2 〉	통과 (1.65ms, 52.3MB)
테스트 3 〉	통과 (3.25ms, 52.2MB)
테스트 4 〉	통과 (0.49ms, 52.2MB)
테스트 5 〉	통과 (0.10ms, 53.4MB)
*/