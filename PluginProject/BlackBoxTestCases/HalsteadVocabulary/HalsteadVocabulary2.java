class HalsteadVocabulary2 // class def counts as operand
{
	HalsteadVocabulary2() { //method def counts as operand
	// Halstead Vocabulary: Sum of Unique Operators (n1) and Operands (n2) in the
	// file n = n1 + n2

	// ASSIGN token type "=" is an operator only first occurence should count: n1 =
	// 1
	// LITERAL_INT token type "int' is an operand ony first occurence should count
	// n2 = 1
	// IDENT token type operand1 and operand2 should each be a Operand: n2 = 3
	// public int operand1 = 1;
	// public int operand2 = 2;

	// Each add operator should count as a unique occurence
	// POST_INC: "++" should count as a unique operator
	// UNARY_PLUS: "+" should count as a unique operator
	// PLUS_ASSIGN: "+=" should count as a unique operator
	// n2 + 0 = 3, n1 + 3 = 4
	// operand1 = operand1++;
	// operand1 = operand1 + operand2;
	// operand1 += operand2;

	// Each subtract operator should count as a unique occurence
	// MINUS_INC: "--" should count as a unique operator
	// UNARY_MINUS: "-" should count as a unique operator
	// MINUS_ASSIGN: "-=" should count as a unique operator
	// n2 + 0 = 3, n1 + 3 = 7
	// operand1 = operand1--;
	// operand1 = operand1 - operand2;
	// operand1 -= operand1;

	// Each multiply operator should count as a unique occurence
	// STAR: "*" should count as a unique operator
	// STAR_ASSIGN: "*=" should count as operator
	// N2 + 0 = 3, N1 + 2 = 9
	// operand1 = operand1 * operand2;
	// operand1 *= operand1;

	// Each divide operator should count as a unique operator
	// DIV: "/" should count as unique operator
	// DIV_ASSIGN: "/=" should count as unique operator
	// n2 + 0 = 3, n1 + 2 = 11
	// operand1 = operand1 / operand2;
	// operand1 /= operand2;

	// Parenthesis in operation (left and right) should each count as unique
	// operator
	// Parenthesis should not count as operator when used in if, for, while...
	// EQUAL: "==" should count as unique operator
	// NOT_EQUAL: "!=" should count as unique operator
	// n2 + 0 = 3, n1 + 4 = 15
	// operand1 = (operand1 + operand2);
	// while (operator1 + operator2 != operator1 + operator2){
	// if (operator1 == operator2){
	// break;
	// }
	// break;
	// }

	// operators and operands in comments should not be counted
	// +, -, /, *, +=, -=, *=, /=, public bool test = false;, public double test1 =
	// 0.0
	// n2 + 0 = 3, n1 + 0 = 15

	// Total Count: n2 = 3 and n1 = 15
	}
}