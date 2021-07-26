package JPillarAndBeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://programmers.co.kr/learn/courses/30/lessons/60061
/*
문제 설명
빙하가 깨지면서 스노우타운에 떠내려 온 "죠르디"는 인생 2막을 위해 주택 건축사업에 뛰어들기로 결심하였습니다. "죠르디"는 기둥과 보를 이용하여 벽면 구조물을 자동으로 세우는 로봇을 개발할 계획인데, 그에 앞서 로봇의 동작을 시뮬레이션 할 수 있는 프로그램을 만들고 있습니다.
프로그램은 2차원 가상 벽면에 기둥과 보를 이용한 구조물을 설치할 수 있는데, 기둥과 보는 길이가 1인 선분으로 표현되며 다음과 같은 규칙을 가지고 있습니다.

기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
단, 바닥은 벽면의 맨 아래 지면을 말합니다.

2차원 벽면은 n x n 크기 정사각 격자 형태이며, 각 격자는 1 x 1 크기입니다. 맨 처음 벽면은 비어있는 상태입니다. 기둥과 보는 격자선의 교차점에 걸치지 않고, 격자 칸의 각 변에 정확히 일치하도록 설치할 수 있습니다. 다음은 기둥과 보를 설치해 구조물을 만든 예시입니다.

기둥과보-1.jpg

예를 들어, 위 그림은 다음 순서에 따라 구조물을 만들었습니다.

(1, 0)에서 위쪽으로 기둥을 하나 설치 후, (1, 1)에서 오른쪽으로 보를 하나 만듭니다.
(2, 1)에서 위쪽으로 기둥을 하나 설치 후, (2, 2)에서 오른쪽으로 보를 하나 만듭니다.
(5, 0)에서 위쪽으로 기둥을 하나 설치 후, (5, 1)에서 위쪽으로 기둥을 하나 더 설치합니다.
(4, 2)에서 오른쪽으로 보를 설치 후, (3, 2)에서 오른쪽으로 보를 설치합니다.
만약 (4, 2)에서 오른쪽으로 보를 먼저 설치하지 않고, (3, 2)에서 오른쪽으로 보를 설치하려 한다면 2번 규칙에 맞지 않으므로 설치가 되지 않습니다. 기둥과 보를 삭제하는 기능도 있는데 기둥과 보를 삭제한 후에 남은 기둥과 보들 또한 위 규칙을 만족해야 합니다. 만약, 작업을 수행한 결과가 조건을 만족하지 않는다면 해당 작업은 무시됩니다.

벽면의 크기 n, 기둥과 보를 설치하거나 삭제하는 작업이 순서대로 담긴 2차원 배열 build_frame이 매개변수로 주어질 때, 모든 명령어를 수행한 후 구조물의 상태를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 5 이상 100 이하인 자연수입니다.
build_frame의 세로(행) 길이는 1 이상 1,000 이하입니다.
build_frame의 가로(열) 길이는 4입니다.
build_frame의 원소는 [x, y, a, b]형태입니다.
x, y는 기둥, 보를 설치 또는 삭제할 교차점의 좌표이며, [가로 좌표, 세로 좌표] 형태입니다.
a는 설치 또는 삭제할 구조물의 종류를 나타내며, 0은 기둥, 1은 보를 나타냅니다.
b는 구조물을 설치할 지, 혹은 삭제할 지를 나타내며 0은 삭제, 1은 설치를 나타냅니다.
벽면을 벗어나게 기둥, 보를 설치하는 경우는 없습니다.
바닥에 보를 설치 하는 경우는 없습니다.
구조물은 교차점 좌표를 기준으로 보는 오른쪽, 기둥은 위쪽 방향으로 설치 또는 삭제합니다.
구조물이 겹치도록 설치하는 경우와, 없는 구조물을 삭제하는 경우는 입력으로 주어지지 않습니다.
최종 구조물의 상태는 아래 규칙에 맞춰 return 해주세요.
return 하는 배열은 가로(열) 길이가 3인 2차원 배열로, 각 구조물의 좌표를 담고있어야 합니다.
return 하는 배열의 원소는 [x, y, a] 형식입니다.
x, y는 기둥, 보의 교차점 좌표이며, [가로 좌표, 세로 좌표] 형태입니다.
기둥, 보는 교차점 좌표를 기준으로 오른쪽, 또는 위쪽 방향으로 설치되어 있음을 나타냅니다.
a는 구조물의 종류를 나타내며, 0은 기둥, 1은 보를 나타냅니다.
return 하는 배열은 x좌표 기준으로 오름차순 정렬하며, x좌표가 같을 경우 y좌표 기준으로 오름차순 정렬해주세요.
x, y좌표가 모두 같은 경우 기둥이 보보다 앞에 오면 됩니다.
입출력 예
n	build_frame	result
5	[[1,0,0,1],[1,1,1,1],[2,1,0,1],[2,2,1,1],[5,0,0,1],[5,1,0,1],[4,2,1,1],[3,2,1,1]]	[[1,0,0],[1,1,1],[2,1,0],[2,2,1],[3,2,1],[4,2,1],[5,0,0],[5,1,0]]
5	[[0,0,0,1],[2,0,0,1],[4,0,0,1],[0,1,1,1],[1,1,1,1],[2,1,1,1],[3,1,1,1],[2,0,0,0],[1,1,1,0],[2,2,0,1]]	[[0,0,0],[0,1,1],[1,1,1],[2,1,1],[3,1,1],[4,0,0]]
입출력 예에 대한 설명
입출력 예 #1

문제의 예시와 같습니다.

입출력 예 #2

여덟 번째 작업을 수행 후 아래와 같은 구조물 만들어집니다.

기둥과보-2.jpg

아홉 번째 작업의 경우, (1, 1)에서 오른쪽에 있는 보를 삭제하면 (2, 1)에서 오른쪽에 있는 보는 조건을 만족하지 않으므로 무시됩니다.

열 번째 작업의 경우, (2, 2)에서 위쪽 방향으로 기둥을 세울 경우 조건을 만족하지 않으므로 무시됩니다.
*/

public class Solution {
    int[] DIRECTION_NO = { 0, 0 };  // 동일 위치
    int[] DIRECTION_UP = { 0, 1 };  // 위
    int[] DIRECTION_UR = { 1, 1 };  // 위   오른
    int[] DIRECTION_RT = { 1, 0 };  //      오른
    int[] DIRECTION_DR = { 1, -1 }; // 아래 오른
    int[] DIRECTION_DN = { 0, -1 }; // 아래
    int[] DIRECTION_DL = { -1, -1 };// 아래 왼
    int[] DIRECTION_LT = { -1, 0 }; //      왼
    int[] DIRECTION_UL = { -1, 1 }; // 위   왼

    Map<String, Boolean[]> building;

    // 기둥 생성시 체크 : 해당좌표가 바닥이거나 아래에 기둥이 있거나 좌우로 보가 있는지
    // 보 생성시 체크 : 생성되는 좌표 좌,우 아래쪽으로 기둥이 하나라도 있거나 좌우에 모두 보가 있는지
    // 기둥 삭제시 체크 : 해당 기둥을 제거하고 나서 해당 기둥에 의존하던 기둥(위) 과 보(좌우) 를 각각 삭제하고 다시 붙일수 있는지 검사
    // 보 삭제시 체크 : 해당 보를 제거 하고 나서 해당 보에 의돈하던 기둥(좌위 우위)과 보(좌좌 우우)를 각각 삭제하고 다시 붙일 수 있는지
    // 검사
    public int[][] solution(int n, int[][] build_frame) {
        // building : 좌표 문자열, boolean 기둥,보 존재여부
        building = new HashMap<String, Boolean[]>();

        for (int[] buildElement : build_frame) {
            int[] pos = { buildElement[0], buildElement[1] };
            boolean isBeam = buildElement[2] == 1;
            boolean isbuild = buildElement[3] == 1;

            // 처리해주세요 얍!
            processFrame(pos, isBeam, isbuild);
        }

        // x y 기둥/보 순으로 정렬해서 리스트에 넣고
        List<int[]> arrayList = sortAndListingBuildingElement(n);
        

        // 순서대로 꺼내서 리턴
        int[][] answer = new int[arrayList.size()][];
        answer = arrayList.toArray(answer);

        return answer;
    }

    public String pos2Str(int[] pos) {
        return pos[0] + "," + pos[1];
    }

    private void processFrame(int[] pos, boolean isBeam, boolean isbuild) {
        String posStr = pos2Str(pos);
        //요소가 없으면 잽싸게 만들어 넣고
        if (!building.containsKey(posStr)) building.put(posStr, new Boolean[] { false, false });

        // 해당 좌표의 요소를 꺼내온다. (0=기둥 1=보)
        Boolean[] posElement = building.get(posStr);

        if (isbuild) {// 생성
            buildFrame(pos, isBeam, posElement);
        }
        else {// 삭제
            removeFrame(pos, isBeam, posElement);
        }
    }

    private List<int[]> sortAndListingBuildingElement(int n) {
        List<int[]> arrayList = new ArrayList<int[]>();
        for (int i = 0; i <= n; i++) { // x = 0~n
            for (int j = 0; j <= n; j++) { // y = 0~n
                String posStr = pos2Str(new int[] { i, j });
                if (building.containsKey(posStr)) { // 좌표에 요소가 저장되어 있으면 검사
                    Boolean[] posElement = building.get(posStr);
                    if (posElement[0]) //기둥이 있으면 추가
                        arrayList.add(new int[] { i, j, 0 });
                    if (posElement[1]) //보가 있으면 추가
                        arrayList.add(new int[] { i, j, 1 });
                }
            }
        }
        return arrayList;
    }

    private void buildFrame(int[] pos, boolean isBeam, Boolean[] posElement) {

        if (isBeam) {// 보
            if (canBuildBeam(pos))
                posElement[1] = true;
        }
        else {// 기둥
            if (canBuildPillar(pos))
                posElement[0] = true;
        }
    }

    private void removeFrame(int[] pos, boolean isBeam, Boolean[] posElement) {
        if (isBeam && posElement[1]) {// 보 && 가 있으면
            posElement[1] = false;// 제거 하고
            if (!canRemoveBeam(pos)) { // 제거된 상태가 유지 가능한 구조인지 체크
                posElement[1] = true;// 불가능하면 다시 만들어준다
            }
        }
        else if (posElement[0]) {// 기둥이 있으면
            posElement[0] = false;// 제거 하고
            if (!canRemovePillar(pos)) {// 제거된 상태가 유지 가능한 구조인지 체크
                posElement[0] = true;// 불가능하면 다시 만들어준다
            }
        }
    }

    public boolean hasPillar(int[] pos) {
        String checkPosStr = pos2Str(pos);
        return building.containsKey(checkPosStr) ? building.get(checkPosStr)[0] : false;
    }

    public boolean hasBeam(int[] pos) {
        String checkPosStr = pos2Str(pos);
        return building.containsKey(checkPosStr) ? building.get(checkPosStr)[1] : false;
    }

    public int[] movedPos(int[] position, int[] move) {
        int[] movedPosition;

        //복사 후 좌표 이동
        movedPosition = position.clone();
        movedPosition[0] += move[0];
        movedPosition[1] += move[1];

        return movedPosition;
    }

    public boolean canBuildBeam(int[] position) {
        int[] checkPos;

        checkPos = movedPos(position, DIRECTION_DN);
        if (hasPillar(checkPos))// 아래 좌표에 기둥이 있는지
            return true;

        checkPos = movedPos(position, DIRECTION_DR);
        if (hasPillar(checkPos))// 오른쪽 아래 좌표에 기둥이 있는지
            return true;

        checkPos = movedPos(position, DIRECTION_LT);
        if (hasBeam(checkPos) == false)// 현재 좌표 왼쪽으로 보가 없으면 안됨 +
            return false;// 앙대!

        checkPos = movedPos(position, DIRECTION_RT);
        if (hasBeam(checkPos))// 현재 좌표 오른쪽으로 보가 있는지(왼쪽으로 보가 있고)
            return true;

        return false;
    }

    public boolean canBuildPillar(int[] position) {
        int[] checkPos;

        if (position[1] == 0) // 바닥이면 항상 OK
            return true;

        checkPos = movedPos(position, DIRECTION_NO);
        if (hasBeam(checkPos)) // 현재 좌표에 보가 있는지
            return true;

        checkPos = movedPos(position, DIRECTION_LT);
        if (hasBeam(checkPos))// 현재 좌표 왼쪽으로 보가 있는지
            return true;

        checkPos = movedPos(position, DIRECTION_DN);
        if (hasPillar(checkPos))// 아래 좌표에 기둥이 있는지
            return true;

        return false;
    }

    // 모두 현재 보 없이 다시 만들수 있는지 (유지 가능한 구조인지) 검사
    public boolean canRemoveBeam(int[] pos) {
        int[] checkPos;

        checkPos = movedPos(pos, DIRECTION_NO);
        if (hasPillar(checkPos) && !canBuildPillar(checkPos))// 현재 좌표에 기둥이 있고 기둥을 지을 수 있는지.
            return false;

        checkPos = movedPos(pos, DIRECTION_LT);
        if (hasBeam(checkPos) && !canBuildBeam(checkPos))// 왼쪽에 보가 있으면 그 보를 만들 수 있는지
            return false;

        checkPos = movedPos(pos, DIRECTION_RT);
        if (hasBeam(checkPos) && !canBuildBeam(checkPos))// 오른쪽에 보가 있으면 그 보를 만들 수 있는지
            return false;
        if (hasPillar(checkPos) && !canBuildPillar(checkPos))// 오른쪽에 기둥이 있으면 그 기둥을 만들 수 있는지
            return false;

        return true;
    }

    // 모두 현재 기둥 없이 다시 만들수 있는지 (유지 가능한 구조인지) 검사
    public boolean canRemovePillar(int[] pos) {
        int[] checkPos;

        checkPos = movedPos(pos, DIRECTION_UP);

        if (hasPillar(checkPos) && !canBuildPillar(checkPos))// 위로 기둥이 있으면 기둥을 만들수 있는지.
            return false;

        if (hasBeam(checkPos) && !canBuildBeam(checkPos))// 위로 보가 있으면 그 보를 만들 수 있는지.
            return false;

        checkPos = movedPos(pos, DIRECTION_UL);
        if (hasBeam(checkPos) && !canBuildBeam(checkPos))// 왼쪽위로 보가 있으면 그 보를 만들 수 있는지
            return false;

        return true;
    }
}

/*
테스트 1 〉	통과 (13.43ms, 53MB)
테스트 2 〉	통과 (11.63ms, 53.5MB)
테스트 3 〉	통과 (11.60ms, 52.9MB)
테스트 4 〉	통과 (12.29ms, 53.1MB)
테스트 5 〉	통과 (21.87ms, 53.6MB)
테스트 6 〉	통과 (11.77ms, 53.3MB)
테스트 7 〉	통과 (10.48ms, 53.1MB)
테스트 8 〉	통과 (12.53ms, 52.8MB)
테스트 9 〉	통과 (10.76ms, 53.8MB)
테스트 10 〉	통과 (20.83ms, 55.6MB)
테스트 11 〉	통과 (25.73ms, 55.3MB)
테스트 12 〉	통과 (27.16ms, 54.4MB)
테스트 13 〉	통과 (28.50ms, 57.6MB)
테스트 14 〉	통과 (23.89ms, 53.5MB)
테스트 15 〉	통과 (25.17ms, 57.5MB)
테스트 16 〉	통과 (37.59ms, 55.1MB)
테스트 17 〉	통과 (31.94ms, 55.5MB)
테스트 18 〉	통과 (20.49ms, 55.3MB)
테스트 19 〉	통과 (29.97ms, 55.6MB)
테스트 20 〉	통과 (37.77ms, 57.7MB)
테스트 21 〉	통과 (29.04ms, 54.6MB)
테스트 22 〉	통과 (23.65ms, 56.1MB)
테스트 23 〉	통과 (29.50ms, 55MB)
 */