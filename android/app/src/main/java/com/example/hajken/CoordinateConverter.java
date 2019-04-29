package com.example.hajken;

import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class CoordinateConverter {

    private String instructions = "<";
    private MathUtility mathUtility = new MathUtility();

    public String returnString(ArrayList<PointF> validPoints){
        Log.d(TAG, "returnString: ");

        instructions = instructions.concat("L,"+(((validPoints.size()-1)*4)+2));

        //Change speed of vehicle
        instructions = instructions.concat(",V,3,"); // this needs to adaptable later

        for(int i = 0; i < validPoints.size()-1;i++){
            instructions = instructions.concat("R,"+mathUtility.getRotation(validPoints.get(i),validPoints.get(i+1)));
            instructions = instructions.concat(",");
            instructions = instructions.concat("F,"+mathUtility.getMagnitude(validPoints.get(i),validPoints.get(i+1)));


            Log.d(TAG, "returnString: "+validPoints.size());
            Log.d(TAG, "returnString: "+i);
           if (i+1 == validPoints.size()-1) {
               instructions = instructions.concat(">");
           } else {
               instructions = instructions.concat(",");
           }
        }
        Log.d(TAG, "returnString: "+instructions);
        return instructions;
    }

}
