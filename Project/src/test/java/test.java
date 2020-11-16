import org.example.testCaseSelector.Main;
import org.junit.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class test {
    static String res_class = "selection-class.txt";
    static String res_method = "selection-method.txt";

    static String target_0 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/0-CMD/target";
    static String change_0 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/0-CMD/data/change_info.txt";
    static String class_0 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/0-CMD/data/selection-class.txt";
    static String method_0 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/0-CMD/data/selection-method.txt";

    static String target_1 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/1-ALU/target";
    static String change_1 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/1-ALU/data/change_info.txt";
    static String class_1 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/1-ALU/data/selection-class.txt";
    static String method_1 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/1-ALU/data/selection-method.txt";

    static String target_2 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/2-DataLog/target";
    static String change_2 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/2-DataLog/data/change_info.txt";
    static String class_2 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/2-DataLog/data/selection-class.txt";
    static String method_2 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/2-DataLog/data/selection-method.txt";

    static String target_3 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/3-BinaryHeap/target";
    static String change_3 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/3-BinaryHeap/data/change_info.txt";
    static String class_3 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/3-BinaryHeap/data/selection-class.txt";
    static String method_3 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/3-BinaryHeap/data/selection-method.txt";

    static String target_4 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/4-NextDay/target";
    static String change_4 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/4-NextDay/data/change_info.txt";
    static String class_4 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/4-NextDay/data/selection-class.txt";
    static String method_4 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/4-NextDay/data/selection-method.txt";

    static String target_5 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/5-MoreTriangle/target";
    static String change_5 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/5-MoreTriangle/data/change_info.txt";
    static String class_5 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/5-MoreTriangle/data/selection-class.txt";
    static String method_5 = "/Users/shichenhang/Documents/学习/大三上/自动化测试/代码/ClassicAutomatedTesting/5-MoreTriangle/data/selection-method.txt";

    @Test
    public void test0c() throws Exception{
        Main.main(new String[]{"-c",target_0,change_0});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(class_0));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_class));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test0m() throws Exception{
        Main.main(new String[]{"-m",target_0,change_0});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(method_0));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_method));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test1c() throws Exception{
        Main.main(new String[]{"-c",target_1,change_1});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(class_1));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_class));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test1m() throws Exception{
        Main.main(new String[]{"-m",target_1,change_1});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(method_1));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_method));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test2c() throws Exception{
        Main.main(new String[]{"-c",target_2,change_2});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(class_2));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_class));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test2m() throws Exception{
        Main.main(new String[]{"-m",target_2,change_2});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(method_2));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_method));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test3c() throws Exception{
        Main.main(new String[]{"-c",target_3,change_3});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(class_3));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_class));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test3m() throws Exception{
        Main.main(new String[]{"-m",target_3,change_3});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(method_3));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_method));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test4c() throws Exception{
        Main.main(new String[]{"-c",target_4,change_4});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(class_4));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_class));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test4m() throws Exception{
        Main.main(new String[]{"-m",target_4,change_4});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(method_4));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_method));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test5c() throws Exception{
        Main.main(new String[]{"-c",target_5,change_5});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(class_5));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_class));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

    @Test
    public void test5m() throws Exception{
        Main.main(new String[]{"-m",target_5,change_5});

        ArrayList<String> expected = new ArrayList<>();
        Scanner in = new Scanner(new FileReader(method_5));
        while (in.hasNextLine())
            expected.add(in.nextLine());

        ArrayList<String> real = new ArrayList<>();
        in = new Scanner(new FileReader(res_method));
        while (in.hasNextLine())
            real.add(in.nextLine());

        for(String s:real){
            if(Pattern.matches("\\s*",s))
                continue;
            assert expected.contains(s);
        }
        for(String s:expected){
            if(Pattern.matches("\\s*",s))
                continue;
            assert real.contains(s);
        }
    }

}
