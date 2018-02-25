import data.GraphEdges;

import java.util.ArrayList;
import java.util.List;

class ResultFormatter {
    static String format(List<List<GraphEdges>> result) {
        List<String> myAnswer = new ArrayList<>();
        result.forEach(answer -> {
            StringBuilder temp = new StringBuilder();
            answer.forEach(edge -> temp.append(edge.getFrom().name).append("--->"));
            temp.append(answer.get(answer.size() - 1).getTo().name);
            myAnswer.add(temp.toString());
        });
        return String.join("\n",myAnswer);
    }
}
