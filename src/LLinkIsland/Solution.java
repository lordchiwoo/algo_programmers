package LLinkIsland;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//디버그 포인트 찍어놓고 실행흐름대로 설명을 해보자.
public class Solution {
    // 최소 비용을 return
    // Map을 만들어놓고 비용 Sort?
    // 연결된 섬 리스트를 만들고
    // 연결된 섬 그룹 리스트를 만들고
    // costs를 [2]기준으로 정렬하고
    // 상위 코스트부터 사용, 연결된 섬은 리스트에 추가. 연결된 섬 그룹 리스트 수정
    // Success?
    // 그룹 분리가 발생할 수 있으므로 그룹이 하나될때까지 하도록 조건 추가

    List<Integer> linkedIslandList;
    HashSet<List<Integer>> linkedIslandGroupSet;

    Map<Integer, Integer> linkedIslandMap;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean useCost = false;

        Arrays.sort(costs, (o1, o2) -> {
            return Integer.compare(o1[2], o2[2]);
        });
        linkedIslandMap = new HashMap<>();
        
        for (int[] cost : costs) {
            useCost = false;
            boolean[] isIslandLinked = { linkedIslandMap.get(cost[0])!=null, linkedIslandMap.get(cost[1])!=null };

            if (isIslandLinked[0] && isIslandLinked[1]) {
                //분리되어있으면 합쳐줘야한다.
                useCost = checkAndMergeIslandMap(cost);
            } else {
                useCost = true;
                addIslandToMap(cost, isIslandLinked);
            }

            // 연결을 사용했으면 비용추가
            if (useCost) answer += cost[2];
        }
        return answer;
    }

    private boolean checkAndMergeIslandMap(int[] cost) {
        int[] linkedIslandTopIndex = {0,0};
        for (int i = 0; i < 2; i++) {
            linkedIslandTopIndex[i] = findTopIsland(cost[i]);
        }

        // 같은 그룹에 있는지 확인하고
        if (linkedIslandTopIndex[0]==linkedIslandTopIndex[1]) {
            // 같은 그룹이면 스킵한다.
            return false;
        } else {
            for(Entry<Integer,Integer> changeTarget : linkedIslandMap.entrySet()){
                if(changeTarget.getValue() == linkedIslandTopIndex[1]){
                    changeTarget.setValue(linkedIslandTopIndex[0]);
                }
            }
            return true;
        }
    }

    private void addIslandToMap(int[] cost, boolean[] isIslandLinked) {
        // 케이스 : 한쪽만 올라가 있으면 상대방의 탑인덱스에 내 인덱스를 연결
        // 둘다 없으면 한쪽을 탑인덱스로 등록하고 반대쪽을 등록(자기 자신을 가리키게 됨)
        for (int i = 0; i < 2; i++) {
            //Top이 없으면 자기 자신이 Top Index
            int linkedIslandTopIndex = findTopIsland(cost[1-i]);
            if (isIslandLinked[i] == false) {
                linkedIslandMap.put(cost[i],linkedIslandTopIndex);
            }
        }
    }

    private int findTopIsland(int islandIndex){
        Integer returnIslandIndex = linkedIslandMap.get(islandIndex);
        while(returnIslandIndex!=null && returnIslandIndex!=islandIndex){
            islandIndex = returnIslandIndex;
            returnIslandIndex = linkedIslandMap.get(returnIslandIndex);
        }
        return returnIslandIndex!=null?returnIslandIndex:islandIndex;
    }

    public int solution2(int n, int[][] costs) {
        int answer = 0;
        boolean useCost = false;

        Arrays.sort(costs, (o1, o2) -> {
            return Integer.compare(o1[2], o2[2]);
        });
        linkedIslandList = new ArrayList<>();
        linkedIslandGroupSet = new HashSet<>();
        
        for (int[] cost : costs) {
            useCost = false;
            boolean[] isIslandLinked = { linkedIslandList.contains(cost[0]), linkedIslandList.contains(cost[1]) };

            if (isIslandLinked[0] && isIslandLinked[1]) {
                //분리되어있으면 합쳐줘야한다.
                useCost = checkAndMergeDifferentIslandGroup(cost);
            } else {
                useCost = true;
                addIslandToLinkedGroup(cost, isIslandLinked);
            }

            // 연결을 사용했으면 비용추가
            if (useCost) answer += cost[2];

            // 모두 연결되었고 하나로 연결되었으면 모든 섬이 서로 통행가능
            if (linkedIslandList.size() == n && linkedIslandGroupSet.size() == 1) break;
        }
        return answer;
    }

    private void addIslandToLinkedGroup(int[] cost, boolean[] isIslandLinked) {
        // 케이스 : 한쪽만 올라가 있으면 한쪽에 집어넣고 끝 
        // 둘다 없으면 새로 생성해서 집어넣고 linkedIslandGroupSet에 추가
        for (int i = 0; i < 2; i++) {
            int linkedIslandIndex = 1 - i;
            if (isIslandLinked[i] == false) {
                List<Integer> tempLinkedIslandGroup = getLinkedIslandGroup(cost[linkedIslandIndex]);
                

                linkedIslandGroupSet.add(tempLinkedIslandGroup);
                linkedIslandList.add(cost[i]);
                tempLinkedIslandGroup.add(cost[i]);
            }
        }
    }

    private boolean checkAndMergeDifferentIslandGroup(int[] cost) {
        // 같은 그룹에 있는지 확인하고
        List<Integer> tempLinkedIslandGroup_for0 = getLinkedIslandGroup(cost[0]);
        List<Integer> tempLinkedIslandGroup_for1 = getLinkedIslandGroup(cost[1]);

        if (tempLinkedIslandGroup_for0.equals(tempLinkedIslandGroup_for1)) {
            // 같은 그룹이면 스킵한다.
            return false;
        } else {
            // 없으면 연결하고 두 그룹을 합친다.
            tempLinkedIslandGroup_for0.addAll(tempLinkedIslandGroup_for1);
            linkedIslandGroupSet.remove(tempLinkedIslandGroup_for1);
            return true;
        }
    }
    
    private List<Integer> getLinkedIslandGroup(int i) {
        for (List<Integer> linkedIslandGroup : linkedIslandGroupSet) 
            if (linkedIslandGroup.contains(i))
                return linkedIslandGroup;
                
        // 연결이 안되어있으면 새로 생성해준다
        return new ArrayList<>();
    }
}

/*
테스트 1 〉	통과 (0.71ms, 52.1MB)
테스트 2 〉	통과 (0.70ms, 52.4MB)
테스트 3 〉	통과 (0.77ms, 52.4MB)
테스트 4 〉	통과 (0.91ms, 52.7MB)
테스트 5 〉	통과 (0.75ms, 51.8MB)
테스트 6 〉	통과 (0.97ms, 52.4MB)
테스트 7 〉	통과 (1.16ms, 53.1MB)
테스트 8 〉	통과 (0.67ms, 52.7MB)
 */

// https://programmers.co.kr/learn/courses/30/lessons/42861
/*
 * 문제 설명 n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때
 * 필요한 최소 비용을 return 하도록 solution을 완성하세요.
 * 
 * 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다. 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬
 * 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
 * 
 * 제한사항
 * 
 * 섬의 개수 n은 1 이상 100 이하입니다. costs의 길이는 ((n-1) * n) / 2이하입니다. 임의의 i에 대해,
 * costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을
 * 연결하는 다리를 건설할 때 드는 비용입니다. 같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과
 * 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다. 모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이
 * 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다. 연결할 수 없는 섬은 주어지지 않습니다. 입출력 예
 * 
 * n costs return 4 [[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]] 4 입출력 예 설명
 * 
 * costs를 그림으로 표현하면 다음과 같으며, 이때 초록색 경로로 연결하는 것이 가장 적은 비용으로 모두를 통행할 수 있도록 만드는
 * 방법입니다.
 */