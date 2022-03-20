// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.LinearQuadraticRegulator;
import edu.wpi.first.math.estimator.KalmanFilter;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.LinearSystemLoop;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Outtake extends SubsystemBase {
  // Motor Controller Objects
  private CANSparkMax flyWheelMotor =
      new CANSparkMax(Constants.MOTOR_FLYWHEEL_ID, MotorType.kBrushless);
  private VictorSPX feedWheelMotor = new VictorSPX(Constants.MOTOR_FEEDWHEEL_ID);

  private double angularVelocity;

  /** Creates a new Outtake. */
  public Outtake() {
    feedWheelMotor.setInverted(true); // Spins backwards
    resetLoop(); // Reset loop is placed in teleop init in the example
  }

  /**
   * @param speed, -1.00 <= speed, 1.00
   */
  public void spinFeedWheel(double speed) {
    feedWheelMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @param speed, decimal percent [-1.0, 1.0]
   */
  public void spinFlywheel(double speed) {
    flyWheelMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Tutorial:
  // https://docs.wpilib.org/en/stable/docs/software/advanced-controls/state-space/state-space-flywheel-walkthrough.html
  // Example:
  // https://github.com/wpilibsuite/allwpilib/blob/3b283ab9aaf9d23d7870b9c3723d03760a0bd378/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/statespaceflywheelsysid/Robot.java

  // Volts per (radian per second)
  private static final double kFlywheelKv = 0.099912;

  // Volts per (radian per second squared)
  private static final double kFlywheelKa = 0.0077474;

  /**
   * The plant holds a state-space model of our flywheel. This system has the following properties:
   *
   * <p>States: [velocity], in radians per second. Inputs (what we can "put in"): [voltage], in
   * volts.
   *
   * <p>Outputs (what we can measure): [velocity], in radians per second.
   *
   * <p>The Kv and Ka constants are found using the FRC Characterization toolsuite.
   */
  private final LinearSystem<N1, N1, N1> m_flywheelPlant =
      LinearSystemId.identifyVelocitySystem(kFlywheelKv, kFlywheelKa);

  // The observer fuses our encoder data and voltage inputs to reject noise.
  private final KalmanFilter<N1, N1, N1> m_observer =
      new KalmanFilter<>(
          Nat.N1(),
          Nat.N1(),
          m_flywheelPlant,
          VecBuilder.fill(
              3.0), // How accurate we think our model is  // As per, it shouldn't be trusted //
                    // TODO: Lower model trust, at this piont, mabye straight up PId or jst a sipmle
                    // P controler would be better
          VecBuilder.fill(0.01), // How accurate we think our encoderdata is
          0.020);

  // A LQR uses feedback to create voltage commands.
  private final LinearQuadraticRegulator<N1, N1, N1> m_controller =
      new LinearQuadraticRegulator<>(
          m_flywheelPlant,
          VecBuilder.fill(
              8.0), // qelms. Velocity error tolerance, in radians per second. Decrease this to more
          // heavily penalize state excursion, or make the controller behave more
          // aggressively.
          VecBuilder.fill(
              12.0), // relms. Control effort (voltage) tolerance. Decrease this to more heavily
          // penalize control effort, or make the controller less aggressive. 12 is a
          // good starting point because that is the (approximate) maximum voltage of a
          // battery.
          0.020); // Nominal time between loops. 0.020 for TimedRobot, but can be lower if using
  // notifiers.

  // The state-space loop combines a controller, observer, feedforward and plant for easy control.
  private final LinearSystemLoop<N1, N1, N1> m_loop =
      new LinearSystemLoop<>(m_flywheelPlant, m_controller, m_observer, 12.0, 0.020);

  public void resetLoop() {
    // Reset our loop to make sure it's in a known state.
    m_loop.reset(VecBuilder.fill(getVelocity()));
  }

  /**
   * @param angVelocity in rad/s, pre-adjusted for gearing
   */
  public void setFlyWheelO(double angVelocity) {
    angularVelocity = angVelocity;

    // Sets the target speed of our flywheel. This is similar to setting the setpoint of a

    // PID controller.
    // We just pressed the trigger, so let's set our next reference
    m_loop.setNextR(
        VecBuilder.fill(
            angVelocity)); // kSpinupRadPerSec appers ato be the refernce (target) volocity

    // Correct our Kalman filter's state vector estimate with encoder data.
    m_loop.correct(VecBuilder.fill(getVelocity()));

    // Update our LQR to generate new voltage commands and use the voltages to predict the next
    // state with out Kalman filter.
    m_loop.predict(0.020);

    // Send the new calculated voltage to the motors.
    // voltage = duty cycle * battery voltage, so
    // duty cycle = voltage / battery voltage

    double nextVoltage = m_loop.getU(0);

    flyWheelMotor.setVoltage(nextVoltage);
  }

  public void stopFlyWheel() {
    // We just released the trigger, so let's spin down
    m_loop.setNextR(VecBuilder.fill(0.0));
  }

  /**
   * @return Flywheel veloicy in rad/s of the NEO encoder times the 1.33 step up gearing
   */
  public double getVelocity() {
    return Units.rotationsPerMinuteToRadiansPerSecond(
        flyWheelMotor.getEncoder().getVelocity()
            * 1.33); // The Spark MAX reports in rev/s be defualt, but WPILib is metric and likes
    // rad/s (eww) for some reason.  See
    // https://www.chiefdelphi.com/t/units-for-spark-max-neo-encoder-and-state-space-control-kalman-filter-correction/406189 and https://github.com/wpilibsuite/allwpilib/blob/3b283ab9aaf9d23d7870b9c3723d03760a0bd378/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/statespaceflywheel/Robot.java
  }

  public boolean reachedVelocity() {
    return (getVelocity() >= angularVelocity * .95) && (getVelocity() <= angularVelocity * 1.05);
  }
}
