import java.util.*;

class Book {
    private String BId;		// �̲ĺ�
    private String BName;	// �̲���
    private String ISBN;

    public String getBId() {
        return BId;
    }

    public void setBId(String id) {
        this.BId = id;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String name) {
        this.BName = name;
    }
    
    public String getISBN() {
    	return ISBN;
    }
    
    public void setISBN(String isbn) {
    	this.ISBN = isbn;
    }
}

class Course {
    private String CName;	// �γ���
    private String CId;		// �γ̺�
    private ArrayList<Book> CBooks = new ArrayList<>(); // �γ����ý̲�

    public String getCName() {
        return CName;
    }

    public void setCName(String name) {
        this.CName = name;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String id) {
        this.CId = id;
    }
    
    public ArrayList<Book> getCBooks() {
        return CBooks;
    }
    
    public void setCBooks(ArrayList<Book> books) {
    	this.CBooks = books;
    }
    
    public void addBooks(Book book){
        this.CBooks.add(book);
    }

    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(CName + " [");
        for (int i = 0; i < CBooks.size(); ++i) {
        	if (i != 0) sb.append(", ");
        	Book b = this.CBooks.get(i);
            sb.append(b.getBName());
        }
        sb.append("]");
        return sb.toString();
    }
}

class Student {
    private String StuId;
    private String StuName;
    private ArrayList<Course> StuCourses = new ArrayList<>();
    
    public String getStuId() {
        return StuId;
    }

    public void setStuId(String id) {
        this.StuId = id;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String name) {
        this.StuName = name;
    }

    public ArrayList<Course> getStuCourses() {
        return StuCourses;
    }
    
    public void setStuCourse(ArrayList<Course> courses) {
    	this.StuCourses = courses;
    }

    public void addCourses(Course course){
        this.StuCourses.add(course);
    }

    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("ѧ��ѧ�ţ�" + StuId + "\n");
    	sb.append("ѧ��������" + StuName + "\n");
    	sb.append("�γ����ƺͽ̲����£�" + "\n");
        for (int j = 0; j < StuCourses.size(); ++j) {
            Course c = StuCourses.get(j);
            sb.append("\t" + c + "\n");
        }
        return sb.toString();
    }
}

 
public class CourseSelectingSystem {
    private ArrayList<Student> students;
    
    CourseSelectingSystem() {
    	students = new ArrayList<>();
    }
    
    public static void main(String[] args) {
    	CourseSelectingSystem css = new CourseSelectingSystem();
        Scanner sc = new Scanner(System.in); 

        while (true) {
            System.out.println("��ӭ����ѧ��ѡ��ϵͳ�����������Ž��в�����");
            System.out.println("1. �����Ϣ");
            System.out.println("2. �鿴��Ϣ");
            System.out.println("3. �˳�");
            int command = Integer.parseInt(sc.nextLine());
            switch (command) {
                case 1: // ���ѧ����Ϣ
                    css.addInfo(sc);
                    break;
                case 2: // �鿴ѧ����Ϣ
                    css.showInfo();
                    break;
                case 3: // �˳�
                    System.out.println("�����˳���ϵͳ���ټ���");
                    return;
                default:
                    System.out.println("��������֧�֣�");
            }
        }
    }

    private void showInfo() {
        for (int i = 0; i < students.size(); ++i) {
            Student stu = students.get(i);
            System.out.println(stu);
        }
    }

    private void addInfo(Scanner sc) {
    	Student stu = new Student();
        System.out.println("����ѧ��ѧ�ţ�");
        stu.setStuId(sc.nextLine());
        
        System.out.println("����ѧ��������");
        stu.setStuName(sc.nextLine());
        
        while (true) {
            addCourses(stu, sc);
            System.out.println("���пγ�Ҫ��ӣ�(Y or N)");
            if (!"Y".equals(sc.nextLine())) break;
        }
        students.add(stu);
    }

    private void addCourses(Student stu, Scanner sc) {
        System.out.println("����γ̺ţ�");
        Course c = new Course();
        c.setCId(sc.nextLine());
        System.out.println("����γ����ƣ�");
        c.setCName(sc.nextLine());

        while (true) {
            addBooks(c,sc);
            System.out.println("���н̲�Ҫ��ӣ�(Y or N)");
            if (!"Y".equals(sc.nextLine())) break;
        }
        stu.addCourses(c);
    }

    private static void addBooks(Course c, Scanner sc) {
        System.out.println("������������ţ�");
        Book bk = new Book();
        bk.setBId(sc.nextLine());
        System.out.println("����������������");
        bk.setBName(sc.nextLine());
        c.addBooks(bk);
    }
}

