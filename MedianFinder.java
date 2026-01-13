import java.util.Arrays;
import java.util.Scanner;

public class MedianFinder {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Recursively sort halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) {
            arr[k++] = leftArr[i++];
        }

        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }


     //Main function to sort and find median
    public static double sortAndFindMedian(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        // Create a copy to avoid modifying original
        int[] sortedArray = arr.clone();

        // Use our implemented merge sort
        mergeSort(sortedArray, 0, sortedArray.length - 1);

        System.out.println("Sorted array: " + Arrays.toString(sortedArray));

        int n = sortedArray.length;

        if (n % 2 != 0) {
            return sortedArray[n / 2];
        } else {
            int mid1 = sortedArray[n / 2 - 1];
            int mid2 = sortedArray[n / 2];
            return (mid1 + mid2) / 2.0;
        }
    }

    //Interactive UI
    public static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Median Calculator ===");
        System.out.println("Enter numbers separated by spaces:");
        System.out.print("> ");

        String input = scanner.nextLine();
        String[] numbersStr = input.trim().split("\\s+");

        if (numbersStr.length == 0 || (numbersStr.length == 1 && numbersStr[0].isEmpty())) {
            System.out.println("No input provided. Using example data.");
            runExample();
            scanner.close();
            return;
        }

        try {
            int[] numbers = new int[numbersStr.length];
            for (int i = 0; i < numbersStr.length; i++) {
                numbers[i] = Integer.parseInt(numbersStr[i]);
            }

            System.out.println("\nInput: " + Arrays.toString(numbers));
            double median = sortAndFindMedian(numbers);
            System.out.println("Sorted: " + Arrays.toString(numbers));
            System.out.printf("Median: %.2f\n", median);

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid integers only.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }


    //Test cases
    public static void runExample() {
        System.out.println("\n=== Example Test Cases ===");

        int[][] testCases = {
                {5, 2, 8, 1, 9},
                {4, 1, 7, 3},
                {10, 20, 30, 40, 50, 60}
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Array: " + Arrays.toString(testCases[i]));
            System.out.println("Median: " + sortAndFindMedian(testCases[i]));
        }
    }

    public static void main(String[] args) {
        interactiveMode();
    }
}