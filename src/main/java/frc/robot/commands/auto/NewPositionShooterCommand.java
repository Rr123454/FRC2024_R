package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooter.ShooterSubsystem;

public class NewPositionShooterCommand extends Command {
    public ShooterSubsystem shooter;
    public Timer timer;

    public PositionShooterCommand(ShooterSubsystem shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
      timer.reset();
    }

    @Override
    public void execute() {
        this.shooter.pivotToAngle(70);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
 
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return timer.hasElapsed(2.5);
    }
}