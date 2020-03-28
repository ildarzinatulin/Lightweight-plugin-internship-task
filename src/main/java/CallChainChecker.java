import java.util.regex.Pattern;

class CallChainChecker {
    private boolean isCorrectExpressionSyntax(String expression) {
        Pattern splitPattern = Pattern.compile("[-+*><=&|()]");
        String[] words = splitPattern.split(expression);
        for (String word : words) {
            if (!isCorrectWord(word)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCorrectWord(String word) {
        return word.equals("element") || word.matches("\\d+") || word.equals("");
    }

    private boolean isCorrectBraceBalance(String expression) {
        int braceBalance = 0;
        int squareBraceBalance = 0;
        for (char c : expression.toCharArray()) {
            switch (c) {
                case '(':
                    braceBalance++;
                    break;
                case ')':
                    braceBalance--;
                    break;
                case '{':
                    squareBraceBalance++;
                    break;
                case '}':
                    squareBraceBalance--;
                    break;
                default:
                    break;
            }
        }
        return braceBalance == 0 && squareBraceBalance == 0;
    }

    boolean isCorrectCallChainType(String callChain) {
        String[] chains = callChain.split("%>%");

        if (!isCorrectBraceBalance(callChain)) {
            return false;
        }

        Pattern chainPartsSeparator = Pattern.compile("[{}]");
        for (String chain : chains) {
            String[] chainParts = chainPartsSeparator.split(chain);
            if (chainParts.length != 2) {
                return false;
            }
        }
        return true;
    }

    boolean isCorrectCallChainSYNTAX(String callChain) {
        String[] chains = callChain.split("%>%");

        Pattern chainPartsSeparator = Pattern.compile("[{}]");
        for (String chain : chains) {
            String[] chainParts = chainPartsSeparator.split(chain);
            if (!(isCorrectCommandSyntax(chainParts[0]) && isCorrectExpressionSyntax(chainParts[1]))) {
                return false;
            }
        }
        return true;
    }

    private boolean isCorrectCommandSyntax(String chainPart) {
        return chainPart.equals("map") || chainPart.equals("filter");
    }
}
