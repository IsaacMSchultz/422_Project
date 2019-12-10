package TeamRebecca;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadMetricsCheck extends AbstractCheck {
    private int operatorsCount = 0;
    private int operandsCount = 0;
    private int uniqueOperators = 0;
    private int uniqueOperands = 0;
    private int cycTotal = 1;
    private List<String> globalVariables = new ArrayList<String>();
    private List<String> tempList = new ArrayList<String>();

    public int getOperatorsCount() {return operatorsCount;}
    public int getOperandsCount() {return operandsCount;}
    public int getUniqueOperators() {return uniqueOperators;}
    public int getUniqueOperands() {return uniqueOperands;}
    public int getCyclomaticComplexity() {return cycTotal;}

    // Sum of total operators and operands
    private int halsteadLength;
    public int getHalsteadLength() {
        return halsteadLength;
    }

    // Unique number of operators and operands
    private int halsteadVocabulary;

    public int getHalsteadVocabulary() {
        return halsteadVocabulary;
    }

    // Program length (N) times log_2 halsteadVocabulary
    private double halsteadVolume;

    public double getHalsteadVolume() {
        return halsteadVolume;
    }

    // D = (uniqueOperators / 2) * (operandsCount / uniqueOperands)
    private double halsteadDifficulty;

    public double getHalsteadDifficulty() {
        return halsteadDifficulty;
    }

    // halsteadVolume * halsteadDifficulty
    private double halsteadEffort;

    public double getHalsteadEffort() {
        return halsteadEffort;
    }

    // 171 - 52 * log_2(halsteadVolume) - 0.23 * (cycTotal) - 16.2 *
    // log_2(getLines().length)
    // Not doing comments lol f adding more
    private double maintainabilityIndex;

    public double getMaintainabilityIndex() {
        return maintainabilityIndex;
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    @Override
    public void beginTree(DetailAST ast) {
        operatorsCount = 0;
        operandsCount = 0;
        uniqueOperators = 0;
        uniqueOperands = 0;
        cycTotal = 1;
        globalVariables.clear();
        tempList.clear();


        // Sum of total operators and operands
        halsteadLength = 0;

        // Unique number of operators and operands
        halsteadVocabulary = 0;

        // Program length (N) times log_2 halsteadVocabulary
        halsteadVolume = 0;

        // D = (uniqueOperators / 2) * (operandsCount / uniqueOperands)
        halsteadDifficulty = 0;

        // halsteadVolume * halsteadDifficulty
        halsteadEffort = 0;

        // 171 - 52 * log_2(halsteadVolume) - 0.23 * (cycTotal) - 16.2 *
        // log_2(getLines().length)
        // Not doing comments lol f adding more
        maintainabilityIndex = 0;
    }

    @Override
    public void finishTree(DetailAST ast) {

        // Sum of total operators and operands
        halsteadLength = getOperatorsCount() + getOperandsCount();

        // Unique number of operators and operands
        halsteadVocabulary = getUniqueOperators() + getUniqueOperands();

        // Program length (N) times log_2 halsteadVocabulary
        halsteadVolume = getHalsteadLength() * (Math.log(getHalsteadVocabulary()) / Math.log(2));

        // D = (uniqueOperators / 2) * (operandsCount / uniqueOperands)
        halsteadDifficulty = (((double) getUniqueOperators() / 2)
                * (double) (getOperandsCount() / (double) getUniqueOperands()));

        // halsteadVolume * halsteadDifficulty
        halsteadEffort = getHalsteadDifficulty() * getHalsteadVolume();

        // 171 - 52 * log_2(halsteadVolume) - 0.23 * (cycTotal) - 16.2 *
        // log_2(getLines().length)
        // Not doing comments lol f adding more
        maintainabilityIndex = (171 - (5.2 * (Math.log(getHalsteadVolume()) / Math.log(2)))) - (0.23 * getCyclomaticComplexity())
                - (16.2 * (Math.log(getLOC()) / Math.log(2)));

        try {
            log(ast.getLineNo(), "Number of Operators: " + operatorsCount);
            log(ast.getLineNo(), "Number of Operands: " + operandsCount);
            log(ast.getLineNo(), "Halstead Length: " + halsteadLength);
            log(ast.getLineNo(), "Halstead Vocabulary: " + halsteadVocabulary);
            log(ast.getLineNo(), "Halstead Difficulty: " + halsteadDifficulty);
            log(ast.getLineNo(), "Halstead Volume: " + halsteadVolume);
            log(ast.getLineNo(), "Halstead Effort: " + halsteadEffort);
            log(ast.getLineNo(), "Maintainability Index: " + maintainabilityIndex);
        } catch (NullPointerException e) {
            System.out.println("Can't run log unless called from treewalker!");
        }
    }

    // Dan using weird functions that CANNOT BE MOCKED!!!!
    public int getLOC() {
        return getLines().length;
    }

    @Override
    public void visitToken(DetailAST ast) {

        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

        /*removed for simplcity*/

        // Global operands
        DetailAST child = objBlock.getFirstChild();

        while (child != null) {
            if (child.getType() == TokenTypes.VARIABLE_DEF) {
                globalVariables.add(child.findFirstToken(TokenTypes.IDENT).getText());
                tempList.add(child.findFirstToken(TokenTypes.IDENT).getText());
                uniqueOperands++;
                operandsCount++;
                operandsCount += countOperands(child);
                operatorsCount += countOperators(child);
            }
            if (child.getType() == TokenTypes.METHOD_DEF) {
                // Add one for the open curly bracket for the statement list
                operatorsCount++;
                operandsCount += countOperands(child.findFirstToken(TokenTypes.SLIST));
                operatorsCount += countOperators(child.findFirstToken(TokenTypes.SLIST));
                tempList.clear();
            }
            child = child.getNextSibling();
        }

    }

    private int[] operatorTokens() {
        return new int[]{TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR,
                TokenTypes.MOD, TokenTypes.LCURLY, TokenTypes.RCURLY, TokenTypes.SLIST, TokenTypes.RPAREN,
                TokenTypes.LPAREN, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR,
                TokenTypes.METHOD_CALL, TokenTypes.DOT, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE,
                TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_BREAK,
                TokenTypes.COMMA, TokenTypes.RBRACK, TokenTypes.ARRAY_DECLARATOR, TokenTypes.ARRAY_INIT,
                TokenTypes.SEMI, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BAND_ASSIGN, TokenTypes.BAND,
                TokenTypes.BSR_ASSIGN, TokenTypes.BSR, TokenTypes.BXOR_ASSIGN, TokenTypes.BXOR, TokenTypes.DIV_ASSIGN,
                TokenTypes.MINUS_ASSIGN, TokenTypes.MOD_ASSIGN, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
                TokenTypes.SL_ASSIGN, TokenTypes.BNOT, TokenTypes.SR_ASSIGN, TokenTypes.SR, TokenTypes.STAR_ASSIGN,
                TokenTypes.INC, TokenTypes.POST_INC, TokenTypes.DEC, TokenTypes.POST_DEC, TokenTypes.LITERAL_DEFAULT};
    }

    private int[] operandTokens() {
        return new int[]{TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
                TokenTypes.IDENT};
    }

    public int countOperators(DetailAST ast) {
        if (ast.getChildCount() > 0) {
            int count = 0;

            // Count default as normal pathing.
            if (ast.getType() == TokenTypes.LITERAL_IF || ast.getType() == TokenTypes.LITERAL_CASE) {
                cycTotal++;
            }

            for (int n : operatorTokens()) {
                count += ast.getChildCount(n);
            }

            // Find first child, assuming first method
            DetailAST child = ast.getFirstChild();

            // Keep checking each method until we have no more
            while (child != null) {
                count += countOperators(child);
                child = child.getNextSibling();
            }
            return count;

            // Means class/interface didn't have nothin
        } else {
            return 0;
        }
    }

    public int countOperands(DetailAST ast) {
        if (ast.getChildCount() > 0) {
            int count = 0;
            int temp = 0;

            // This gross thing cause duplicate global variables aren't unique
            for (int n : operandTokens()) {
                temp = ast.getChildCount(n);
                if (temp > 0) {
                    DetailAST child = ast.getFirstChild();
                    while (child != null) {
                        if (child.getType() == TokenTypes.IDENT) {
                            if (!globalVariables.contains(child.getText()) || !tempList.contains(child.getText())) {
                                uniqueOperands++;
                                tempList.add(child.getText());
                            }
                        }
                        child = child.getNextSibling();
                    }
                    count += temp;
                }
            }

            DetailAST child = ast.getFirstChild();

            while (child != null) {
                count += countOperands(child);
                child = child.getNextSibling();
            }
            return count;
        } else {
            return 0;
        }
    }
}
