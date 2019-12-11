// This test file covers the case where the method call is within a comment
// Total of: 0

class LocalMethodCheck2 { //total of: 0
    LocalMethodCheck2() {
        // otherClass.test(); //external
        // anotherOtherClass.method(); //external
        // otherClass.method(); //external
        // method(); //1
        // REE(); //2
    }

    public void test() {
        // otherClass.method(); //external
        // method(); //3
    }
}