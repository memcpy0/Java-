
import java.util.*;
import java.io.*;

public class PIMManager {
	static String dataFilePath = "PIMDatabase.dat";
	static File dataFile = new File(dataFilePath);
	static LinkedList<PIMEntity> itemList = new LinkedList<>();
	
	// ��itemList���������еĶ��󱣴浽�ļ���
	private static void saveData() {
		if (dataFile.canWrite()) { // ��д�ļ�
			try (					
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
			) {
				oos.writeObject(itemList); // ���л����϶���
				oos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// �������ļ������л�,�������ɶ�������itemList
	private static void loadData() {
		if (dataFile.canRead() && dataFile.length() > 0) { // �ɶ��ļ�,���ȴ���0ʱ��ȡ
			try (
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile));
			) {
				itemList = (LinkedList<PIMEntity>)ois.readObject(); // ���������л�
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		if (!dataFile.exists()) { // ��������ļ�������
			dataFile.createNewFile(); // �½�һ�������ļ�
		} else loadData(); // ��������ļ�����,���������
		
		System.out.println("Welcome to PIM.");
		Scanner sc = new Scanner(System.in);
		String op, input, date, text, priority, desc, firstName, lastName, email;
		do {
			System.out.println("---Enter a command (supported commands are List Create Save Load Quit)---");
			// ֧��List,Create,Save,Load,Quit����
			op = sc.nextLine();
			switch (op) {
			case "List": // ������ж���,����ʵ���˶����toString����,���������Ӧ���ַ�����ʾ
				System.out.println("There are "+ itemList.size() + " items.");
				for (int i = 0; i < itemList.size(); ++i) {
					System.out.println("Item " + (i + 1) + ": " + itemList.get(i));
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
					
					itemList.add(todo);
					break; 
				case "note":
					PIMNote note = new PIMNote();
					System.out.println("Enter note text:"); // ����¼˵���ı� 
					text = sc.nextLine();
					note.setNoteText(text);
					
					System.out.println("Enter note priority:"); // ����¼���ȼ�
					priority = sc.nextLine();
					note.setPriority(priority);
					
					itemList.add(note);
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

					itemList.add(appointment);
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

					itemList.add(contact);
				    break;
				default:
					System.out.println("the item type is not exist");
					break;
				}
				break;
			case "Save":
				saveData();
				System.out.println("Items have been saved.");
				break;
			case "Load":
				loadData();
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