package student_manager_system;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Io {
    public static void main(String[] args) {
        // 注：
        // 1.编号不一定是单个字符，所以不能只取charAt(0)
        // 2.将文件中的数据以对象的方式加载到数组中进行排序或者删除等操作

        // 问题：
        // 1.文件里面如何删除学生
        File fileNameAge = new File("basic_project\\src\\student_manager_system\\name_age.txt");
//        if (fileNameAge.isFile()) {
//            fileNameAge.delete();
//        }
        File fileScore = new File("basic_project\\src\\student_manager_system\\score.txt");
//        if (fileScore.isFile()) {
//            fileScore.delete();
//        }
        Scanner scanner = new Scanner(System.in);
        boolean work = true;
        while (work) {
            outputFunctionInfo();
            int i = scanner.nextInt();
            switch (i) {
                case 1:
                    insertNameAndAge(fileNameAge, fileScore);
                    break;
                case 2:
                    search(fileNameAge, fileScore);
                    break;
                case 3:
                    update(fileNameAge, fileScore);
                    break;
                case 4:
                    delete(fileNameAge, fileScore);
                    break;
                case 5:
                    insertScore(fileNameAge, fileScore);
                    break;
                case 6:
                    sortByOneCourse(fileNameAge, fileScore);
                    break;
                case 7:
                    sortByScoreSum(fileNameAge, fileScore);
                    break;
                case 8:
                    work = false;
                    break;
            }
        }
    }

    // 输出该学生管理系统的功能信息
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

    // 输出所有学生的所有信息
    public static void outputStudentsInfo(ArrayList<Student> students) {
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        for (Student student : students) {
            student.outputInfo();
        }
    }

    // 添加学生的姓名和年龄
    public static void insertNameAndAge(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
        System.out.println("请输入学生的姓名:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("请输入学生的年龄");
        int age = scanner.nextInt();
        Student s = new Student();
        s.setName(name);
        s.setAge(age);
        students.add(s);
        arrayToFile(students, fileNameAge, fileScore);
        System.out.println("添加学生成功");
        outputStudentsInfo(students);
    }

    // 将文件的数据以对想的形式存储到数组
    public static ArrayList<Student> fileToArray(File fileNameAge, File fileScore) {
        BufferedReader brNameAge = null;
        BufferedReader brScore = null;
        String lineNameAge = null;
        String lineScore = null;
        ArrayList<Student> students = new ArrayList<>();
        try {
            brNameAge = new BufferedReader(new FileReader(fileNameAge));
            brNameAge.readLine();
            while ((lineNameAge = brNameAge.readLine()) != null) {
                String[] nameAge = lineNameAge.split("    ");
                Student s = new Student(true);
                s.setId(Integer.valueOf(nameAge[0]));
                s.setName(nameAge[1]);
                s.setAge(Integer.valueOf(nameAge[2]));
                brScore = new BufferedReader(new FileReader(fileScore));
                brScore.readLine();
                while ((lineScore = brScore.readLine()) != null) {
                    String[] score = lineScore.split("    ");
                    if (score[0].equals(nameAge[0])) {
                        s.setPython(Double.valueOf(score[1]));
                        s.setJava(Double.valueOf(score[2]));
                        s.setLinux(Double.valueOf(score[3]));
                        s.setSql(Double.valueOf(score[4]));
                    }
                }
                students.add(s);
                brScore.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brNameAge != null) {
                try {
                    brNameAge.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (brScore != null) {
                try {
                    brScore.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }

    // 将数组中的数据存储到文件中
    public static void arrayToFile(ArrayList<Student> students, File fileNameAge, File fileScore) {
        FileWriter fwNameAge = null;
        FileWriter fwScore = null;
        try {
            fwNameAge = new FileWriter(fileNameAge);
            fwNameAge.write("编号    姓名    年龄");
            fwScore = new FileWriter(fileScore);
            fwScore.write("编号    python    java    linux    sql    总分    平均分");
            fwNameAge.write("\r\n");
            fwScore.write("\r\n");
            for (Student s : students) {
                fwNameAge.write(s.getId() + "    " + s.getName() + "    " + s.getAge());
                fwScore.write(s.getId() + "    " + s.getPython() + "    " + s.getJava() + "    " + s.getLinux()
                + "    " + s.getSql() + "    " + s.getScore_sum() + "    " + s.getScore_avg());
                fwNameAge.write("\r\n");
                fwScore.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fwNameAge != null) {
                try {
                    fwNameAge.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fwScore != null) {
                try {
                    fwScore.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 根据学生的编号输出学生的信息
    public static void search(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
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
        if (s != null) {
            s.outputInfo();
        }
    }

    // 根据学生的编号录入学生的成绩
    public static void insertScore(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
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
        if (s != null) {
            s.setPython(python);
            s.setJava(java);
            s.setLinux(linux);
            s.setSql(sql);
        }
        System.out.println("录入成绩成功");
        arrayToFile(students, fileNameAge, fileScore);
        outputStudentsInfo(students);
    }

    // 根据学生的编号删除学生的信息
    public static void delete(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
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
        arrayToFile(students, fileNameAge, fileScore);
        outputStudentsInfo(students);
    }

    // 根据学生的编号更新学生的姓名和年龄
    public static void update(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
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
        if (s != null) {
            s.setName(name);
            s.setAge(age);
        }
        System.out.println("更新学生信息成功");
        arrayToFile(students, fileNameAge, fileScore);
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        s.outputInfo();
    }

    // 根据某一门课程的成绩对学生进行排名
    public static void sortByOneCourse(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
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
        arrayToFile(students, fileNameAge, fileScore);
        outputStudentsInfo(students);
    }

    // 根据总成绩对学生进行排名
    public static void sortByScoreSum(File fileNameAge, File fileScore) {
        ArrayList<Student> students = fileToArray(fileNameAge, fileScore);
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) Math.ceil(o2.getScore_sum() - o1.getScore_sum());
            }
        });
        arrayToFile(students, fileNameAge, fileScore);
        outputStudentsInfo(students);
    }
}
