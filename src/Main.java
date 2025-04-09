import com.datastruct.Heap;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] sizes = {10000, 20000, 40000, 80000};

        for (int n : sizes) {
            System.out.println("Ukuran data: " + n);

            // Generate data acak
            List<Integer> data = generateRandomList(n);
            List<Integer> copy1 = new ArrayList<>(data);
            List<Integer> copy2 = new ArrayList<>(data);
            List<Integer> copy3 = new ArrayList<>(data);
            List<Integer> copy4 = new ArrayList<>(data);

            // Heapsort
            long start = System.nanoTime();
            Heap<Integer, Integer> heap = new Heap<>(n, false); // false = max heap
            for (int val : copy1) {
                heap.insert(val, val);
            }
            heap.sort();
            long end = System.nanoTime();
            System.out.println("Heapsort: " + (end - start) + " ns (" + (end - start) / 1_000_000 + " ms)");

            // Insertion Sort
            start = System.nanoTime();
            insertionSort(copy2);
            end = System.nanoTime();
            System.out.println("Insertion Sort: " + (end - start) + " ns (" + (end - start) / 1_000_000 + " ms)");

            // Selection Sort
            start = System.nanoTime();
            selectionSort(copy3);
            end = System.nanoTime();
            System.out.println("Selection Sort: " + (end - start) + " ns (" + (end - start) / 1_000_000 + " ms)");

            // Bubble Sort
            start = System.nanoTime();
            bubbleSort(copy4);
            end = System.nanoTime();
            System.out.println("Bubble Sort: " + (end - start) + " ns (" + (end - start) / 1_000_000 + " ms)");

            System.out.println("----------------------------------------------------");
        }
    }

    public static List<Integer> generateRandomList(int size) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(rand.nextInt(size));
        }
        return list;
    }

    public static void insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }

    public static void selectionSort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) < list.get(min_idx)) {
                    min_idx = j;
                }
            }
            int temp = list.get(min_idx);
            list.set(min_idx, list.get(i));
            list.set(i, temp);
        }
    }

    public static void bubbleSort(List<Integer> list) {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
