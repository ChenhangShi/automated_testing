package org.example.testCaseSelector.Tools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Tools {
    public static void writeFile(String path, String content){
        try {
            Writer writer = new FileWriter(path);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String toDot(String projectName, ArrayList<String> vertexList, ArrayList<ArrayList<Integer>> adjacentList){
        String dotContent = "digraph " + projectName + " {\n";
        for (int calleeIndex = 0; calleeIndex < vertexList.size(); calleeIndex++) {
            String callee = vertexList.get(calleeIndex);
            // 如果没有调用者
            if (adjacentList.get(calleeIndex).isEmpty())
                continue;
            for (int callerIndex : adjacentList.get(calleeIndex)) {
                String caller = vertexList.get(callerIndex);
                dotContent += "\t\"" + callee + "\" -> \"" + caller + "\";\n";
            }
        }
        dotContent.trim();
        dotContent += "}";
        return dotContent;
    }
}
