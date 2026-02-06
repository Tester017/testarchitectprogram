
package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateResourceRequest {
    private String name;
    private String status;
    private String type;
    private String startDate;
    private String endDate;
    private double expectedRevenue;
    private double budgetedCost;
    private String description;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public double getExpectedRevenue() { return expectedRevenue; }
    public void setExpectedRevenue(double expectedRevenue) { this.expectedRevenue = expectedRevenue; }
    public double getBudgetedCost() { return budgetedCost; }
    public void setBudgetedCost(double budgetedCost) { this.budgetedCost = budgetedCost; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
