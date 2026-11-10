package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


public class FlowerBoxHardware {
    private DcMotor flowerBox;

    private Servo flowerBoxTip;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep()


    public FlowerBoxHardware(LinearOpMode opMode) {this.opMode = opMode;}

    // TODO: check numbers for constants
    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_MM = 38.2;

    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_MM * Math.PI;

    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    private double position = 0;



    public void flowerInitialize(){
        flowerBox = opMode.hardwareMap.get(DcMotor.class, "flowerBox");
        flowerBoxTip = opMode.hardwareMap.get(Servo.class, "place holder");
        flowerBoxTip.scaleRange(-0.03,0);
        flowerBoxTip.getPosition();

        flowerBox.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        flowerBox.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flowerBox.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
// 736 mm

    // makes slider go to that position
    public void slide(double position, double sliderPower){

        sliderPower = Math.abs(sliderPower);
        position = Math.abs(position);
      // TODO: later add the max

      //  if position > 736;
        if (position > flowerBox.getCurrentPosition()){
            flowerBox.setPower(sliderPower);

            while (opMode.opModeIsActive() && flowerBox.getCurrentPosition() < Math.abs(position * TICKS_PER_CM)) {
                opMode.idle();
            }
        }
        else if (position < flowerBox.getCurrentPosition()) {
            flowerBox.setPower(-sliderPower);

            while (opMode.opModeIsActive() && flowerBox.getCurrentPosition() > Math.abs(position * TICKS_PER_CM)) {
                opMode.idle();
            }
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


    // TODO:  add boundaries like MIN_TICKS and MAX_TICKS so that we do not move out of range

    public void setSlidePower(double sliderPower) {
        flowerBox.setPower(sliderPower);
    }

    public void getAngle(){
       position = flowerBoxTip.getPosition();
    }




}
