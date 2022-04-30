import java.util.Arrays;

/**
 * Binary Search (이진 탐색)
 * 정렬된 상태의 데이터에서 특정 값을 빠르게 탐색하는 방법
 * 찾고자 하는 값과 데이터 중앙에 있는 값을 비교
 * - 찾고자 하는 값이 더 작으면 데이터 왼쪽 부분에서 이진 탐색
 * - 찾고자 하는 값이 더 크면 데이터 오른쪽 부분에서 이진 탐색
 * 시간 복잡도 : O(logn)
 *
 * 자바에서 기본적으로 제공하는 BT가 있다. Arrays.binarySearch();
 */

public class BinarySearch {

    // 반복문 사용
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    // 재귀 호출 구조
    public static int binarySearch2(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearch2(arr, target, left, mid - 1);
        } else {
            return binarySearch2(arr, target, mid + 1, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10, 20, 30, 40, 50, 60};

        System.out.println(binarySearch(arr, 30));
        System.out.println();

        System.out.println(binarySearch2(arr, 30, 0, arr.length - 1));

        /**
         * 자바 기본 Binary Search
         */

        // 배열 안에 데이터가 있는 경우, 해당 위치 인덱스 반환
        System.out.println(Arrays.binarySearch(arr,5));

        // 배열 안에 데이터가 없는 경우, -(데이터가 원래 들어가야할 위치 인덱스) -1 반환
        // 즉 -(인덱스가 1부터 시작할 때 들어가야할 위치)
        System.out.println(Arrays.binarySearch(arr, 6));
    }
}
