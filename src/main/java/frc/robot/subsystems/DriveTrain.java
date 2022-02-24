// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  /** Creates a new DriveTrain. */
  CANSparkMax leftMotor1 = new CANSparkMax(RobotMap.MOTOR_LEFT_ID_1, MotorType.kBrushed);
  CANSparkMax leftMotor2 = new CANSparkMax(RobotMap.MOTOR_LEFT_ID_2, MotorType.kBrushed);
  CANSparkMax rightMotor1 = new CANSparkMax(RobotMap.MOTOR_RIGHT_ID_1, MotorType.kBrushed);
  CANSparkMax rightMotor2 = new CANSparkMax(RobotMap.MOTOR_RIGHT_ID_2, MotorType.kBrushed);

  MotorControllerGroup leftMotors = new  MotorControllerGroup(leftMotor1,leftMotor2);
  MotorControllerGroup rightMotors = new  MotorControllerGroup(rightMotor1,rightMotor2);

  DifferentialDrive dualDrive = new DifferentialDrive(leftMotors,rightMotors); 

  public Drivetrain() {
  }

  // Wrapper Methods
  /**
   * @author .
   * @param speed
   * @param rotation
   */
  public void aDrive(double speed,double rotation){
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
