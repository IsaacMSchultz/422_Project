// This test tries all the unique operators 
// This test should return 
// 40 unique operators 


class OperatorCountCheck1 {
    
    OperatorCountCheck1() {
       int equals = 5; //1
       int bitwiseAnd = 5 & 5; //3
       int bitwiseAndAssign = bitwiseAnd &= 5; // 5
       int bitwiseNot = ~5; //8
       int bitwiseOr = 5 | 5; // 9
       int shiftright = bitwiseAnd >>> 5; // 11
       int bitwiseOrAssign = bitwiseAnd |= 5; //13
       int bitwiseXor = 5 ^ 5; //15
       int bitwiseXorAssign = bitwiseAnd ^= 5; //17
       String[] commaTest = new String[] {"One", "Two"}; // 19
        for(String colonTest : testArray) {} //20
       equals--; //21
       equals++; //22
       double divide = 5/5; //24
       double divideAssign = divide /= 5; //26
       double dotTest = 5.3; //27
       boolean equalsTest = 5 == 5; //29
       boolean greaterThanOrEqual = 5 >= 5; //31
       boolean greaterThan = 5 > 5; //32
       String indexTest = commaTest[0]; //34
       boolean condAnd = equalsTest && greaterThan; //36
       boolean lessThan = 5 < 5; //38
       boolean lessThanOrEqual = 5 <= 5; // 40
       boolean instance_of = (equalsTest instanceof greaterThan); //42
       boolean logicalNot = !greaterThan; //44
       boolean condOr = greaterThan || equalsTest; // 46
       int minus = 5 - 5; //48
       int minusAssin = minus -= 5; //50
       int mod = 5 % 5; // 52
       int modAssign = mod %= 5; // 54
       boolean notEqual = 5 != 5; // 56
       int add = 5 + 5; //58
       int addAssing = add += 5; //59
       int res = lessThan ? 5 : 4; //63
       int shiftleft = bitwiseAnd << 5; //65
       int shiftLeftAssign = bitwiseAnd <<= 5; //67
       int shiftRightAssign = bitwiseAnd >>= 5; //69
       int shiftRight2 = bitwiseAnd >> 5; // 71
       int multiply = 5 * 5; //73
       int multiplyAssing = multiply *= 5;// 75
    }
}