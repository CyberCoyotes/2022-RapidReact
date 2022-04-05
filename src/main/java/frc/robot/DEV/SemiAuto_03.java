// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.DEV;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.Launcher.LaunchSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;


public class SemiAuto_03 {
    public void SemiAutoVersion3(Index indexMotors, Launcher launcher){
    // double tx = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("tx").getDouble(0);
    double tx = 2; // Defining for testing purposes
    // double ty = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("ty").getDouble(0);
    
      if (tx < 5) {
          new IndexSpeed(indexMotors, 0.5);
         } else {
          new LaunchSpeed(launcher, 0.5, 0.5);}
    }
} // end of class