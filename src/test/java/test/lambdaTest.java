package test;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class lambdaTest {

    public static void main(String[] args) {

        // 断言函数接口
        Predicate<Integer> predicate = i -> i > 0;
        System.out.println(predicate.test(-9));

        // 消费函数接口
        Consumer<String> consumer = System.out::println;
        consumer.accept("这是输入的数据");

        // 提供数据接口
        Supplier<Integer> supplier = () -> 10 + 1;
        System.out.println("提供的数据是：" + supplier.get());

        // 一元函数接口
        UnaryOperator<Integer> unaryOperator = i -> i * 2;
        System.out.println("计算结果为：" + unaryOperator.apply(10));

        // 二元函数接口
        BinaryOperator<Integer> binaryOperator = (a, b) -> a * b;
        System.out.println("计算结果为：" + binaryOperator.apply(10, 10));

        String[] stringsArray = {"q" , "e"};
        Arrays.sort(stringsArray, (s1, s2) -> s1.compareToIgnoreCase(s2));

        List<String> list = Arrays.asList(new String[]{"6" , "2" , "3" , "5"});
        Collections.sort(list , (str1 , str2) -> str1.compareTo(str2));
        System.out.println(list);


        List<String> proNames = Arrays.asList(new String[]{"ni" , "hao" , "Lambda" , "Hello"});
        List<String> stringList = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(stringList);

         String waibu = "lambda :";
         List<String> proStrs = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
         List<String>execStrs = proStrs.stream().map(chuandi -> {
         Long zidingyi = System.currentTimeMillis();
         //waibu = waibu + "";
         return waibu + chuandi + " -----:" + zidingyi;
         }).collect(Collectors.toList());
         execStrs.forEach(System.out::println);
    }

}