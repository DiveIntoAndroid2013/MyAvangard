package ru.omsu.diveintoandroid.myavangard.model;

import java.util.Date;

/**
 * Match
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class Match {

    /**
     * Id матча
     */
    public long matchId;

    /**
     * Название первой команды (хозяева)
     */
    public String team1Name;

    /**
     * Название второй команды (гости)
     */
    public String team2Name;

    /**
     * URL логотип первой команды
     */
    public String team1Logo;

    /**
     * URL логотип второй команды
     */
    public String team2Logo;

    /**
     * Результат матче в виде <голы хозяев>:<голы гостей>, например, 2:1
     */
    public String result;

    /**
     * Время встречи (Дата)
     */
    public Date time;
}
