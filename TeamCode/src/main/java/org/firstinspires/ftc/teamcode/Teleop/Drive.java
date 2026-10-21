package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@TeleOp(name="Drive Logic", group="Teleop")
public class Drive extends OpMode {
    boolean DpDown= false;
    Hardware hw = new Hardware();
    double intakePos = 0;

    @Override
    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
    }

    @Override
    public void loop() {

        /* TODO:
           Add Enum for Red and Blue tips and IDs
           Develop y tracking and hive flip tracking system using getAprilTagPoseY()
           Maybe: Get the field maps switching depending on hive flips
         */

        hw.updateVision();

        if(gamepad1.dpadDownWasPressed()){DpDown = !DpDown;}

        if (DpDown) {
            hw.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
        } else {
            hw.driveWithLimeLightGoalCorrection(gamepad1.left_stick_x, -gamepad1.left_stick_y, 30);
        }

        hw.shootOnKey(gamepad1.right_trigger);
        hw.intakeOnKey(gamepad1.left_trigger);

        if (gamepad1.dpadLeftWasPressed())
        {
            intakePos -= 0.1;
        }
        else if (gamepad1.dpadRightWasPressed())
        {
            intakePos += 0.1;
        }

        hw.liftBalls(gamepad1.left_bumper);

        telemetry.addData("Tx: ", hw.getTagValues(32).getTx());
        telemetry.addData("Ty: ", hw.getTagValues(32).getTy());
        telemetry.addData("Tag ID 30 y pose: ", hw.getAprilTagPoseY(32));
        telemetry.addData("Servo Pos: ", intakePos);
        telemetry.update();
    }
}
