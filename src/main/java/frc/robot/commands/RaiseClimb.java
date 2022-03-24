// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbRaiser;

public class RaiseClimb extends CommandBase {
  private final ClimbRaiser climbRaiser;

  /** Creates a new raiseClimb. */
  public RaiseClimb(ClimbRaiser goingUp) {
    climbRaiser = goingUp;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(goingUp);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("Raiseing climb...");
    climbRaiser.climbExtend(.25);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climbRaiser.climbExtend(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return climbRaiser.getFWDLimitState();
  }
}
