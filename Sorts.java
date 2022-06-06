import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class Sorts {
	/**
	 * Number of comparisons made from the most recent sort
	 *
	 * Resets every time a new sort is done
	 */

	public static int comparisons = 0;

	/**
	 * Permutates an array randomly.
	 *
	 * @param arr array to be permutated
	 */

	public static void permutate(int[] arr) {
		long seed = ThreadLocalRandom.current().nextLong();
		permutate(arr, seed);
	}

	/**
	 * Permutates an array randomly with an associated seed.
	 * The seed determines what random numbers are produced.
	 *
	 * @param arr array to be permutated
	 * @param seed seed of the Java Random class
	 */

	public static void permutate(int[] arr, long seed) {
		Random rand = new Random(seed);

		for(int i = 0; i < arr.length - 1; i++) {
			swap(arr, i, rand.nextInt(arr.length - i) + i);
		}
	}

	/**
	 * Sorts an array in-place using bubble sort.
	 *
	 * @param arr array to be sorted
	 */

	public static void bubbleSort(int[] arr) {
		reset();

		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = 0; j < arr.length - 1 - i; j++) {
				if(comparator(arr[j + 1], arr[j])) {
					swap(arr, j, j + 1);
				}
			}
		}
	}

	/**
	 * Sorts an array in-place using insertion sort.
	 *
	 * @param arr array to be sorted
	 */

	public static void insertionSort(int[] arr) {
		reset();

		for(int i = 1; i < arr.length; i++) {
			for(int j = i - 1; j >= 0 && comparator(arr[j + 1], arr[j]); j--) {
				swap(arr, j, j + 1);
			}
		}
	}

	/**
	 * Sorts an array in-place using selection sort.
	 *
	 * @param arr array to be sorted
	 */

	public static void selectionSort(int[] arr) {
		reset();

		for(int i = 0; i < arr.length - 1; i++) {
			int min = arr[i];
			int index = i;

			for(int j = i + 1; j < arr.length; j++) {
				if(comparator(arr[j], min)) {
					min = arr[j];
					index = j;
				}
			}

			swap(arr, i, index);
		}
	}

	/**
	 * Sorts an array in-place using QuickSort.
	 *
	 * @param arr array to be sorted
	 */

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	private static void quickSort(int[] arr, int l, int r) {
		if(l > r) {
			return;
		}
		if(l == r) {
			return;
		}

		int p = pivot(arr, l, r);

		quickSort(arr, l, p - 1);
		quickSort(arr, p + 1, r);
	}

	private static int pivot(int[] arr, int l, int r) {
		int mid = l + (r - l)/2;

		if(comparator(arr[mid], arr[l])) {
			swap(arr, l, mid);
		}
		if(comparator(arr[r], arr[l])) {
			swap(arr, l, r);
		}
		if(comparator(arr[mid], arr[r])) {
			swap(arr, mid, r);
		}

		int pivot = arr[r];

		int to_swap = l;
		for(int i = l; i < r; i++) {
			if(comparator(arr[i], pivot)) {
				swap(arr, i, to_swap);
				to_swap++;
			}
		}

		swap(arr, to_swap, r);

		return to_swap;
	}

	/**
	 * Sorts an array in-place using merge sort.
	 *
	 * @param arr array to be sorted
	 */

	public static void mergeSort(int[] arr) {
		reset();
		mergeSplit(arr);
	}

	public static void mergeSplit(int[] arr) {
		if(arr.length <= 1) {
			return;
		}

		int mid = arr.length / 2;
		int[] left_arr = new int[mid];
		int[] right_arr = new int[arr.length - mid];

		for(int i = 0; i < mid; i++) {
			left_arr[i] = arr[i];
		}
		for(int i = 0; i < arr.length - mid; i++) {
			right_arr[i] = arr[i + mid];
		}

		mergeSplit(left_arr);
		mergeSplit(right_arr);
		merge(arr, left_arr, right_arr);
	}

	public static void merge(int[] arr, int[] left_arr, int[] right_arr) {
		int left_i = 0;
		int right_i = 0;

		for(int i = 0; i < arr.length; i++) {
			if(left_i < left_arr.length &&
					(right_i >= right_arr.length || comparator(left_arr[left_i], right_arr[right_i]))) {
				arr[i] = left_arr[left_i];
				left_i++;
			}
			else {
				arr[i] = right_arr[right_i];
				right_i++;
			}
		}
	}

	public static void bogoSort(int[] arr) {
		while(!isInOrder(arr)) {
			permutate(arr);
		}
	}

	public static void reset() {
		comparisons = 0;
	}

	public static boolean comparator(int a, int b) {
		comparisons++;
		if(a < b) {
			return true;
		}
		else {
			return false;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	public static boolean isInOrder(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			if(!comparator(arr[i], arr[i + 1])) {
				return false;
			}
		}
		return true;
	}
}
