package com.darkguardsman.planet.orbit.data;

import java.awt.*;

/**
 * Object that orbits another object
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2018.
 */
public class OrbitalObjects
{
    /** Orbit offset X axis */
    public int orbitOffsetX;

    /** Orbit offset Y axis */
    public int orbitOffsetY;

    /** Ellipse size of orbit */
    public int orbitSizeA;

    /** Ellipse size of orbit */
    public int orbitSizeB;

    /** Speed of the orbit */
    public double orbitSpeed;

    /** Distance for the orbit */
    public double orbitDistance;

    /** Distance traveled each orbit step */
    public double orbitStepDistance;

    /** Number of steps to complete an orbit */
    public int orbitSteps;

    /** Size of the object render */
    public int renderSize = 5;

    /** Color of the object render */
    public Color renderColor = Color.BLUE;

    //Star this orbital is part of
    private StarSystem starSystem;

    //Current tick linked to the position values
    private int currentOrbitTick;

    //Position, based on tick of the star system
    private double x;
    private double y;

    public OrbitalObjects(int x, int y, int a, int b, int s)
    {
        this.orbitOffsetX = x;
        this.orbitOffsetY = y;
        this.orbitSizeA = a;
        this.orbitSizeB = b;
        this.orbitSpeed = s;
        calculateOrbit();
    }

    /**
     * Gets the X position, will update the position
     * if the star system tick has changed since last
     * called.
     *
     * @return X position, relative to object being orbited
     */
    public double getX()
    {
        if (currentOrbitTick != getStarSystem().getTick())
        {
            calculatePosition();
        }
        return x;
    }

    /**
     * Gets the Y position, will update the position
     * if the star system tick has changed since last
     * called.
     *
     * @return Y position, relative to object being orbited
     */
    public double getY()
    {
        if (currentOrbitTick != getStarSystem().getTick())
        {
            calculatePosition();
        }
        return y;
    }

    /**
     * Calculates the position of the orbit
     */
    public void calculatePosition()
    {
        currentOrbitTick = getStarSystem().getTick();
        x = orbitSizeA * Math.cos(getAngle());
        y = orbitSizeB * Math.sin(getAngle());
    }

    /**
     * Calculates the orbital data. Needs to be
     * called any time the orbital data is changed.
     */
    public void calculateOrbit()
    {
        //https://www.mathsisfun.com/geometry/ellipse-perimeter.html
        orbitDistance = 2 * Math.PI * Math.sqrt((orbitSizeA * orbitSizeA + orbitSizeB * orbitSizeB) / 2f);

        //Number of steps need to complete an orbit at the speed given
        orbitSteps = (int) Math.max(1, Math.floor(orbitDistance / (float) orbitSpeed));

        //Distance to travel each step (smoothed version of speed)
        orbitStepDistance = orbitDistance / orbitSteps;
    }

    /**
     * Gets the current angle of orbit
     *
     * @return 0 - 2PI
     */
    public double getAngle()
    {
        return getProgress() * 2 * Math.PI;
    }

    /**
     * Gets the progress of the orbit
     *
     * @return 0.0 - 1.0
     */
    public double getProgress()
    {
        return (orbitStepDistance * (currentOrbitTick % orbitSteps)) / orbitDistance;
    }

    /**
     * Gets the star system of orbit. May
     * wrapper for parent object as needed.
     *
     * @return
     */
    public StarSystem getStarSystem()
    {
        return starSystem;
    }

    /**
     * Sets the star system of orbit. Is not used
     * on all objects as some wrapper to parent.
     *
     * @param starSystem
     */
    public void setStarSystem(StarSystem starSystem)
    {
        this.starSystem = starSystem;
    }
}
