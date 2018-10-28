package com.darkguardsman.planet.orbit.data;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2018.
 */
public class Planet
{
    public StarSystem starSystem;

    public int orbitOffsetX;
    public int orbitOffsetY;

    public int orbitSizeA;
    public int orbitSizeB;

    public double orbitSpeed;

    public double orbitDistance;
    public double orbitStepDistance;
    public int orbitSteps;

    private int currentOrbitTick;

    private double x;
    private double y;

    public Planet(int x, int y, int a, int b, int s)
    {
        this.orbitOffsetX = x;
        this.orbitOffsetY = y;
        this.orbitSizeA = a;
        this.orbitSizeB = b;
        this.orbitSpeed = s;
        calculateOrbit();
    }

    public double getX()
    {
        if(currentOrbitTick != starSystem.tick)
        {
            calculatePosition();
        }
        return x;
    }

    public double getY()
    {
        if(currentOrbitTick != starSystem.tick)
        {
            calculatePosition();
        }
        return y;
    }

    public void calculatePosition()
    {
        currentOrbitTick = starSystem.tick;
        x = orbitSizeA * Math.cos(getAngle());
        y = orbitSizeB * Math.sin(getAngle());
    }

    public void calculateOrbit()
    {
        //https://www.mathsisfun.com/geometry/ellipse-perimeter.html
        orbitDistance = 2 * Math.PI * Math.sqrt((orbitSizeA * orbitSizeA + orbitSizeB * orbitSizeB) / 2);

        //Number of steps need to complete an orbit at the speed given
        orbitSteps = (int)Math.max(1, Math.floor(orbitDistance / orbitSpeed));

        //Distance to travel each step (smoothed version of speed)
        orbitStepDistance = orbitDistance / orbitSteps;
    }

    public double getAngle()
    {
        return getProgress() * 2 * Math.PI;
    }

    public double getProgress()
    {
        return orbitStepDistance * (currentOrbitTick % orbitSteps) / orbitStepDistance;
    }

}
