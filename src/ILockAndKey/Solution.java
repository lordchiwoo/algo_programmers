package ILockAndKey;

//https://programmers.co.kr/learn/courses/30/lessons/60059
/*
문제 설명
고고학자인 "튜브"는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다. 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.

잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.

자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다. 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.

열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.

제한사항
key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
M은 항상 N 이하입니다.
key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
0은 홈 부분, 1은 돌기 부분을 나타냅니다.
입출력 예
key	lock	result
[[0, 0, 0], [1, 0, 0], [0, 1, 1]]	[[1, 1, 1], [1, 1, 0], [1, 0, 1]]	true
입출력 예에 대한 설명
자물쇠.jpg

key를 시계 방향으로 90도 회전하고, 오른쪽으로 한 칸, 아래로 한 칸 이동하면 lock의 홈 부분을 정확히 모두 채울 수 있습니다.
*/

public class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        int keySize = key.length;
        int lockSize = lock.length;
        Lock myLock = new Lock(lock);

        // key 0,0 좌표의 이동 가능 범위 : (-1*(keySize-1)) ~ (lockSize-1)
        int minOffset = 1 - keySize; // -1 * keySize-1;
        int maxOffset = lockSize - 1;
        for (int rotation = 0; rotation <= 270; rotation += 90) {
            // 로테이션 후의 키 생성
            Key newKey = new Key(key, rotation);
            boolean keyMatching = true;
            for (int offsetH = minOffset; offsetH <= maxOffset; offsetH++) {
                for (int offsetV = minOffset; offsetV <= maxOffset; offsetV++) {
                    //System.out.println("[" + offsetH + ", " + offsetV + "] R" + rotation);
                    keyMatching = isMatchKey(newKey, myLock, offsetH, offsetV);

                    if (keyMatching) {
                        return true;
                    }
                }
            }
        }

        // 검사 범위 :

        return false;
    }

    private boolean isMatchKey(Key newKey, Lock myLock, int offsetH, int offsetV) {
        for (int y = 0; y < myLock.size; y++) {
            for (int x = 0; x < myLock.size; x++) {
                //offset 위치에 Key 0,0을 위치 시키면 lock의 x,y에 겹치는 Key 좌표를 아래와 같이 추출한다.
                int keyStoneX = x - offsetH; 
                int keyStoneY = y - offsetV;

                //해당 좌표의 데이터를 꺼내서
                int keyStone = newKey.getXY(keyStoneX, keyStoneY);
                int lockStone = myLock.getXY(x, y);

                //맞물리지 않으면 실패
                if (1 != keyStone + lockStone) {
                    return false;
                }
            }
        }
        return true;
    }

    class Arr2d {
        int[][] data;
        int size;

        Arr2d() {
            data = null;
            size = 0;
        }

        Arr2d(int[][] orgData) {
            data = orgData;
            size = data.length;
        }

        int getXY(int x, int y) {
            if (x < 0 || x >= size)
                return 0;
            if (y < 0 || y >= size)
                return 0;

            return data[x][y];
        }
    }

    class Lock extends Arr2d {
        Lock(int[][] orgData) {
            super(orgData);
        }
    }

    class Key extends Arr2d {
        int[][] rawData;

        Key(int[][] orgData) {
            super(orgData);
        }

        Key(int[][] key, int rotation) {
            rawData = key;
            data = new int[rawData.length][rawData[0].length];

            size = data.length;
            for (int x = 0; x < size; x++)
                for (int y = 0; y < size; y++)
                    data[x][y] = getXYR(x, y, rotation);
        }

        // , int offsetH, int offsetV
        int getXYR(int x, int y, int rotation) {
            int temp = 0;
            switch (rotation) {
                case 0:
                    break;
                case 90:
                    temp = x;
                    x = size - y - 1;
                    y = temp;
                    break;
                case 180:
                    x = size - x - 1;
                    y = size - y - 1;
                    break;
                case 270:
                    temp = y;
                    y = size - x - 1;
                    x = temp;
                    break;
                default:

            }

            return rawData[x][y];
        }
    }
}

/*
테스트 1 〉	통과 (0.86ms, 52.2MB)
테스트 2 〉	통과 (0.65ms, 52.1MB)
테스트 3 〉	통과 (2.96ms, 52.3MB)
테스트 4 〉	통과 (0.64ms, 51.8MB)
테스트 5 〉	통과 (3.41ms, 52.5MB)
테스트 6 〉	통과 (5.94ms, 52.9MB)
테스트 7 〉	통과 (5.89ms, 52.1MB)
테스트 8 〉	통과 (4.80ms, 52MB)
테스트 9 〉	통과 (1.70ms, 51.9MB)
테스트 10 〉	통과 (10.72ms, 53MB)
테스트 11 〉	통과 (15.61ms, 52.3MB)
테스트 12 〉	통과 (0.63ms, 52.7MB)
테스트 13 〉	통과 (0.87ms, 53.1MB)
테스트 14 〉	통과 (1.21ms, 52.7MB)
테스트 15 〉	통과 (1.58ms, 68MB)
테스트 16 〉	통과 (2.73ms, 52.1MB)
테스트 17 〉	통과 (0.80ms, 51.8MB)
테스트 18 〉	통과 (7.16ms, 52.7MB)
테스트 19 〉	통과 (0.99ms, 52.8MB)
테스트 20 〉	통과 (9.18ms, 53MB)
테스트 21 〉	통과 (2.33ms, 52.3MB)
테스트 22 〉	통과 (1.17ms, 53.1MB)
테스트 23 〉	통과 (1.22ms, 51.9MB)
테스트 24 〉	통과 (1.59ms, 52.2MB)
테스트 25 〉	통과 (21.36ms, 52.6MB)
테스트 26 〉	통과 (3.79ms, 52.3MB)
테스트 27 〉	통과 (6.48ms, 52.9MB)
테스트 28 〉	통과 (1.88ms, 52.1MB)
테스트 29 〉	통과 (1.37ms, 52.1MB)
테스트 30 〉	통과 (1.84ms, 51.7MB)
테스트 31 〉	통과 (4.64ms, 52.8MB)
테스트 32 〉	통과 (2.69ms, 52.3MB)
테스트 33 〉	통과 (1.30ms, 52.7MB)
테스트 34 〉	통과 (0.66ms, 52.2MB)
테스트 35 〉	통과 (0.94ms, 51.4MB)
테스트 36 〉	통과 (1.29ms, 53.6MB)
테스트 37 〉	통과 (0.79ms, 52.7MB)
테스트 38 〉	통과 (0.93ms, 52.7MB)
 */