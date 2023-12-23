package Collections2.Task.Generics;

import Collections2.Task.MyHashMap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList2 arrayList = new MyArrayList2<String>();
        arrayList.add("1");
        arrayList.add(21, "2");
        arrayList.add(22, "3");
        arrayList.add(22, "4");
        arrayList.add(22, "5");
        System.out.println(arrayList.getAllElements());

        System.out.println("==============================================================");

        System.out.println("ArrayList size is : " + arrayList.size());
        System.out.println("ArrayList length is : " + arrayList.length());

        System.out.println("==============================================================");

        System.out.println("Value to remove: " + arrayList.get(1));
        arrayList.remove("2");
        System.out.println("Array : " + arrayList.getAllElements());

        System.out.println("==============================================================");

        for (int i = 0; i < arrayList.length(); i++) {
            System.out.println("Value is : " + arrayList.get(i));
        }

        System.out.println("==============================================================");

        System.out.println("Contains : " + arrayList.contains("1"));

        System.out.println("==============================================================");

        System.out.println("IndexOf : " + arrayList.indexOf("3"));

        System.out.println("==============================================================");

        System.out.println("Is empty : " + arrayList.isEmpty());

        System.out.println("==============================================================");

        arrayList.add("2");
        System.out.println(arrayList.getAllElements());
        System.out.println("Last indexOf : " + arrayList.lastIndexOf("2"));

        System.out.println("==============================================================");

        arrayList.clear();
        System.out.println("After clear: " + arrayList.getAllElements());


        System.out.println("--- MY LINKED LIST ---");
        System.out.println("Adding elements: ");

        MyLinkedList2 linked = new MyLinkedList2<>();
        linked.add("1");
        linked.add("4");
        linked.add("5");
        linked.add(1, "2");
        linked.add("3");
        linked.add("2");
        System.out.println("Get all:  " + linked.getAllElements());

        System.out.println("==============================================================");

        System.out.println("Size: " + linked.size());

        System.out.println("==============================================================");

        System.out.println("IndexOf: " + linked.indexOf("2"));

        System.out.println("==============================================================");

        System.out.println("LastIndexOf: " + linked.lastIndexOf("2"));

        System.out.println("==============================================================");

        System.out.println("Contains : " + linked.contains("2"));

        System.out.println("==============================================================");

        System.out.println("Peek : " + linked.peek());
        System.out.println("Get all:  " + linked.getAllElements());

        System.out.println("==============================================================");

        System.out.println("Poll : " + linked.poll());
        System.out.println("Get all:  " + linked.getAllElements());

        System.out.println("==============================================================");

        System.out.println("Peek : " + linked.offer("2"));

        System.out.println("==============================================================");

        System.out.println("Peek : " + linked.remove("2"));

        System.out.println("==============================================================");
        System.out.println("==============================================================");

        System.out.println("Get element with index 0: " + linked.get(0));
        System.out.println("Get element with index 1: " + linked.get(1));
        System.out.println("Get element with index 2: " + linked.get(2));

        System.out.println("==============================================================");

        System.out.println("Before clear: " + linked.getAllElements());
        linked.clear();
        System.out.println("Clear: " + linked.getAllElements());


        System.out.println("--- MY HASH MAP ---");
        MyHashMap2 hashMap = new MyHashMap2<String,String>();


        hashMap.put("Hello", "1");
        hashMap.put("World", "2");
        hashMap.put("World", "3");
        hashMap.put("Java", "7");
        hashMap.put("JVM", "13");
        hashMap.put("Javac", "4");

        System.out.println(hashMap.getAllValuesKeys());

        System.out.println("==============================================================");

        System.out.println("Key array: " + Arrays.toString(hashMap.keyArray()));

        System.out.println("==============================================================");

        System.out.println("Value array: " + Arrays.toString(hashMap.valueArray()));

        System.out.println("==============================================================");

        System.out.println("Is empty: " + hashMap.isEmpty());

        System.out.println("==============================================================");

        System.out.println("HashMap size: " + hashMap.size());

        System.out.println("==============================================================");

        System.out.println("HashMap size: " + hashMap.get("Hello"));

        System.out.println("==============================================================");

        System.out.println("HashMap size: " + hashMap.remove("Hello"));
        System.out.println(hashMap.getAllValuesKeys());

        System.out.println("==============================================================");

        hashMap.clear();
        System.out.println(hashMap.getAllValuesKeys());

        System.out.println("==============================================================");
    }
}
