class ExternalMethodCases {

	public ExternalMethodCases() {
		int testVal = testFunction(5);

		testVal = Other.externalTest(3);
	}

	public int testFunction(int i) {
		return i + i;
	}
}