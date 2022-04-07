/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.DEV;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Launcher;


public class SemiAuto_07 extends CommandBase{
    private final Launcher launcher;
    
      NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-back");//Instantiate the tables
       double tx = limelight.getEntry("tx").getDouble(0.0);
       double triggerValue = 5.0;

    public SemiAuto_07(Launcher launch) {
        // Use addRequirements() here to declare subsystem dependencies.

        
        launcher = launch;
        addRequirements(launcher);
      }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {}
      
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
        if (tx <= triggerValue){
          launcher.setLauncherSpeed(0.5, 0.5);
        }
        else {
          launcher.stopLauncher();
        }
        
      }
    
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {
      }
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return false;
      }

}
    