package spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter @Setter
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Playlist {

    Boolean collaborative;
    String description;
    ExternalUrls external_urls;
    Followers followers;
    String href;
    String id;
    List<Object> images;
    String name;
    Owner owner;
    Object primary_color;
    @JsonProperty("public")
    boolean _public;
    String snapshot_id;
    Tracks tracks;
    String type;
    String uri;

}
