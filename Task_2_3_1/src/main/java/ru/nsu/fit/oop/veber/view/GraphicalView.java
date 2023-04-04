package ru.nsu.fit.oop.veber.view;

import ru.nsu.fit.oop.veber.presenter.Presenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GraphicalView implements View {

    private final Presenter presenter;


    public GraphicalView(Presenter presenter) {
        this.presenter = presenter;
        createUI();
    }

    private void createUI() {

        Box topBox = Box.createHorizontalBox();

        topBox.add(Box.createHorizontalStrut((800)));

        topBox.add(Box.createVerticalStrut((600)));

        JFrame frame = new JFrame("Passive MVP Swing");

        (frame.getContentPane()).setBackground(Color.white);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(topBox, BorderLayout.NORTH);

        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent event) {
                System.out.println("was pressed " + event.getKeyCode());
                presenter.processKeyInput(event);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        frame.pack();

        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
}
