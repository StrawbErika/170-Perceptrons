import java.io.*;
import java.util.*;

public class Perceptrons {

    public ArrayList<ArrayList<Double>> vectors;
    public ArrayList<ArrayList<Double>> weights;
    public Double r, t, b, a, z, y;

    public Perceptrons(){
        this.r = 0.0;
        this.t = 0.0;
        this.b = 0.0;
        this.vectors = new ArrayList<ArrayList<Double>>();
        this.weights = new ArrayList<ArrayList<Double>>();
    }

    public void getA(ArrayList<Double> vect, ArrayList<Double> w){
        for(int i = 0; i< vect.size(); i++){
            a = vect.get(i) * w.get(i);
            System.out.println(vect.get(i) + " * " + w.get(i) + " = " + a);
        }

    }

    public void getY(){
        if(a > t){
            y = 1.0;
        }
        else{
            y = 0.0;
        }
    }
    public void adjustWeights(ArrayList<Double> previous){
        Double curr;
        for(int i = 0; i< vectors.size(); i++){
            for(int j = 0; j < vectors.get(i).size(); j++){
                curr = vectors.get(i).get(j) + r*previous.get(i)*(z-y);
                vectors.get(i).set(j, curr);
            }
        }
    }

    public Boolean checkConverge(){
        Boolean check = false;
        for(int i = 0; i< weights.size(); i++){
            for(int j = 0; j < weights.get(i).size(); j++){
                if(weights.get(i).get(j) == 0.0){
                    check = true;
                }
            }
        }
        return check;
    }


    public void loadFile(String filename) {
        try{
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream inData = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inData));
            String line;
            Double input;
            int in = 1;

            while((line  = br.readLine()) != null)
            {
                if(in > 3){
                    String[] values = line.split(" "); //stores all the words from the line in values
                    ArrayList<Double> vect = new ArrayList<Double>();
                    for (String str : values) {
                        input = Double.parseDouble(str);
                        vect.add(input);
                    }
                    vectors.add(vect);
                }
                else{
                    input = Double.parseDouble(line);
                    if(in == 1){
                        this.r = input;
                    }
                    else if(in == 2){
                        this.t = input;
                    }
                    else{
                        this.b = input;
                    }
                    in++;
                }
            }
            inData.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void saveFile() { //writes file
        String filename = "output.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("x0 x1 b w0 w1 wb a y z \n");
            for(int i = 0; i < weights.size(); i++){
                for(int j = 0; j < weights.get(i).size(); j++){
                    writer.write(weights.get(i).get(j)+" ");
                }
                writer.write("\n");
            }
            writer.write("x0 x1 b w0 w1 wb a y z \n");

            writer.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        }
        catch(IOException ex) {
            System.out.println("Error writing file '" + filename + "'");
        }
    }
}
