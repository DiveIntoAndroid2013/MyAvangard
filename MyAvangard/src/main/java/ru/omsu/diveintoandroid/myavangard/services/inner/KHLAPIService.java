package ru.omsu.diveintoandroid.myavangard.services.inner;

import retrofit.http.GET;
import retrofit.http.Query;
import ru.omsu.diveintoandroid.myavangard.services.inner.responses.GetGamesResponse;
import ru.omsu.diveintoandroid.myavangard.services.inner.responses.GetProtocolResponse;

/**
 * KHLAPIService
 *
 * @author Alex Korovyansky <korovyansk@gmail.com>
 */
public interface KHLAPIService {

    @GET("/games.php?tournament=current")
    public GetGamesResponse getGames();

    @GET("/protocolTeams.php")
    public GetProtocolResponse getProtocol(@Query("game")long matchId);
}
