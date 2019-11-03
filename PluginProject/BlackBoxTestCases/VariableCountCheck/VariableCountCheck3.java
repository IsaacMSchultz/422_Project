// This will test if variable usages get counted as a definition
// Should return 0

class VariableCountCheck3 {
    private int test1; //1
    private String test2; //2
    private double test3; //3

    public VariableCountCheck3() {
        test1 = 0; //shouldnt count
        test2 = "Zero"; //shouldnt count
        test3 = 0.0; //shouldnt count
    }
}