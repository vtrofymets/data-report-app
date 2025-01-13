package org.vt.model;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class ReportData {

    @CsvBindByName(column = "Team")
    private String team;
    @CsvBindByName(column = "Total Effort")
    private long totalEffort;
    @CsvBindByName(column = "Remaining Effort")
    private double remainingEffort;

    public String getTeam() {
        return team;
    }

    public long getTotalEffort() {
        return totalEffort;
    }

    public double getRemainingEffort() {
        return remainingEffort / totalEffort;
    }

    public static ReportData from(InputData inputData) {
        ReportData reportData = new ReportData();
        reportData.team = inputData.getTeam();
        reportData.remainingEffort = Double.parseDouble(inputData.getOriginalEstimate());
        reportData.totalEffort++;
        return reportData;
    }

    public ReportData merge(ReportData other) {
        if (this.team == null && other.team != null) {
            this.team = other.team;
        } else if (this.team != null && !this.team.equals(other.team)) {
            throw new IllegalArgumentException("Teams don't match!");
        }
        this.remainingEffort += other.remainingEffort;
        this.totalEffort = totalEffort + other.totalEffort;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportData that = (ReportData) o;
        return Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(team);
    }

    @Override
    public String toString() {
        return "ReportData{" + "team='" + team + '\'' + ", totalEffort=" + totalEffort + ", remainingEffort=" + remainingEffort + '}';
    }
}
