// This test case covers when there are external method calls and internal method calls.
// Total of: 3

class LocalMethodCheck1 { //total of: 3
    LocalMethodCheck1() {
        otherClass.test(); //external
        anotherOtherClass.method(); //external
        otherClass.method(); //external
        method(); //1
        REE(); //2
    }

    public void test() {
        otherClass.method(); //external
        method(); //3
    }
}