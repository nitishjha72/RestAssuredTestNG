package spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorRoot {

    @JsonProperty("error")
    private ErrorDetails errorDetails;

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }

}
