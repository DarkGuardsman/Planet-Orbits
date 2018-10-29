package com.darkguardsman.planet.orbit.ui;

import com.darkguardsman.planet.orbit.data.Planet;
import com.darkguardsman.planet.orbit.data.StarSystem;

import javax.swing.*;
import java.awt.*;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2018.
 */
public class RenderPanel extends JPanel
{
    StarSystem starSystem;

    public RenderPanel(StarSystem starSystem)
    {
        this.starSystem = starSystem;
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Clear screen
        //g2.setPaint(Color.BLACK);
        //g2.fillRect(0, 0, getWidth(), getHeight());

        //Draw outside boarder
        g2.setPaint(Color.GREEN);
        drawBorder(g2, 1);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g2.setPaint(Color.YELLOW);
        g2.fillOval(centerX, centerY, 10, 10);

        if (starSystem != null)
        {
            starSystem.tick();
            for (Planet planet : starSystem.planets)
            {
                g2.setPaint(Color.GREEN);
                g2.drawOval(centerX + planet.orbitOffsetX, centerY + planet.orbitOffsetY, planet.orbitSizeA, planet.orbitSizeB);

                g2.setPaint(Color.BLUE);
                g2.fillOval(centerX + planet.orbitOffsetX + (int) planet.getX() / 2, centerY + planet.orbitOffsetY + (int) planet.getY() / 2, 5, 5);
            }
        }
    }

    /**
     * Draws a border around the component to define the edge
     *
     * @param g2
     */
    protected void drawBorder(Graphics2D g2, int padding)
    {
        g2.drawRect(padding, padding, getWidth() - padding * 2, getHeight() - padding * 2);
    }
}
