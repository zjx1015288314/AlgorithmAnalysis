package com.zjx.DataStructure.Chapter2;

import java.util.function.Function;

/**
 * function x^2-5x+4
 */
public class DataAnalysisByGeneric {
    static <T> float find(float low, float high, Function<Float,Float> function) throws Exception {
        if (low > high){
            throw new Exception("Illegal State");

        }
        float mid = (low + high)/2;
        if (Math.abs(function.apply(mid)) <= 0.00001){
            return mid;
        }else{
            if (function.apply(mid) * function.apply(low) < 0)
                return find(low,mid,function);
            else
                return find(mid,high,function);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("The result of function x^2-5x+4 is " + find(-4.2f,5.0f,new MyFunction()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
class MyFunction implements Function<Float,Float>{

    @Override
    public Float apply(Float aFloat) {
        return aFloat*aFloat - 5*aFloat + 4;
    }
}
