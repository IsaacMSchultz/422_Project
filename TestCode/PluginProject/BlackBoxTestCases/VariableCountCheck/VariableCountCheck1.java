// This test will test all types of variable definitions
// public, protected, private variables
// different variable data types
// local variables and class level variables method variables
// 9 variable definitions

class VariableCountCheck1 {
    
    private int integer_one = 0; //1
    private double double_one = 0.0;//2
    public String string_one = "Zero";//3
    final int MAX_VAL = 100;//4

    public void test() {
        int integer_three = 3;//5

        if(integer_three == 3) {
            String three = "Three";//6
        }
    }
}


class Test2 {
    public int integer_two = 2;//7
    protected double double_two = 2.0;//8
    private String string_two = "Two";//9
}