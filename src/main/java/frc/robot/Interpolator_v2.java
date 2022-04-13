/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Interpolator_v2 {

    //This is an ordered list of "distance" measurements (this example is the y-angle reported by the Limelight).
    //This must be monotonically decreasing

    // Find these values from Limelight, tY
    private static double angles[] = {20.00, 18.00, 11.75, 2.00}; // 3.75 was max previously
    
    // The front and back launch motors need to have different speeds
    // Speed of the front launcher motor
    private static double speedFront[] = {0.30, 0.35, 0.40, 0.30}; 
    // Speed of the back launcher motor
    private static double speedBack[] = {0.35, 0.40, 0.45, 0.60};
    
    // If it consistently shoots too low, this variable is used to correct that
    private static double manualOffset = 0;

    // At some point would have max angle at which point the launch motors should not fire, i.e. speed = 0  
    private static double maxAngle = 25;
    
    /**
     * Static method that takes in the "distance" measurement and outputs "speed"
     * @param Distance measurement in "distance" units
     * @returns Speed in "speed" units
     */
    private static double getInterpolation(double[] speeds, double angle) {
    // Search through the angles[] array to find the two values that the current
    // measurement is between (this is like finding two points on a graph)
        // int interAngle = 0;
        // while(angles[interAngle] > angle) {
            /// interAngle++;

        // Added a Max Angle but this might not be needed with line of code in 'if' ~Scoy Comment
        int interAngle = 0;
         while(angles[interAngle] > angle && angles[interAngle] < maxAngle ) {
         interAngle++;
        

            //If it didn't find two points, return the last speed
            
            if(interAngle >= angles.length) {
                return speeds[speeds.length - 1];
            
            /** This might be where launcher should default to speed = 0 to break out of loop; 
             * We have a manual firing button 
             * so we don't want the robot fire at default speed ~Scoy Comment
            **/

            // if(interAngle >= angles.length) {
                // Sreturn speeds[0];
            
            }
        }
        
        /** Attempted fix to break out of loop
         * Chance of heading a taget solution = exactly zero is very, very small
         * 0 is more likely to be returned if there is no target acquired 
         * 
        * Original code
         */
        if(interAngle == 0) {
            return speeds[interAngle];

        // if(interAngle == 0 || interAngle > maxAngle) {
            // return speeds[0];
        }

        // Make a line from the points (angles[i-1], speeds[i-1]) and (angles[i], speeds[i])
        // in the form y = mx+b
        int interAngle1 = interAngle-1;
        int interAngle2 = interAngle;
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
        SmartDashboard.putNumber("Offset", offset);
    }
}