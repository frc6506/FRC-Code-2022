/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  
  public static final int MOTOR_LEFT_ID_1 = 10;
  public static final int MOTOR_LEFT_ID_2 = 11;
  public static final int MOTOR_RIGHT_ID_1 = 12;
  public static final int MOTOR_RIGHT_ID_2 = 13;

  // Joystick
  public static final int CONTROLLER_PORT_ID = 0;

  // left joystick
  public static final int JOYSTICK_DRIVE_FORWARDS_ID = 1;
  public static final int JOYSTICK_DRIVE_ROTATION_ID = 0;

}
