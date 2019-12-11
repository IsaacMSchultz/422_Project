public class HalsteadLength1 {  // 1 operands from class def
	// Halstead Length: Sum of Operators and Operands in the file N = N1 + N2

	// ASSIGN token type "=" is an operator in each assignment should count: N1 = 2
	// LITERAL_INT token type "int' is an operand should count N2 = 2
	// IDENT token type Operand1 and Operator2 should each be a Operand: N2 = 4
	public int operand1 = 1; //2 operands, 1 operator
	public int operand2 = 2; //2 operands, 1 operator

	public HalsteadLength1() { //1 operand from method def

		// POST_INC: "++" should count as operator
		// N2 + 2 = 8, N1 + 2 = 4
		operand1 = operand1++;

		// UNARY_PLUS: "+" should count as operator
		// N2 + 3 = 11, N1 +2 = 6
		operand1 = operand1 + operand2;

		// PLUS_ASSIGN: "+=" should count as operator
		// N2 + 2 = 13, N1 + 1 = 7
		operand2 += operand1;

		// POST_DEC: "--" should count as operator
		// N2 + 2 = 15, N1 + 2 = 9
		operand1 = operand1--;

		// MINUS_ASSIGN: "-=" should count as operator
		// N2 + 2 = 17, N1 + 1 = 10;
		operand1 -= operand1;

		// UNARY_MINUS: "-" should count as operator
		// N2 + 3 = 20, N1 + 2 = 12
		operand1 = operand1 - operand2;

		// STAR: "*" should count as operator
		// N2 + 3 = 23, N1 + 2 = 14
		operand1 = operand1 * operand2;

		// STAR_ASSIGN: "*=" should count as operator
		// N2 + 2 = 25, N1 + 1 = 15
		operand1 *= operand1;

		// SR: ">>" should count as operator
		// N2 + 3 = 28, N1 + 2 = 17
		operand1 = operand1 >> 1;

		// SR_ASSIGN: ">>=" should count as effort
		// N2 + 2 = 30, N1 + 1 = 18
		operand1 >>= operand1;

		// LR: "<<" should count as operator
		// N2 + 3 = 33, N1 + 2 = 20
		operand1 = operand1 << 1;

		// LR_ASSIGN: "<<=" should count as operator
		// N2 + 2 = 35, N1 + 1 = 21
		operand1 <<= operand1;

		// RPAREN and LPAREN: "()" should count as operators
		// N2 + 3 = 38, N1 + 4 = 25
		operand1 = (operand1 + operand2); //bug in code is these do not count as operators (off by 2)

		// Parenthesis should not count as operator when used in if, for, while...
		// NOT_EQUAL: "!=" should count as operator
		// MOD: "%" should count as operator
		// N2 + 5 = 43, N1 + 3 = 28
		if (operand1 != operand2) {
			operand1 = operand1 % operand2;
		}

		// EQUAL: "==" should count as operator
		// MOD_ASSIGN: "%=" should count as operator
		// N2 + 4 = 47, N1 + 2 = 30
		while (operand1 == operand2) {
			operand1 %= operand1;
		}

		// BOR_ASSIGN: "|=" should count as operator
		// BAND_ASSIGN: "&=" should count as operator
		// BAND: "&" should count as operator
		// BOR: "|" should count as operator
		// LAND: "&&" should count as operator
		// LOR: "||" should count as operator
		// LITERAL_BOOL the Boolean definition is counted as an operand
		// N2 + 21 = 68, N1 + 14 = 44
		operand1 |= operand1;
		operand1 &= operand1;
		operand1 = operand1 & operand2;
		operand1 = operand1 | operand2;
		Boolean operand3 = operand1 == 1 && operand2 == 2;
		operand3 = operand1 == 1 || operand2 == 2;

		// LT: "<" should count as operator
		// GT: ">" should count as operator
		// LE: "<=" should count as operator
		// GE: ">=" should count as opertor
		// N2 + 12 = 80, N1 + 8 = 52
		operand3 = operand < operand2;
		operand3 = operand1 > operand2;
		operand3 = operand1 <= operand2;
		operand3 = operand1 >= operand2;

		// DIV: "/" should count as operator
		// N2 + 3 = 83, N1 + 2 = 54
		operand1 = operand1 / operand2;

		// DIV_ASSIGN: "/=" should count as operator
		// N2 + 2 = 85, N1 + 1 = 55
		operand1 /= operand2;

		// LCURLY and RCURLY: "{}" should each count as operators
		// LBRACK and RBRACK: "[]" should count as an operator
		// N2 + 5 = 90, N1 + 5 = 60
		int[] operand4 = { operand1 }; //BUG: Found that {} are not counted as operators (off by 4)
		operand1 = operand4[0];

		// COMMA: "," should count as operator
		// DOT: "." should count as operator
		// N2 + 7 = 97, N1 + 6 = 66
		int[] operand5 = { operand1, operand2 }; //Off by 2 in result here, doesnt catch {} (off by 6)
		operand1 = operand5.length; //bug: doesnt count .(kjashdjas) as operand

		// QUESTION: "?" should count as operator
		// COLON: ":" should count as operator
		// N2 + 5 = 102, N1 + 4 = 70
		operand1 = operand1 == 1 ? operand2 : operand1;

		// Total number of operands (N1) = 102 //off by 1
		// Total number of operator (N2) = 70 //off by 6
	}
}