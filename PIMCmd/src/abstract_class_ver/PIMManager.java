package abstract_class_ver;
import java.util.*;
import java.io.*;

public class PIMManager{
	static int itemCount = 0; // ������ʾ�ж��ٸ�����,��1��ʼʹ��
	static String[] itemList = new String[200];  
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to PIM.");
		Scanner sc = new Scanner(System.in);
		String op, input, date, text, priority, desc, firstName, lastName, email;
		do {
			System.out.println("---Enter a command (supported commands are List Create Save Load Quit)---");
			// ֧��List,Create,Save,Load,Quit����
			op = sc.nextLine();
			switch (op) {
			case "List":
				System.out.println("There are "+ itemCount + " items.");
				for (int i = 1; i <= itemCount; ++i) {
					System.out.println(itemList[i]);
				}
				break;
			case "Create":
				System.out.println("Enter an item type ( todo, note, contact or appointment )");
				input = sc.nextLine();
				switch (input) {
				case "todo":
					PIMTodo todo = new PIMTodo();
					System.out.println("Enter date for todo item:"); // �������������
					date = sc.nextLine();
					todo.setDate(date);
			
					System.out.println("Enter todo text:");	// ���������˵���ı�
					text = sc.nextLine();
					todo.setTodoText(text);
					
					System.out.println("Enter todo priority:"); // �����������ȼ� 
					priority = sc.nextLine();
					todo.setPriority(priority); 
					
					itemList[++itemCount] = todo.toString();
					break; 
				case "note":
					PIMNote note = new PIMNote();
					System.out.println("Enter note text:"); // ����¼˵���ı� 
					text = sc.nextLine();
					note.setNoteText(text);
					
					System.out.println("Enter note priority:"); // ����¼���ȼ�
					priority = sc.nextLine();
					note.setPriority(priority);
					
					itemList[++itemCount] = note.toString();
					break;
				case "appointment":
					PIMAppointment appointment = new PIMAppointment();
					System.out.println("Enter date for todo item:"); // �������������
					date = sc.nextLine();
					appointment.setDate(date);
					
					System.out.println("Enter appointment description:"); // �������������
					text = sc.nextLine();
					appointment.setDescription(text);
					
					System.out.println("Enter appointment priority:"); // �����������ȼ�
					priority = sc.nextLine();
					appointment.setPriority(priority);

					itemList[++itemCount] = appointment.toString();
					break;
				case "contact":
					PIMContact contact = new PIMContact();
					System.out.println("Enter firstname for contact item:"); // ��ϵ��firstName 
					firstName = sc.nextLine();
					contact.setFirstName(firstName);
					
					System.out.println("Enter lastname for contact item:"); // ��ϵ��lastName
					lastName = sc.nextLine();
					contact.setLastName(lastName);
					
					System.out.println("Enter email for contact item:"); // ��ϵ������ 
					email = sc.nextLine();
					contact.setEmail(email);
					
					System.out.println("Enter contact priority:"); // ��ϵ�����ȼ�
					priority = sc.nextLine();
					contact.setPriority(priority);

					itemList[++itemCount] = contact.toString();
				    break;
				default:
					System.out.println("the item type is not exist");
					break;
				}
				break;
			case "Save":
				System.out.println("Items have been saved.");
				break;
			case "Load":
				break;
			case "Quit":
				sc.close();
				break;
			default:
				System.out.println("the command is not exist");
			}
		} while (!op.equals("Quit"));
	}
}