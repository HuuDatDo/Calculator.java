package App;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

class Main{
    public static void main (String args[]){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter equation");
        String equation = myObj.nextLine();
        boolean contain_neg=false;
        for (int i=0;i<equation.length();i++){
            if (equation.charAt(i)=='-'){
                contain_neg=true;
                break;
            }
        }
        if(contain_neg){
            NegativeCalculator negCalculator = new NegativeCalculator(equation);
            int[] neg_list = negCalculator.ThrowException();
            throw new ArithmeticException(Arrays.toString(neg_list));
        }
        CalculatorWithDelimeters calculating = new CalculatorWithDelimeters(equation);
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
    public int[] String2Int(String parts){
        String[] IntArray=EliminateInvalidations(parts);
        int[] int_parts= new int[IntArray.length];
        for (int i=0;i<IntArray.length;i++){
            int u = Integer.valueOf(IntArray[i]);
            int_parts[i]= u;
        }
        return int_parts;
    }
    //Change \n to ,
    public String AlternativeDelimeter(String equation){
        String filteredEquation=equation.replace("\\n",",");
        return filteredEquation;
    }

    public int[] RemoveMoreThan1000(int[] arrayOfIntegers){
        int numberOfDeletion=0;
        int num=0;
        for (int i=0;i<arrayOfIntegers.length;i++){
            if (arrayOfIntegers[i]>1000){
                numberOfDeletion++;
            }
        }
        int[] processedArray = new int[arrayOfIntegers.length-numberOfDeletion];
        for (int i=0;i<arrayOfIntegers.length;i++){
            if (arrayOfIntegers[i]<1000){
                processedArray[i-num]=arrayOfIntegers[i];
            }
            else{
                num+=1;
            }
        }
        return processedArray;
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
        String filteredEquation = AlternativeDelimeter(this.equation);
        int[] arr = String2Int(filteredEquation);
        arr=RemoveMoreThan1000(arr);
        int result = Sum(arr);
        return result;
    }
}

class NegativeCalculator extends Calculator{
    public NegativeCalculator(String equation) {
        super(equation);
    }

    public int[] SplitNegative(int[] equation){
        int neg = 0;
        for (int i = 0; i < equation.length ; i++) {
            if(equation[i] < 0){
                neg++;
            }
        }
        int[] arr_neg = new int[neg];
        int countneg = 0;
        for (int i = 0; i < equation.length ; i++) {
            if(equation[i] < 0){
                arr_neg[countneg] = equation[i];
                countneg++;
            }
        }
        return arr_neg;
    }

    public int[] ThrowException(){
        String filteredEquation = AlternativeDelimeter(equation);
        int[] arr = String2Int(filteredEquation);
        return SplitNegative(arr);
    }
}

public class CalculatorWithDelimeters extends Calculator{

    public CalculatorWithDelimeters(String equation) {
        super(equation);
    }

    public ArrayList<String> SplitDelimetersAndEquation(String equation){
        ArrayList<String> arrayList = new ArrayList<>(); 
        if (equation.charAt(0)!='/'){
            arrayList.add(equation.replace("\n",","));
            return arrayList;   
        };
        
        String regex = "";
        int i = 2;
   
        while(equation.charAt(i) != '\n'){
            if(equation.charAt(i) != '['){
                arrayList.add(String.valueOf(equation.charAt(i)));
                i++;
                break;
            }
            if(equation.charAt(i) == '['){
                i++;
                while(equation.charAt(i) != ']'){
                    regex += equation.charAt(i);
                    i++;
                }
            arrayList.add(regex);
            regex = "";
            i++;
            }
        }
        arrayList.add(equation.substring(i + 1, equation.length()));
        return arrayList; 
    }

    public String NewAlternativeDelimeter(ArrayList<String> equation){
        String filteredEquation=equation.get(equation.size()-1);
        filteredEquation.replace("\n",",");
        for (int i=0; i<equation.size()-1;i++){
            String delimeter = equation.get(i);
            filteredEquation=filteredEquation.replace(delimeter,",");
        }
        return filteredEquation;
    }

    @Override
    public int Result(){
        ArrayList<String> splittedEquation=SplitDelimetersAndEquation(this.equation);
        String filteredEquation = NewAlternativeDelimeter(splittedEquation);
        int[] arr = String2Int(filteredEquation);
        arr=RemoveMoreThan1000(arr);
        int result = Sum(arr);
        return result;
    }
}
