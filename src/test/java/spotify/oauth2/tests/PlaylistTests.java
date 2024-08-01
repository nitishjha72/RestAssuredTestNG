package spotify.oauth2.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spotify.oauth2.api.StatusCode;
import spotify.oauth2.api.applicationAPI.PlaylistAPI;
import spotify.oauth2.pojo.ErrorRoot;
import spotify.oauth2.pojo.Playlist;
import spotify.oauth2.utils.DataLoader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static spotify.oauth2.utils.FakerUtils.generateDescription;
import static spotify.oauth2.utils.FakerUtils.generateName;

public class PlaylistTests {


//    @Test
//    public void validateTestStatusCode(){
//        given(requestSpecification).
//        when().get("/users/3156nvm2z5an3ydsbmszbrmzhdoe/playlists").
//        then().spec(responseSpecification).assertThat().statusCode(200);
//    }

    public Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().name(name).description(description)._public(_public).build();
    }

    public void assertPlaylistEqual(Playlist reqPlaylist, Playlist responsePlaylist){
        assertThat(responsePlaylist.getName(), equalTo(reqPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(reqPlaylist.getDescription()));
        assertThat(responsePlaylist.is_public(), equalTo(reqPlaylist.is_public()));
    }

    public void assertError(ErrorRoot errorRoot, int statusCode, String message){
        assertThat(errorRoot.getErrorDetails().getStatus(), equalTo(statusCode));
        assertThat(errorRoot.getErrorDetails().getMessage(), equalTo(message));
    }

    @Link(name = "Sample Link", type = "issue")
    @Issue("9876")
    @TmsLink("123567")
    @Description("This is the api which can create a playlist")
    @Test(description = "should be able to create a playlist")
    public void shouldBeAbleToCreateAPlayList(){
        Playlist reqPlaylist = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistAPI.post(reqPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.code));

        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(reqPlaylist, responsePlaylist);
    }


    @Test()
    public void shouldBeAbleToGetAPlaylist(){
        Playlist reqPlaylist = playlistBuilder("RestAssuredTestNitish_2", "Testing Nitish Rest Assured", true);

        Response response = PlaylistAPI.get(DataLoader.getInstance().getPlaylistId());
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.code));
        Playlist responsePlaylist = response.as(Playlist.class);

        assertPlaylistEqual(reqPlaylist, responsePlaylist);
    }

    @Test
    public void shouldBeAbleToUpdateAPlayList(){
        Playlist reqPlaylist = playlistBuilder(generateName(), generateDescription(), true);
        Response response = PlaylistAPI.update(reqPlaylist,DataLoader.getInstance().getUpdatePlaylistId());
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.code));
    }

    @Test
    public void shouldNotBeAbleToCreateAPlayListWithoutName(){
        Playlist reqPlaylist  = playlistBuilder("", generateDescription(), false);

        Response response = PlaylistAPI.post(reqPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_400.code));
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertError(errorRoot, StatusCode.CODE_400.code, StatusCode.CODE_400.msg);
    }

    @Test
    public void shouldNotBeAbleToCreateAPlayListWitExpiredToken(){
        Playlist reqPlaylist  = playlistBuilder(generateName(), generateDescription(), false);

        Response response = PlaylistAPI.post("1234", reqPlaylist);
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_401.code));
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertError(errorRoot, StatusCode.CODE_401.code, StatusCode.CODE_401.msg);
    }


}
