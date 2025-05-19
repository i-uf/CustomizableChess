package com.i_uf.chess.utils;

import java.util.Collection;

public interface BoardData<T extends GameData> {
    T instance();
    Collection<Move> lastMove();
    PieceData<T> get(Position pos);
    void set(PieceData<T> piece, Position pos);
    int turn();
    void turn(int player);
    int win();
    int nextTurn();
    BoardData<T> move(Position first, Position second);
    Collection<Position> getMoves(Position pos);
    PieceData<T>[] pieces();
}