// This test is used to find faults counting the number of casts in code, and in comments
// Total of: 3

class NumberOfCastsCheck3 { // total of: 3
    NumberOfCastsCheck3() {
        int a = 0, b = 1, c = 2;
        double d = 1.2, e = 3.5, f = 34.1;
        a = (int) d;
        e = (double) b;
        c = (int) a;
        // a = (int) d;
        // e = (double) b;
        // c = a;
    }
}