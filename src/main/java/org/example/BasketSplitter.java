package org.example;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class BasketSplitter{
    private final String absolutePathToConfigFile;
    private List<String> minSolution;
    public BasketSplitter(String absolutePathToConfigFile) {
        this.absolutePathToConfigFile=absolutePathToConfigFile;
    }
    private Map<String,List<String>> praseConfigFile() throws IOException, ParseException {
        ConfigParser configParser=new ConfigParser(absolutePathToConfigFile);
        return configParser.praseJson();
    }
    private Set<String> getDeliversList(List<String> items, Map<String, List<String>> configFileContent){
        Set<String> deliversList=new TreeSet<>();
        for(String item:items){
            for(String deliver:configFileContent.get(item)){
                deliversList.add(deliver);
            }
        }
        return deliversList;
    }
    private void recursionFunction(Map<String,Boolean> takenItems, ArrayList<String> deliversList, int i, List<String> solution, Map<String, List<String>> configFileContent){
        if(!takenItems.containsValue(false)){
            if(minSolution.size()==0 || solution.size()< minSolution.size()){
                minSolution=List.copyOf(solution);
            }
            return;
        }

        if(i==deliversList.size()){
            return;
        }

        Map<String,Boolean> previousTakenItems=new HashMap<>(takenItems);
        for(String item:configFileContent.keySet()){
            if(configFileContent.get(item).contains(deliversList.get(i))&& takenItems.containsKey(item) && !takenItems.get(item)) {
                takenItems.put(item, true);
            }
        }
        solution.add(deliversList.get(i));

        recursionFunction(takenItems,deliversList,i+1,solution,configFileContent);

        takenItems=previousTakenItems;
        solution.remove(deliversList.get(i));

        recursionFunction(takenItems,deliversList,i+1,solution,configFileContent);

        return;
    }
    private Map<String,List<String>> getFinalSolution(List<String> delivers,List<String> items,Map<String, List<String>> configFileContent){
        Map<String,List<String>> solution = new HashMap<>();
        Map<String,List<String>> numeberOfDelivers = new HashMap<>();
        while(items.size()!=0){
            for(String deliver:delivers){
                numeberOfDelivers.put(deliver,new LinkedList<>());
            }
            for(String item:items){
                for(String deliver:configFileContent.get(item)){
                    if(delivers.contains(deliver)){
                        numeberOfDelivers.get(deliver).add(item);
                    }
                }
            }

            String maxDeliver=null;
            List<String> maxList=null;
            for(String deliver:delivers){
                if(maxList==null||maxList.size()<numeberOfDelivers.get(deliver).size()){
                    maxList=numeberOfDelivers.get(deliver);
                    maxDeliver=deliver;
                }
            }
            solution.put(maxDeliver,maxList);
            for(String item: Objects.requireNonNull(maxList)){
                items.remove(item);
            }
            delivers.remove(maxDeliver);
        }

        return solution;
    }
    private List<String> getDeliversSolution(List<String> items, Map<String, List<String>> configFileContent){
        minSolution=new ArrayList<>();
        List<String> solution=new ArrayList<>();
        Set<String> deliversList=getDeliversList(items,configFileContent);
        Map<String,Boolean>takenItems=new HashMap<>();
        for(String item:items){
            takenItems.put(item,false);
        }

        recursionFunction(takenItems, new ArrayList<>(deliversList),0,solution,configFileContent);
        return minSolution;
    }
    public Map<String, List<String>> split(List<String> items) throws IOException, ParseException {
        Map<String, List<String>> configFileContent=praseConfigFile();
        List<String> deliversSolution=getDeliversSolution(items,configFileContent);
        System.out.println(deliversSolution);
        Map<String,List<String>> solutionToReturn = getFinalSolution(new ArrayList<>(deliversSolution),new ArrayList<>(items),configFileContent);
        System.out.println(solutionToReturn);
        return solutionToReturn;
    }

}