public class MaintainabilityCases2 {
	public void MaintainabilityCases2() {
		//MI=(171 - (5.2 * (log(64*4) / log(2)))) - (0.23 * 6) - (16.2 * (log(32) / log(2)))

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