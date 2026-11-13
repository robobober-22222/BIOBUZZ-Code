package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class FlowerBoxHardware {
    private DcMotor flowerBox;

    private Servo flowerBoxTip;
    //private TouchSensor TouchSensorWheel;
    private LinearOpMode opMode; // This lets us use OpMode features like sleep()


    public FlowerBoxHardware(LinearOpMode opMode) {this.opMode = opMode;}
    //TODO: Check parameters
    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_MM = 38.2;

    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_MM * Math.PI;

    private final double TICKS_PER_MM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    private double TipAngle;





    public void flowerInitialize(){
        flowerBox = opMode.hardwareMap.get(DcMotor.class, "flowerBox");
        flowerBoxTip = opMode.hardwareMap.get(Servo.class, "flowerBoxTip");
        //TouchSensorWheel = opMode.hardwareMap.get(TouchSensor.class, "TouchSensorWheel");
        flowerBoxTip.scaleRange(0,1);
        flowerBoxTip.getPosition();

        flowerBox.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        flowerBox.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flowerBox.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    // makes slider go to that position
    /*
    public void slide(double position, double sliderPower){
        //TODO: add max position
        sliderPower = Math.abs(sliderPower);
        if (!opMode.opModeIsActive()){
            return;
        }
        if (position <= 0 ) {
            setSlidePower(-sliderPower);
           while (opMode.opModeIsActive() && TouchSensorWheel.isPressed() != true){
               opMode.idle();
            }
           stopFlower();
       } else {
            if (!TouchSensorWheel.isPressed()){

            }
            resetFlower();
            setSlidePower(sliderPower);
            while (opMode.opModeIsActive() && Math.abs(flowerBox.getCurrentPosition()) < Math.abs(position * TICKS_PER_MM)){
                opMode.idle();
            }
            stopFlower();
        }
        opMode.sleep(500);

    }
    */
    public void servoTurn(double position){
            // servo turns
            flowerBoxTip.setPosition(position);
            opMode.sleep(500);
    }
    public void stopFlower() {
        setSlidePower(0);
    }


    // TODO:  add boundaries like MIN_TICKS and MAX_TICKS so that we do not move out of range

    public void setSlidePower(double sliderPower) {
        flowerBox.setPower(sliderPower);
    }

    public void getAngle(){
       TipAngle = flowerBoxTip.getPosition();
    }

    public void resetFlower(){
        flowerBox.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flowerBox.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }




}
