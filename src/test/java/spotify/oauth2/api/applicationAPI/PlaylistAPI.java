package spotify.oauth2.api.applicationAPI;

import io.restassured.response.Response;
import spotify.oauth2.api.RestResource;
import spotify.oauth2.pojo.Playlist;
import spotify.oauth2.utils.ConfigLoader;

import static spotify.oauth2.api.Route.*;
import static spotify.oauth2.api.TokenManager.getToken;

public class PlaylistAPI {

    public static Response post(Playlist reqPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), reqPlaylist);
    }

    public static Response post(String token, Playlist reqPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, reqPlaylist);
    }

    public static Response get(String playlist){
        return RestResource.get(PLAYLISTS + "/" + playlist, getToken());
    }

    public static Response update(Playlist reqPlaylist, String playlist){
        return RestResource.update(PLAYLISTS + "/" + playlist, getToken(), reqPlaylist);
        //6IEVkE1bdRf6ToEvXKuZ22
    }

}
