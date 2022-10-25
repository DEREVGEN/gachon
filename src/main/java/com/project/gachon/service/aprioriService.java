package com.project.gachon.service;

import java.util.*;

public class aprioriService {
    Set<Tuple> c;
    Set<Tuple> l;
    Set<Integer> finalSet_Integer;
    Set<String> finalSet_String;
    int min_support;
    Map<String, Integer> inputDict;
    Map<String, String> outputDict;      // 理쒖쥌 寃곌낵. key:weather, value:color
    ArrayList[] intInput;
    ArrayList[] stringInput;

    public aprioriService(ArrayList[] stringInput) {
        this.stringInput = stringInput;
    }

    public Map<String, String> apriori() throws Exception {

        convert();

        c = new HashSet<>();
        l = new HashSet<>();
        finalSet_Integer = new HashSet<>();
        finalSet_String = new HashSet<>();
        /*Scanner scan = new Scanner(System.in);
        System.out.print("Enter the minimum support (as an integer value): ");
        min_support = scan.nextInt();*/
        min_support = 2;

        System.out.println("Dict Info: "+ inputDict);

        Set<Integer> candidate_set = new HashSet<>();
        for(int i = 0; i < intInput.length; i++) {
            System.out.println("Transaction Number: " + (i+1));
            Object[] input = intInput[i].toArray();
            for(int j = 0; j < intInput[i].size(); j++) {
                System.out.println("\tItem"+ (j+1) + " = " + input[j]);
                candidate_set.add((Integer) input[j]);
            }
        }

        Iterator<Integer> iterator = candidate_set.iterator();
        while(iterator.hasNext()) {
            Set<Integer> s = new HashSet<>();
            s.add(iterator.next());
            Tuple t = new Tuple(s, count(s));
            c.add(t);
        }

        prune();
        generateFrequentItemsets();

        System.out.println("\n-+- FinalSet -+-\n" + finalSet_Integer);
        Iterator iter = finalSet_Integer.iterator();
        while(iter.hasNext()) {
            int value = (int) iter.next();
            for(Map.Entry<String, Integer> entry : inputDict.entrySet()){
                if(entry.getValue().equals(value)){
                    finalSet_String.add(entry.getKey());
                    break;
                }
            }
        }
        System.out.println(finalSet_String);

        outputDict = new HashMap<>();
        iter = finalSet_String.iterator();
        while(iter.hasNext()) {
            String value = (String) iter.next();
            String[] arr = value.split("[.]");
            outputDict.put(arr[0], arr[1]);
        }
        System.out.println(outputDict);

        return outputDict;
    }

    void convert() {
        inputDict = new HashMap<>();
        int value = 1;
        for (ArrayList strInput : stringInput) {
            for (Object s : strInput) {
                if (inputDict.get(s) == null) {
                    inputDict.put(String.valueOf(s), value);
                    value++;
                }
            }
        }

        intInput = new ArrayList[stringInput.length];
        for (int i = 0; i < stringInput.length; i++){
            intInput[i] = new ArrayList<>();
            for (Object s : stringInput[i]){
                intInput[i].add(inputDict.get(s));
            }
        }
    }

    int count(Set<Integer> s) {
        int i, j;
        int support = 0;
        int count;
        boolean containsElement;
        for(i = 0; i < intInput.length; i++) {
            count = 0;
            Iterator<Integer> iterator = s.iterator();
            while(iterator.hasNext()) {
                int element = iterator.next();
                containsElement = false;
                Object[] input = intInput[i].toArray();
                for(j = 0; j < intInput[i].size(); j++) {
                    if(element == (int) input[j]) {
                        containsElement = true;
                        count++;
                        break;
                    }
                }
                if(!containsElement) {
                    break;
                }
            }
            if(count == s.size()) {
                support++;
            }
        }
        return support;
    }

    void prune() {
        l.clear();
        Iterator<Tuple> iterator = c.iterator();
        while(iterator.hasNext()) {
            Tuple t = iterator.next();
            if(t.support >= min_support) {
                l.add(t);
            }
        }
        System.out.println("-+- L -+-");
        if(!l.isEmpty()){
            finalSet_Integer.clear();
        }
        for(Tuple t : l) {
            System.out.println("Support of " + t.itemset + " : " + t.support);

            Set<Integer> temp = t.itemset;
            Iterator iter = temp.iterator();
            while(iter.hasNext()) {
                finalSet_Integer.add((Integer) iter.next());
            }
        }
        System.out.println(finalSet_Integer);
    }

    void generateFrequentItemsets() {
        boolean toBeContinued = true;
        int element = 0;
        int size = 1;
        Set<Set> candidate_set = new HashSet<>();
        while(toBeContinued) {
            candidate_set.clear();
            c.clear();
            Iterator<Tuple> iterator = l.iterator();
            while(iterator.hasNext()) {
                Tuple t1 = iterator.next();
                Set<Integer> temp = t1.itemset;
                Iterator<Tuple> it2 = l.iterator();
                while(it2.hasNext()) {
                    Tuple t2 = it2.next();
                    Iterator<Integer> it3 = t2.itemset.iterator();
                    while(it3.hasNext()) {
                        try {
                            element = it3.next();
                        } catch(ConcurrentModificationException e) {
                            // Sometimes this Exception gets thrown, so simply break in that case.
                            break;
                        }
                        temp.add(element);
                        if(temp.size() != size) {
                            Integer[] int_arr = temp.toArray(new Integer[0]);
                            Set<Integer> temp2 = new HashSet<>();
                            for(Integer x : int_arr) {
                                temp2.add(x);
                            }
                            candidate_set.add(temp2);
                            temp.remove(element);
                        }
                    }
                }
            }
            Iterator<Set> candidate_set_iterator = candidate_set.iterator();
            while(candidate_set_iterator.hasNext()) {
                Set s = candidate_set_iterator.next();
                // These lines cause warnings, as the candidate_set Set stores a raw set.
                c.add(new Tuple(s, count(s)));
            }
            prune();
            if(l.size() <= 1) {
                toBeContinued = false;
            }
            size++;
        }
//        System.out.println("\n=+= FINAL LIST =+=");
//        for(Tuple t : l) {
//            System.out.println(t.itemset + " : " + t.support);
//        }
    }
}

class Tuple {
    Set<Integer> itemset;
    int support;

    Tuple() {
        itemset = new HashSet<>();
        support = -1;
    }

    Tuple(Set<Integer> s) {
        itemset = s;
        support = -1;
    }

    Tuple(Set<Integer> s, int i) {
        itemset = s;
        support = i;
    }
}