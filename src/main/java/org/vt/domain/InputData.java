package org.vt.domain;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class InputData {

    @CsvBindByName(column = "Issue Key")
    private String issueKey;
    @CsvBindByName(column = "Issue Id")
    private String issueId;
    @CsvBindByName(column = "Parent Id")
    private String parentId;
    @CsvBindByName(column = "Summary")
    private String summary;
    @CsvBindByName(column = "Status")
    private String status;
    @CsvBindByName(column = "Issue Type")
    private String issueType;
    @CsvBindByName(column = "Original Estimate")
    private String originalEstimate;
    @CsvBindByName(column = "Priority")
    private String priority;
    @CsvBindByName(column = "Team")
    private String team;

    public String getIssueKey() {
        return issueKey;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getParentId() {
        return parentId;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() {
        return status;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getOriginalEstimate() {
        return originalEstimate;
    }

    public String getPriority() {
        return priority;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InputData inputData = (InputData) o;
        return Objects.equals(issueKey, inputData.issueKey) && Objects.equals(issueId,
                inputData.issueId) && Objects.equals(parentId, inputData.parentId) && Objects.equals(summary,
                inputData.summary) && Objects.equals(status, inputData.status) && Objects.equals(issueType,
                inputData.issueType) && Objects.equals(originalEstimate, inputData.originalEstimate) && Objects.equals(
                priority, inputData.priority) && Objects.equals(team, inputData.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueKey, issueId, parentId, summary, status, issueType, originalEstimate, priority, team);
    }
}
