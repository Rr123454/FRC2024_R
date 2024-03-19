package frc.robot.subsystems.conveyor;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;
import frc.robot.util.NetworkTablesUtil;

public class ConveyorSubsystem extends SubsystemBase {
    private final CANSparkMax shooterFeederMotor;
    private final CANSparkMax conveyorMotorLeader;
    private final CANSparkMax conveyorMotorFollower;

    private static final DoublePublisher leftCurrentPublisher = NetworkTablesUtil.MAIN_ROBOT_TABLE.getDoubleTopic("left_conveyor_current").publish();
    private static final DoublePublisher rightCurrentPublisher = NetworkTablesUtil.MAIN_ROBOT_TABLE.getDoubleTopic("right_conveyor_current").publish();

    public ConveyorSubsystem() {
        this.shooterFeederMotor = new CANSparkMax(PortConstants.CONVEYOR_TO_SHOOTER_MOTOR_ID, MotorType.kBrushless);
        this.conveyorMotorFollower = new CANSparkMax(PortConstants.CONVEYOR_LEFT_MOTOR_ID, MotorType.kBrushless);
        this.conveyorMotorLeader = new CANSparkMax(PortConstants.CONVEYOR_RIGHT_MOTOR_ID, MotorType.kBrushless);

        this.conveyorMotorFollower.follow(conveyorMotorLeader, true);
    }

    public void setShooterFeederMotorSpeed(double speed) {
        this.shooterFeederMotor.set(speed);
    }

    public void setConveyorMotorsSpeed(double speed) {
        this.conveyorMotorLeader.set(speed);
    }

    @Override
    public void periodic() {
        leftCurrentPublisher.set(this.conveyorMotorFollower.getOutputCurrent());
        rightCurrentPublisher.set(this.conveyorMotorLeader.getOutputCurrent());
    }
}