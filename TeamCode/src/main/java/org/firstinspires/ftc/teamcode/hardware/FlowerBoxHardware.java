package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class FlowerBoxHardware {
    private DcMotor flowerBox;

    private Servo flowerBoxTip;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep()
    public FlowerBoxHardware(LinearOpMode opMode) {this.opMode = opMode;}

    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_CM = 10.4;

    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI;

    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    public void flowerInitialize(){
        flowerBox = opMode.hardwareMap.get(DcMotor.class, "flowerBox");
        flowerBoxTip = opMode.hardwareMap.get(Servo.class, "place holder");
        flowerBoxTip.scaleRange(-10,0);
        flowerBoxTip.getPosition();

        flowerBox.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
// 84 mm
    public void slider(double distance, double power){
        flowerBox.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flowerBox.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        power = Math.abs(power);
        if(distance < 0 ){
            power *= -1;
        }

        flowerBox.setPower(power);

        while (opMode.opModeIsActive() && Math.abs(flowerBox.getCurrentPosition()) < Math.abs(distance * TICKS_PER_CM)) {
            opMode.idle();
        }
        flowerBox.setPower(0);
        opMode.sleep(500);

    }
    public void servoTurn(double position){
            // servo turns
            flowerBoxTip.setPosition(position);
            opMode.sleep(500);
    }




}
