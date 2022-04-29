import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Radix Sort(기수 정렬)
 * - 낮은 자리 수부터 정렬하는 방식(1의자리 -> 10의자리 -> 100의자리)
 * - 각 원소 간의 비교 연산을 하지 않아 빠르지만, 기수 테이블을 위한 메모리가 필요하다.
 * - 시간 복잡도 = O(dn)  (d는 최대자릿수)
 * - Stable sort
 * -
 * - 기수 테이블은 큐로 구성 (0~9까지의 key를 가진 map에 value가 queue)
 */
public class RadixSort {

    public static void radixSort(int[] arr) {
        // 기수 테이블 생성
        ArrayList<Queue<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }

        int idx = 0;
        int div = 1; // 자릿수를 뜻함
        int maxLen = getMaxLen(arr);

        // 최대자릿수만큼 반복
        for (int i = 0; i < maxLen; i++) {
            // 배열 돌면서 값의 자릿수별로 테이블에 넣어줌
            for (int j = 0; j < arr.length; j++) {
                list.get(arr[j] / div % 10).offer(arr[j]);
            }

            // 테이블에 정렬된 것을 다시 하나의 배열로 만들어줌
            for (int j = 0; j < 10; j++) {
                Queue<Integer> queue = list.get(j);

                while (!queue.isEmpty()) {
                    arr[idx++] = queue.poll();
                }
            }

            // 다음 자릿수를 위한 설정
            idx = 0;
            div *= 10;
        }
    }

    // 배열에서 최대 자릿수 구하기
    public static int getMaxLen(int[] arr) {
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            // log이용해서 자릿수 구하는 방법
            int len = (int) Math.log10(arr[i]) + 1;
            if (maxLen < len) {
                maxLen = len;
            }
        }
        return maxLen;
    }
}
