/**
 * Shell Sort(셸 정렬)
 * - 삽입정렬은 오름차순 정렬 기준으로 내림차순으로 이미 정렬되어 구성된 데이터에 대해서는
 * - 앞의 데이터와 하나씩 비교하며 모두 교환해야한다.
 * - 이러한 약점을 보완하는 방식이 Shell sort
 * - 이전의 모든 데이터와 비교하지 않고 일정 간격을 두어 비교
 * - 간격 설정에 따라 worst case가 O(n^2) 지만 일반적인 산포 데이터 기준으로는 삽입 정렬에 비해 빠르다.
 */
public class ShellSort {

    public static void shellSort(int[] arr) {
        // 보통 간격은 배열 길이의 절반으로 시작함.
        int gap = arr.length / 2;

        // 절반씩 줄여나가면서 진행
        for (int g = gap; g > 0; g /= 2) {

            // i = g번째 인덱스
            for (int i = g; i < arr.length; i++) {
                int tmp = arr[i];

                int j = 0;

                // i에서 간격 만큼 빼면 i보다 간격만큼 앞에 있는 녀석하고 비교가 가능하다. 삽입 정렬과 동일
                for (j = i - g; j >= 0; j -= g) {
                    if (arr[j] > tmp) {
                        arr[j + g] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j + g] = tmp;
            }
        }
    }


}
