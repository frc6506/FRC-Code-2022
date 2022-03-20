// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotContainer;

public class Shoot extends CommandBase {

  /** Creates a new Shoot. */
  public Shoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    
    // TODO: Consider changing static access to passed paramters as per https://stackoverflow.com/questions/45180476/passing-an-instance-through-constructors-or-accessing-it-with-static
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    CommandScheduler.getInstance().schedule(RobotContainer.startFlywheel); // Not sure best way to get commands
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(RobotContainer.outtake.reachedVelocity()) {
      CommandScheduler.getInstance().schedule(RobotContainer.spinFeedWheel);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    CommandScheduler.getInstance().cancel(RobotContainer.spinFeedWheel); // Canceling will/ should  interupt the command and stop the feed wheel
    CommandScheduler.getInstance().schedule(RobotContainer.stopFlywheel);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
