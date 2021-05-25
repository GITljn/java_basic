package student_manager_system;

public class Student {
    static private int ID = 0;
    private int id;
    private String name;
    private int age;
    private double python;
    private double java;
    private double linux;
    private double sql;
    private double score_sum;
    private double score_avg;

    public Student() {
        id = Student.ID;
        Student.ID += 1;
    }

    public Student(boolean load) {

    }

    public void outputInfo() {
        System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                "    " + sql + "    " + getScore_sum() + "    " + getScore_avg());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPython() {
        return python;
    }

    public void setPython(double python) {
        this.python = python;
    }

    public double getJava() {
        return java;
    }

    public void setJava(double java) {
        this.java = java;
    }

    public double getLinux() {
        return linux;
    }

    public void setLinux(double linux) {
        this.linux = linux;
    }

    public double getSql() {
        return sql;
    }

    public void setSql(double sql) {
        this.sql = sql;
    }

    public double getScore_sum() {
        score_sum = python + java + linux + sql;
        return score_sum;
    }

    public void setScore_sum(double score_sum) {
        this.score_sum = score_sum;
    }

    public double getScore_avg() {
        score_avg = score_sum / 4;
        return score_avg;
    }
}
