package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.CBA1Input;
import frc.robot.subsystems.Drivetrain;

public class CBATimeCommand extends ParallelCommandGroup implements ICBACommand{
    public CBATimeCommand(Drivetrain drivetrain, CBA1Input input) {
        super();
        addCommands(new ParallelDeadlineGroup(new WaitCommand(input.interval), new DriveCommand(drivetrain, () -> {return input.x;}, () -> {return input.y;}, () -> {return input.theta;})));
    }
}
