import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] arr = {5, 3, 4, 7, 1};
		Sorts.mergeSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}