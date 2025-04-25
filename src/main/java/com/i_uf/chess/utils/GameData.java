package com.i_uf.chess.utils;

public interface GameData {
    String id();
    int players();
    BoardData<?> create();
    String serialize(BoardData<?> board);
    BoardData<?> deserialize(String text);
    void render(Canvas canvas, BoardData<?> board);
}