/*
*   Tests the super.methodCall(), super is a local method call
*   Should return 4
*/

class ExternalMethodCheck4 { //total of: 3
    ExternalMethodCheck4() {
        otherClass.test(); //external
        anotherOtherClass.method(); //external
        otherClass.method(); //external
    }

    public void test() {
        otherClass.method(); //external
    }
}

class LocalMethodCheck4Child extends ExternalMethodCheck4 { //total of: 1
    LocalMethodCheck4Child() {
    }

    public void test2() {
        super.test(); // 1
    }
}