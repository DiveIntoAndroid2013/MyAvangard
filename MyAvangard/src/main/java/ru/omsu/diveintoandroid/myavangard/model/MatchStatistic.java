package ru.omsu.diveintoandroid.myavangard.model;

/**
 * MatchStatistic
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public class MatchStatistic {

    /**
     * Id матча
     */
    public long matchId;

    /**
     * Количество бросков по воротам для первой команды
     */
    public int team1ShotsOnGoal;

    /**
     * Процент отраженных бросков для первой команды
     */
    public double team1SavesPercent;

    /**
     * Количество выигранных вбрасываний для первой команды
     */
    public int team1FaceOffsWon;

    /**
     * Количество голов в большинстве для первой команды
     */
    public int team1PowerPlayGoals;

    /**
     * Количество бросков по воротам для второй команды
     */
    public int team2ShotsOnGoal;

    /**
     * Процент отраженных бросков для второй команды
     */
    public double team2SavesPercent;

    /**
     * Количество выигранных вбрасываний для второй команды
     */
    public int team2FaceOffsWon;

    /**
     * Количество голов в большинстве для второй команды
     */
    public int team2PowerPlayGoals;

}
