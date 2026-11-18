package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@TeleOp(name="Drive Logic", group="Teleop")
public class Drive extends OpMode {
    Hardware hw = new Hardware();
    ButtonMapping controller = new ButtonMapping();

    int driver = 0;
    boolean currentTip = false;
    boolean alliance = false;
    // false = red
    // true = blue
    boolean llEnabled = false;
    public int primeTagID = 0;
    public float MAXVEL = 1080;

    @Override
    public void init() {
        hw.declareHardware(hardwareMap);
        hw.limeLight.startCamera();
        hw.limeLight.defineLLConstants(13, 33);
    }

    @Override
    public void loop() {
        controller.UpdateButtonMap(gamepad1, driver);

        if (controller.toggleLLAuto) {
            llEnabled = !llEnabled;
            if (!llEnabled) {hw.limeLight.resetPrimeID();}
        }

        if (controller.switchAlliance)
        {
            hw.limeLight.updateAlliance(true);
            hw.limeLight.resetHiveHeights();
            hw.limeLight.resetPrimeID();
        }
        else
        {
            hw.limeLight.updateAlliance(false);
        }

        if (gamepad1.dpadRightWasPressed() && driver < 1)
        {
            driver ++;
        }
        if (gamepad1.dpadLeftWasPressed() && driver > 0)
        {
            driver --;
        }

        /* TODO:
           Detect height of cetain tags and set current hive flip
           Autocorrect Rotation and power
         */

        if (llEnabled)
        {
            hw.limeLight.updateVision();

            if (primeTagID == 0 || !hw.limeLight.LLActive(primeTagID))
            {
                primeTagID = hw.limeLight.grabTagValueAlliance(alliance, currentTip);
                MAXVEL = 1080;
            }
            else
            {
                MAXVEL = (float) hw.limeLight.updateVelocity();
            }

        }
        else
        {
            hw.limeLight.resetPrimeID();
            MAXVEL = 1080;
        }

        if (!llEnabled)
        {
            hw.drive(controller.axialMovementX, -controller.axialMovementY, controller.rotationalMovement);
        }
        else
        {
            hw.driveLL(controller.axialMovementX, -controller.axialMovementY, controller.rotationalMovement, primeTagID);
        }

        hw.shootOnKey(controller.shootKey, MAXVEL);
        hw.intakeOnKey(controller.intakeKey);

        hw.liftBalls(controller.flipperKey);

        hw.limeLight.printAlliance(telemetry);

        if (llEnabled)
        {
            telemetry.addData("PrimeLLTag = ", primeTagID);
            if (hw.limeLight.LLActive(primeTagID))
            {
                telemetry.addData("Current Prime Tx = ", hw.limeLight.getTagValues(primeTagID).getTx());
                telemetry.addData("Current Prime Range = ", Math.abs(((hw.limeLight.getAprilTagPoseY(primeTagID) - 13) / (Math.tan(Math.toRadians(33 + hw.limeLight.getTagValues(primeTagID).getTy()))))));
            }
            hw.limeLight.printTagIds(telemetry);
            hw.limeLight.printStatus(telemetry);
            telemetry.addLine("");

            hw.limeLight.grabHiveHeightDiagnostics(telemetry);
        }

        telemetry.addData("Button Map: ", controller.name);
        telemetry.addData("Button Map#: ", driver);
        telemetry.addLine("");
        telemetry.addData("MAXVEL: ", MAXVEL);
        telemetry.addData("Motor Wheel Vel: ", hw.flyWheel.getVelocity());
        telemetry.update();
    }

    public void stop()
    {
        hw.limeLight.stopCamera();
    }
}
