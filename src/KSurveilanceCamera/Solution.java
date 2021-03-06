package KSurveilanceCamera;

import java.util.Arrays;

//디버그 포인트 찍어놓고 실행흐름대로 설명을 해보자.
public class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, 
            (o1, o2) -> {return Integer.compare(o1[1], o2[1]);}
        );

        int nextExitTime = -30000;
        int deployedCamera = 0;
        for(int[] route : routes){
            if(route[0] >nextExitTime){
                deployedCamera++;  
                nextExitTime = 30001;
            }    
            nextExitTime = Math.min(nextExitTime,route[1]);  
        }

        return deployedCamera;
    }
    //경로 입력 - 차량 입력
    // 추가 경로 입력시 중복 구간 확인 차량 후  차량 리스트 추가
    // 중복 구간 확인을 어떻게 해야되나?
    // linkedList 로 시작점/끝점을 정의하고
    // 잘라나간다.
    
    // 다 입력한 뒤에 
    // 차량 댓수로 sort하고
    //상위 댓수 위치 구간 어딘가에 카메라를 설치했다 치면
    // 이외 구간에서 해당 구간 차량을 모두 지우고 다시 sort / 설치 반복 Till 차량이 없어질때까지.
    // 그러면 Happy?... 

    // 이게 탐욕법 카테고리에 있으니까 포문 한큐?
    // 시작지점 기준으로 routes를 sort 하고
    // for문을 돌면서 현재리스트를 들고가다가 local maximum -1이 되기 전에 (즉 최초로 차량이 빠져나가는 시점 전에) 카메라를 deploy
    // deploy 한 시점에서 카메라에 찍힌 차량은 모두 제거. (는 초기화네.)
}

/*
정확성  테스트
테스트 1 〉   통과 (0.70ms, 51.8MB)
테스트 2 〉   통과 (0.78ms, 54.3MB)
테스트 3 〉   통과 (0.85ms, 53MB)
테스트 4 〉   통과 (0.84ms, 52.3MB)
테스트 5 〉   통과 (2.00ms, 52.5MB)
효율성  테스트
테스트 1 〉   통과 (7.44ms, 53.3MB)
테스트 2 〉   통과 (4.85ms, 52.8MB)
테스트 3 〉   통과 (10.40ms, 57.5MB)
테스트 4 〉   통과 (1.19ms, 54.3MB)
테스트 5 〉   통과 (8.33ms, 57MB)
 */

 
//https://programmers.co.kr/learn/courses/30/lessons/42884
/*
문제 설명
고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.

고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.

제한사항

차량의 대수는 1대 이상 10,000대 이하입니다.
routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점, routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.
입출력 예

routes	return
[[-20,15], [-14,-5], [-18,-13], [-5,-3]]	2
입출력 예 설명

-5 지점에 카메라를 설치하면 두 번째, 네 번째 차량이 카메라를 만납니다.

-15 지점에 카메라를 설치하면 첫 번째, 세 번째 차량이 카메라를 만납니다.
*/