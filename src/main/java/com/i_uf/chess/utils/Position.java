package com.i_uf.chess.utils;

import java.util.Objects;

public record Position(int rank, int file) {
    public boolean equals(Object o) {
        if (!(o instanceof Position(int rank1, int file1))) return false;
        return rank == rank1 && file == file1;
    }
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}