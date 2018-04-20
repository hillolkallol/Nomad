package Model;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A2219BF8-4D4E-7C33-D0F6-F8CCD1C68CF7]
// </editor-fold> 
public class User {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E1F9C7C1-2C06-BC69-ECFE-B43EE8E15E13]
    // </editor-fold> 
    private String firstName;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.13CD9B7A-A4A7-FC82-8099-D7ACCA48BFD0]
    // </editor-fold> 
    private String lastName;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8689E6E5-41D5-B540-DC62-109BF990A209]
    // </editor-fold> 
    private int userID;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.39B06EA4-9C42-E31A-56C3-18CA3BA35425]
    // </editor-fold> 
    private boolean IsDriver;
    private String email_id;
    private String password;
    private String address;
    private String gender;
    
    private int LNo;
    private int INo;
    private String ICom;
    

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile () {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserID() {
        return userID;
    }

    public boolean isIsDriver() {
        return IsDriver;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public int getLNo() {
        return LNo;
    }

    public int getINo() {
        return INo;
    }

    public String getICom() {
        return ICom;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setIsDriver(boolean IsDriver) {
        this.IsDriver = IsDriver;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLNo(int LNo) {
        this.LNo = LNo;
    }

    public void setINo(int INo) {
        this.INo = INo;
    }

    public void setICom(String ICom) {
        this.ICom = ICom;
    }
    
    
}

