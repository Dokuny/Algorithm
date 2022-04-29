/**
 Merge Sort(합병정렬)
 - 배열을 절반으로 계속 분할해서 길이가 1이 되도록 만들고, 인접한 부분끼리 정렬하면서 합병하는 방식 (Divide & Conquer)

 - 장점
 - 동일한 값의 경우, 들어온 순서대로 정렬되므로 Stable Sort 이다.
 - 최악의 경우에도 시간 복잡도 = O(nlogn)

 - 단점
 - 추가적인 배열이 필요하므로 in-place Sort가 아니기 때문에 메모리 사용량이 많다.
 - 원본 배열에 추가적인 배열의 값을 복사하는 과정이 많은 시간을 소비하기 때문에 데이터가 많을 경우 시간이 많이 소요된다.
 */

public class MergeSort {

    public static void mergeSort(int[] arr, int[] tmp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, tmp, left, mid);
            mergeSort(arr, tmp, mid + 1, right);
            merge(arr,tmp,left,right,mid);
        }
    }

    public static void merge(int[] arr, int[] tmp, int left, int right, int mid) {
        // 반으로 나눈 배열 중, 왼쪽 배열에서 움직일 포인터로 초깃값은 가장 앞 인덱스인 left
        int p = left;
        // 오른쪽 배열에서 움직일 포인터로 초깃값은 가장 앞 인덱스인 mid+1
        int q =mid +1;
        int idx = p;

        // 합병할 데이터가 남아있는 경우
        while (p <= mid || q <= right) {

            // 둘 다 남아있는 경우, 서로 비교하면서 포인터 값들 증가
            if (p <= mid && q <= right) {
                if (arr[p] <= arr[q]) {
                    tmp[idx++] = arr[p++];
                } else {
                    tmp[idx++] = arr[q++];
                }
            }

            // 둘 다 비교한 후, 한쪽이 끝에 도달한 경우
            // 남아있는 데이터를 처리
            if (p <= mid) {
                tmp[idx++] = arr[p++];
            }

            if (q <= right) {
                tmp[idx++] = arr[q++];
            }

            // 원래 배열에 정렬된 데이터를 넣기
            for (int i = left; i <= right; i++) {
                arr[i] = tmp[i];
            }
        }
    }
}
