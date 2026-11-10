package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class FlowerBoxHardware {
    private DcMotor flowerBox;

    private Servo flowerBoxTip;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep()


    public FlowerBoxHardware(LinearOpMode opMode) {this.opMode = opMode;}

    // TODO: this constans are wrong
    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_CM = 10.4;

    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI;

    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    private double position = 0;



    public void flowerInitialize(){
        flowerBox = opMode.hardwareMap.get(DcMotor.class, "flowerBox");
        // TODO: fix
        flowerBoxTip = opMode.hardwareMap.get(Servo.class, "place holder");
        // TODO: range from 0 to 1, isn't it?
        flowerBoxTip.scaleRange(-10,0);
        flowerBoxTip.getPosition();

        flowerBox.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
// TODO: mm or sm? 
// 84 mm
    public void slider(double distance, double sliderPower){
        // TODO: how about not reset it every time, so that 
        // we know at which height slider is right now
        flowerBox.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flowerBox.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sliderPower = Math.abs(sliderPower);
        if(distance < 0 ){
            sliderPower *= -1;
        }

        flowerBox.setPower(sliderPower);

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
    public void stopMotor() {
        setSlidePower(0);
    }

    // ====== SET DRIVE POWER ======
    // TODO: lets add boundaries like MIN_TICKS and MAX_TICKS so that we do not move out of range
    public void setSlidePower(double sliderPower) {
        flowerBox.setPower(sliderPower);
    }

    public void getAngle(){
       position = flowerBoxTip.getPosition();
    }




}
