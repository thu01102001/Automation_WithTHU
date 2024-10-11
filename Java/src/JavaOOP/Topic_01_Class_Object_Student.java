package JavaOOP;

public class Topic_01_Class_Object_Student {
    private int studentID;
    private String studentName;
    private float knowledgePoint;
    private float practicePoint;

    public Topic_01_Class_Object_Student(int studentID, String studentName, float knowledgePoint, float practicePoint) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.knowledgePoint = knowledgePoint;
        this.practicePoint = practicePoint;
    }

    private int getStudentID() {
        return studentID;
    }

    private void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    private String getStudentName() {
        return studentName;
    }

    private void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private float getKnowledgePoint() {
        return knowledgePoint;
    }

    private void setKnowledgePoint(float knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    private float getPracticePoint() {
        return practicePoint;
    }

    private void setPracticePoint(float practicePoint) {
        this.practicePoint = practicePoint;
    }

    public float getAveragePoint() {
        return (this.knowledgePoint + this.practicePoint *2) / 3;
    }

    public void showStudentInfo() {
        System.out.println("************************");
        System.out.println("ID Student: " + this.getStudentID());
        System.out.println("Name Student: " + this.getStudentName());
        System.out.println("Knowledge Point Student: " + this.getKnowledgePoint());
        System.out.println("Practice Point Student: " + this.getPracticePoint());
        System.out.println("Average Point Student: " + this.getAveragePoint());
    }

    public static void main(String[] args) {
        Topic_01_Class_Object_Student firstStudent = new Topic_01_Class_Object_Student(1, "Thu", 7.5f, 8f);
        Topic_01_Class_Object_Student secondStudent = new Topic_01_Class_Object_Student(2, "Khanh", 7f, 8f);
        Topic_01_Class_Object_Student thirdStudent = new Topic_01_Class_Object_Student(3, "Ngan", 7f, 8.5f);

        firstStudent.showStudentInfo();
        secondStudent.showStudentInfo();
        thirdStudent.showStudentInfo();

    }

}
