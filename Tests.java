package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import App.*;


public class Tests{
    @Test
    public void Test1(){
        String x ="4,-3";
        int expectedValue=1;
        CalculatorWithDelimeters calculator = new CalculatorWithDelimeters(x);
        assertEquals("Test 1 passed",expectedValue, calculator.Result());
    }

    public void Test2(){
        String x ="1\n2,3";
        int expectedValue=6;
        CalculatorWithDelimeters calculator = new CalculatorWithDelimeters(x);
        assertEquals("Test 2 passed",expectedValue, calculator.Result());
    }

    public void Test3(){
        String x ="5,tytyt";
        int expectedValue=5;
        CalculatorWithDelimeters calculator = new CalculatorWithDelimeters(x);
        assertEquals("Test 3 passed",expectedValue, calculator.Result());
    }

    public void Test4(){
        String x = "2,1001,6";
        int expectedValue=8;
        CalculatorWithDelimeters calculator = new CalculatorWithDelimeters(x);
        assertEquals("Test 4 passed",expectedValue, calculator.Result());
    }

    public void Test5(){
        String x = "//[***]\n11***22***33";
        int expectedValue=66;
        CalculatorWithDelimeters calculator = new CalculatorWithDelimeters(x);
        assertEquals("Test 5 passed",expectedValue, calculator.Result());
    }
}
