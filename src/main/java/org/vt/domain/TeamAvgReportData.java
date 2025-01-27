package org.vt.domain;

import java.util.Objects;

public class TeamAvgReportData {

    private String team;
    private long totalEffort;
    private double remainingEffort;

    public String getTeam() {
        return team;
    }

    public long getTotalEffort() {
        return totalEffort;
    }

    public double getRemainingEffort() {
        return remainingEffort;
    }

    public double getAvgEffort() {
        return remainingEffort / totalEffort;
    }

    public static TeamAvgReportData of(String team, double estimate) {
        TeamAvgReportData teamAvgReportData = new TeamAvgReportData();
        teamAvgReportData.team = team;
        teamAvgReportData.remainingEffort = estimate;
        teamAvgReportData.totalEffort++;
        return teamAvgReportData;
    }

    public TeamAvgReportData merge(TeamAvgReportData other) {
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
        TeamAvgReportData that = (TeamAvgReportData) o;
        return Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(team);
    }

    @Override
    public String toString() {
        return "TeamAvgReportDto{" + "team='" + team + '\'' + ", totalEffort=" + totalEffort + ", remainingEffort=" + remainingEffort + '}';
    }
}
