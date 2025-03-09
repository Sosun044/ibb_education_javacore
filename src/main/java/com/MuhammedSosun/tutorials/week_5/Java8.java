package com.MuhammedSosun.tutorials.week_5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Java8 {
    public static List<String> getArrayToDataList(){
        String[] frontend = {"html5", "css3", "seo", "seo", "seo", "bootstrap5", "tailwindcss", "reactjs", "angular", "jquery", "nodejs"};
        List<String> arrayList;
        arrayList = List.of(frontend);
        return arrayList;
    }
    public static List<String> streamCollectData(){
        List<String> list = getArrayToDataList();
        List<String> result = list.stream().collect(Collectors.toList());
        return result;
    }
    private static void streamForEach(){
        List<String> list = streamCollectData();
        list.stream().forEach((temp) -> {
            System.out.print(temp + " ");
        });
    }
    private static void streamDistinct(){
        List<String> list = streamCollectData();
        list.stream().distinct().forEach(temp -> {
            System.out.println(temp + " ");
        });
    }
    public static void streamSorted(){
        List<String> list = streamCollectData();
        list.stream().sorted(Comparator.reverseOrder()).forEach(temp -> System.out.println(temp + " "));

    }
    public static void streamFilter(){
        List<String> list = streamCollectData();
        list.stream().sorted(Comparator.reverseOrder()).filter(temp -> !"seo".equals(temp)).forEach((temp) -> System.out.println(temp + " "));
    }
    public static void streamMap(){
        List<String> list = streamCollectData();
        list.stream().sorted(Comparator.reverseOrder()).map((temp) -> temp.toUpperCase()).forEach((temp) -> System.out.println(temp + " "));
    }
    public static void streamLimit(){
        List<String> list = streamCollectData();
        list.stream().sorted().filter(temp -> !"seo".equals(temp)).map(temp -> temp.toUpperCase()).limit(9).forEach(temp -> System.out.println(temp + " "));
    }

    public static void main(String[] args) {

        System.out.println(getArrayToDataList());
        System.out.println("\n\n\n");
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
        streamForEach();
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
        streamDistinct();
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
        streamSorted();
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
        streamFilter();
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
        streamMap();
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
        streamLimit();
        System.out.println(streamCollectData());
        System.out.println("\n\n\n");
    }

}
