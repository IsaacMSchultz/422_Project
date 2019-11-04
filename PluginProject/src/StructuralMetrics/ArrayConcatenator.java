package StructuralMetrics;

import java.util.HashSet;

// Simple array concatenator from https://stackoverflow.com/questions/16738371/merge-two-arrays-together
public final class ArrayConcatenator {
	public static int[] concatArray(int[] arrOne, int[] arrTwo) {
		
		int index = 0;
		
		HashSet<Integer> set = new HashSet<Integer>();

		for (int i = 0; i < arrTwo.length; i++) {
			set.add(arrTwo[i]);
		}
		for (int i = 0; i < arrOne.length; i++) {
			set.add(arrOne[i]);
		}
		
		int[] arrThree = new int[set.size()];
		
		for (int item : set) {
			arrThree[index++] = item;
		}

		return arrThree;
	}
}
