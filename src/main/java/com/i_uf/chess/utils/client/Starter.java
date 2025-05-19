package com.i_uf.chess.utils.client;

import com.i_uf.chess.utils.BoardData;
import com.i_uf.chess.utils.GameData;
import com.i_uf.chess.utils.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Starter<T extends GameData> extends JPanel {
    public final Listener<T> listener;
    public final Level<BoardData<T>> level;
    public App app = new App();

    public Starter(Listener<T> listener, Level<BoardData<T>> level) {
        this.listener = listener;
        this.level = level;

        MouseAdapter mouse = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                app.starts.put(e.getButton(), app.pos.clone());
                listener.onMousePressed(app, level, e.getButton());
                mouseEvent();
            }
            public void mouseReleased(MouseEvent e) {
                listener.onMouseReleased(app, level, e.getButton());
                app.starts.remove(e.getButton());
                mouseEvent();
            }
            public void mouseWheelMoved(MouseWheelEvent e) {
                listener.onMouseScrolled(app, level, new int[] { 0, e.getScrollAmount() });
                mouseEvent();
            }
            public void mouseDragged(MouseEvent e) {
                app.pos[0] = e.getX();
                app.pos[1] = e.getY();
            }
            public void mouseMoved(MouseEvent e) {
                app.pos[0] = e.getX();
                app.pos[1] = e.getY();
                mouseEvent();
            }
            private void mouseEvent() {
                setCursor(app.cursor);
                SwingUtilities.invokeLater(Starter.this::repaint);
            }
        };
        KeyAdapter key = new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        };
        addMouseListener(mouse);
        addMouseWheelListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener(key);
    }
    protected void paintComponent(Graphics g) {
        Canvas canvas = new Canvas(0, 0, getWidth(), getHeight());
        listener.render(app, canvas, level);
        canvas.graphics(g);
    }
}
