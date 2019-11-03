
class CommentCases {
    // a single line of comments should count once

    //two single line comments
    //one after the other, like this, should count once

    /*one line of a block comment should count once*/

    /*multiple
    lines
    of
    a 
    block
    comment
    should
    count
    once
    */

    String commentTest = "'//' should not count as a comment";

    String commentTest2 = "/*should not be counted as a comment*/";

    int i = 0; //inline comments should be counted
}