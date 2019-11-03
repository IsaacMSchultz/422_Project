// This test is used to find faults counting the number of looping structures
// Total of: 3

class NumberOfLoopsCheck1 { // total of: 3
    NumberOfLoopsCheck1() {
        boolean condition = true;

        for (int i = 0; i > 10; i++) { // 1

        }

        do { // 2

        } while (condition == true);

        while (contition == true) { // 3

        }
    }
}