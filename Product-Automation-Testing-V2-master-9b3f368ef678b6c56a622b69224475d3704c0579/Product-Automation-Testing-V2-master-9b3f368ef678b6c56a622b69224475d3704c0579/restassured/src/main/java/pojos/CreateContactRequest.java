package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateContactRequest {
    private Integer count;
    private List<String> contact_id;
    private String modified_from;
    private String modified_to;

    // Getters and Setters
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getContact_id() {
        return contact_id;
    }
    public void setContact_id(List<String> contact_id) {
        this.contact_id = contact_id;
    }

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
}
