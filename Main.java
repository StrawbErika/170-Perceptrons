import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Perceptrons percept = new Perceptrons();
        percept.loadFile("input.txt");
        int j = 0;
        // !percept.checkConverge()


        for(int i = 0; i < percept.vectors.size(); i++){
            ArrayList<Double> w = new ArrayList<Double>();
            for(int k = 0; k < percept.vectors.get(i).size(); k++){
                w.add(0.0);
            }
            percept.weights.add(w);
        }
        System.out.println(percept.vectors.size());
        System.out.println(percept.weights.size());
        while(j != 2){
            for(int i = 0; i < percept.vectors.size(); i++){
                ArrayList<Double> v, w;
                ArrayList<Double> prev;
                v = percept.vectors.get(i);
                w = percept.weights.get(i);
                percept.getA(v, w);
                percept.getY();
                if(i == 0) {
                    prev = percept.vectors.get(i);
                }
                else{
                    prev = percept.vectors.get(i-1);
                }
                percept.adjustWeights(prev);
            }
            j++;
        }

        percept.saveFile();
    }
}
