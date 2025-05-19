package com.i_uf.chess.utils;

public abstract class AbstractBoard<T extends GameData> implements BoardData<T> {
    protected Position size;
    protected PieceData<T>[][] board;
    private int turn = 0;
    public PieceData<T> get(Position pos) {
        if(pos.rank() >= size.rank() || pos.file() >= size.file() || pos.rank() < 0 || pos.file() < 0) return null;
        return board[pos.rank()][pos.file()];
    }
    public void set(PieceData<T> piece, Position pos) {
        if(pos.rank() >= size.rank() || pos.file() >= size.file() || pos.rank() < 0 || pos.file() < 0) return;
        board[pos.rank()][pos.file()] = piece;
    }
    public int turn() {
        return turn;
    }
    public void turn(int player) {
        turn = player;
    }
}
