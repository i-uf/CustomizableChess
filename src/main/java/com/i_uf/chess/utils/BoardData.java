package com.i_uf.chess.utils;

public interface BoardData<T extends GameData> {
    PieceData<T> get(Position pos);
    void set(PieceData<T> piece, Position pos);
    int turn();
    int turn(int player);
    int win();
}