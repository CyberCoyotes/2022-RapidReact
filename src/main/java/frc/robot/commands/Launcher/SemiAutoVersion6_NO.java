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

/** Add your docs here. */
public class SemiAutoVersion6_NO{

boolean targetLock = false;
    
    double tx = 2;

    public void targetStatus(Index indexMotors, Launcher launcher)  {  

      // TODO eventually add the ty conditional, but for now just use the tx and indexMotors command.
      if (tx < 5) {
        //set targetLock to true when tx & ty are within the parameters
        targetLock = true;
        return; 
      } // end of 'if' conditional
      
    }

    public void SemiAutoVersion6(Index indexMotors, Launcher launcher){
      //if targetLock equals true, run the launch sequence
      if (targetLock){
          new IndexSpeed(indexMotors, 0.5);
         } else {
          new LaunchSpeed(launcher, 0.5, 0.5);}
    }
} // end of class