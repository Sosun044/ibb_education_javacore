    package com.MuhammedSosun.tutorials.week_5;

    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;

    @FunctionalInterface
    public interface MathOperation{
        int operation(int a,int b);
    }
    class LambdaExample{
        public static void main(String[] args) {
            MathOperation op = (a,b) -> a+b;
            System.out.println(op.operation(10,5));
            MathOperation op1 = (a,b) -> a-b;
            System.out.println(op1.operation(10,5));
            MathOperation op2 = (a,b) -> a*b;
            System.out.println(op2.operation(10,5));
            MathOperation op3 = (a,b) -> b == 0 ? 0: a/b;
            System.out.println(op3.operation(10,0));

            Thread thread = new Thread(() ->System.out.println("Thread çalışıyor (Lambda)"));
            thread.start();

            List<String> names = Arrays.asList("Ahmet", "Mehmet", "Ali", "Veli", "Ayşe");

            List<String> filtredNames = names.stream().filter(name -> name.startsWith("A")).collect(Collectors.toList());
            System.out.println(filtredNames);

            names.sort((k,m) -> m.compareTo(k));
            System.out.println("A-Z sıralama: " + names);
            names.sort((k,m) -> k.compareTo(m));
            System.out.println("A-Z sıralama: " + names);

            Map<String, Integer> studentGrades = new HashMap<>();
            studentGrades.put("Ahmet", 85);
            studentGrades.put("Mehmet", 92);
            studentGrades.put("Ayşe", 78);

            studentGrades.forEach((name,grade) -> System.out.println(name + " -> " + grade));
        }
    }

