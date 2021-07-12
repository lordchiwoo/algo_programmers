package HNumberOfRooms;

import java.util.Arrays;
import java.util.HashMap;
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

1-0 현재위치에 점이 있는지 확인한다.
1-0-1 점이 없으면 1-1-0으로 간다
1-0-2 점이 있으면 directionList에서 d와 같은 기록이 있는지 확인하고
1-0-2-1 같은 기록이 있으면 방의 갯수는 변화가 없다(그린 선을 다시 그림)
1-0-2-2 같은 기록이 없으면 새로운 폐Loop가 생성 된 것이므로 방의 갯수 +1 한다.

1-1-0 directionList 에 d를 추가한다.(중복 제거?)

Class visitedNodeList
*/

public class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        Map<String, Point> drawnCoordinates = new HashMap<>(arrows.length);

        Point prevPoint = new Point();
        drawnCoordinates.put(prevPoint.positionCode(), prevPoint);

        for (int directionTo : arrows) {
            int directionFrom = (directionTo + 4) % 8; // 선을 반대로 긋는 경우도 감안해서 처리하려면 어디서 왔는지도 기록한다
            String movedPositionCode = prevPoint.movedPositionCode(directionTo);
            boolean isLineExisted = prevPoint.lineTo[directionTo] == 1;
            // TODO edge case : { 6, 5, 2, 7, 1, 4, 2, 4, 6 }; 일 때 Unit Square를 4등분 하는 이등변
            // 삼각형이 생기므로 1,3,5,7 일때 +0.5, +0.5 노드를 추가하고 In Out방향을 같이 기록한다.
            if (isLineExisted == false) {
                boolean isNextPointExisted = drawnCoordinates.containsKey(movedPositionCode);
                Integer[] diagonalDirection = { 1, 3, 5, 7 };
                int directionDiagonalRight = (directionTo + 2) % 8; // 대각선인 경우 직각으로 교차하는 선

                if (Arrays.asList(diagonalDirection).contains(directionTo)) {
                    String passingPositionCode = prevPoint.movedPositionCode(directionTo, true);
                    boolean isDiagonalHalfPointExisted = drawnCoordinates.containsKey(passingPositionCode);

                    if (isDiagonalHalfPointExisted == false) {
                        drawnCoordinates.put(passingPositionCode, new Point(passingPositionCode));
                    }

                    Point diagonalPassingPoint = drawnCoordinates.get(passingPositionCode);
                    boolean isRightDiagonalExisted = diagonalPassingPoint.lineTo[directionDiagonalRight] ==1;

                    System.out.println(" -> [" + passingPositionCode + "] -> " + directionTo + " -> " + directionDiagonalRight + " -> " + diagonalPassingPoint + " => " + answer);
                    if (isRightDiagonalExisted) {
                        answer++;
                        System.out.println(answer);
                    }

                    diagonalPassingPoint.lineTo[directionTo] = 1;
                    diagonalPassingPoint.lineTo[directionFrom] = 1; // 선을 반대로 긋는 경우도 감안해서 처리하려면 어디서 왔는지도 기록한다
                }

                prevPoint.lineTo[directionTo] = 1;
                if (isNextPointExisted) {
                    answer++;
                } else {
                    drawnCoordinates.put(movedPositionCode, new Point(movedPositionCode));
                }

                System.out.println(prevPoint + " -> " + directionTo + " -> [" + movedPositionCode + "] => " + answer);
            }
            prevPoint = drawnCoordinates.get(movedPositionCode);
            prevPoint.lineTo[directionFrom] = 1; // 선을 반대로 긋는 경우도 감안해서 처리하려면 어디서 왔는지도 기록한다
        }
        return answer;
    }

    class Point {
        private int[][] movingCoords = { { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 },
                { -1, -1 }, };

        float x, y;
        int[] lineTo;

        Point() {
            this(0, 0);
        }

        Point(float xVal, float yVal) {
            x = xVal;
            y = yVal;
            lineTo = new int[8];
        }

        Point(String positionCode) {
            this(0, 0);
            String[] positionStr = positionCode.split(",");

            this.setX(Float.parseFloat(positionStr[0]));
            this.setY(Float.parseFloat(positionStr[1]));
        }

        void setX(float xVal) {
            x = xVal;
        }

        void setY(float yVal) {
            y = yVal;
        }

        String positionCode() {
            return positionCode(x, y);
        }

        <T> String positionCode(T xVal, T yVal) {
            return String.format("%.1f", (float) xVal) + ", " + String.format("%.1f", (float) yVal);
        }

        String movedPositionCode(int direction) {
            return movedPositionCode(direction, false);
        }

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

        @Override
        public String toString() {

            return "[" + x + ", " + y + "]" + Arrays.toString(lineTo);
        }
    }
}

/*


*/