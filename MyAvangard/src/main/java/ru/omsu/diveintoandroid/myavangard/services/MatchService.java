package ru.omsu.diveintoandroid.myavangard.services;

import java.util.List;

import ru.omsu.diveintoandroid.myavangard.model.Match;
import ru.omsu.diveintoandroid.myavangard.model.MatchStatistic;

/**
 * MatchService
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public interface MatchService {

    /**
     * Возвращает базовую информацию о матче по Id
     * @param matchId
     * @return
     */
    public Match getMatch(long matchId);

    /**
     * Возвращает статистику для матча по его Id
     * @param matchId
     * @return
     */
    public MatchStatistic getMatchStatistic(long matchId);

    /**
     * Возвращает список матчей
     * @return
     */
    public List<Match> getMatches();
}
