package com.example.stand_scout_2015;

import android.os.Environment;

import java.io.File;

public  class Global {
	public static String name,robot, startPos = "", team, comment, weakness;
	public static int[] stats = new int[29];   //must be corrected later
    public static String[] boolStats = new String[19], comments = new String[22];
	public static final int ioAutoTote = 0,
							ioAutoContainer = ioAutoTote + 1,
							ioAutoStepCont = ioAutoContainer + 1,
							ioAutoKnockCont = ioAutoStepCont + 1,
                            ioTele6HighTote = ioAutoKnockCont + 1,
                            ioTele5HighTote = ioTele6HighTote + 1,
                            ioTele4HighTote = ioTele5HighTote + 1,
                            ioTele3HighTote = ioTele4HighTote + 1,
                            ioTele2HighTote = ioTele3HighTote + 1,
                            ioTele1HighTote = ioTele2HighTote + 1,
                            ioTele6HighCont = ioTele1HighTote + 1,
                            ioTele5HighCont = ioTele6HighCont + 1,
                            ioTele4HighCont = ioTele5HighCont + 1,
                            ioTele3HighCont = ioTele4HighCont + 1,
                            ioTele2HighCont = ioTele3HighCont + 1,
                            ioTele1HighCont = ioTele2HighCont + 1,
                            ioTele4HighCoopTote = ioTele1HighCont + 1,
                            ioTele3HighCoopTote = ioTele4HighCoopTote + 1,
                            ioTele2HighCoopTote = ioTele3HighCoopTote + 1,
                            ioTele1HighCoopTote = ioTele2HighCoopTote + 1,
                            ioTeleLitter = ioTele1HighCoopTote + 1,
                            ioTeleRecycle = ioTeleLitter + 1,
                            ioTeleKnockStack = ioTeleRecycle + 1,
                            ioTeleKnockCont = ioTeleKnockStack + 1,
                            ioDriver = ioTeleKnockCont + 1,
                            ioNoodle = ioDriver + 1,
                            ioSpeed = ioNoodle + 1,
                            ioCap = ioSpeed + 1,
                            ioCapHeight = ioCap + 1,
                            ioRobSet = 0,
                            ioStackedTote = ioRobSet + 1,
                            ioLeftTopStack = ioStackedTote + 1,
                            ioMidTopStack = ioLeftTopStack + 1,
                            ioRightTopStack = ioMidTopStack + 1,
                            ioLeftBottomStack = ioRightTopStack + 1,
                            ioMidBottomStack = ioLeftBottomStack + 1,
                            ioRightBottomStack = ioMidBottomStack + 1,
                            ioLeftFeed = ioRightBottomStack + 1,
                            ioRightFeed = ioLeftFeed + 1,
                            ioLeftLandfill = ioRightFeed + 1,
                            ioRightLandfill = ioLeftLandfill + 1,
                            ioLeftStageCont = ioRightLandfill + 1,
                            ioMidStageCont = ioLeftStageCont + 1,
                            ioRightStageCont = ioMidStageCont + 1,
                            ioLeftStepCont = ioRightStageCont + 1,
                            ioMidLeftStepCont = ioLeftStepCont + 1,
                            ioMidRightStepCont = ioMidLeftStepCont + 1,
                            ioRightStepCont = ioMidRightStepCont + 1;
    public static int match = 1;
    public static File f = new File(Environment.getExternalStorageDirectory() + "/" + robot + ".csv");
}
