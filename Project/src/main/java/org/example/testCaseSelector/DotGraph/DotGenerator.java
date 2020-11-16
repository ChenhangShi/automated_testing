package org.example.testCaseSelector.DotGraph;


import java.util.ArrayList;

public interface DotGenerator {
    void generateDot();
    ArrayList<String> getVertexList();
    ArrayList<ArrayList<Integer>> getAdjacentList();
}
