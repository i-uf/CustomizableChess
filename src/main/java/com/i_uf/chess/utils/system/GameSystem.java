package com.i_uf.chess.utils.system;

import com.i_uf.chess.utils.*;

import java.util.*;

public final class GameSystem {
    private GameSystem() {}
    private static int gameId = 0;
    private static final HashMap<Class<?>, Collection<Level<?>>> games = new HashMap<>();
    private static final LinkedList<Player> players = new LinkedList<>();
    public static DateId get() {
        return new DateId(new Date(), gameId++);
    }
    public static <T extends GameData> Level<BoardData<T>> startGame(BoardData<T> board, Collection<Player> players) {
        if(players.size() != board.instance().players()) return null;

        Level<BoardData<T>> level = new Level<>(board, players);
        Class<?> clazz = level.startingPosition.instance().getClass();
        if(!games.containsKey(clazz)) games.put(clazz, new LinkedList<>());
        games.get(clazz).add(level);

        players.forEach(player -> player.setNowPlaying(level));

        return level;
    }
}
