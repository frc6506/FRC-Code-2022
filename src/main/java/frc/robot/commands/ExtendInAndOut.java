// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.InAndOut2;

public class ExtendInAndOut extends CommandBase {
  private final InAndOut2 outAndIn2;
  /** Creates a new ExtendInAndOut. */
  public ExtendInAndOut(InAndOut2 inAndOut) {
    outAndIn2 = inAndOut;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(inAndOut);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    outAndIn2.InAndOutExtend(.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return outAndIn2.getFWDLimitState(); // Stop running if FWD limit reached
  }
}
