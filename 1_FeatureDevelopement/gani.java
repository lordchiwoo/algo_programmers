private int[] 스택큐_기능개발()
{
    try
    {
        int[] progresses = new int[] { 93, 30, 55 };
        int[] speeds = new int[] { 1, 30, 5 };

        // 프로세스 처리 일수 개산
        int[] iprogresssCNT = new int[progresses.Length];

        for (int i = 0; i < progresses.Length; i++)
        {
            int iCNT = 0;

            // 각 프로세스별로 100% 까지 며칠이 필요한지 계산한다.
            while (progresses[i] < 100)
            {
                iCNT ++;
                progresses[i] += speeds[i];
            }
            // 프로세스별 카운트
            iprogresssCNT[i] = iCNT;
        }

        // 배열로 가지고 있기 위한
        ArrayList al = new ArrayList();

        int k = 0;  // 비교 인덱스 a
        int j = 0;  // 비교 인덱스 b

        // 각 프로세스별 배포 카운트
        int iResult = 1;

        // 해당 프로세스의 종료 플레그
        bool bPush = false;

        // 프로세스 개수 만큼 반복
        for (k = 0; k < iprogresssCNT.Length; k++)
        {
            // k 와 한자리씩 비교하기 위한 반복
            for (j = k + 1; j <= iprogresssCNT.Length; j++)
            {
                if (j == iprogresssCNT.Length)
                {
                    // (비교군이 끝났다) 비교가 끝이라면 해당 프로세스의 체크는 아웃
                    bPush = true;
                    break;
                }
                else if (iprogresssCNT[k] >= iprogresssCNT[j])
                {
                    // (뒤에 더 빨리 끝나는 프로세스가있다) 첫 기능보다 뒤에 기능이 일찍 끝나는 경우 라면 개수는 ++
                    iResult += 1;
                }
                else
                {
                    // (일반적인) 위에 조건이 해당 하지 않는다면 뒤에 일수가 더 적다면
                    bPush = true;
                    break;
                }
            }

            if (bPush)
            {
                // 비교 체크 플레그 다시 변경
                bPush = false;

                // 결과 값
                al.Add(iResult);

                // 일수 초기화
                iResult = 1;

                // 데이터가 추가되었으므로 추가된 비교 a 군의 인덱스는 한칸뒤로 밀려난다.
                k = j - 1;
            }
        }

        return al.OfType<int>().ToArray();
    }
    catch (Exception ex)
    {
        MessageBox.Show(ex.Message);
        return null;
    }
}