/**
 * Quick Sort(퀵 정렬)
 * - 임의의 기준 값 (= pivot)을 정하고 그 값을 기준으로 좌우 분할하며 정렬하는 방식
 * -
 * - 장점
 * - 특정한 경우를 제외하고는 O(nlogn)이며, 다른 O(nlogn) 알고리즘 보다 대체적으로 빠르다.(merge의 2~3배)
 * - in-place sort 이다
 * - 재귀호출로 인한 공간 복잡도가 logN으로 메모리를 적게 소비한다.
 * -
 * - 단점
 * - 특정한 경우 , 최악의 시간인 O(n^2)가 발생한다.
 * - 재귀를 사용하기 때문에 재귀를 사용하지 못하는 환경일 때 구현이 복잡해진다.
 */

public class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;

        // 양쪽 끝에서부터 pivot값과 비교
        while (i < j) {
            // 오른쪽 끝에서부터 pivot값보다 작은 인덱스를 찾기
            // pivot보다 크면 다음 인덱스를 찾아야 하니 j--;
            while (arr[j] > pivot && i < j) {
                j--;
            }

            // 왼쪽부터 pivot 값 보다 큰 인덱스를 찾기
            // pivot보다 작으면 다음 인덱스를 확인해야하니 i++
            while (arr[i] <= pivot && i < j) {
                i++;
            }

            // 양쪽 끝에서부터 서로 걸린 값끼리 교환
            swap(arr, i, j);
        }

        // 맨처음이 pivot값이였으니 그것과 현재 i와j가 만난 부분을 교체(pivot을 기준으로 좌우로 나뉘어야 하므로)
        swap(arr, left, i);

        // 새로운 pivot 값 반환
        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
