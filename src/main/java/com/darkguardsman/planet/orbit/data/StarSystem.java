package com.darkguardsman.planet.orbit.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2018.
 */
public class StarSystem
{
    public final List<Planet> planets = new ArrayList();

    public int tick;

    public void add(Planet planet)
    {
        planet.starSystem = this;
        planets.add(planet);
    }

    public void tick()
    {
        tick++;
        if(tick >= Integer.MAX_VALUE - 2)
        {
            tick = 0;
        }
    }
}
