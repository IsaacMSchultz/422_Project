
public class MaintainabilityIndexCases {
	
	//MI= 171-5.2*log2(64*4) - 0.23 * 1 - 16.2 * 4 + 50 = 114.37
	
	int first  =  0;
	int second = first++;
	int third = second-first;
	int fourth = second * third;
	int fifth = first + third;
	int sixth = fifth--;
	int seventh = second+ third;
	int eighth = seventh + first;
	int ninth = first + third;
	first = first + second + third + fourth + fifth + sixth + seventh + eighth + first + second + third;
	
	

}
