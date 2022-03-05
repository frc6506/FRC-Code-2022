// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  // Motor Controller Objects
  CANSparkMax leftMotor1 = new CANSparkMax(Constants.MOTOR_LEFT_FRONT_ID, MotorType.kBrushless);
  CANSparkMax leftMotor2 = new CANSparkMax(Constants.MOTOR_LEFT_BACK_ID, MotorType.kBrushless);
  CANSparkMax rightMotor1 = new CANSparkMax(Constants.MOTOR_RIGHT_FRONT_ID, MotorType.kBrushless);
  CANSparkMax rightMotor2 = new CANSparkMax(Constants.MOTOR_RIGHT_BACK_ID, MotorType.kBrushless);

  // Motor Controller Group Objects
  MotorControllerGroup leftMotors = new  MotorControllerGroup(leftMotor1, leftMotor2);
  MotorControllerGroup rightMotors = new  MotorControllerGroup(rightMotor1, rightMotor2);

  // Invert Right side (Correct backwardsness.)
  rightMotors.setInverted(true);

  DifferentialDrive dualDrive = new DifferentialDrive(leftMotors, rightMotors); 

  public Drivetrain() {
  }

  // Wrapper Methods
  /**
   * @param speed
   * @param rotation
   */
  public void aDrive(double speed, double rotation){
    dualDrive.arcadeDrive(speed,rotation);
  }

  /**
   * 
   * @param leftSPeed
   * @param righSpeed
   */
  public void dDrive(double leftSpeed, double rightSpeed) {
    dualDrive.tankDrive(leftSpeed, rightSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
