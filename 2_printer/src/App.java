import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1; //첫문서여도 1을 리턴할거니까.
        //사고의 흐름을 적어보자.
        
        // 내 우선순위보다 높은 애는 모두 나보다 먼저 처리됨
        // 내 우선순위보다 바로 윗등급의 문서들이 어떻게 처리되느냐에 따라 
        //내 우선순위레벨 처리 시작시점의 배열의 상태가 변경되어있는 상태가 결정됨.
        
        //내 우선순위보다 높은애들을 카운팅
        //내 우선순위 바로 윗등급 문서의 처리 순서를 확인?...하려면 그 윗등급도 처리해야되나?
        //방법/룰/공식이 있을텐데...
        //O(M*N)으로 처리해야되나? 9부터 8 7 6 5 4 3 2 1? (M=9, N=100)
        
        //첫 순회(N)에서 각 우선순위별로 배열을 생성해서 인덱스를 삽입
        //for 9to1
        //  //내 우선순위이면 현재 배열에서 내 location을 찾아서 그 인덱스를 answer에 더하고 종료
        //  //최고 우선순위의 마지막 인덱스를 기준으로 그것보다 낮은 숫자는 모두 뒤로 보낸다.
        //아니아니아니아니
        
        //int lastProcessedLocation = 100; //도입
        //  lastProcessedLocation //보다 작은 수 중 Max 값/보다 큰 수 중 Min값 이 주요 키가 되겠다.
        //  보다 작은 수 중 Max 값이 다음 우선순위 처리 중 맨 뒤에 있을 값이 되겠지?
        //  보다 큰 수 중 Min값이 다음 우선순위 처리 중 맨 먼저 처리될 값이 되겠지?
        //  내 우선순위에 도달하지 않았으면
        //      보다 작은 수 중 Max 값을 구해서 lastProcessedLocation에 넣고 
        //      Answer에 현재 배열의 길이를 더한 뒤
        //      루프 넘김
        //  내 우선순위에 도달했으면
        //      보다 큰 수 중 Min값 부터 내 loc index까지의 거리를 구해서 
                    //..면 원형 단방향 큐를 쓰는게 좋나?..는 그냥 모듈러 연산? 
                    //내 loc index보다 크면 전체길이 - 해당 loc index + 내 loc index
                    //내 loc index보다 작으면 내 loc index - 해당 loc index
        //      Answer에 더하고
        //      전체 루프 종료
        
        // 하면 될것같음.  Almost O(2N)? 여기까지 30분컷!
            
        //  코딩 시작!
        int maxPriority=-100;
        int myDocPriority = priorities[location];
        int currentPriority, priorityIndex=0;
        Map<Integer,ArrayList<Integer>> priorityMap = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> currentAL;
        
        //첫 순회(N)에서 각 우선순위별로 배열을 생성해서 인덱스를 삽입
        for(priorityIndex = 0 ; priorityIndex < priorities.length ; priorityIndex++){
            //현재 우선 순위를 꺼내서
            currentPriority = priorities[priorityIndex];

            //내 우선 순위보다 낮은 애들은 관심 밖이니까 스킵
            if(myDocPriority > currentPriority) continue;
            //최대 Priority를 구하고
            maxPriority = Math.max(maxPriority, currentPriority);

            //동일 순위가 없었으면 배열을 새로 만들고 있으면 꺼내서
            if(!priorityMap.containsKey(currentPriority))
            {
                currentAL = new ArrayList<Integer>();
                priorityMap.put(currentPriority, currentAL);
            }
            else{
                currentAL = priorityMap.get(currentPriority);
            }
            //해당 우선순위 배열에 인덱스(위치)를 삽입
            currentAL.add(priorityIndex);
        }

        //마지막으로 처리된 인덱스
        int lastProcessedLocation = 100;
        int processedDocumentCount = 0;
        for(currentPriority = maxPriority ; currentPriority >= myDocPriority ; currentPriority--){
            if(!priorityMap.containsKey(currentPriority))
            {
                continue;
            }
            currentAL = priorityMap.get(currentPriority);

            if(currentPriority == myDocPriority)
            {
                // 내 우선순위에 도달하면 내 문서가 배열 안에서 몇번째 처리되는지 계산하고 종료
                processedDocumentCount += this.countMyDocSequence(lastProcessedLocation, location, currentAL);
            }
            else{
                // 아니면 마지막 처리되는 인덱스를 찾고 처리된 도큐먼트 카운팅
                lastProcessedLocation = this.processDocPrinter(lastProcessedLocation, currentAL);
                processedDocumentCount += currentAL.size();
            }
        }

        answer = processedDocumentCount;
        return answer;
    }

    private int countMyDocSequence(int lastProcessedLocation, int myDocLocation, ArrayList<Integer> currentAL) {
        //마지막 처리된 인덱스보다 큰   수중에 최소값 : 맨 처음 처리될 인덱스
        int minAmongBiggerLocation = 100;
        int nDocProcessedB4MyDoc = -1;
        //myDocLocation = currentAL.indexOf(myDocLocation);
        for(int i=0; i < currentAL.size() ; i++)
        {
            int currentLocation = currentAL.get(i);
            if(currentLocation > lastProcessedLocation){
                minAmongBiggerLocation = Math.min(minAmongBiggerLocation, currentLocation);
            }
        }

        int myDocLocationIndex = currentAL.indexOf(myDocLocation);
        if(minAmongBiggerLocation == 100) {
            //100이면 lastProcessedLocation보다 큰 수가 없으므로 맨 앞부터 순서대로 출력한다.
            nDocProcessedB4MyDoc = myDocLocationIndex+1;
        }
        else{
            //lastProcessedLocation보다 큰 수부터 순서대로 출력한다.
            int minAmongBiggerIndex = currentAL.indexOf(minAmongBiggerLocation);//100이면 lastProcessedLocation보다 큰 수가 없으므로 맨 앞부터 순서대로 출력한다.
            
            if(myDocLocationIndex >= minAmongBiggerIndex)
                nDocProcessedB4MyDoc = myDocLocationIndex - minAmongBiggerIndex + 1;
            else{
                nDocProcessedB4MyDoc = myDocLocationIndex + currentAL.size() - minAmongBiggerIndex +1;
            }
        }
        return nDocProcessedB4MyDoc;
    }

    private int processDocPrinter(int lastProcessedLocation, ArrayList<Integer> currentAL) {
        //마지막 처리된 인덱스보다 작은 수중에 최대값 : 다음 lastProcessedLocation
        int maxAmongSmallerLocation = -1;
        for(int i=0; i < currentAL.size() ; i++)
        {
            int currentLocation = currentAL.get(i);
            if(currentLocation < lastProcessedLocation){
                maxAmongSmallerLocation = Math.max(maxAmongSmallerLocation, currentLocation);
            }
        }
        if(maxAmongSmallerLocation == -1) 
            maxAmongSmallerLocation = currentAL.get(currentAL.size()-1);
        return maxAmongSmallerLocation;
    }
}


public class App {
    public static void main(String[] args) throws Exception {
        Solution solved = new Solution();
        int[] priorities = {2, 1, 3, 2};// {2, 2,5,6,7,4,1,8,3,1,3,1,5,7,4,9, 3, 2};
        int location = 2;
        int myJobSequence = solved.solution(priorities, location);

        System.out.println("Hello, World!" + myJobSequence);
    }
}