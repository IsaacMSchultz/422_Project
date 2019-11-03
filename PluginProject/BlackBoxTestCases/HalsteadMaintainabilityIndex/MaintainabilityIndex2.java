public class MaintainabilityCases2 {
	MaintainabilityCases2() {
		//MI= 171-5.2*log2(64*4) - 0.23 * 4 - 16.2 * 5 + 50 = 97.48

		int first = 0;

		int second = first++;

		int third = second - first;

		int fourth = second * third;

		int fifth = first + third;

		int sixth = fifth--;

		int seventh = second + third;

		int eighth = seventh + first;

		for (int i = 0; i < eighth; i++) {
			first++;
		}

		if (first < seventh) {
			first = seventh + second;
		} else if (first > second) {
			first = fifth + sixth + seventh;
		}
	}
}
