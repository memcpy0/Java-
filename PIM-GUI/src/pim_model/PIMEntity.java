package pim_model;
import java.io.Serializable;

/**
 * <p>��Ŀ���ƣ�PIM GUI</p>
 * <p>�����ƣ�PIMEntity</p>
 * ����ʱ�䣺2022��5��30�� <br>
 * ��������������Ϣ��¼�ĳ�����
 * @author����ƽ
 */
public abstract class PIMEntity implements Serializable {
	String priority; // every kind of item has a priority
	String type;
	
    // default constructor sets priority to "normal"
    PIMEntity() {
    	priority = "Low Priority";
    }

    // priority can be established via this constructor.
    PIMEntity(String priority) {
    	this.priority = priority;
    }

    // accessor method for getting the priority string
    public String getPriority() {
        return priority;
    }
    // method that changes the priority string
    public void setPriority(String p) {
    	priority = p;
    }
    
    public String getType() {
    	return type;
    }
 
    abstract public String toString();
    
    abstract public boolean equals(PIMEntity p);
    
    // set current object value as p
    abstract public void setEntity(PIMEntity p);
}
