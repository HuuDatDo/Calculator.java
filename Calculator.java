import java.util.Scanner;
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
    public int[]String2Int(String parts){
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

    public String[] SplitNegativeAndPositive(){
        String neg="";
        String pos="";
        for(int i=0;i<this.equation.length();i++){
            if (this.equation.charAt(i)==','){
                continue;
            }
            if (this.equation.charAt(i)=='-'){
                neg+=this.equation.substring(i,i+2);
                neg+=",";
                i++;
            }
            else{
                pos+=this.equation.charAt(i);
                pos+=",";
            }
        }
        String[] SplittedEquation={pos,neg};
        return SplittedEquation;
    }
    public int[] NewResult() {
        String[] pos_and_neg = SplitNegativeAndPositive();
        String filteredPosEquation=AlternativeDelimeter(pos_and_neg[0]);
        String filteredNegEquation=AlternativeDelimeter(pos_and_neg[1]);
        int[] pos_arr= String2Int(filteredPosEquation);
        int[] neg_arr= String2Int(filteredNegEquation);
        int pos_result = Sum(pos_arr);
        int neg_result = Sum(neg_arr);
        pos_arr=RemoveMoreThan1000(arr);
        int[] finalResult={pos_result,neg_result};
        return finalResult;
    }
}
