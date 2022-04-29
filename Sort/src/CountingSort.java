import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Counting Sort(계수 정렬)
 * - 숫자끼리 비교하지 않고 카운트를 세서 정렬하는 방식
 * - 카운팅을 위한 카운팅 테이블이 필요
 * - Un-Stable sort
 * - 시간 복잡도 = O(n+k)  (k는 정렬 대상 데이터 중 최대 값)
 * -
 * - [1,3,2,4,5] 이런 배열처럼 수의 분포가 좁다면 배열을 이용하는게 좋고
 * - [1,24005,4,3] 이런식으로 수 자체는 적은데 수의 분포가 넓다면 해시맵을 이용하는게 좋다.
 */

public class CountingSort {

    // 카운팅 테이블이 배열인 방식
    public static void countingSort1(int[] arr) {
        // 카운팅 테이블을 만들기 위해 가장 큰 값을 구해서
        int max = Arrays.stream(arr).max().getAsInt();

        // 가장 큰 값까지 들어갈 수 있는 테이블을 만든다. (인덱스가 0부터 시작하기 때문에 +1)
        // 만약 음수까지 나온다면 0을 기준으로 음수값최대 + 양수값 최대 + 1(0을 의미) 해주면된다
        int[] table = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            // 배열 데이터 값을 키값으로 해서 테이블에 카운팅 (++)을 해준다.
            table[arr[i]]++;
        }

        int idx = 0;

        // 테이블 크기만큼 반복문 실행
        for (int i = 0; i < table.length; i++) {
            // 테이블에서 값이 0이 될 때까지 반복복            while (table[i] > 0) {
            // 원래의 배열에 값을 재배치 (음수가 있는 배열일 경우 0의 인덱스를 구해서 그만큼 빼주면 된다)
            arr[idx++] = i;
            table[i] -= 1;

        }
    }

    // 카운팅 테이블이 HashMap인 방식
    public static void countingSort2(int[] arr) {
        // 해시맵으로 테이블 생성
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // 테이블의 키값을 오름차순 정렬렬
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        int idx = 0;

        for (int i = 0; i < list.size(); i++) {

            // 원래 숫자
            Integer key = list.get(i);
            // 원래 숫자의 갯수
            Integer cnt = map.get(key);

            while (cnt > 0) {
                arr[idx++] = key;
                cnt--;
            }
        }
    }
}
