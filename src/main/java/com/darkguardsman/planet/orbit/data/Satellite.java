package com.darkguardsman.planet.orbit.data;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 11/1/2018.
 */
public class Satellite extends OrbitalObjects
{
    public Planet planet;
    public boolean artificial = false;

    public Satellite(int x, int y, int a, int b, int s)
    {
        super(x, y, a, b, s);
    }
}
