package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.CBA1Input;
import frc.robot.subsystems.Drivetrain;



public class CBA extends SequentialCommandGroup {
    /**the coords to for the robot to attempt to replicate; note this will be a shallow attempt, and may need tuning forward*/
    //#region properties

    /* Discussing conversion of desired ending angle to actual input
    80% power
    1 second
    150% turning
    90 deg
        very likely dependent on turn power and time alone

        let t = turn power, s = time, in seconds
        90deg = 1.5t*1s
        1 deg = (1.5/90)*1s
        45 deg = 45*(1.5/90)*1
        90 deg = 45*(1.5/90)*2 (?)

            */
    
    private CBA1Input[] coords;
    //Subsystem. Drives.
    private Drivetrain driveSubsytem;
    //A simple flag to indicate if the command has finished.
    //private boolean isFin = false;
    //#endregion
   
//#region helpers

private double DegToPower(double theta, double seconds){
    return theta*(1.5/90*seconds);
}
    private ParallelDeadlineGroup TranslateIntoUsableCommand(CBA1Input input) {
        double deg = DegToPower(input.theta, input.interval);
        WaitCommand deadline = new WaitCommand(input.interval);
        DriveCommand driver = new DriveCommand(driveSubsytem, () -> {return input.x;}, () -> {return input.y;}, () -> {return deg;});
    
        return new ParallelDeadlineGroup(deadline, driver);
    
    }
    
//#endregion

//#region constructors

/**
 * @param subsystem The subsystem
 * @param coords the Inputs to use 
 */
public CBA(Drivetrain subsystem,CBA1Input[] coords) {
 
     this.driveSubsytem = subsystem;
     this.coords = coords;
     //note that we cannot add commands while the bot is running, which is to say that during initialize() we cannot add commands as that runs when the command is scheduled
     for(CBA1Input input : coords){
        //Adds a usable version of the input as a command deadlined by the interval for every input.
        //This could possibly be optimized, but list conversions make me wanna cry 
        addCommands(TranslateIntoUsableCommand(input));}


   
     }
     
/**
     * @param collections the Double array to construct a new Clock-Based-Auton sequence. The ordering for each double is as follows:
     *  <ol>
     *      <li>X-coord</li>
     *      <li>Y-coord</li>
     *      <li>Theta(turning)</li>
     *      <li>Interval</li>
     *  </ol>
     */
public CBA(Drivetrain subsystem, Double[]... collections) {
    this.driveSubsytem = subsystem;
    for(int i = 0; i < collections.length; i++){
       coords[i].x = collections[i][0];
       coords[i].y = collections[i][1];
       coords[i].theta = collections[i][2];
       coords[i].interval = collections[i][3];
       
    }


    for(CBA1Input input : coords){
        //Adds a usable version of the input as a command deadlined by the interval for every input.
        //This could possibly be optimized, but list conversions make me wanna cry 
        addCommands(TranslateIntoUsableCommand(input));}
}

//#endregion


//#region overrides

    @Override
    public void initialize() {
        
        super.initialize();
        addRequirements(driveSubsytem);
        
          
    }
    

//#endregion

}
