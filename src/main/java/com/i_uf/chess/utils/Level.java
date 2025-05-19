package com.i_uf.chess.utils;

import java.util.Collection;
import java.util.Stack;

public class Level<T extends BoardData<?>> {
    public final T startingPosition;
    public final Collection<Player> players;
    private T current;
    private final Stack<T> stack = new Stack<>();
    private final Stack<T> undid = new Stack<>();
    public Level(T startingPosition, Collection<Player> players) {
        this.startingPosition = startingPosition;
        current = startingPosition;
        this.players = players;
    }
    public T current() {
        return current;
    }
    public boolean push(T item) {
        if(item == null || !pushable()) return false;
        stack.push(current);
        current = item;
        return true;
    }
    public int win() {
        return current().win();
    }
    public boolean undoable() { return false; }
    public boolean pushable() { return true;}
    public boolean undo() {
        if(stack.empty() || !undoable()) return false;
        undid.push(current());
        current = stack.pop();
        return true;
    }
    public boolean redo() {
        if(undid.empty() || !undoable() || !pushable()) return false;
        return push(undid.pop());
    }
}
