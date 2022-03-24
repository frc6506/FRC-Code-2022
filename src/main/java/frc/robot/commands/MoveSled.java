// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sled;

public class MoveSled extends CommandBase {
  private final Sled sled;
  /** Creates a new moveSled. */
  public MoveSled(Sled slideyBit) {
    sled = slideyBit;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(slideyBit);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = RobotContainer.controller.getRawAxis(5) * 0.75;
    sled.slideSled(speed);    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    sled.slideSled(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Make this defualt command?
    return false;
  }
}
