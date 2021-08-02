package MRescueBoat;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

//디버그 포인트 찍어놓고 실행흐름대로 설명을 해보자.
public class Solution {
    // 최소 비용을 return

    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        
        List<Integer> list = IntStream.of(people).boxed().collect(Collectors.toList());
        ArrayDeque<Integer> peopleDeque = new ArrayDeque<>(list);

        int onBoardWeight = 0;
        while(peopleDeque.size()>0)
        {
            onBoardWeight = peopleDeque.pollLast();
            if(peopleDeque.size()>0 && limit-onBoardWeight >= peopleDeque.peekFirst()){
                peopleDeque.pollFirst();
            }

            answer++;
        }
        
        return answer;
    }
}

/*
정확성  테스트
테스트 1 〉	통과 (15.00ms, 53.7MB)
테스트 2 〉	통과 (17.42ms, 52.7MB)
테스트 3 〉	통과 (7.90ms, 52.9MB)
테스트 4 〉	통과 (8.18ms, 53.6MB)
테스트 5 〉	통과 (9.45ms, 53.2MB)
테스트 6 〉	통과 (13.04ms, 53MB)
테스트 7 〉	통과 (6.42ms, 52.9MB)
테스트 8 〉	통과 (3.65ms, 53MB)
테스트 9 〉	통과 (9.21ms, 51.6MB)
테스트 10 〉	통과 (11.41ms, 53.8MB)
테스트 11 〉	통과 (10.08ms, 53.3MB)
테스트 12 〉	통과 (14.29ms, 53MB)
테스트 13 〉	통과 (11.17ms, 52.3MB)
테스트 14 〉	통과 (9.96ms, 53.5MB)
테스트 15 〉	통과 (7.42ms, 52.7MB)
효율성  테스트
테스트 1 〉	통과 (29.94ms, 57.9MB)
테스트 2 〉	통과 (42.72ms, 56.4MB)
테스트 3 〉	통과 (47.46ms, 57MB)
테스트 4 〉	통과 (34.43ms, 54.5MB)
테스트 5 〉	통과 (37.65ms, 55.1MB)

산당님 풀이
Arrays.sort(people);
int index = 0;
for(int i=people.length-1; i>=index; i--){
    answer++;       
    if(people[i] + people[index] > limit) continue;
    index++;
}
와...난 바본가.
 */

// https://programmers.co.kr/learn/courses/30/lessons/42885
/*
 * 문제 설명 무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다. 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게
 * 제한도 있습니다.
 * 
 * 예를 들어, 사람들의 몸무게가 [70kg, 50kg, 80kg, 50kg]이고 구명보트의 무게 제한이 100kg이라면 2번째 사람과 4번째
 * 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로 구명보트의 무게 제한을 초과하여 같이 탈 수 없습니다.
 * 
 * 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
 * 
 * 사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때, 모든 사람을 구출하기 위해 필요한
 * 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.
 * 
 * 제한사항 무인도에 갇힌 사람은 1명 이상 50,000명 이하입니다. 각 사람의 몸무게는 40kg 이상 240kg 이하입니다. 구명보트의
 * 무게 제한은 40kg 이상 240kg 이하입니다. 구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을
 * 구출할 수 없는 경우는 없습니다. 입출력 예 people limit return [70, 50, 80, 50] 100 3 [70, 80,
 * 50] 100 3
 */

 /*
 초반 삽질의 결과물을 주석으로 남김.

 
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        
        int halfOver = limit/2;
        int halfOverIndex;
        for(halfOverIndex=0;halfOverIndex < people.length;halfOverIndex++){
            if(people[halfOverIndex] > halfOver)
            break;
        }

        int[] lessThanHalf = Arrays.copyOfRange(people, 0, halfOverIndex);
        int[] moreThanHalf = Arrays.copyOfRange(people, halfOverIndex, people.length);
        List<Integer> lessThanHalfList = IntStream.of(lessThanHalf).boxed().collect(Collectors.toList());
        List<Integer> moreThanHalfList = IntStream.of(moreThanHalf).boxed().collect(Collectors.toList());

        int onBoarder=0;
        int boatOnboarderWeight = 0;
        while(lessThanHalfList.size()>0 || moreThanHalfList.size()>0){
            
            if(moreThanHalfList.size()>0){
                boatOnboarderWeight = moreThanHalfList.get(0);
                moreThanHalfList.remove(0);
                onBoarder++;
            }

            int weightResidue = limit - boatOnboarderWeight;
            while(onBoarder<2 && lessThanHalfList.size()>0 && weightResidue>=lessThanHalfList.get(0)){
                weightResidue = pickEqualOrSmaller(lessThanHalfList, weightResidue);
                onBoarder++;
            }
            
            boatOnboarderWeight = 0;
            onBoarder=0;
            answer++;
        }
        return answer;
    }

    // Binary Search
    public int pickEqualOrSmaller(List<Integer> list, int value) {
        int key = smallerOrEqual(list, value);
        if (key == -1)
            return value;
        value = value - list.get(key);
        list.remove(key);

        return value;
    }

    int smallerOrEqual(List<Integer> arr, int target) {
        int idx = Collections.binarySearch(arr, target);
        if (idx < 0) {
            // target not found, so return index prior to insertion point
            return -idx - 2;
        }
        // target found, so skip to last of target value(s)
        while (idx < arr.size() - 1 && arr.get(idx + 1) == target) {
            idx++;
        }
        return idx;
    }
*/
/*
정확성  테스트
테스트 1 〉	통과 (18.37ms, 53.5MB)
테스트 2 〉	통과 (11.80ms, 53.3MB)
테스트 3 〉	통과 (18.76ms, 53MB)
테스트 4 〉	통과 (14.10ms, 53.1MB)
테스트 5 〉	통과 (22.25ms, 52.7MB)
테스트 6 〉	통과 (5.94ms, 53.3MB)
테스트 7 〉	통과 (6.66ms, 53.6MB)
테스트 8 〉	통과 (3.71ms, 53MB)
테스트 9 〉	통과 (7.11ms, 54.1MB)
테스트 10 〉	통과 (14.01ms, 52.5MB)
테스트 11 〉	통과 (10.50ms, 53.1MB)
테스트 12 〉	통과 (7.24ms, 53.1MB)
테스트 13 〉	통과 (13.70ms, 53.3MB)
테스트 14 〉	통과 (10.86ms, 53.4MB)
테스트 15 〉	통과 (12.06ms, 52.7MB)
효율성  테스트
테스트 1 〉	실패 (시간 초과)
테스트 2 〉	실패 (시간 초과)
테스트 3 〉	실패 (시간 초과)
테스트 4 〉	실패 (시간 초과)
테스트 5 〉	실패 (시간 초과)
 */