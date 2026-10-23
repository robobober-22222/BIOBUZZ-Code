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

    static double speed_factor = 0.5;
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



        reset();

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(logoDirection, usbDirection)
        );
        imu.initialize(parameters);

        imu.resetYaw();

        while (opModeInInit()) {
            reset();
        }

        waitForStart();

        if (opModeIsActive()) {

            straight(20,speed_factor);
            rotate(90,-speed_factor);
            straight(110,speed_factor);
            rotate(90,-speed_factor);
            straight(50,speed_factor);
            straight(50,-speed_factor);
            side(20,speed_factor);
            rotate(180,-speed_factor);
            straight(240,speed_factor);
            rotate(90,speed_factor);
            straight(50,speed_factor);
            rotate(90,speed_factor);
            sleep(500);
            rotate(90,speed_factor);
            straight(90,speed_factor);









        }
    }
    public void straight(double distance, double power) {
        reset();

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

        reset();

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
    public void reset(){
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

