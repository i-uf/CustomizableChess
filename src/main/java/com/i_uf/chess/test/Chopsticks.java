package com.i_uf.chess.test;

import com.i_uf.chess.utils.*;
import com.i_uf.chess.utils.client.Listener;

import java.util.*;

public class Chopsticks implements GameData {
    public static final Chopsticks INSTANCE = new Chopsticks();

    public String id() {
        return "chopsticks";
    }
    public int players() {
        return 2;
    }
    public ChopsticksBoard create() {
        return new ChopsticksBoard();
    }
    // "[0,0][0,1][1,0][1,1] [turn]" - [a,b] = a-th player's b-th hand, [turn] = Turn(0 or 1)
    // "1111 0" = startingPosition
    public String saveBoard(BoardData<?> board) {
        if(board instanceof ChopsticksBoard test) {
            return (test.get(new Position(0, 0)).id())
            + (test.get(new Position(0, 1)).id())
            + (test.get(new Position(1, 0)).id())
            + (test.get(new Position(1, 1)).id())
            + ' '
            + test.turn();
        } else return null;
    }
    public BoardData<?> loadBoard(String text) {
        if(text.matches("^[0-4]{4} [01]$")) {
            return new ChopsticksBoard(new ChopsticksPiece[][]
                    {
                            { ChopsticksPiece.values()[text.charAt(0)-'0'], ChopsticksPiece.values()[text.charAt(1)-'0'] },
                            { ChopsticksPiece.values()[text.charAt(2)-'0'], ChopsticksPiece.values()[text.charAt(3)-'0'] }
                    }, new Move(new Position(-1, -1), new Position(-1, -1)), text.charAt(5)-'0');
        }
        return null;
    }
    public String saveLevel(Level<?> level) {
        if(!(level.startingPosition instanceof ChopsticksBoard)) return null;
        return "";
    }
    /*
    """
    -first=[name]
    -firstElo=[elo]
    -second=[name]
    -secondElo=[elo]
    -result=[result]
    [notations]
    """
    - [result] = result(0-1 or 1-0 or *)
    - [notations] = "([notation]\\s)*"
    - [notation] = "([LR]{2}|[+-][123])[+#%]?"

     */
    public Level<?> loadLevel(String text) {
        return null;
    }
    public void rating(Level<?> level) {
        if(level.startingPosition.instance() != this || level.win() == -1) return;
        GameData instance = level.startingPosition.instance();
        Player[] a = level.players.toArray(Player[]::new);
        int winner = a[level.win()].getElo(instance, 500);
        int loser = a[level.win()^1].getElo(instance, 500);

        int value = Math.max(5, 10 + (loser - winner) / 20); // W:200, L:200 -> 10, W:200, L:100 -> 5, W:200, L:300 -> 15
        winner += value;
        loser -= value;

        a[level.win()].setElo(instance, Math.min(5000, winner));
        a[level.win()^1].setElo(instance, Math.max(0, loser));
    }
    public static class ChopsticksBoard extends AbstractBoard<Chopsticks> {
        public final Collection<Move> lastMove;
        public ChopsticksBoard() {
            this(new ChopsticksPiece[][]{{ChopsticksPiece.F1, ChopsticksPiece.F1}, {ChopsticksPiece.F1, ChopsticksPiece.F1}},
                    new Move(new Position(-1, -1), new Position(-1, -1)), 0);
        }
        public ChopsticksBoard(ChopsticksPiece[][] board, Move lastMove, int turn) {
            this.board = board;
            this.lastMove = List.of(lastMove);
            turn(turn);
        }
        public ChopsticksBoard move(Position first, Position second) {
            if(getMoves(first).stream().noneMatch(p -> p.equals(second))) return null;
            ChopsticksPiece[][] newBoard = Arrays.stream((ChopsticksPiece[][]) board)
                    .map(ChopsticksPiece[]::clone).toArray(ChopsticksPiece[][]::new);
            Move newMove = new Move(first, second);

            return new ChopsticksBoard(newBoard, newMove, nextTurn());
        }
        public Collection<Position> getMoves(Position pos) {
            // WOW! AMAZING CODE!
            ChopsticksPiece my = (ChopsticksPiece) get(pos);
            ChopsticksPiece myOff = (ChopsticksPiece) get(new Position(pos.rank(), pos.file()^1));
            ChopsticksPiece opps = (ChopsticksPiece) get(new Position(pos.rank()^1, pos.file()));
            ChopsticksPiece oppsOff = (ChopsticksPiece) get(new Position(pos.rank()^1, pos.file()^1));
            LinkedList<Position> result = new LinkedList<>();
            if(turn() != pos.rank() || my.num == 0) return result;
            if(opps.num != 0) result.add(new Position(pos.rank()^1, pos.file()));
            if(oppsOff.num != 0) result.add(new Position(pos.rank()^1, pos.file()^1));
            int a = pos.rank()*2-1;
            for(int i = 1; i < my.num && i + myOff.num < 5; i++) {
                if(myOff.num + i != my.num) result.add(new Position(a*i+pos.rank(), pos.file()));
            }
            return result;
        }
        public Chopsticks instance() {
            return INSTANCE;
        }
        public Collection<Move> lastMove() {
            return lastMove;
        }
        public int win() {
            return get(new Position(turn(), 0)) == ChopsticksPiece.F0 && get(new Position(turn(), 1)) == ChopsticksPiece.F0 ? turn()^1 : -1;
        }
        public int nextTurn() {
            return turn()^1;
        }
        public ChopsticksPiece[] pieces() { return ChopsticksPiece.values(); }
    }
    public enum ChopsticksPiece implements PieceData<Chopsticks> {
        F0(0), F1(1), F2(2), F3(3), F4(4);
        public final int num;
        ChopsticksPiece(int num) {
            this.num = num;
        }
        public String id() {
            return num + "";
        }
    }
    public static class ChopsticksListener implements Listener<Chopsticks> {

    }
}