package student_manager_system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        System.out.println("请定义学生人数:");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        ArrayList<Student> students = new ArrayList<>(num);
        boolean work = true;
        while (work) {
            outputFunctionInfo();
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    insertNameAndAge(students);
                    break;
                case 2:
                    search(students);
                    break;
                case 3:
                    update(students);
                    break;
                case 4:
                    delete(students);
                    break;
                case 5:
                    insertScore(students);
                    break;
                case 6:
                    sortByOneCourse(students);
                    break;
                case 7:
                    sortByScoreSum(students);
                    break;
                case 8:
                    work = false;
                    break;
            }
        }
    }

    public static void outputFunctionInfo() {
        System.out.println("----- 请选择你要执行的功能 -----");
        System.out.println("1:添加一个学生");
        System.out.println("2:查找一个学生");
        System.out.println("3:根据学生学号更新学生基本信息");
        System.out.println("4:根据学生编号删除学生");
        System.out.println("5:根据学生编号录入学生各门成绩");
        System.out.println("6:根据某门成绩进行排序");
        System.out.println("7:根据总分进行排序");
        System.out.println("8:退出系统");
    }

    public static void outputStudentsInfo(ArrayList<Student> students) {
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        for (Student student : students) {
            student.outputInfo();
        }
    }

    public static void insertNameAndAge(ArrayList<Student> students) {
        System.out.println("请输入学生的姓名:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("请输入学生的年龄");
        int age = scanner.nextInt();
        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        students.add(s);
        System.out.println("添加学生成功");
        outputStudentsInfo(students);
    }

    public static void search(ArrayList<Student> students) {
        System.out.println("请输入你要查找的学生的编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        Student s = null;
        for (Student student : students) {
            if (student.getId() == i) {
                s = student;
                break;
            }
        }
        s.outputInfo();
    }

    public static void insertScore(ArrayList<Student> students) {
        System.out.println("请输入学生编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println("请输入学生的python成绩");
        double python = scanner.nextDouble();
        System.out.println("请输入学生的java成绩");
        double java = scanner.nextDouble();
        System.out.println("请输入学生的linux成绩");
        double linux = scanner.nextDouble();
        System.out.println("请输入学生的sql成绩");
        double sql = scanner.nextDouble();
        Student s = null;
        for (Student student : students) {
            if (student.getId() == i) {
                s = student;
                break;
            }
        }
        s.setPython(python);
        s.setJava(java);
        s.setLinux(linux);
        s.setSql(sql);
        System.out.println("录入成绩成功");
        outputStudentsInfo(students);
    }

    public static void delete(ArrayList<Student> students) {
        System.out.println("请输入你要删除的学生的编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Student s = null;
        for (Student student : students) {
            if (student.getId() == i) {
                s = student;
                break;
            }
        }
        students.remove(s);
        System.out.println("删除成功");
        outputStudentsInfo(students);
    }

    public static void update(ArrayList<Student> students) {
        System.out.println("请输入你要更新的学生的编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i > students.size()) {
            System.out.println("编号输入有误");
            return;
        }
        System.out.println("请输入学生姓名:");
        // nextLine会把输入完编号按的回车读进去
        String name = scanner.next();
        System.out.println("请输入学生年龄:");
        int age = scanner.nextInt();
        Student s = null;
        for (Student student : students) {
            if (student.getId() == i) {
                s = student;
                break;
            }
        }
        s.setName(name);
        s.setAge(age);
        System.out.println("更新学生信息成功");
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        s.outputInfo();
    }

    public static void sortByOneCourse(ArrayList<Student> students) {
        System.out.println("请选择你要排序的课程(1:python 2:java 3:linux 4:sql)");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i) {
            case 1:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return (int) Math.ceil(o2.getPython() - o1.getPython());
                    }
                });
                break;

            case 2:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return (int) Math.ceil(o2.getJava() - o1.getJava());
                    }
                });
                break;

            case 3:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return (int) Math.ceil(o2.getLinux() - o1.getLinux());
                    }
                });
                break;

            case 4:
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return (int) Math.ceil(o2.getSql() - o1.getSql());
                    }
                });
                break;
        }
        outputStudentsInfo(students);
    }

    public static void sortByScoreSum(ArrayList<Student> students) {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) Math.ceil(o2.getScore_sum() - o1.getScore_sum());
            }
        });
        outputStudentsInfo(students);
    }
}
