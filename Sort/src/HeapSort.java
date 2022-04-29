/**
 * Heap Sort(힙 정렬)
 * - Heap 자료구조 기반 정렬 방식
 *
 * - 이진 tree 자료구조 성질 이용 (0번 인덱스부터 시작한다는 기준)
 * - 1. 왼쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 1
 * - 2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 × 2 + 2
 * - 3. 부모 노드 인덱스 = (자식 노드 인덱스 - 1) / 2
 * -
 * - 장점
 * - 부분 정렬을 할 때 효과적
 * - 최악의 경우에도 시간 복잡도 = O(nlogn)
 * -
 * - 단점
 * - 일반적인 O(nlogn) 정렬 알고리즘에 비해 성능이 조금 떨어진다.
 * - Stable sort 가 아니다.
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        // 기존 배열을 max heap 자료구조로 변경
        // 성질 3번 이용
        // 자식 노드가 있는 애들 까지만 반복문을 돌리려면 마지막 노드의 부모 노드 인덱스를 알면 된다.
        // arr.length /2 - 1 = (arr.length - 1 (마지막 자식 노드 인덱스) - 1 )/ 2
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // 오름차순으로 정렬
        for (int i = arr.length-1; i >0 ; i--) {
            // 힙 자료구조로 변경되어있는 상태이기 때문에 가장 앞의 값이 가장 큰 값이므로
            // 맨 앞 값을 뒤로 보내주는 것을 반복한다.
            swap(arr, 0, i);
            heapify(arr,0,i);
        }
    }


    private static void heapify(int[] arr, int parentIdx, int size) {
        //  성질 1,2번 이용
        int leftIdx = 2 * parentIdx + 1;
        int rightIdx = 2 * parentIdx + 2;
        int maxIdx = parentIdx;

        // 부모 노드와 자식 노드들 비교 후, 큰 값이 부모 노드로 오게하기 위함
        if (leftIdx < size && arr[maxIdx] < arr[leftIdx]) {
            maxIdx = leftIdx;
        }

       if (rightIdx < size && arr[maxIdx] < arr[rightIdx]) {
            maxIdx = rightIdx;
        }

        // 부모노드가 자식 노드들과 비교된 후 값이 바뀌었을 때, 실질적인 교체가 이루어지는 부분
        if (parentIdx != maxIdx) {
            swap(arr,parentIdx,maxIdx);

            // 원래 자식노드 인덱스 위치의 값이 바뀌었으니 다시 정렬을 해주어야하기 때문에 heapify 재귀호출
            heapify(arr,maxIdx,size);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
