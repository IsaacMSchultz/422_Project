/*
    Calls an external method
    Should return 1. 
*/


class ExternalMethodCheck2 {
    public ExternalMethodCheck2() {
        Math.random();

        this.Test(); //Should not count 

    }

    public void Test() {

    }
}