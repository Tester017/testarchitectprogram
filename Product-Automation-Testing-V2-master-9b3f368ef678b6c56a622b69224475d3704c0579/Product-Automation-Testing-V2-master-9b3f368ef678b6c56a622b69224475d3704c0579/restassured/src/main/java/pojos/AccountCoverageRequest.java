package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountCoverageRequest {
    private String modified_from;
    private String modified_to;
    private int int_start;
    private int count;

    // Getters and Setters
    public String getModified_from() {
        return modified_from;
    }

    public void setModified_from(String modified_from) {
        this.modified_from = modified_from;
    }

    public String getModified_to() {
        return modified_to;
    }

    public void setModified_to(String modified_to) {
        this.modified_to = modified_to;
    }

    public int getInt_start() {
        return int_start;
    }

    public void setInt_start(int int_start) {
        this.int_start = int_start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
