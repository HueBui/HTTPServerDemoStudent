package entity;

public class Student {
    private int idStudent;
    private String nameStudent;
    private String password;
    private String address;
    private String email;
    private int phone;
    private int sex;
    private String token;
    private String role;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Student(int idStudent, String nameStudent, String password, String address, String email, int phone, int sex, String token, String role) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.token = token;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nameStudent='" + nameStudent + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", sex=" + sex +
                ", token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
