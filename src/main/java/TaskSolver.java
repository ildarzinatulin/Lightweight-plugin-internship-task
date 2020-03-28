import java.util.regex.Pattern;

class TaskSolver {
    private final CallChainChecker callChainChecker;

    TaskSolver(CallChainChecker callChainChecker) {
        this.callChainChecker = callChainChecker;
    }

    String solution(String callChain) {
        if (!callChainChecker.isCorrectCallChainSYNTAX(callChain)) {
            return "SYNTAX ERROR";
        }
        if (!callChainChecker.isCorrectCallChainType(callChain)) {
            return "TYPE ERROR";
        }

        String[] chains = callChain.split("%>%");

        String mapExpression = "element";
        StringBuilder filterExpression = new StringBuilder();
        Pattern chainPartsSeparator = Pattern.compile("[{}]");
        boolean wasCondition = false;

        for (String chain : chains) {
            String[] chainParts = chainPartsSeparator.split(chain);
            String command = chainParts[0];
            String expression = chainParts[1];
            switch (command) {
                case "map":
                    mapExpression = expression.replace("element", mapExpression);
                    break;
                case "filter":
                    if (wasCondition) {
                        filterExpression.append("&");
                    }
                    wasCondition = true;
                    filterExpression.append(expression.replace("element", mapExpression));
                    break;
            }
        }
        if (!wasCondition) filterExpression.append("element");

        return String.format("filter{%s}%%>%%map{%s}", filterExpression, mapExpression);
    }

}
