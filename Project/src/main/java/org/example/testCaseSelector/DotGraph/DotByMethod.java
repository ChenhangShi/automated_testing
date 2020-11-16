package org.example.testCaseSelector.DotGraph;

import com.ibm.wala.classLoader.ShrikeBTMethod;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import org.example.testCaseSelector.Main;
import org.example.testCaseSelector.Tools.Tools;

import java.util.ArrayList;
import java.util.Iterator;

public class DotByMethod implements DotGenerator {
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


    @Override
    public void generateDot() {
        extractGraph();

        String projectName = Main.getProjectName();
        String dotContent = Tools.toDot(projectName,vertexList,adjacentList);
        Tools.writeFile("method-" + projectName + ".dot", dotContent);
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
                    String vertex = classInnerName + ' ' + signature;
                    if (!vertexList.contains(vertex)) {
                        vertexList.add(vertex);
                        adjacentList.add(new ArrayList<>());
                    }
                }
            }
        }
        // 获取方法调用图邻接表
        for (CGNode node : callGraph) {
            if (node.getMethod() instanceof ShrikeBTMethod) {
                ShrikeBTMethod method = (ShrikeBTMethod) node.getMethod();
                // 使用Primordial类加载器加载的类都属于Java原生类，一般不关心
                if ("Application".equals(method.getDeclaringClass().getClassLoader().toString())) {
                    String classInnerName = method.getDeclaringClass().getName().toString();
                    String signature = method.getSignature();
                    String callee = classInnerName + ' ' + signature;
                    int calleeIndex = vertexList.indexOf(callee);
                    // 获取本方法的调用者
                    Iterator<CGNode> callerNodesItr = callGraph.getPredNodes(node);
                    while (callerNodesItr.hasNext()) {
                        CGNode callerNode = callerNodesItr.next();
                        if (callerNode.getMethod() instanceof ShrikeBTMethod) {
                            ShrikeBTMethod callerMethod = (ShrikeBTMethod) callerNode.getMethod();
                            if ("Application".equals(callerMethod.getDeclaringClass().getClassLoader().toString())) {
                                String callerInnerName = callerMethod.getDeclaringClass().getName().toString();
                                String callerSigniture = callerMethod.getSignature();
                                String caller = callerInnerName + ' ' + callerSigniture;
                                Integer callerIndex = vertexList.indexOf(caller);
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
