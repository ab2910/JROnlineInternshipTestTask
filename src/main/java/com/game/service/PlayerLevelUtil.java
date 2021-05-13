package com.game.service;

public class PlayerLevelUtil {

    /* calculate level from experience */
    /* not verifying if long value lies within int limits cause not */
    public static Integer calculateLevel(Integer e) {
        Double level = (Math.sqrt(200.0*e + 2500.0) - 50.0)/100.0;
        //return (int) Math.round(level);
        return (int) Math.floor(level);
    }

    /* calculate untilNextLevel from level and experience */
    public static Integer calculateUntilNextLevel(Integer l, Integer e) {
        return 50 * (l + 1) * (l + 2) - e;
    }
}
