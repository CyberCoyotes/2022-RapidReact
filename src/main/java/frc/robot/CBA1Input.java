package frc.robot;

public class CBA1Input {
    
public double x, y, theta, interval; //joint declaration, usable for seperate types of same decoration and type




public CBA1Input(double x, double y, double interval) {
    this.x = x;
    this.y = y;
    this.interval = interval;
    this.theta = 0;
    
  
}
    
public CBA1Input(double x, double y,double interval, double theta ) {
    this.x = x;
    this.y = y;
    //Allows us to implement rotations later on
    this.theta = theta;
    this.interval = interval;

    
}
    // a probably terrible solution for this
public void zeroTheta(){
this.theta = 0;
}

}
