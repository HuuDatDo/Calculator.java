import java.util.Scanner;
import java.util.ArrayList;

class Main{
    public static void main (String args[]){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter equation");
        String equation = myObj.nextLine();
        Calculator calculating = new Calculator(equation);
        System.out.println(calculating.Result());
    }
}

class Calculator{
    
    String equation;

    public Calculator(String equation){
        this.equation=equation;
    }

    //Requirement 1: Computing the sum

    //Split to array of elements
    public String[] String2Arrays(String equations){
        String[] parts = equations.split(",");
        return parts;
    }
    //Check and eliminate invalid elements
    public String[] EliminateInvalidations(String equations){
        String[] equationArray=String2Arrays(equations);
        String[] processedArray=new String[equationArray.length];
        for (int i=0;i<equationArray.length;i++){
            if (equationArray[i].matches(".*[a-z].*")){
                processedArray[i]="0";
            }
            else if (equationArray[i].trim().isEmpty()){
                processedArray[i]="0";
            }
            else {
                processedArray[i]=equationArray[i];
            }
            
        }
        return processedArray;
    }    
    //Convert String array into int array
    public int[]String2Int(String parts){
        String[] IntArray=EliminateInvalidations(parts);
        int[] int_parts= new int[IntArray.length];
        for (int i=0;i<IntArray.length;i++){
            int u = Integer.parseInt(IntArray[i]);
            int_parts[i]= u;
        }
        return int_parts;
    }

    //Take the sum
    public int Sum(int[] arraysOfIntegers){
        int sum =0;
        for (int i=0;i<arraysOfIntegers.length;i++){
            sum+=arraysOfIntegers[i];
        }
        return sum;
    }

    public int Result() {
        int[] arr = String2Int(this.equation);
        int result = Sum(arr);
        return result;
    }
}