package com.i_uf.chess.test;

import com.i_uf.chess.utils.Level;
import com.i_uf.chess.utils.Player;
import com.i_uf.chess.utils.Profile;
import com.i_uf.chess.utils.client.Starter;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(((~(1000000*0+0x2432/320/43)^0^0^0-~0+1*3)+(1<<(0*340+(2*2^3)-(8^12)))));
        frame.setContentPane(
                new Starter<>(new Chopsticks.ChopsticksListener(), new Level<>(Chopsticks.INSTANCE.create(),
                        Arrays.asList(
                                new Player(new Profile("test", "test account", new ImageIcon(Main.class.getResource("/profile0.png")).getImage()), new HashMap<>(), new HashMap<>()),
                                new Player(new Profile("sets", "sets account", new ImageIcon(Main.class.getResource("/profile0.png")).getImage()), new HashMap<>(), new HashMap<>())
                        )
                ))
        );
        frame.getContentPane().setPreferredSize(new Dimension(1000, 800));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}