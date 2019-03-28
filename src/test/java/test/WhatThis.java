package test;

import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WhatThis {

    public void whatThis(){
        //转全小写
        List<String> proStrs = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
        List<String> execStrs = proStrs.stream().map(str -> {
            System.out.println(this.getClass().getName());
            return str.toLowerCase();
        }).collect(Collectors.toList());
        execStrs.forEach(System.out::println);
    }

    public static void main(String[] args) {
        WhatThis wt = new WhatThis();
        wt.whatThis();


        List<Integer> nums = Lists.newArrayList(1,null,3,4,null,6);
        long count = nums.stream().filter(num -> num != null).count();
        System.out.println(count);



        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);

        Stream.iterate(1, item -> item + 1).limit(100).forEach(System.out::println);


        List<Integer> numss = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());

    }
}
