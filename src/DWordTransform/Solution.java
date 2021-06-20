package DWordTransform;

import java.util.Map;
import java.util.HashMap;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] visited;

    static Queue < Integer > bfsQueueCurrent;
    static Queue < Integer > bfsQueueNext;

    public int solution(String begin, String target, String[] words) {

        //거리가 1인애들만으로 인접노드 그래프를 구성한다음에 BFS로 최단거리 탐색을 하면 될 것 같음
        int answer = 0;
        int endIndex = -1;

        visited = new int[words.length];
        bfsQueueCurrent = new LinkedList < > ();
        bfsQueueNext = new LinkedList < > ();


        Map < Integer, String > wordMap = new HashMap < Integer, String > ();
        for (int idx = 0; idx < words.length; idx++) {
            wordMap.put(idx, words[idx]);
            if (target.equals(words[idx]))
                endIndex = idx;
        }
        if (endIndex == -1) return answer;

        //거리가 1인애들만으로 인접노드 그래프를 구성(N^2??)
        LinkedList < LinkedList < Integer >> lisfOfAdjNodeList = buildListOfAdjNodeList(wordMap);

        //시작점에서도 인접노드 그래프 그리고
        lisfOfAdjNodeList.add(buildAdjNodeList(begin, wordMap));
        bfsQueueCurrent.add(lisfOfAdjNodeList.size() - 1);

        answer++;

        //최단거리 탐색 with visited
        while (!bfsQueueCurrent.isEmpty()) {
            //check Node has endIndex
            int currentNode = bfsQueueCurrent.poll();

            LinkedList < Integer > linkedNodeList = lisfOfAdjNodeList.get(currentNode);
            for (int adjNode: linkedNodeList) {
                System.out.println(adjNode);
                if (adjNode == endIndex)
                    return answer; // +1??
                else {
                    if (visited[adjNode] == 0) {
                        visited[adjNode] = 1;
                        bfsQueueNext.offer(adjNode);
                    }
                }
            }

            //End of Queue
            //swap bfsQueueCurrent / bfsQueueNext
            if (bfsQueueCurrent.isEmpty()) {
                Queue < Integer > bfsQueueTemp = bfsQueueCurrent;
                bfsQueueCurrent = bfsQueueNext;
                bfsQueueNext = bfsQueueTemp;

                answer++;
            }
        }

        //다 뒤졌는데 못 찾음
        return 0;
    }

    public LinkedList < LinkedList < Integer >> buildListOfAdjNodeList(Map < Integer, String > wordMap) {
        LinkedList < LinkedList < Integer >> lisfOfAdjNodeList = new LinkedList < LinkedList < Integer >> ();

        for (Map.Entry < Integer, String > elem: wordMap.entrySet())
            lisfOfAdjNodeList.add(
                buildAdjNodeList(elem.getValue(), wordMap)
            );

        return lisfOfAdjNodeList;
    }

    public LinkedList < Integer > buildAdjNodeList(String source, Map < Integer, String > wordMap) {
        LinkedList < Integer > adjacentNodeList = new LinkedList < Integer > ();
        for (Map.Entry < Integer, String > elem: wordMap.entrySet()) {
            String adjNodeString = elem.getValue();
            if (source != adjNodeString)
                if (1 == this.checkDistance(source, adjNodeString))
                    adjacentNodeList.add(elem.getKey());;
        }

        return adjacentNodeList;
    }
    public int checkDistance(String source, String target) {
        int result = 0;
        String longStr, shortStr;

        //if (source.length() == 0 || target.length() == 0) return Math.max(source.length(), target.length());
        //if (source.length() > target.length()) { longStr = source; shortStr = target;}
        //else { longStr = target; shortStr = source; }


        longStr = source;
        shortStr = target;

        for (int i = 0; i < shortStr.length(); i++) {
            if (longStr.charAt(i) != shortStr.charAt(i))
                result++;
        }

        return result;
    }
}