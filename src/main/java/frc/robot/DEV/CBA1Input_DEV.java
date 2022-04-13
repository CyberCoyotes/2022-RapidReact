package frc.robot.dev;

public class CBA1Input_DEV {
    
public double x, y, theta, interval; //joint declaration, usable for seperate types of same decoration and type



public CBA1Input_DEV(double x, double y, double interval) {
    this.x = x;
    this.y = y;
    this.interval = interval;
    
  
}
    
public CBA1Input_DEV(double x, double y,double interval, double theta ) {
    this.x = x;
    this.y = y;
    //Allows us to implement rotations later on
    this.theta = theta;
    this.interval = interval;

    
}
    
}
