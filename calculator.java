import java.util.Scanner;
import java.io.*;
import java.util.*;

class MyException extends Exception
{
    public MyException(String str)
    {
        System.out.println(str);
    }
}

class StringCalculator{

    protected static int method_invoke = 0;
    int sum;

    public String Add(String numbers){
        
        try {

            sum = 0;

            int negatives = 0;
            for(int i=0; i<numbers.length(); i++){
                if(numbers.charAt(i) == '-'){
                    negatives += 1;
                }
            }
    
            if(negatives > 1){
                throw new MyException("negatives not allowed");
            } 

            if(numbers.contains("\n,") || numbers.contains(",\n")){
                //System.out.println("String Not Accepted");
                return "String Not Accepted";
            }
            
            if(numbers.contains("\n")){
                numbers = numbers.replace("\n", ",");
            }
    
            if(numbers.contains("//;\n")){
                numbers = numbers.replace("//;\n", ",").replace(";",",");
            }
    
            if(numbers == ""){
                sum = 0;
            }
            else{
                numbers = numbers.replaceAll("[^\\d]", " ");

                numbers = numbers.trim();
                numbers = numbers.replaceAll(" ", ",");
                String[] split_num = numbers.split(",");

                for(int i=0; i<split_num.length; i++)
                    sum += Integer.parseInt(split_num[i]);
            }
            method_invoke += 1;

        } catch (Exception e) { }

        return String.valueOf(sum);
    }

    public int GetCalledCount(){
        return method_invoke;
    }
}

public class calculator{

    public static void main(String[] args){
        
        try {
            String num;
            Scanner sc = new Scanner(System.in);
            
            StringCalculator obj = new StringCalculator();
            
            System.out.println(obj.Add("1,2,3"));
            System.out.println(obj.Add("1\n2;3"));
            System.out.println(obj.Add("1,\n"));
            System.out.println(obj.Add("//;\n1,2;2"));
            System.out.println(obj.Add("//[***]\n1***2***3"));
            System.out.println(obj.Add("//[*][%]\n1*2%3"));
        
            System.out.println(obj.GetCalledCount());

        } catch (Exception e) { }
        
    }
}