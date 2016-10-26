package fi.jamk.sgkygolf;


import java.util.List;

public interface AsyncJsonResponse {

    void onFetchDataTaskComplete(List<GolfCourse> golfCourseList);

}
