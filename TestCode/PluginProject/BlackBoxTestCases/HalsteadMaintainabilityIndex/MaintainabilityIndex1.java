public class MaintainabilityIndex1 {
	public MaintainabilityIndex1() {

		// MI= 171-5.2*log2(64*4) - 0.23 * 1 - 16.2 * 4 + 50 = 114.37
		int first = 0; //4
		int second = first++; //6
		int third = second - first;//9
		int fourth = second * third;//12
		int fifth = first + third;//15
		int sixth = fifth--;//17
		int seventh = second + third;//20
		int eighth = seventh + first;//23
		int ninth = first + third;//26
		first = first + second + third + fourth + fifth + sixth + seventh + eighth + first + second + third; //38
	}
}