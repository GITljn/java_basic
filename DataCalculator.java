import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataCalculator {
    public static void main(String[] args) {
        while (true) {
            System.out.println("********** 欢迎使用日期计算器 **********");
            System.out.println("********* 计算几天后的日期请按 1 ********");
            System.out.println("********* 计算两个日期差值请按 2 ********");
            System.out.println("********* 退出计算日期程序请按 0 ********");
            System.out.println("********** 请选择要使用的功能 **********");

            Scanner scanner = new Scanner(System.in);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            int function = 0;
            while(true) {
                try {
                    function = scanner.nextInt();
                    if (function != 1 && function !=2 && function != 0) {
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    scanner.nextLine();
                    System.out.println("**** 输入有误，请重新选择要使用的功能 ****");
                    System.out.println("********* 计算几天后的日期请按 1 ********");
                    System.out.println("********* 计算两个日期差值请按 2 ********");
                    System.out.println("********** 请选择要使用的功能 **********");
                }
            }
            if (1 == function) {
                System.out.println("*********** 请输入相隔的天数 ***********");
                long day = 0;
                while(true) {
                    try {
                        day = scanner.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("***** 输入有误，请重新输入相隔的天数 ******");
                        scanner.nextLine();
                    }
                }
                Date dateStart = new Date();
                String dateStartFormat = sdf.format(dateStart);
                System.out.println("************* 当前日期为 *************");
                System.out.println("*********** " + dateStartFormat + " ***********");
                long intervalSecond = day * 86400000;
                Date dateEnd = new Date(intervalSecond + dateStart.getTime());
                String dateEndFormat = sdf.format(dateEnd);
                System.out.println("*********** " + day + "天后的日期为" + " ***********");
                System.out.println("*********** " + dateEndFormat + " ***********\n");
            } else if (2 == function) {
                Date dateStartFormat = null;
                Date dateEndFormat = null;
                while (true) {
                    try {
                        System.out.println("***** 请输入起始日期（YYYY年MM月DD日）*****");
                        String dateStart = scanner.next();
                        dateStartFormat = sdf.parse(dateStart);
                        break;
                    } catch (Exception e) {
                        System.out.println("******* 起始日期输入有误，请重新输入 *******");
                    }
                }

                while(true) {
                    try {
                        System.out.println("***** 请输入截止日期（YYYY年MM月DD日）*****");
                        String dateEnd = scanner.next();
                        dateEndFormat = sdf.parse(dateEnd);
                        break;

                    } catch (Exception e) {
                        System.out.println("******* 截止日期输入有误，请重新输入 *******");
                    }
                }
                long interval = dateEndFormat.getTime() - dateStartFormat.getTime();
                long day = interval / 86400000;
                System.out.println("************ 两个日期之间相隔 ************");
                System.out.println("**************** " + day + "天" + " ****************\n");
            } else if (0 == function) {
                System.out.println("********* 欢迎再次使用日期计算器 *********");
                break;
            }
        }

    }
}
