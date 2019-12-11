// This test file covers the case where method calls on a superclass from a child class are counted as external method calls
// Total of: 4

class LocalMethodCheck4 { //total of: 3
    LocalMethodCheck4() {
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

class LocalMethodCheck4Child extends LocalMethodCheck3 { //total of: 1
    LocalMethodCheck4Child() {
        super(); //does this count as a local method call?
    }

    public void test2() {
        super.test(); // 1
    }
}