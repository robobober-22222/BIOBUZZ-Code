package org.firstinspires.ftc.teamcode.competition;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@Autonomous(name = "Auto Preload Partner Flower")
public class AutoPreloadPartnerFlower extends LinearOpMode{
    DrivetrainHardware robot;
    @Override
    public void runOpMode() {

        // Connect and prepare the robot hardware
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();
        // driveRotate() cannot handle 180 degrees as an argument. Use 170 instead to account for friction.
        if (opModeIsActive()){
            sleep(500); // shoot
            robot.driveRotate(110, 0.3); // rotate to face the FLOWER (imitate moving to FLOWER before we have an intake)
            sleep(300); // intake activation
            robot.driveRotate(-110, 0.3); // rotate to face the HIVE
            sleep(500); // shoot
            // GARDEN collection
            robot.driveStraight(300, 0.3); // move to the opposite side of the playing field
            robot.driveSide(150, 0.3); // move towards GARDEN
            robot.driveStraight(20,0.3); // move to the GARDEN position
            sleep(300); // intake activation
            // Move to shooting position
            robot.driveStraight(-20,0.3);
            robot.driveSide(-150, 0.3);
            robot.driveRotate(170, 0.3);
            sleep(500); // shoot
            // FLOWER collection
            robot.driveRotate(-45, 0.3);
            robot.driveStraight(180,0.3);
            sleep(300); // intake
            // Move to shooting position
            robot.driveStraight(-180,0.3);
            robot.driveRotate(45, 0.3);
            sleep(500); // shoot
            // PARKING
            robot.driveStraight(300, 0.3);
            robot.driveSide(-150, 0.3);
            robot.driveSide(5, 0.3); // Make sure the robot isn't touching the wall
        }
    }
}