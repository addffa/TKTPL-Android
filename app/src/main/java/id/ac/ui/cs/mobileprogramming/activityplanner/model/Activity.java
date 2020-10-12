package id.ac.ui.cs.mobileprogramming.activityplanner.model;

public class Activity {
    private String day;
    private String activity;
    private String details;

    public Activity(String day, String activity, String details) {
        this.day = day;
        this.activity = activity;
        this.details = details;
    }

    public String getDay() {
        return day;
    }

    public String getActivity() {
        return activity;
    }

    public String getDetails() {
        return details;
    }
}
