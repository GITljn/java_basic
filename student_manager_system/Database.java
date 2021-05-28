package student_manager_system;

import java.sql.*;
import java.util.Scanner;

public class Database {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
            boolean work = true;
            Scanner scanner = new Scanner(System.in);
            while (work) {
                outputFunctionInfo();
                int i = scanner.nextInt();
                switch (i) {
                    case 0:
                        outputStudentsInfo(conn);
                        break;
                    case 1:
                        insertNameAndAge(conn);
                        break;
                    case 2:
                        search(conn);
                        break;
                    case 3:
                        update(conn);
                        break;
                    case 4:
                        delete(conn);
                        break;
                    case 5:
                        insertScore(conn);
                        break;
                    case 6:
                        sortByOneCourse(conn);
                        break;
                    case 7:
                        sortByScoreSum(conn);
                        break;
                    case 8:
                        work = false;
                        break;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public static void outputFunctionInfo() {
        System.out.println("----- 请选择你要执行的功能 -----");
        System.out.println("0:查看当前数据库中的信息");
        System.out.println("1:添加一个学生");
        System.out.println("2:查找一个学生");
        System.out.println("3:根据学生编号更新学生基本信息");
        System.out.println("4:根据学生编号删除学生");
        System.out.println("5:根据学生编号录入学生各门成绩");
        System.out.println("6:根据某门成绩进行排序");
        System.out.println("7:根据总分进行排序");
        System.out.println("8:退出系统");
    }

    // 输出数据库中学生的信息
    public static void outputStudentsInfo(Connection conn) {
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        String sql = "select * from student";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double python = resultSet.getDouble("python");
                double java = resultSet.getDouble("java");
                double linux = resultSet.getDouble("linux");
                double sql_ = resultSet.getDouble("sql_");
                double score_sum = resultSet.getDouble("score_sum");
                double score_avg = resultSet.getDouble("score_avg");
                System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                        "    " + sql_ + "    " + score_sum + "    " + score_avg);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // 插入学生的名字和年龄
    public static void insertNameAndAge(Connection conn) {
        System.out.println("请输入学生的姓名:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("请输入学生的年龄");
        int age = scanner.nextInt();
        String sql = "insert into student(id, name, age) values(null, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                System.out.println("添加学生成功");
            } else {
                System.out.println("添加学生失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        outputStudentsInfo(conn);
    }

    // 根据学生编号查找学生信息
    public static void search(Connection conn) {
        System.out.println("请输入你要查找的学生的编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        String sql = "select * from student where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.wasNull()) {
                System.out.println("查找失败");
                return;
            }
            resultSet.next();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            double python = resultSet.getDouble("python");
            double java = resultSet.getDouble("java");
            double linux = resultSet.getDouble("linux");
            double sql_ = resultSet.getDouble("sql_");
            double score_sum = resultSet.getDouble("score_sum");
            double score_avg = resultSet.getDouble("score_avg");
            System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                    "    " + sql_ + "    " + score_sum + "    " + score_avg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // 根据学生编号录入学生成绩
    public static void insertScore(Connection conn) {
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
        double score_sum = python + java + linux + sql;
        double score_avg = score_sum / 4;
        String sql_insertScore = "update student set python=?, java=?, linux=?, sql_=?, score_sum=?, score_avg=? where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql_insertScore);
            preparedStatement.setDouble(1, python);
            preparedStatement.setDouble(2, java);
            preparedStatement.setDouble(3, linux);
            preparedStatement.setDouble(4, sql);
            preparedStatement.setDouble(5, score_sum);
            preparedStatement.setDouble(6, score_avg);
            preparedStatement.setInt(7, i);
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("录入成绩成功");
            } else {
                System.out.println("录入成绩失败");
            }
            outputStudentsInfo(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    // 根据学生编号删除学生
    public static void delete(Connection conn) {
        System.out.println("请输入你要删除的学生的编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        String sql_delete = "delete from student where id = ?";
        PreparedStatement preStmt = null;
        try {
            preStmt = conn.prepareStatement(sql_delete);
            preStmt.setInt(1, i);
            int count = preStmt.executeUpdate();
            if (count == 1) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
            outputStudentsInfo(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preStmt != null) {
                try {
                    preStmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    // 更新学生的姓名和年龄
    public static void update(Connection conn) {
        System.out.println("请输入你要更新的学生的编号:");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        PreparedStatement preStmt_contailID = null;
        PreparedStatement preStmt_update = null;
        try {
            String sql_containID = "select * from student where id = ?";
            preStmt_contailID = conn.prepareStatement(sql_containID);
            preStmt_contailID.setInt(1, i);
//            System.out.println(preStmt_contailID.toString());
            ResultSet resultSet = preStmt_contailID.executeQuery();
            if (!resultSet.next()) {
                System.out.println("不存在该学生");
                return;
            }
            System.out.println("请输入学生姓名:");
            // nextLine会把输入完编号按的回车读进去
            String name = scanner.next();
            System.out.println("请输入学生年龄:");
            int age = scanner.nextInt();
            String sql_updateNameAndAge = "update student set name=?, age=? where id = ?";
            preStmt_update = conn.prepareStatement(sql_updateNameAndAge);
            preStmt_update.setString(1, name);
            preStmt_update.setInt(2, age);
            preStmt_update.setInt(3, i);
            int count = preStmt_update.executeUpdate();
            if (count == 1) {
                System.out.println("更新学生信息成功");
                outputStudentsInfo(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preStmt_contailID != null) {
                try {
                    preStmt_contailID.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preStmt_update != null) {
                try {
                    preStmt_update.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    // 根据某门课程排名
    public static void sortByOneCourse(Connection conn) {
        System.out.println("请选择你要排序的课程(1:python 2:java 3:linux 4:sql)");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        switch (i) {
            case 1:
                String sql_python = "select * from student order by ifnull(python, 0) desc";
                PreparedStatement preStmtPY = null;
                try {
                    preStmtPY = conn.prepareStatement(sql_python);
                    ResultSet resultSet = preStmtPY.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        double python = resultSet.getDouble("python");
                        double java = resultSet.getDouble("java");
                        double linux = resultSet.getDouble("linux");
                        double sql_ = resultSet.getDouble("sql_");
                        double score_sum = resultSet.getDouble("score_sum");
                        double score_avg = resultSet.getDouble("score_avg");
                        System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                                "    " + sql_ + "    " + score_sum + "    " + score_avg);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        preStmtPY.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;

            case 2:
                String sql_java = "select * from student order by ifnull(java, 0) desc";
                PreparedStatement preStmtJava = null;
                try {
                    preStmtJava = conn.prepareStatement(sql_java);
                    ResultSet resultSet = preStmtJava.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        double python = resultSet.getDouble("python");
                        double java = resultSet.getDouble("java");
                        double linux = resultSet.getDouble("linux");
                        double sql_ = resultSet.getDouble("sql_");
                        double score_sum = resultSet.getDouble("score_sum");
                        double score_avg = resultSet.getDouble("score_avg");
                        System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                                "    " + sql_ + "    " + score_sum + "    " + score_avg);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        preStmtJava.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;

            case 3:
                String sql_linux = "select * from student order by ifnull(linux, 0) desc";
                PreparedStatement preStmtLinux = null;
                try {
                    preStmtLinux = conn.prepareStatement(sql_linux);
                    ResultSet resultSet = preStmtLinux.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        double python = resultSet.getDouble("python");
                        double java = resultSet.getDouble("java");
                        double linux = resultSet.getDouble("linux");
                        double sql_ = resultSet.getDouble("sql_");
                        double score_sum = resultSet.getDouble("score_sum");
                        double score_avg = resultSet.getDouble("score_avg");
                        System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                                "    " + sql_ + "    " + score_sum + "    " + score_avg);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        preStmtLinux.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;

            case 4:
                String sql_sql = "select * from student order by ifnull(sql_, 0) desc";
                PreparedStatement preStmtSql = null;
                try {
                    preStmtSql = conn.prepareStatement(sql_sql);
                    ResultSet resultSet = preStmtSql.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        double python = resultSet.getDouble("python");
                        double java = resultSet.getDouble("java");
                        double linux = resultSet.getDouble("linux");
                        double sql_ = resultSet.getDouble("sql_");
                        double score_sum = resultSet.getDouble("score_sum");
                        double score_avg = resultSet.getDouble("score_avg");
                        System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                                "    " + sql_ + "    " + score_sum + "    " + score_avg);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    try {
                        preStmtSql.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                break;
        }
    }

    // 根据总成绩排名
    public static void sortByScoreSum(Connection conn) {
        System.out.println("请选择你要排序的课程(1:python 2:java 3:linux 4:sql)");
        System.out.println("编号    姓名    年龄    python    java    linux    sql    总分    平均分");
        String sql_sql = "select * from student order by ifnull(score_sum, 0) desc";
        PreparedStatement preStmtSql = null;
        try {
            preStmtSql = conn.prepareStatement(sql_sql);
            ResultSet resultSet = preStmtSql.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double python = resultSet.getDouble("python");
                double java = resultSet.getDouble("java");
                double linux = resultSet.getDouble("linux");
                double sql_ = resultSet.getDouble("sql_");
                double score_sum = resultSet.getDouble("score_sum");
                double score_avg = resultSet.getDouble("score_avg");
                System.out.println(id + "    " +name + "    " + age + "    " + python + "    " + java + "    " + linux +
                        "    " + sql_ + "    " + score_sum + "    " + score_avg);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preStmtSql.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
