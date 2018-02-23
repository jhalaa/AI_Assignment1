import data.GraphEdges;

import java.util.ArrayList;
import java.util.List;

class ResultFormatter {
    static String format(List<List<GraphEdges>> result) {
        List<String> myAnswer = new ArrayList<>();
        for (List<GraphEdges> answer : result){
            StringBuilder temp= new StringBuilder();
            for (GraphEdges edge : answer){
                temp.append(edge.getFrom().name).append("--->");
            }
            temp.append(answer.get(answer.size() - 1).getTo().name);
            myAnswer.add(temp.toString());
        }
        return String.join("\n",myAnswer);
    }
}
