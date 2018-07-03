package 算法图解练习;

import java.util.*;

import static 算法图解练习.狄克斯特拉1.dijkstra1;


public class 狄克斯特拉2 {
    public static void main(String[] args) {

        //init data
        Map<String,Integer> weights = new HashMap<>();
        weights.put("START_黑胶唱片",5);
        weights.put("START_海报",0);
        weights.put("黑胶唱片_吉他",15);
        weights.put("黑胶唱片_架子鼓",20);
        weights.put("海报_吉他",30);
        weights.put("海报_架子鼓",35);
        weights.put("吉他_钢琴",20);
        weights.put("架子鼓_钢琴",10);

        Map<String,List<String>> data = new HashMap<>();
        data.put("START",Arrays.asList("黑胶唱片","海报"));
        data.put("黑胶唱片",Arrays.asList("吉他","架子鼓"));
        data.put("海报",Arrays.asList("吉他","架子鼓"));
        data.put("吉他",Arrays.asList("钢琴"));
        data.put("架子鼓",Arrays.asList("钢琴"));
        data.put("钢琴",Arrays.asList(""));

        dijkstra1(weights, data);
    }

}
