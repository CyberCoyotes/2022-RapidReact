package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.CBA1Input;
import frc.robot.subsystems.Drivetrain;

public class CBAObjectiveThetaCommand extends ParallelCommandGroup implements ICBACommand {
    public CBAObjectiveThetaCommand(Drivetrain drivetrain, CBA1Input input) {
        //this should work since everything is still field oriented. 
        addCommands(new ParallelCommandGroup(new ParallelDeadlineGroup(new WaitCommand(input.interval), new DriveCommand(drivetrain, () -> {return input.x;}, () -> {return input.y;}, () -> {return 0.0;})), new TurnToDegrees(drivetrain, input.theta)));//need the turndegrees from main
    }
}
