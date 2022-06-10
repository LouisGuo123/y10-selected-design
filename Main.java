import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int len = 50000;

		int[] arr = new int[len];
		for(int i = 0; i < len; i++) {
			arr[i] = i;
		}

		Sorts.permutate(arr);
		Sorts.mergeSort(arr);
		Sorts.permutate(arr);
		Sorts.mergeSort(arr);
		Sorts.permutate(arr);
		Sorts.mergeSort(arr);

		int trials = 100;
		double[] times = new double[trials];
		double average = 0;
		double average_comparisons = 0;
		for(int i = 0; i < trials; i++) {
			Sorts.permutate(arr);

			long start = System.nanoTime();
			Sorts.mergeSort(arr);
			long end = System.nanoTime();

			times[i] = (end - start) / 1000000000.0;
			average += (end - start) / (1000000000.0 * trials);
			average_comparisons += Sorts.comparisons / (double)trials;
			System.out.println("Trial " + (i + 1) + " done with " + Sorts.comparisons + " comparisons.");
		}

		System.out.println(average * 1000);
		System.out.println(average_comparisons);
		System.out.println(Arrays.toString(times));
	}

	public static int[] copyArray(int[] a) {
		int[] output = new int[a.length];

		for(int i = 0; i < a.length; i++) {
			output[i] = a[i];
		}

		return output;
	}
}