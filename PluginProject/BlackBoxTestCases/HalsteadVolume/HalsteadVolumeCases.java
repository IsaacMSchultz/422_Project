public class HalseadVolumeTester {
	HalseadVolumeTester() {
		// Halstead Volume: The program volume (V) is measured as the Halstead Length
		// (N1 + N2) times
		// the 2-base logarithm of the Halstead Vocabulary (n1 + n2): V = N log2 (n)

		// N2 = 2, N1 = 4, n1 = 1, n2 = 3
		public int operand1 = 1;
		public int operand2 = 2;

		// Mathematical operators should increase the Halstead Volume
		// N2 = 15, N1 = 8, n1 = 4, n2 = 5
		operand1 = operand1 + operand2;
		operand2 = operand1 * operand2;
		operand1 = operand2 - operand1;

		// Halstead Volume Calculation: N = 23 and n = 9
		// V = 23 log2 (10)
		// V = 23(3.1169925001)
		// V = 72.90827
	}
}