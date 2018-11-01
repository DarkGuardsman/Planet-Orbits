package com.darkguardsman.planet.orbit.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2018.
 */
public class StarSystem
{
    private final List<OrbitalObjects> planets = new ArrayList();

    private int tick;

    public void tick()
    {
        tick = getTick() + 1;
        if (getTick() >= Integer.MAX_VALUE - 2)
        {
            tick = 0;
        }
    }

    /**
     * Current star update tick. Used to sync
     * logic and animations.
     *
     * @return 0 to (Integer.MAX - 2)
     */
    public int getTick()
    {
        return tick;
    }

    /**
     * List of planets orbiting the star
     *
     * @return
     */
    public List<OrbitalObjects> getPlanets()
    {
        return planets;
    }

    /**
     * Called to add an object to orbit the star
     *
     * @param planet
     */
    public void add(OrbitalObjects planet)
    {
        planet.setStarSystem(this);
        planets.add(planet);
    }
}
