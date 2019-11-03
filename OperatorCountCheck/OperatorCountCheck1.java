// This test tries all the unique operators 
// This test should return 
// 40 unique operators 


class OperatorCountCheck1 {
    
    OperatorCountCheck1() {
       int equals = 5;
       int bitwiseAnd = 5 & 5;
       int bitwiseAndAssign = bitwiseAnd &= 5;
       int bitwiseNot = ~5;
       int bitwiseOr = 5 | 5;
       int shiftright = bitwiseAnd >>> 5;
       int bitwiseOrAssign = bitwiseAnd |= 5;
       int bitwiseXor = 5 ^ 5;
       int bitwiseXorAssign = bitwiseAnd ^= 5;
       String[] commaTest = new String[] {"One", "Two"};
        for(String colonTest : testArray) {}
       equals--;
       equals++;
       double divide = 5/5;
       double divideAssign = divide /= 5;
       double dotTest = 5.3;
       boolean equalsTest = 5 == 5;
       boolean greaterThanOrEqual = 5 >= 5;
       boolean greaterThan = 5 > 5;
       String indexTest = commaTest[0];
       boolean condAnd = equalsTest && greaterThan;
       boolean lessThan = 5 < 5;
       boolean lessThanOrEqual = 5 <= 5;
       boolean instance_of = (equalsTest instanceof greaterThan);
       boolean logicalNot = !greaterThan;
       boolean condOr = greaterThan || equalsTest;
       int minus = 5 - 5;
       int minusAssin = minus -= 5;
       int mod = 5 % 5;
       int modAssign = mod %= 5;
       boolean notEqual = 5 != 5;
       int add = 5 + 5;
       int addAssing = add += 5;
       int res = lessThan ? 5 : 4; //34
       int shiftleft = bitwiseAnd << 5;
       int shiftLeftAssign = bitwiseAnd <<= 5;
       int shiftRightAssign = bitwiseAnd >>= 5;
       int shiftRight2 = bitwiseAnd >> 5;
       int multiply = 5 * 5;
       int multiplyAssing = multiply *= 5;// 40
    }
}