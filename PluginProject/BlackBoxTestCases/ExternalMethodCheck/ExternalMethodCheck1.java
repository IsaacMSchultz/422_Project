/*
    Calls a local method 
    Should return 0. 
*/

class ExternalMethodCheck1 {
    public ExternalMethodCheck1() {
        Test();
        this.Test(); //
    }

    public void Test() {
        System.out.println("This is a test");
    }
}