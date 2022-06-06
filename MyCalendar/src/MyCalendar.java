/**
 * �����û�������,���������������������������������
 * java MyCalendar 2025
 * java MyCalendar 4 2025
 * java MyCalendar 4 25 2025
 */
import java.util.*;
import java.time.*;

/**
 * @author ��ƽ 21030540006
 * @since 2022/04/09
 */
public class MyCalendar {
	private static int[][] monthDay = {
		{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
		{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	};
	private static String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	/**
	 * �ж��Ƿ�Ϊ����
	 * @param y ��
	 * @return true��ʾ������
	 */
	private static boolean isLeapYear(int y) {
		return (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0));
	}
	
	/**
	 * ����ĳ��ĳ���м���
	 * @param y ��
	 * @param m ��
	 * @return ĳ��ĳ�µ�����
	 */
	private static int getMonthDay(int y, int m) {
		return isLeapYear(y) ? monthDay[0][m] : monthDay[1][m];
	}
	
	/**
	 * ʹ��Kim Larson's formula����ָ���������������ڼ�
	 * @param y ��
	 * @param m ��
	 * @param d ��
	 * @return 0~6, ��Ӧ��һ������
	 */
	private static int kimLarson(int y, int m, int d) {
		if (m == 1 || m == 2) {
			m += 12;
			--y;
		}
		return (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
	}
	
	/**
	 * ����һ���µ���������
	 * @param y ��
	 * @param m ��
	 * @return ĳ��ĳ�µ����� 
	 */
	private static StringBuffer[] getMonthCalendar(int y, int m) {
		// ��ʼ������һ�ܵ��ַ���,�������
		StringBuffer outWeek = new StringBuffer();
		// ��ʼ������һ���µ�����,�������
		// ����ϵͳ����������,ÿ�����ʱ��ʾ����,����ͷ��Ϊ7��
		int weekIdx = 1;
		StringBuffer[] outMonth = new StringBuffer[7];
		outMonth[0] = new StringBuffer(" Mon Tue Wed Thu Fri Sat Sun");
		
		// ������м���
		int dayOfMonth = getMonthDay(y, m);
		// ����µ�һ�������ڼ�
		int target = kimLarson(y, m, 1);
		// �ÿո��������ÿ��1�ŵ�����
		for (int i = 0; i < target; ++i) {
			outWeek.append("    ");
		}
		
		// ����һ��ʣ�µ�����뵽��һ�ܵ��ַ���
		int day = 1;
		for (int i = target; i < 7; ++i) {
			outWeek.append(String.format("%4d", day++));
		}
		// ����һ�ܼ���������
		outMonth[weekIdx++] = outWeek;
		
		// �����������μ���������
		while (day <= dayOfMonth) {
			outWeek = new StringBuffer();
			for (int i = 0; i < 7; ++i) {
				outWeek.append(String.format("%4d", day++)); // �����ÿ���µ����һ��
				// ���һ��û�н���,�����¸���,����"����"��һ��
				if (day > dayOfMonth) {
					for (; i < 6; ++i) {
						outWeek.append("    ");
					}
					break;
				}
			}
			outMonth[weekIdx++] = outWeek;
		}
		
		// δ��������Ҫ����
		while (weekIdx <= 6) {
			outMonth[weekIdx++] = new StringBuffer("                            "); // 4*7=28���ո�
		}
		return outMonth;
	}
	
	/**
	 * ����һ�����������
	 * @param y ��
	 * @return ĳ�������
	 */
	private static StringBuffer[][] getYearCalendar(int y) {
		// ��ʼ������һ�������,�������
		StringBuffer[][] outYear = new StringBuffer[12][7];
		for (int m = 1; m <= 12; ++m) 
			outYear[m - 1] = getMonthCalendar(y, m);
		return outYear;
	}
	
	/**
	 * @param args �����в���
	 */
	public static void main(String[] args) {
		int year, month, day;
		if (args.length == 1) { // ���ĳ�������
			year = Integer.parseInt(args[0]);
			month = 0;
			StringBuffer[][] yearCalendar = getYearCalendar(year);
			int line = 4; // ÿһ������ĸ��µ�����
			for (int seg = 0; seg < 12 / line; ++seg) {
				for (int i = 0; i < line; ++i) // ����⼸���µı�ͷ
					System.out.print(yearCalendar[month + i][0] + "\t");
				System.out.println();
				for (int r = 1; r < 7; ++r) {
					for (int i = 0; i < line; ++i) {
						System.out.print(yearCalendar[month + i][r] + "\t");
					}
					System.out.println();
				}
				month += line;
			}
		} else if (args.length == 2) { // ���ĳ��ĳ�µ�����
			month = Integer.parseInt(args[0]);
			year = Integer.parseInt(args[1]);
			StringBuffer[] monthCalendar = getMonthCalendar(year, month);
			for (StringBuffer m : monthCalendar) {
				System.out.println(m);
			}
		} else if (args.length == 3) { // ���ĳ��ĳ��ĳ��Ϊ���ڼ�
			month = Integer.parseInt(args[0]);
			day = Integer.parseInt(args[1]);
			year = Integer.parseInt(args[2]);
			System.out.println(weekDay[kimLarson(year, month, day)]);
		}
	}
}
