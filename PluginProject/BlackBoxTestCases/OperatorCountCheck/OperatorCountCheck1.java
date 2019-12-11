// This test tries all the unique operators 
// This test should return 
// 40 unique operators 


class OperatorCountCheck1 { //                                                                                      1
    
    public void OperatorCountCheck1() { //                                                                          4
       int equals = 5; //1											// 1
       int bitwiseAnd = 5 & 5; //3
       int bitwiseAndAssign = bitwiseAnd &= 5; // 5
       int bitwiseNot = ~5; //7
       int bitwiseOr = 5 | 5; // 9									// 5
       int shiftright = bitwiseAnd >>> 5; // 11
       int bitwiseOrAssign = bitwiseAnd |= 5; //13
       int bitwiseXor = 5 ^ 5; //15
       int bitwiseXorAssign = bitwiseAnd ^= 5; //17
       String[] commaTest = new String[] {"One", "Two"}; // 19		// 10                                           12
        for(String colonTest : testArray) {} //20                                                                   18
       equals--; //21
       equals++; //22
       double divide = 5/5; //24
       double divideAssign = divide /= 5; //26						// 15
       double dotTest = 954326.5553; //27							// This is not a new operator! just using the equals here. the . does not count as an operator!
       boolean equalsTest = 5 == 5; //29
       boolean greaterThanOrEqual = 5 >= 5; //31
       boolean greaterThan = 5 > 5; //33
       String indexTest = commaTest[0]; //35						// 20                                           20
       boolean condAnd = equalsTest && greaterThan; //37
       boolean lessThan = 5 < 5; //39
       boolean lessThanOrEqual = 5 <= 5; // 41
       boolean instance_of = (equalsTest instanceof greaterThan); //43                                              22
       boolean logicalNot = !greaterThan; //45
       boolean condOr = greaterThan || equalsTest; // 47
       int minus = 5 - 5; //49
       int minusAssin = minus -= 5; //51
       int mod = 5 % 5; // 53
       int modAssign = mod %= 5; // 55
       boolean notEqual = 5 != 5; // 57
       int add = 5 + 5; //59
       int addAssing = add += 5; //61
       int res = lessThan ? 5 : 4; //64 //because checkstyle counts the colon as one too!
       int shiftleft = bitwiseAnd << 5; //66
       int shiftLeftAssign = bitwiseAnd <<= 5; //68
       int shiftRightAssign = bitwiseAnd >>= 5; //70
       int shiftRight2 = bitwiseAnd >> 5; // 72
       int multiply = 5 * 5; //74
       int multiplyAssing = multiply *= 5;// 76
    } //                                                                                                            23
} //                                                                                                                24