package com.i_uf.chess.utils.client;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class App {
    public Cursor cursor = Cursor.getDefaultCursor();
    final int[] pos = {0, 0};
    final HashMap<Integer, int[]> starts = new HashMap<>();
    final LinkedList<Integer> keys = new LinkedList<>();

    public boolean isKeyPressed(int key) { return keys.contains(key); }
    public boolean isMousePressed(int button) { return starts.containsKey(button); }
    public int[] getMousePressedPosition(int button) { return isMousePressed(button) ? starts.get(button).clone() : null; }
    public int[] getMousePosition() { return pos.clone(); }
}