package org.example.testCaseSelector;

import com.ibm.wala.classLoader.Language;
import com.ibm.wala.classLoader.ShrikeBTMethod;
import com.ibm.wala.ipa.callgraph.*;
import com.ibm.wala.ipa.callgraph.impl.AllApplicationEntrypoints;
import com.ibm.wala.ipa.callgraph.impl.Util;
import com.ibm.wala.ipa.callgraph.propagation.SSAPropagationCallGraphBuilder;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.annotations.Annotation;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.config.AnalysisScopeReader;
import org.example.testCaseSelector.TestCaseSelector.SelectorByClass;
import org.example.testCaseSelector.TestCaseSelector.SelectorByMethod;
import org.example.testCaseSelector.TestCaseSelector.TestCaseSelector;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    // 项目名称
    private static String projectName;
    // 调用图
    private static CallGraph callGraph;
    // 变更信息
    private static HashSet<String> changes = new HashSet<>();
    // 用例选择器
    private static TestCaseSelector testCaseSelector;
    // test类和其@Test方法
    private static HashMap<String,HashSet<String>> testMethodsOfTestClasses = new HashMap<>();

    public static CallGraph getCallGraph() {
        return callGraph;
    }

    public static String getProjectName() {
        return projectName;
    }

    public static HashSet<String> getChanges() {
        return changes;
    }

    public static HashMap<String, HashSet<String>> getTestMethodsOfTestClasses() {
        return testMethodsOfTestClasses;
    }

    /*
    委托式控制风格，Main调用选择器，选择器调用依赖图生成器
    */
    public static void main(String[] args) throws CancelException, ClassHierarchyException, InvalidClassFileException, IOException {
        String choice = args[0];
        before(args);
        // 移交控制权
        if (choice.equals("-c")) {
            testCaseSelector = new SelectorByClass();
        } else if (choice.equals("-m")) {
            testCaseSelector = new SelectorByMethod();
        } else {
            System.out.println("wrong choice");
            return;
        }
        testCaseSelector.select();
    }

    /*
        生成0-CFA调用图
        获取变更信息
        获取测试类和其@Test方法
        */
    private static void before(String[] args) throws IOException, InvalidClassFileException, ClassHierarchyException, CancelException {
        String projectTarget = args[1];
        String changeInfo = args[2];
        String[] targetPath = args[1].split("/|\\\\");
        try {
            projectName = targetPath[targetPath.length - 2].split("-")[1];
        }catch (Exception e){
            projectName = "unknown";
        }
        if (!projectTarget.endsWith(File.separator))
            projectTarget += File.separator;

        /*
        String scopePath = Main.class.getClassLoader().getResource("scope.txt").getPath();
        scopePath = java.net.URLDecoder.decode(scopePath,"utf-8");
        System.out.println(scopePath);
        String exPath = Main.class.getClassLoader().getResource("exclusion.txt").getPath();
        exPath = java.net.URLDecoder.decode(exPath,"utf-8");
        File exFile = new File("exclusion.txt");
        */
        AnalysisScope scope;
        ClassHierarchy cha;

        // 读取变更信息
        getChanges(changeInfo);


        // 读取配置文件
        scope = AnalysisScopeReader.readJavaScope("scope.txt",
                new File("exclusion.txt"),
                Main.class.getClassLoader());


        // 添加测试类
        addClassFiles(scope, new File(projectTarget + "test-classes"));
        cha = ClassHierarchyFactory.makeWithRoot(scope);
        AllApplicationEntrypoints entrypoints = new AllApplicationEntrypoints(scope, cha);
        AnalysisOptions option = new AnalysisOptions(scope, entrypoints);
        SSAPropagationCallGraphBuilder builder = Util.makeZeroCFABuilder(
                Language.JAVA, option, new AnalysisCacheImpl(), cha, scope);
        callGraph = builder.makeCallGraph(option);
        // 遍历cg中所有的节点，获取测试类名和测试类的@Test方法
        for (CGNode node : callGraph) {
            if (node.getMethod() instanceof ShrikeBTMethod) {
                ShrikeBTMethod method = (ShrikeBTMethod) node.getMethod();
                // 使用Primordial类加载器加载的类都属于Java原生类，一般不关心
                if ("Application".equals(method.getDeclaringClass().getClassLoader().toString())) {
                    String classInnerName = method.getDeclaringClass().getName().toString();
                    String signature = method.getSignature();
                    // 放入测试类
                    if(!testMethodsOfTestClasses.containsKey(classInnerName)){
                        testMethodsOfTestClasses.put(classInnerName,new HashSet<>());
                    }
                    // 如果是junit的@Test方法
                    Collection<Annotation> annotations = method.getAnnotations();
                    for(Annotation annotation:annotations){
                        if(annotation.toString().contains("Lorg/junit/Test")){
                            testMethodsOfTestClasses.get(classInnerName).add(signature);
                            break;
                        }
                    }
                }
            }
        }


        // 添加src类
        addClassFiles(scope, new File(projectTarget + "classes"));
        cha = ClassHierarchyFactory.makeWithRoot(scope);
        entrypoints = new AllApplicationEntrypoints(scope, cha);
        option = new AnalysisOptions(scope, entrypoints);
        builder = Util.makeZeroCFABuilder(
                Language.JAVA, option, new AnalysisCacheImpl(), cha, scope);
        callGraph = builder.makeCallGraph(option);
    }


    /*
    递归添加目录下的class文件
    */
    private static void addClassFiles(AnalysisScope scope, File parentDir) throws InvalidClassFileException {
        File[] fileList = parentDir.listFiles();
        for (File file : fileList) {
            if (file.isFile() && file.getAbsolutePath().endsWith(".class")) {
                scope.addClassFileToScope(ClassLoaderReference.Application, file);
            } else if (file.isDirectory()) {
                addClassFiles(scope, file);
            }
        }
    }

    // 读取变更信息
    private static void getChanges(String path) {
        try {
            Scanner in = new Scanner(new FileReader(path));
            while (in.hasNextLine())
                changes.add(in.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
