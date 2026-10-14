package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LimeLight {
    public Limelight3A limelight3A;

    public void init(HardwareMap hwMap)
    {
        limelight3A = hwMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(8);
    }

    public LLResult getValues()
    {
        LLResult result = limelight3A.getLatestResult();

        return result;
    }

    public void startCamera()
    {
        limelight3A.start();
    }

    public void stopCamera()
    {
        limelight3A.stop();
    }
}
