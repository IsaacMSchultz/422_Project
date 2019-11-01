// This test is used to find faults counting the number of casts
// Total of: 2

class NumberOfCastsCheck1 { // total of: 2
    NumberOfCastsCheck1() {
        int a = 0, b = 1, c = 2;
        double d = 1.2, e = 3.5, f = 34.1;
        a = (int) d;
        e = (double) b;
        c = a;
    }
}