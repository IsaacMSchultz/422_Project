package StructuralMetrics;

// Simple array concatenator from https://stackoverflow.com/questions/16738371/merge-two-arrays-together
public final class ArrayConcatenator {
	public static int[] concatArray(int[] arrOne, int[] arrTwo) {
		int[] arrThree = new int[arrTwo.length + arrOne.length];
		int index = arrTwo.length;

		for (int i = 0; i < arrTwo.length; i++) {
			arrThree[i] = arrTwo[i];
		}
		for (int i = 0; i < arrOne.length; i++) {
			arrThree[i + index] = arrOne[i];
		}

		return arrThree;
	}
}
