package fi.jamk.sgkygolf;

/**
 * Created by H3694 on 25.10.2016.
 */

public class GolfCourse {
    String name;
    String address;
    String email;
    String phone;
    String webpage;
    String photoId;

    public GolfCourse(String name, String position, String email, String phone, String webpage, String photoId) {
        this.name = name;
        this.address = position;
        this.email = email;
        this.phone = phone;
        this.webpage = webpage;
        this.photoId = photoId;
    }

}
