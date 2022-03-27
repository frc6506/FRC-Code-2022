// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// TODO unfinished

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveDist extends CommandBase {
  private final Drivetrain driveTrain;

  // private double distance;

  /** Creates a new AutoDriveDist. */
  public AutoDriveDist(Drivetrain dt) {
    driveTrain = dt;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // driveTrain.resetDistCounter();  // TODO unfinished
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Auton2 go!!!");
    double speed = -.25;
    driveTrain.dDrive(speed, -speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.dDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return driveTrain.getDistanceDriven() < 8;
  }
}
