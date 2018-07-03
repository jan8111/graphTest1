package 算法图解练习;

import java.util.*;

public class Exe1 {
    public static void main(String[] args) {

        //init data
        Map<String,Integer> weights = new HashMap<>();
        weights.put("START_A",6);
        weights.put("START_B",2);
        weights.put("A_END",1);
        weights.put("B_A",3);
        weights.put("B_END",5);

        Map<String,List<String>> data = new HashMap<>();
        data.put("START",Arrays.asList("A","B"));
        data.put("A",Arrays.asList("END"));
        data.put("B",Arrays.asList("A","END"));
        data.put("END",Arrays.asList(""));

        //prepare costs
        Map<String,Integer> costs = new HashMap<>();
        List<String> visited = new ArrayList<>();
        for (String key1 : data.keySet()) {
            if(!key1.equals("START")){
                String key2 = "START"+"_"+key1;
                Integer valud2 = weights.get(key2);
                if(valud2!=null){
                    costs.put(key1,valud2);
                }else{
                    costs.put(key1,Integer.MAX_VALUE);
                }
            }
        }
        loop1(weights, costs, visited);

        System.out.println("costs = " + costs);
    }

    private static void loop1(Map<String, Integer> weights, Map<String, Integer> costs, List<String> visited) {
        //step1
        String minCostNode = findMinCost(costs,visited);
        Integer minCostValue= costs.get(minCostNode);
        visited.add(minCostNode);

        //step2
        for (String key3 : weights.keySet()) {
            if(key3.startsWith(minCostNode)){
                String targetKey1=key3.split("_")[1];
                Integer costTarget = costs.get(targetKey1);
                Integer costCurrent = weights.get(key3);
                if(costCurrent <costTarget ){
                    costs.put(targetKey1,costCurrent+minCostValue);
                }
            }
        }

        boolean allDone = visited.size()==costs.size()-1;// -1:remove "END"
        if(allDone){
            return;
        }else{
            loop1(weights, costs, visited);
        }

    }

    private static String findMinCost(Map<String,Integer> costs, List<String> visited) {
        int minCost = Integer.MAX_VALUE;
        String minKey = null;
        for (String costkey1 : costs.keySet()) {
            if(!visited.contains(costkey1)){
                int costValue1 = costs.get(costkey1);
                if(costValue1<minCost){
                    minCost = costValue1;
                    minKey = costkey1;
                }
            }
        }
        return minKey;
    }
}
