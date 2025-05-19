package com.i_uf.chess.utils;

import com.i_uf.chess.utils.client.Listener;

public interface GameData {
    String id();
    int players();
    BoardData<?> create();
    String saveBoard(BoardData<?> board);
    BoardData<?> loadBoard(String text);
    String saveLevel(Level<?> level);
    Level<?> loadLevel(String text);
    void rating(Level<?> level);
}