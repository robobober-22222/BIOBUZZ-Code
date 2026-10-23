package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name = "Auto Path By Methods")
public class AutoPathByMethods extends LinearOpMode {

    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;

    final double MOTOR_TICKS = 537.7;
    final double WHEEL_DIAMETER_CM = 10.4;

    final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI;
    final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    IMU imu;

    @Override
    public void runOpMode() {

        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack   = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack  = hardwareMap.get(DcMotor.class, "rightBack");

        imu= hardwareMap.get(IMU.class, "imu");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        resetEncoders();

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(logoDirection, usbDirection)
        );
        imu.initialize(parameters);

        imu.resetYaw();

        while (opModeInInit()) {

        }

        waitForStart();

        if (opModeIsActive()) {

            straight(20,0.3);
            rotate(90,0.3);
            straight(110,0.3);
            rotate(90,-0.3);
            straight(50,0.3);
            straight(50,-0.3);
            side(20,0.3);
            rotate(180,-0.3);
            straight(240,0.3);
            rotate(90,0.3);
            straight(50,0.3);
            rotate(90,0.3);
            sleep(500);
            rotate(90,0.3);
            straight(90,0.3);

        }
    }
    public void straight(double distance, double power) {
        resetEncoders();

        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);

        while (opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < distance * TICKS_PER_CM){}

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
        sleep(500);
    }

    public void rotate(double angle, double power) {

        imu.resetYaw();

        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(-power);
        rightBack.setPower(-power);

        while (opModeIsActive() && Math.abs(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)) < angle){}

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
        sleep(500);
    }
    public void side(double distance, double power){

        resetEncoders();

        leftFront.setPower(power);
        leftBack.setPower(-power);
        rightFront.setPower(-power);
        rightBack.setPower(power);

        while (opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < distance * TICKS_PER_CM){}

        leftFront.setPower(0.0);
        leftBack.setPower(0.0);
        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
        sleep(500);
    }
    public void resetEncoders(){
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}

