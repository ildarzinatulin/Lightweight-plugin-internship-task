import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskSolverTest {
    private final CallChainChecker callChainChecker = new CallChainChecker();
    private final TaskSolver taskSolver = new TaskSolver(callChainChecker);

    @Test
    void testsFromExample() {
        assertEquals("filter{(element>10)&(element<20)}%>%map{element}",
                taskSolver.solution("filter{(element>10)}%>%filter{(element<20)}"));
        assertEquals("filter{((element+10)>10)}%>%map{((element+10)*(element+10))}",
                taskSolver.solution("map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}"));
        assertEquals("filter{(element>0)&(element<0)}%>%map{(element*element)}",
                taskSolver.solution("filter{(element>0)}%>%filter{(element<0)}%>%map{(element*element)}"));
    }

    @Test
    void incorrectInput() {
        assertEquals("TYPE ERROR",
                taskSolver.solution("filter{(element>10)%>%filter{(element<20)}"));
        assertEquals("SYNTAX ERROR",
                taskSolver.solution("mup{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}"));
        assertEquals("SYNTAX ERROR",
                taskSolver.solution("filter{(element>0)}%>%filter{(element<t0)}%>%map{(element*element)}"));
    }
}