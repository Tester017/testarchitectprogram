package pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateResponse extends BaseResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("errors")
    private String[] errors;

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("count_from")
    private int countFrom;

    @JsonProperty("count_to")
    private int countTo;

    @JsonProperty("status")
    private int status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("accounts")
    private List<Object> accounts; // Use specific POJO if you know account structure

    // Getters and setters...
}


