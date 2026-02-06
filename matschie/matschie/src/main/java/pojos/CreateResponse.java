package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateResponse extends BaseResponse{

    @JsonProperty("id")
    private String id;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("errors")
    private String[] errors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

    
}
