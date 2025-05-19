package com.i_uf.chess.utils.client;

import com.i_uf.chess.utils.BoardData;
import com.i_uf.chess.utils.GameData;
import com.i_uf.chess.utils.Level;

public interface Listener<T extends GameData> {
    default void render(App app, Canvas canvas, Level<BoardData<T>> level) {}

    default void onMousePressed(App app, Level<BoardData<T>> level, int button) {}
    default void onMouseReleased(App app, Level<BoardData<T>> level, int button) {}
    default void onMouseMoved(App app, Level<BoardData<T>> level) {}
    default void onMouseScrolled(App app, Level<BoardData<T>> level, int[] delta) {}

    default void onKeyPressed(App app, Level<BoardData<T>> level, int key) {}
    default void onKeyReleased(App app,  Level<BoardData<T>> level, int key) {}
    default void onKeyTyped(App app, Level<BoardData<T>> level, char c) {}
}