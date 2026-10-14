package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@TeleOp(name="Drive Logic", group="Teleop")
public class Drive extends OpMode {
    boolean DpDown= false;
    Hardware hw = new Hardware();

    @Override
    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
    }

    @Override
    public void loop() {
        // Button Toggles Logic
        if(gamepad1.dpadDownWasPressed()){DpDown = !DpDown;}

        if (DpDown) {
            hw.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
        } else {
            hw.driveWithLimeLight(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        }

        hw.shootOnKey(gamepad1.right_trigger);
        hw.intakeOnKey(gamepad1.left_trigger);

        telemetry.addData("Tx: ", hw.limeLight.getValues().getTx());
        telemetry.addData("Ty: ", hw.limeLight.getValues().getTy());
        telemetry.update();
    }
}
