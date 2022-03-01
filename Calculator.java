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
        //Requirement 4
        if(contain_neg){
            NegativeCalculator negCalculator= new NegativeCalculator(equation);
            System.out.println(Arrays.toString(negCalculator.NewResult()));
        }
        else{
            Calculator calculating = new Calculator(equation);
            System.out.println(calculating.Result());
        }
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

    public int[][] SplitNegativeAndPositive(int[] equation){
        int pos = 0;
        int neg = 0;
        for (int i = 0; i < equation.length ; i++) {
            if(equation[i] >= 0){
                pos++;
            }else{
                neg++;
            }
        }
        int[] arr_pos = new int[pos];
        int[] arr_neg = new int[neg];

        int countpos = 0;
        int countneg = 0;
        for (int i = 0; i < equation.length ; i++) {
            if(equation[i] > 0){
                arr_pos[countpos] = equation[i];
                countpos++;
            }else{
                arr_neg[countneg] = equation[i];
                countneg++;
            }
        }
        int[][] pos_and_neg={arr_pos,arr_neg};
        return pos_and_neg;
    }

    public int[] NewResult() {
        String filteredEquation=AlternativeDelimeter(this.equation);
        int[] arr= String2Int(filteredEquation);
        arr=RemoveMoreThan1000(arr);
        int[][] pos_and_neg=SplitNegativeAndPositive(arr);
        int[] pos_arr=pos_and_neg[0];
        int[] neg_arr=pos_and_neg[1];
        int pos_sum = Sum(pos_arr);
        int neg_sum = Sum(neg_arr);
        int[] finalResult={pos_sum,neg_sum};
        return finalResult;
    }
}

class CalculatorWithDelimeters extends Calculator{

    public CalculatorWithDelimeters(String equation) {
        super(equation);
    }

    @Override
    public String AlternativeDelimeter(String equation){
        String replacedString;
        String firstSign=equation.substring(0,2);
        String secondSign=equation.substring(3,5);
        if (firstSign.equals("//") && secondSign.equals("\\n")){
            String delimeter = equation.substring(2,3);
            String rawEquation=equation.substring(5,equation.length());
            replacedString = rawEquation.replace(delimeter,",");
            return replacedString;
        }
        else{
            return equation;
        }
    }

    public Result(){
        String filteredEquation = AlternativeDelimeter(this.equation);
        System.out.println(filteredEquation);
        int[] arr = String2Int(filteredEquation);
        arr=RemoveMoreThan1000(arr);
        int result = Sum(arr);
        return result;
    }
}
