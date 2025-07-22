package org.example.simulation.intersection.parameters;


/**  Parameters on which @IntelligentTrafficLoadBasedStrategy
 *   calculates best TrafficLightPhase
 */
public class TunableParameters {
    /**
     * Bonus to be added to each waiting car on red light
     */
    public static final int WAITING_TIME_BONUS = 1;

    /**
     * Priority of straight or right lane (left and right turns happen from the same lane!),
     * should be higher than priority of left lane, as in usual those lanes are more frequently used.
     */
    public static final int STRAIGHT_OR_RIGHT_LANE_PRIORITY = 3;

    /**
     * Priority of left lane. Low, because in usual this lane is not so frequently used.
     */
    public static final int LEFT_LINE_PRIORITY = 1;

    /**
     * To prevent from to frequent changes of phase.
     * If current phase is worse by less than <value of this constant>, we won't change it.
     */
    public static final float MIN_TRAFFIC_LIGHT_PHASE_PRIORITY_DIFFERENCE = 0.2f;
}
