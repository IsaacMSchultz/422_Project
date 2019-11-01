// This test is used to find faults counting the number of casts if they are in a comment
// Total of: 0

class NumberOfCastsCheck2 { // total of: 0
    NumberOfCastsCheck2() {
        int a = 0, b = 1, c = 2;
        double d = 1.2, e = 3.5, f = 34.1;
        // a = (int) d;
        // e = (double) b;
        // c = a;
    }
}