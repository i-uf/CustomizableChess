package com.i_uf.chess.utils;

import java.util.Collection;

public interface PieceData<T extends GameData> {
    Collection<Position> canGo(BoardData<T> board, Position pos);
}