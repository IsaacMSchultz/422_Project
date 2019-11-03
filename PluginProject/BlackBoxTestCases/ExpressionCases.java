class ExpressionCases {
	public ExpressionCases() {
		int i;

		i = 2 + 3;

		i = i + 5;

		boolean relational = 4 != 4;
		i = 1;

		expressionTest(i);
	}

	public int expressionTest(int x) {
		return x + 5;
	}
}