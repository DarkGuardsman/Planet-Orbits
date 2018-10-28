package com.darkguardsman.planet.orbit.ui;

import com.darkguardsman.planet.orbit.data.Planet;
import com.darkguardsman.planet.orbit.data.StarSystem;

import javax.swing.*;
import java.awt.*;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/28/2018.
 */
public class DisplayFrame extends JFrame
{
    Timer timer = new Timer(50, action -> update());

    RenderPanel renderPanel;

    Button playButton;
    JTextField playSpeedField;
    boolean currentlyPlaying;

    StarSystem system;

    public DisplayFrame()
    {
        //Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setMinimumSize(new Dimension(800, 800));
        setLocation(200, 200);
        setTitle("Visualization - heat pathfinder");

        add(buildCenter());

        pack();
    }

    protected JPanel buildCenter()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(renderPanel = new RenderPanel(), BorderLayout.CENTER);
        renderPanel.setMinimumSize(new Dimension(600, 600));
        renderPanel.setPreferredSize(new Dimension(600, 600));

        panel.add(buildControls(), BorderLayout.WEST);
        return panel;
    }

    protected JPanel buildControls()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20, 2));
        panel.setMinimumSize(new Dimension(200, 0));

        //--------------------------------------------------------

        playButton = new Button("Play");
        playButton.addActionListener(e -> play(false));
        panel.add(playButton);

        panel.add(playSpeedField = new JTextField());
        playSpeedField.setText("" + timer.getDelay());

        //--------------------------------------------------------
        //Spacer
        panel.add(new JPanel());
        panel.add(new JPanel());
        //--------------------------------------------------------

        return panel;
    }

    protected void play(boolean forceStop)
    {
        if (currentlyPlaying || forceStop)
        {
            currentlyPlaying = false;
            timer.stop();
            playButton.setLabel("Play");
        }
        else
        {
            //Set play speed
            timer.setDelay(Integer.parseInt(playSpeedField.getText().trim()));

            //Note we are playing
            currentlyPlaying = true;

            //Start timer
            timer.start();

            //Change text to note the button can stop play
            playButton.setLabel("Stop");
        }
    }

    public void update()
    {
        if(system == null)
        {
            system = new StarSystem();
            system.add(new Planet(0, 0, 2, 1, 1));
        }
    }
}
