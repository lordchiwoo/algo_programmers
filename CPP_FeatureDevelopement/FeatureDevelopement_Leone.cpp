#include <vector>

using namespace std;

//https://programmers.co.kr/learn/courses/30/lessons/42586?language=cpp
vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    while(progresses.size()>0)
    {
        // 이전 Feature 개발이 모두 끝나야 개발 종료를 선언 가능 featureIndex==finishedFeatureCount
        int featureIndex = 0; 
        int finishedFeatureCount=0;

        auto it_progress = std::begin(progresses);
        auto it_speed = std::begin(speeds);

        while (it_progress != std::end(progresses)) {
            if (*it_progress < 100) *it_progress += *it_speed;
            
            if (*it_progress >= 100 && featureIndex==finishedFeatureCount)
            {
                finishedFeatureCount++;
                
                it_progress = progresses.erase(it_progress);
                it_speed = speeds.erase(it_speed);
            }
            else
            {
                ++it_progress;
                ++it_speed;
            }
            featureIndex++;
        }
        if(finishedFeatureCount>0)
            answer.push_back(finishedFeatureCount);
    }
    return answer;
}

int main()
{
    vector<int> progresses {93, 30, 55};
    vector<int> speeds {1, 30, 5};
    solution(progresses, speeds);
    return 0;
}