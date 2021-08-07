package in.gamernation.app.ModalClasses;

public class MyProfileResponse {
    private String Profile_Picture;
    private String Name;
    private int Progress;
    private String Date_of_Birth;
    private String Phone_no;
    private String Email;
    private String Pan_details;
    private String Invitation_code;
    private Boolean Email_Verified;

    public String getProfile_Picture() {
        return Profile_Picture;
    }

    public void setProfile_Picture(String profile_Picture) {
        Profile_Picture = profile_Picture;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
        Progress = progress;
    }

    public String getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        Date_of_Birth = date_of_Birth;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPan_details() {
        return Pan_details;
    }

    public void setPan_details(String pan_details) {
        Pan_details = pan_details;
    }

    public String getInvitation_code() {
        return Invitation_code;
    }

    public void setInvitation_code(String invitation_code) {
        Invitation_code = invitation_code;
    }

    public Boolean getEmail_Verified() {
        return Email_Verified;
    }

    public void setEmail_Verified(Boolean email_Verified) {
        Email_Verified = email_Verified;
    }
}
