import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String callChain = in.nextLine();
        in.close();

        CallChainChecker callChainChecker = new CallChainChecker();
        TaskSolver taskSolver = new TaskSolver(callChainChecker);
        System.out.println(taskSolver.solution(callChain));
    }

}
