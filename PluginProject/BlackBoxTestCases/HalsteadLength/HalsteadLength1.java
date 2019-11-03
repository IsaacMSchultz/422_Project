public class HalsteadLength1 {
	// Halstead Length: Sum of Operators and Operands in the file N = N1 + N2

	// ASSIGN token type "=" is an operator in each assignment should count: N1 = 2
	// LITERAL_INT token type "int' is an operand should count N2 = 2
	// IDENT token type Operand1 and Operator2 should each be a Operand: N2 = 4
	public int operand1 = 1;
	public int operand2 = 2;

	public HalsteadLength1() {
	
	// POST_INC: "++" should count as operator
	// N2 + 2 = 6, N1 + 2 = 4
	operand1 = operand1++;
	
	// UNARY_PLUS: "+" should count as operator
	// N2 + 3 = 9, N1 +2 = 6
	operand1 = operand1 + operand2;
	
	// PLUS_ASSIGN: "+=" should count as operator
	// N2 + 2 = 11, N1 + 1 = 7
	operand += operand1;
	
	// POST_DEC: "--" should count as operator
	// N2 + 2 = 13, N1 + 2 = 9
	operand1 = operand1--;
	
	// MINUS_ASSIGN: "-=" should count as operator
	// N2 + 2 = 15, N1 + 1 = 10;
	operand1 -= operand1;
	
	//UNARY_MINUS: "-" should count as operator
	// N2 + 3 = 18, N1 + 2 = 12
	operand1 = operand1 - operand2;
	
	// STAR: "*" should count as operator
	// N2 + 3 = 21, N1 + 2 = 14
	operand1 = operand1 * operand2;
	
	// STAR_ASSIGN: "*=" should count as operator
	// N2 + 2 = 23, N1 + 1 = 15
	operand1 *= operand1;
	
	// SR: ">>" should count as operator
	// N2 + 2 = 25, N1 + 2 = 17
	operand1 = operand1 >> 1;
	
	// SR_ASSIGN: ">>=" should count as effort
	// N2 + 2 = 27, N1 + 1 = 18
	operand1 >>= operand1;
	
	// LR: "<<" should count as operator
	// N2 + 2 = 29, N1 + 2 = 20
	operand1 = operand1 << 1;
	
	// LR_ASSIGN: "<<=" should count as operator
	// N2 + 2 = 31, N1 + 1 = 21
	operand1 <<= operand1;
	
	// RPAREN and LPAREN: "()" should count as operators
	// N2 + 3 = 34, N1 + 3 = 24
	operand1 = (operand1 + operand2);
	
	// Parenthesis should not count as operator when used in if, for, while...
	// NOT_EQUAL: "!=" should count as operator
	// MOD: "%" should count as operator
	// N2 + 4 = 38, N1 + 2 = 26
	if (operand1 != operand2){
		operand1 % operand2;
	}
	
	// EQUAL: "==" should count as operator
	// MOD_ASSIGN: "%=" should count as operator
	// N2 + 4 = 42, N1 + 2 =  28
	while (operand1 == operand2){
		operand1 %= operand1;
	}
	
	// BOR_ASSIGN: "|=" should count as operator
	// BAND_ASSIGN: "&=" should count as operator
	// BAND: "&" should count as operator
	// BOR: "|" should count as operator
	// LAND: "&&" should count as operator
	// LOR: "||" should count as operator
	// N2 + 14 = 56, N1 + 8 = 36
	operand1 |= operand1;
	operand1 &= operand1;
	operand1 = operand1 & operand2;
	operand 1 = operand1 | operand 2;
	operand1 && operand2;
	operand1 || operand2;
	
	// LT: "<" should count as operator
	// GT: ">" should count as operator
	// LE: "<=" should count as operator
	// GE: ">=" should count as opertor
	// N2 + 8 = 64, N1 + 4 = 40
	operand < operand2;
	operand1 > operand2;
	operand1 <= operand2;
	operand1 >= operand2;
	
	// DIV: "/" should count as operator
	// N2 + 3 = 67, N1 + 2 = 42
	operand1 = operand1 / operand2;
	
	// DIV_ASSIGN: "/=" should count as operator
	// N2 + 2 = 69, N1 + 1 =  43
	operand1 /= operand2;
	
	// LCURLY and RCURLY: "{}" should each count as operators
	// LBRACK and RBRACK: "[]" should each count as operators
	// N2 + 4 = 73, N1 + 6 = 49
	operand1 = operand2{};
	operand1 = operand[];
	
	// COLON: ":" should count as operator
	// COMMA: "," should count as operator
	// DOT: "." should count as operator
	// N2 + 6 = 79, N1 + 3 = 52
	operand1,operand2;
	operand2.operand1;
	operand1:operand2;
	
	// QUESTION: "?" should count as operator
	// N2 + 2 = 81, N1 + 1 = 53
	operand1 ? operand2;
	
	//Total number of operands (N1) = 81
	//Total number of operator (N2) = 53
}
}