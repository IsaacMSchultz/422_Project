// This test is used to find faults counting the number of looping structures
// Total of: 1

class NumberOfLoopsCheck2 { // total of: 1
    NumberOfLoopsCheck2() {
        boolean condition = true;

        for (int i = 0; i > 10; i++) { // 1

        }

        // do {

        // } while (condition == true);

        // while (contition == true) {

        // }
    }
}