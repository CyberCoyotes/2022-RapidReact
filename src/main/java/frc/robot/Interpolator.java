/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Interpolator {
    
    /* Use these for testing with extreme values
    private static double angles[] = {30, 0};
    private static double speedFront[] = {1, 0};
    private static double speedBack[] = {1, 0};
    */

    //This is an ordered list of "distance" measurements (this example is the y-angle reported by the Limelight).
    //This must be monotonically decreasing

    // Find these values from Limelight
    private static double angles[] = {20.00, 18.00, 11.75, 2.00}; // 3.75 was max previously
    
    // The front and back launch motors need to have different speeds

    // Speed of the front launcher motor
    private static double speedFront[] = {0.30, 0.35, 0.40, 0.30}; 
    // Speed of the back launcher motor
    private static double speedBack[] = {0.35, 0.40, 0.45, 0.60};
    
    // If it consistently shoots too low, this variable is used to correct that
    private static double manualOffset = 0;

    /**
     * Static method that takes in the "distance" measurement and outputs "speed"
     * @param Distance measurement in "distance" units
     * @returns Speed in "speed" units
     */
    private static double getInterpolation(double[] speeds, double angle) {

    // Search through the angles[] array to find the two values that the current
    // measurement is between (this is like finding two points on a graph)
        int interAngleIndex = 0;
        while(angles[interAngleIndex] > angle) {
            interAngleIndex++;

            //If it didn't find two points, return the last speed
            if(interAngleIndex >= angles.length) {
                return speeds[speeds.length - 1];
            }
        }
        
        // Original code
        if(interAngleIndex == 0) {
            return speeds[interAngleIndex];        
        }

        // Make a line from the points (angles[i-1], speeds[i-1]) and (angles[i], speeds[i])
        // in the form y = mx+b
        int interAngle1 = interAngleIndex-1;
        int interAngle2 = interAngleIndex;
        double m = (speeds[interAngle2]-speeds[interAngle1])/(angles[interAngle2]-angles[interAngle1]);
        double b = speeds[interAngle1] - m*angles[interAngle1];

        // Find where the recorded point fits on that line, that's the speed!
        double interpolation = m*angle + b + manualOffset;
        return interpolation;
    }

    // Returns the interpolation speed value of the front motor to the SmartDashboard
    public static double getFrontSpeed(double angle) {
        double interResult = getInterpolation(speedFront, angle);
        SmartDashboard.putNumber("Inter. Front Speed", interResult);
        return interResult;
    }

    // Returns the interpolation speed value of the back motor to the SmartDashboard
    public static double getBackSpeed(double angle) {
        double interResult = getInterpolation(speedBack, angle);
        SmartDashboard.putNumber("Inter. Back Speed", interResult);
        return interResult;
    }

    /**
     * Static method used to set a constant offset (for chronic over or under shooting)
     * @param Offset in "speed" units
     */
    public static void setOffset(double offset) {
        manualOffset = offset;
    }
}