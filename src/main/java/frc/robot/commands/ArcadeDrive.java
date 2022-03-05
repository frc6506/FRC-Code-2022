// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

//import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
//import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotContainer;
//import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.Constants;

public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain driveTrain;
  
  /** Creates a new ArcadeDrive. */
  //public ArcadeDrive(Subsystem subsystem) {
  public ArcadeDrive(Drivetrain dt) {
    //m_subsystem = subsystem;
    driveTrain = dt;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  //

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //double speed = Robot.m_oi.getAxis(RobotMap.JOYSTICK_DRIVE_FORWARDS_ID) * -1;
    //double rotation = Robot.m_oi.getAxis(RobotMap.JOYSTICK_DRIVE_ROTATION_ID)
    double speed = RobotContainer.controller.getRawAxis(1);
    double rotation = RobotContainer.controller.getRawAxis(0);
    driveTrain.aDrive(speed, rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
