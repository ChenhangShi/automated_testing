package org.example.testCaseSelector.DotGraph;

import com.ibm.wala.classLoader.ShrikeBTMethod;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.types.annotations.Annotation;
import org.example.testCaseSelector.Main;
import org.example.testCaseSelector.Tools.Tools;

import java.util.*;

public class DotByClass implements DotGenerator {
    private CallGraph callGraph = Main.getCallGraph();
    // 调用图顶点
    private ArrayList<String> vertexList = new ArrayList<>();
    // 调用图邻接表
    private ArrayList<ArrayList<Integer>> adjacentList = new ArrayList<>();

    @Override
    public ArrayList<String> getVertexList() {
        return vertexList;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getAdjacentList() {
        return adjacentList;
    }


    /*
    生成类的调用者的dot图
    */
    public void generateDot() {
        extractGraph();

        String projectName = Main.getProjectName();
        String dotContent = Tools.toDot(projectName,vertexList,adjacentList);
        Tools.writeFile("class-" + projectName + ".dot", dotContent);
    }

    private void extractGraph() {
        // 获取所有顶点
        for (CGNode node : callGraph) {
            if (node.getMethod() instanceof ShrikeBTMethod) {
                ShrikeBTMethod method = (ShrikeBTMethod) node.getMethod();
                // 使用Primordial类加载器加载的类都属于Java原生类，一般不关心
                if ("Application".equals(method.getDeclaringClass().getClassLoader().toString())) {
                    String classInnerName = method.getDeclaringClass().getName().toString();
                    String signature = method.getSignature();
                    if (!vertexList.contains(classInnerName)) {
                        vertexList.add(classInnerName);
                        adjacentList.add(new ArrayList<>());
                    }
                }
            }
        }
        // 获取类调用图邻接表
        for (CGNode node : callGraph) {
            if (node.getMethod() instanceof ShrikeBTMethod) {
                ShrikeBTMethod method = (ShrikeBTMethod) node.getMethod();
                // 使用Primordial类加载器加载的类都属于Java原生类，一般不关心
                if ("Application".equals(method.getDeclaringClass().getClassLoader().toString())) {
                    String calleeInnerName = method.getDeclaringClass().getName().toString();
                    int calleeIndex = vertexList.indexOf(calleeInnerName);
                    // 获取本类的调用者
                    Iterator<CGNode> callerNodesItr = callGraph.getPredNodes(node);
                    while (callerNodesItr.hasNext()) {
                        CGNode callerNode = callerNodesItr.next();
                        if (callerNode.getMethod() instanceof ShrikeBTMethod) {
                            ShrikeBTMethod callerMethod = (ShrikeBTMethod) callerNode.getMethod();
                            if ("Application".equals(callerMethod.getDeclaringClass().getClassLoader().toString())) {
                                String callerInnerName = callerMethod.getDeclaringClass().getName().toString();
                                Integer callerIndex = vertexList.indexOf(callerInnerName);
                                if (!adjacentList.get(calleeIndex).contains(callerIndex))
                                    adjacentList.get(calleeIndex).add(callerIndex);
                            }
                        }
                    }

                }
            }
        }
    }
}
