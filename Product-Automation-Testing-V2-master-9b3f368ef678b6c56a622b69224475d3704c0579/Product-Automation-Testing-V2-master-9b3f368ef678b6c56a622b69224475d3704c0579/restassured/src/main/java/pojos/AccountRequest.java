
package pojos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRequest {
    private int int_start;
    private List<String> account_id;


    // Getters and setters
    public int getint_start() { return int_start; }
    public void setint_start(int int_start) { this.int_start = int_start; }
    
    public List<String> getaccount_id() { return account_id; }
    public void setaccount_id(List<String> account_id) { this.account_id = account_id; }

   
}
