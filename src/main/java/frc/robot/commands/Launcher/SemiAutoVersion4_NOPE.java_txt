/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Launcher;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.IndexSpeed;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Launcher;


public class SemiAutoVersion4 extends CommandBase {
   
    public SemiAutoVersion4(){}
    
    public void excute(Index indexMotors, Launcher launcher){
    // double tx = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("tx").getDouble(0);
    double tx = 2; // Defining for testing purposes
    // double ty = NetworkTableInstance.getDefault().getTable("limelight-back").getEntry("ty").getDouble(0);
    
      if (tx < 5) {
          new IndexSpeed(indexMotors, 0.5);
         } else {
          new LaunchSpeed(launcher, 0.5, 0.5);}
    }

    public void end(boolean interrupted) {
        ;
      }

} // end of class