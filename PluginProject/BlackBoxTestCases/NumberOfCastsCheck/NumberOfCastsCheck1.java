// This test case covers when there are external method calls and internal method calls.
// Total of: 3

class NumberOfCastsCheck1 { // total of: 3
    NumberOfCastsCheck1() {
        int a = 0, b = 1, c = 2;
        double d = 1.2, e = 3.5, f = 34.1;
        a = (int) d;
        e = (double) b;
        c = a;
    }
}