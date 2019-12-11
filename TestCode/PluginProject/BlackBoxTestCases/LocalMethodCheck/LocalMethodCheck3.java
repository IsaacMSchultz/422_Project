// This test file covers the case where method calls on a superclass from a child class are counted as external method calls
// Total of: 2

class LocalMethodCheck3 { //total of: 1
    LocalMethodCheck3() {
        this.test();
    }

    public void test() {
        // for the test
    }
}

class LocalMethodCheck3Child extends LocalMethodCheck3 { //total of: 1
    LocalMethodCheck3Child() {
        super(); //does this count as a local method call?
    }

    public void test2() {
        super.test(); // 1
    }
}