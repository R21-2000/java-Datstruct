import com.datastruct.Heap;
import java.util.*;

public class HeapSort {
    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 40000, 80000};

        for (int n : sizes) {
            System.out.println("\nUkuran Data: " + n);

            // Buat data acak
            Integer[] data = generateRandomData(n);

            // HeapSort
            Heap<Integer, Integer> heap = new Heap<>(n, true); //false = max || true = min
            for (int value : data) {
                heap.add(value, value);
            }

            long start = System.nanoTime();
            heap.sort();
            long finish = System.nanoTime();

            System.out.println("HeapSort Waktu: " + ((finish - start) / 1_000_000) + " ms (" + (finish - start) + " ns)");

            // Salin data hasil sort
            Integer[] sortedData = new Integer[n];
            for (int i = 0; i < n; i++) {
                sortedData[i] = heap.getKey(i);
            }

            // Insertion Sort
            Integer[] insertionData = Arrays.copyOf(sortedData, sortedData.length);
            start = System.nanoTime();
            insertionSort(insertionData);
            finish = System.nanoTime();
            System.out.println("InsertionSort Waktu: " + ((finish - start) / 1_000_000) + " ms (" + (finish - start) + " ns)");

            // Selection Sort
            Integer[] selectionData = Arrays.copyOf(sortedData, sortedData.length);
            start = System.nanoTime();
            selectionSort(selectionData);
            finish = System.nanoTime();
            System.out.println("SelectionSort Waktu: " + ((finish - start) / 1_000_000) + " ms (" + (finish - start) + " ns)");

            // Bubble Sort
            Integer[] bubbleData = Arrays.copyOf(sortedData, sortedData.length);
            start = System.nanoTime();
            bubbleSort(bubbleData);
            finish = System.nanoTime();
            System.out.println("BubbleSort Waktu: " + ((finish - start) / 1_000_000) + " ms (" + (finish - start) + " ns)");
        }
    }

    static Integer[] generateRandomData(int n) {
        Integer[] data = new Integer[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            data[i] = rand.nextInt(n * 10);
        }
        return data;
    }

    static void insertionSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void selectionSort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    static void bubbleSort(Integer[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
