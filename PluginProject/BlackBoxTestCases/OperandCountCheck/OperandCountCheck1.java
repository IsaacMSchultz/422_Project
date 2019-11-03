/*
* Description: This class tests the operand count check
* by checking valid operands, making sure it returns the correct operand count
* should return 116 operands. 
* 39 different operands from varaible names
* 	Additional 6 from different numbers and strings as operands
* Total of 45 different operands
*/

class OperandCountCheck1 { //1
    
    OperandCountCheck1() { //2
       int equals = 5; //4										//1
       int bitwiseAnd = 5 & 5; //7
       int bitwiseAndAssign = bitwiseAnd &= 5; //10
       int bitwiseNot = ~5; //12
       int bitwiseOr = 5 | 5; //15
       int shiftright = bitwiseAnd >>> 5; //18
       int bitwiseOrAssign = bitwiseAnd |= 5; //21
       int bitwiseXor = 5 ^ 5; //24
       int bitwiseXorAssign = bitwiseAnd ^= 5; //27
       String[] commaTest = new String[] {"One", "Two"}; //30	//3
        for(String colonTest : testArray) {} //33
       equals--; //34
       equals++;//35
       double divide = 5/5; //38
       double divideAssign = divide /= 5; //41
       double dotTest = 5.3; //43								//4
       boolean equalsTest = 5 == 5; //46
       boolean greaterThanOrEqual = 5 >= 5; //49
       boolean greaterThan = 5 > 5; //52
       String indexTest = commaTest[0]; //56					//5
       boolean condAnd = equalsTest && greaterThan; //59
       boolean lessThan = 5 < 5; //62
       boolean lessThanOrEqual = 5 <= 5; //65
       boolean instance_of = (equalsTest instanceof greaterThan); //68
       boolean logicalNot = !greaterThan; //70
       boolean condOr = greaterThan || equalsTest; //73
       int minus = 5 - 5; //76
       int minusAssin = minus -= 5; //79
       int mod = 5 % 5; //82
       int modAssign = mod %= 5; //85
       boolean notEqual = 5 != 5; //88
       int add = 5 + 5; //91
       int addAssing = add += 5; //94
       int res = lessThan ? 5 : 4; //98							//6
       int shiftleft = bitwiseAnd << 5; //101
       int shiftLeftAssign = bitwiseAnd <<= 5;//104
       int shiftRightAssign = bitwiseAnd >>= 5;//107
       int shiftRight2 = bitwiseAnd >> 5;//110
       int multiply = 5 * 5;//113
       int multiplyAssing = multiply *= 5; //116
    }
}