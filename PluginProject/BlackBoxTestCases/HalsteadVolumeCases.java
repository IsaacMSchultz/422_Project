public class HalseadVolumeTester {
	HalseadVolumeTester() {
	// Halstead Volume: The program volume (V) is measured as the Halstead Length (N1 + N2) times
	// the 2-base logarithm of the Halstead Vocabulary (n1 + n2): V = N log2 (n)
	
	// N2 = 2, N1 = 4, n1 = 1, n2 = 3
	public int operand1 = 1;
	public int operand2 = 2;
		
	// Mathematical operators should increase the Halstead Volume
	// N2 = 7, N1 = 12, n1 = 6, n2 = 3
	operand1 = (operand1 + operand2) - (operand1 * operand2)
	
	// Halstead Volume Calculation: N = 19 and  n = 9
	// V = 19 log2 (9) 
	// V = 19(3.1169925001)
	// V = 60.22857502	
	}
}