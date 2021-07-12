package HNumberOfRooms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://programmers.co.kr/learn/courses/30/lessons/49190
/*
문제 설명
원점(0,0)에서 시작해서 아래처럼 숫자가 적힌 방향으로 이동하며 선을 긋습니다.

스크린샷 2018-09-06 오후 4.55.33.png

ex) 1일때는 오른쪽 위로 이동

그림을 그릴 때, 사방이 막히면 방하나로 샙니다.
이동하는 방향이 담긴 배열 arrows가 매개변수로 주어질 때, 방의 갯수를 return 하도록 solution 함수를 작성하세요.

제한사항
배열 arrows의 크기는 1 이상 100,000 이하 입니다.
arrows의 원소는 0 이상 7 이하 입니다.
방은 다른 방으로 둘러 싸여질 수 있습니다.
입출력 예
arrows	return
[6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0]	3
입출력 예 설명
스크린샷 2018-09-06 오후 5.08.09.png

(0,0) 부터 시작해서 6(왼쪽) 으로 3번 이동합니다. 그 이후 주어진 arrows 를 따라 그립니다.
삼각형 (1), 큰 사각형(1), 평행사변형(1) = 3

0 :  0,-1
1 : 1,-1
2: : 1,0
3 : 1,1
4 : 0,1
5 : -1,1
6 : -1,0
7 : -1,-1

Initial Thought : 이동하면서 기존에 점이 찍힌 지점을 지나가면 폐Loop가 생성된다.(방갯수 +1)
점이 찍힌 점을 지나갈때 동일한 방향에서 왔으면(이미 그은 선이면) 방의 갯수는 변동이 없다.

중복된 큰 방은 없다고 했을때
1. 현재위치에 점을 찍는다.
2. 주어진 방향(d)으로 이동한다

를 기본 동작으로

1-0 다음 위치에 점이 있는지 확인한다.
1-0-1 점이 없으면 패스
1-0-2 점이 있으면 directionList에서 d와 같은 기록이 있는지 확인하고
1-0-2-1 같은 기록이 있으면 방의 갯수는 변화가 없다(그린 선을 다시 그림)
1-0-2-2 같은 기록이 없으면 새로운 폐Loop가 생성 된 것이므로 방의 갯수 +1 한다.

1-1-0 directionList 에 d를 추가한다.(중복 제거?)

    테스트 케이스 통과 오류로 인한 추가작업1
선이 그어진 기록을 확인 할때 주어진 direction만 저장하면 반대로 선이 그어질때를 검출하지 못한다.
 (directionTo + 4) % 8 로 반대 방향 direction을 만들어서 도착한 점에도 해당 선을 추가해준다.

    테스트 케이스 통과 오류로 인한 추가작업2
5 2 7 2 의 경우 x.5, y.5 를 대각선으로 교차하는 선이 추가로 이등변삼각형을 생성한다.
좌표를 float형으로 바꾸고 대각선이동의 경우 x.5, y.5 의 in/out 을 표시해주면서
(directionTo + 2) % 8 로 직각 방향 선이 해당 x.5, y.5 포인트에 존재하는지 확인하여
존재 하면 이등변삼각형을 포함하는 방이 무조선 생성되었다고 판단한다.
위와 마찬가지로 동일한 선이 다시 그려지는 경우는 포함하지 않도록 한다.

Class visitedNodeList
*/

public class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        //그림이 그려진 좌표 - 중복처리를 위해 맵으로 
        Map<String, Point> drawnCoordinates = new HashMap<>(arrows.length);

        Point prevPoint = new Point(); //0,0 
        //투입!
        drawnCoordinates.put(prevPoint.positionCode(), prevPoint);
        List<Integer> DIAGONAL_DIRECTION_ARR = Arrays.asList( 1, 3, 5, 7 );  //사선 방향 Direction Index
        boolean HALF_MOVE = true;

        for (int directionTo : arrows) {
            // 이미 그어진 선인가?
            boolean isLineExisted = prevPoint.lineTo[directionTo] == 1;
            // 그어진 선 표시
            prevPoint.lineTo[directionTo] = 1;

            // 선을 반대로 긋는 경우도 감안해서 처리하려면 이동한 포인트에도 반대방향으로 선을 그어준다.
            int directionFrom = (directionTo + 4) % 8; 

            // 이동할 다음 포인트의 Str Code => nextX, nextY
            String movedPositionCode = prevPoint.movedPositionCode(directionTo);

            //기존에 그어진 선이 아닐때만 계산/처리
            if (isLineExisted == false) {
                //다음 포인트가 존재한다는 것은 폐Loop가 하나 생긴다는 얘기
                boolean isNextPointExisted = drawnCoordinates.containsKey(movedPositionCode);
                if (isNextPointExisted) {
                    answer++;
                } else {
                    //아니면 새로 만들어서 넣어준다.
                    drawnCoordinates.put(movedPositionCode, new Point(movedPositionCode));
                }
                
                ////////////////////////중간점 Cross///////////////////////////////////////////////
                {
                    // edge case : { 6, 5, 2, 7, 1, 4, 2, 4, 6 }; 일 때 Unit Square를 4등분 하는 
                    // 이등변 삼각형이 생기므로 사선이동(1,3,5,7) 일때 +0.5, +0.5 노드를 추가하고
                    // In Out방향을 같이 기록한다.
                    int directionDiagonalRight = (directionTo + 2) % 8; // 대각선인 경우 직각으로 교차하는 선
                        
                    //사선 방향 이동이면 .5 체크 시작
                    if (DIAGONAL_DIRECTION_ARR.contains(directionTo)) {
                        // 지나가는 중간 점의 코드 추출
                        String passingPositionCode = prevPoint.movedPositionCode(directionTo, HALF_MOVE);
                        
                        //중간 점이 없으면 생성 해준다
                        boolean isDiagonalHalfPointExisted = drawnCoordinates.containsKey(passingPositionCode);
                        if (isDiagonalHalfPointExisted == false) {
                            drawnCoordinates.put(passingPositionCode, new Point(passingPositionCode));
                        }

                        Point diagonalPassingPoint = drawnCoordinates.get(passingPositionCode);
                        //지나가는 점에 현재방향의 직각으로 선이 그어져 있다면
                        boolean isRightDiagonalExisted = diagonalPassingPoint.lineTo[directionDiagonalRight] == 1;
    
                        //System.out.println(" -> [" + passingPositionCode + "] -> " + directionTo + " -> " + directionDiagonalRight + " -> " + diagonalPassingPoint + " => " + answer);
                        if (isRightDiagonalExisted) {
                            // 어떻게 빙돌았어도 새로운 폐Loop가 생기게 되므로 방이 추가된다.
                            answer++; 
                        }
    
                        
                        // XX 선을 반대로 긋는 경우도 감안해서 처리하려면 이동한 포인트에도 반대방향으로 선을 그어준다. XX
                        // =>대각선일때 직각인 선을 편하게 찾기 위해서 in out을 동시에 표시해 준다.
                        diagonalPassingPoint.lineTo[directionTo] = 1;
                        diagonalPassingPoint.lineTo[directionFrom] = 1;
                    }
               
                }

                //System.out.println(prevPoint + " -> " + directionTo + " -> [" + movedPositionCode + "] => " + answer);
            }

            //next Point 설정작업
            prevPoint = drawnCoordinates.get(movedPositionCode);
            //next Point에 어디에서 선이 그어졌는지 기록한다.            
            // 선을 반대로 긋는 경우도 감안해서 처리하려면 이동한 포인트에도 반대방향으로 선을 그어준다.
            prevPoint.lineTo[directionFrom] = 1; 
        }
        return answer;
    }

    class Point {
        private int[][] movingCoords = { 
            {  0, -1 },   // 0  ↑
            {  1, -1 },   // 1  ↗
            {  1,  0 },   // 2  →
            {  1,  1 },   // 3  ↘
            {  0,  1 },   // 4  ↓
            { -1,  1 },   // 5  ↙
            { -1,  0 },   // 6  ←
            { -1, -1 },   // 7  ↖
         };

        float x, y;
        int[] lineTo; // 해당 좌표에 그어진 선 기록

        Point() {
            this(0, 0);
        }

        void setX(float xVal) {x = xVal;}

        void setY(float yVal) {y = yVal;}

        Point(float xVal, float yVal) {
            x = xVal;
            y = yVal;
            lineTo = new int[8];
        }

        String positionCode() {
            return positionCode(x, y);
        }

        // Overloading
        <T> String positionCode(T xVal, T yVal) {
            return String.format("%.1f", (float) xVal) + ", " + String.format("%.1f", (float) yVal);
        }

        String movedPositionCode(int direction) {
            return movedPositionCode(direction, false); // 정규 이동 (1)
        }

        // Overloading // 대각선 이동중 발생가능한 교차점 (0.5)
        String movedPositionCode(int direction, boolean halfMove) {
            float coefficient = 1;
            if (halfMove)
                coefficient = 0.5f;
            float movedX = x;
            float movedY = y;
            int[] movingCoord = movingCoords[direction];

            movedX += movingCoord[0] * coefficient;
            movedY += movingCoord[1] * coefficient;

            return positionCode(movedX, movedY);
        }

        //movedPositionCode 로 새로운 좌표(Next Point)를 생성하기 위한 생성자
        Point(String positionCode) {
            this(0, 0);
            String[] positionStr = positionCode.split(",");

            this.setX(Float.parseFloat(positionStr[0]));
            this.setY(Float.parseFloat(positionStr[1]));
        }

        @Override
        public String toString() {

            return "[" + x + ", " + y + "]" + Arrays.toString(lineTo);
        }
    }
}

/*


*/