
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DrivetrainHardwareSimple {

    // ====== HARDWARE OBJECTS ======
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private IMU imu;

    // ====== CONSTANTS ======
    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_CM = 10.4;
    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI;
    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;
    private LinearOpMode opMode; // This lets us use OpMode features like sleep.
    public DrivetrainHardwareSimple(LinearOpMode opMode) {this.opMode = opMode;}

    // ====== INITIALIZATION ======
    public void initialize() {
        // ------ Hardware Init ------
        leftFront = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = opMode.hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = opMode.hardwareMap.get(DcMotor.class, "rightBack");

        imu = opMode.hardwareMap.get(IMU.class, "imu");

        // ------ DcMotor Modes ------
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // ------ IMU Init ------
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection));

        // ------ Reset Values ------
        imu.initialize(parameters);
        imu.resetYaw();
        resetEncoders();
    }

    // ====== STRAIGHT DRIVE ======
    public void straight(double distance, double power) {
        resetEncoders();

        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        while (opMode.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < distance * TICKS_PER_CM) {
            opMode.idle();
        }
        stopMotors();
        opMode.sleep(500);
    }

    // ====== ROTATE DRIVE ======
    public void rotate(double angle, double power) {
        imu.resetYaw();

        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(-power);
        rightBack.setPower(-power);

        while (opMode.opModeIsActive() && Math.abs(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)) < angle) {
            opMode.idle();
        }
        stopMotors();
        opMode.sleep(500);
    }

    // ====== STRAFE DRIVE ======
    public void side(double distance, double power) {
        resetEncoders();

        leftFront.setPower(power);
        leftBack.setPower(-power);
        rightFront.setPower(-power);
        rightBack.setPower(power);

        while (opMode.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < distance * TICKS_PER_CM) {
            opMode.idle();
        }
        stopMotors();
        opMode.sleep(500);
    }

    // ====== RESET ENCODERS ======
    public void resetEncoders() {

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // ====== STOP MOTORS ======
    public void stopMotors() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }
}
