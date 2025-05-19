package com.i_uf.chess.utils;

import com.i_uf.chess.utils.system.GameSystem;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private final Map<GameData, Integer> elo;
    private Level<?> nowPlaying = null;
    private final Map<DateId, Level<?>> games;
    public Player(Profile profile, Map<GameData, Integer> elo, Map<DateId, Level<?>> games) {
        this.name = name;
        this.elo = elo;
        this.games = games;
    }
    public int getElo(GameData game, int defaultValue) {
        return elo.getOrDefault(game, defaultValue);
    }
    public void setElo(GameData game, int elo) {
        this.elo.put(game, elo);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Level<?> getNowPlaying() {
        return nowPlaying;
    }
    public void setNowPlaying(Level<?> nowPlaying) {
        this.nowPlaying = nowPlaying;
        if(nowPlaying != null) games.put(GameSystem.get(), nowPlaying);
    }
    public Map<DateId, Level<?>> getGames() {
        return games;
    }
}
