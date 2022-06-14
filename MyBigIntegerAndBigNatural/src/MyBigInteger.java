import java.util.ArrayList;
public class MyBigInteger {
	// dΪ�洢����������,��ͷ��β����洢ʮ��������
	protected final int[] d;
	// signumΪ����λ,0��ʾΪ0,-1��ʾ����,1��ʾ����
	protected final int signum;
	// len��ʾʵ�����ݵĳ���(ʮ����λ��)
	protected final int length;
	protected final static int MAX_DATA_LENGTH = 100000;
	public static final MyBigInteger ZERO = new MyBigInteger();
	public static final MyBigInteger ONE = new MyBigInteger("1");
	public static final MyBigInteger TWO = new MyBigInteger("2");
	public static final MyBigInteger NEGATIVE_ONE = new MyBigInteger("-1");
	public static final MyBigInteger TEN = new MyBigInteger("10");
	public static final MyBigInteger MAX_VALUE; // ����MyBigInteger����
	public static final MyBigInteger MIN_VALUE; // ��С��MyBigInteger����
	static {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_DATA_LENGTH; ++i)
			sb.append("9");
		MAX_VALUE = new MyBigInteger(sb.toString());
		MIN_VALUE = new MyBigInteger("-" + sb.toString());
	}
	
	// �չ��캯��,�õ���ʾ0�Ĵ�����
	// Ϊ��ʡ�ռ�,Լ��������0��data���鳤Ϊ0
	public MyBigInteger() {	
		d = new int[0];
		signum = 0;
		length = 0;
	}
	// ��String�������ݼ��Թ���,���������ַ�����������+,-,����֮����ַ�
	public MyBigInteger(String val) {
		int cursor = 0;
		final int len = val.length();
		if (len == 0) {
            throw new NumberFormatException("Zero length BigInteger");			
		}
        // ���,���ֻ��һ��ǰ������
        int sign = 1;
        int index1 = val.lastIndexOf('-');
        int index2 = val.lastIndexOf('+');
        if (index1 >= 0) { // ����-��
        	if (index1 != 0 || index2 >= 0) { // ��-�Ų�Ϊ��һ�����Ż򻹴���+��
        		throw new NumberFormatException("Illegal embedded sign character");
        	}
        	sign = -1; // Ϊ����
        	cursor = 1;
        } else if (index2 >= 0) { // ����+��
        	if (index2 != 0) { // ��+�Ų�Ϊ��һ������
                throw new NumberFormatException("Illegal embedded sign character");
        	}
        	cursor = 1;
        }
        if (cursor == len) { // valֻ����һ������
            throw new NumberFormatException("Zero length BigInteger");			
        }
        // �������ǧ��λ,
        if (val.contains(",")) { // �������","
        	if (checkFormat(val, cursor)) {
        		StringBuilder sb = new StringBuilder();
        		for (int i = cursor; i < len; ++i) {
        			if (val.charAt(i) != ',') 
        				sb.append(val.charAt(i));
        		}
        		val = sb.toString();
        	} else throw new NumberFormatException("Illegal embedded thousandth");
        }
        
        // ����ǰ��0,��������λ��
        while (cursor < len && 
        		Character.digit(val.charAt(cursor), 10) == 0) { // ʮ������λΪ0
        	++cursor;
        }
        if (cursor == len) { // ��ʱ����ȫ0����
        	d = new int[0];
        	signum = 0;
        	length = 0;
        	return;
        } 
        signum = sign;
        length = len - cursor;
        d = new int[length];
        for (int i = cursor; i < val.length(); ++i) {
        	int v = (int)(val.charAt(len - (i - cursor) - 1) - '0');
        	d[i - cursor] = v;
        }
	}
	
	private boolean checkFormat(String val, int cursor) {
		for (int i = val.length() - 4; i > cursor; i -= 4) {
			if (val.charAt(i) != ',') return false;
		}
		return val.charAt(cursor) != ',';
	}

	// �ô����int[]�ͷ���λ����(�ڲ�ʹ��)
	protected MyBigInteger(int[] mag, int signum) {
		this.d = mag;
		this.signum = signum;
		this.length = mag.length;
	}

	// �Ƚ����ߵĴ�С,a>b����1,a=0����0,a<b����-1
	int compare(MyBigInteger a) {
		if (signum != a.signum) return (signum > a.signum ? 1 : -1); // ���,�ĸ����Ŵ����־ʹ�
		if (length != a.length) {
			return (length > a.length ? (signum >= 0 ? 1 : -1) 	 // ͬ��,�ĸ����ȴ���Ϊ�����ʹ�
					: (signum < 0 ? 1 : -1));					 // �ĸ�����С��Ϊ�����ʹ�
		}
		// ͬ�������ݳ�����ͬ
		for (int i = length - 1; i >= 0; --i) { // �Ӹ�λ��ʼ�Ƚ�
			if (d[i] > a.d[i]) return 1;
			else if (d[i] < a.d[i]) return -1;
		}
		return 0; // �������������
	}
	
	// ��������ľ���ֵ
	public MyBigInteger abs(MyBigInteger a) {
		if (a.equals(ZERO)) return ZERO; // 0�ľ���ֵΪ0
		if (a.signum != -1) return a; // �����͸����ľ���ֵ��������
		else return negate(a); // �����ľ���ֵ������(�����෴��)
	}
	// ����������෴��
	public MyBigInteger negate(MyBigInteger a) {
		return new MyBigInteger(a.d, -a.signum);
	}
	
	// �߾��ȼӷ�
	public MyBigInteger add(MyBigInteger a) {
		if (signum == 0 && a.signum == 0) return ZERO; 		// ��Ϊ0
		if (signum == 0) return a;							// һ��Ϊ0
		if (a.signum == 0) return this;
		// ��������������Ϊ0
		if (signum == a.signum) { // ͬ��ʱ,ת��Ϊ�������ļӷ�
			StringBuilder sb = new StringBuilder();
			int carry = 0; // ��λ
			for (int i = 0; i < length || i < a.length; ++i) { // �ӵ�λ���
				if (i < length) carry += d[i];
				if (i < a.length) carry += a.d[i]; // ������Ӧλ���λ���
				sb.append((char)('0' + carry % 10));	// ��λ��Ϊ��λ���
				carry /= 10;					// ʮλ��Ϊ�µĽ�λ
			}
			if (carry != 0) // �������λ��Ϊ0,��ֱ�Ӹ�����������λ
				sb.append((char)('0' + carry));
			sb.append(signum == 1 ? "" : "-");	// �ӷ��ķ���Ϊsignum
			return new MyBigInteger(sb.reverse().toString());
		} else { // ���ʱ,ת��Ϊ����������֮��ļ���
			if (signum == -1) // thisΪ����,aΪ����,��this+a����a-abs(this)
				return a.subtract(abs(this));
			else // thisΪ����,aΪ����,��this+a����this-abs(a)
				return subtract(abs(a));
		}
	}
	
	// �߾��ȼ���
	public MyBigInteger subtract(MyBigInteger a) {
		if (signum == 0 && a.signum == 0) return ZERO; 	// ��Ϊ0
		if (signum == 0) return negate(a); // this=0,����-a
		if (a.signum == 0) return this; // aΪ0,this-a����this
		
		// ��������������Ϊ0ʱ
		if (signum != a.signum) { // ���ʱ
			if (signum == 1) { // aΪ����,this-a=this+abs(a)
				return add(abs(a));
			} else { // thisΪ����,this-a=-(abs(this)+a)
				return negate(a.add(abs(this)));
			}
			// �ɼ�Ϊnew MyBigInteger(abs(a).add(abs(this)), signum);
		} 
		
		// ͬ��ʱ,����a,b�ľ���ֵ,�ô�ľ���ֵ��ȥС�ľ���ֵ,��Ϊ����ľ���ֵ
		int cmp = compare(a);
		if (cmp == 0) return ZERO; // this-a=a-a=0
		int[] resultD = (cmp > 0 ? substract(d, a.d) : substract(a.d, d)); // ����ֵ���С
		return new MyBigInteger(resultD, cmp == signum ? 1 : -1);
	}
	
	// �ڲ�ʹ��
	private int[] substract(int[] a, int[] b) { // a>b,a-b
		ArrayList<Integer> c = new ArrayList<>();
		int higher = a[0], cur = -1, tmpB;
		for (int i = 0; i < a.length || i < b.length; ++i) {
			cur = higher;
			higher = (i + 1 < a.length) ? a[i + 1] : 0;
			tmpB = (i < b.length) ? b[i] : 0;
			if (cur < tmpB) {
				--higher;  // ���λ��1
				cur += 10; // ��ǰλ+10
			}
			c.add((int)(cur - tmpB)); // �������Ϊ��ǰλ��� 
		}
		while (c.get(c.size() - 1) == 0) c.remove(c.size() - 1); // ȥ����λ0
		int[] result = new int[c.size()];
		int len = 0;
		for (int x : c) result[len++] = x;
		return result;
	}
	
	// �߾��ȳ���
	public MyBigInteger divide(MyBigInteger a) {
		return new MyBigInteger();
	}
	
	// �߾��ȳ˷�
	public MyBigInteger multiply(MyBigInteger a) {
		if (signum == 0 || a.signum == 0) return ZERO;

		int[] tmp = new int[length + a.length]; 
		for (int i = 0; i < a.length; ++i) { // this*a
			int carry = 0;
			for (int j = 0; j < length; ++j) {
				int t = a.d[i] * d[j] + carry + tmp[i + j];
				tmp[i + j] = (int)(t % 10);
				carry = t / 10; // ��λ����Ϊ�µĽ�λ
			}
			if (carry != 0) //a.d��ÿһλ����d�����λ�����Ľ�λ
				tmp[length + i] = (int)(carry % 10);
		}
		// ���������,ʮ����λ��ҪôΪ����λ��֮��,ҪôΪ����λ��֮��-1
		int len = length + a.length;
		// ������λ�Ƿ�Ϊ0,���Ϊ0,��һλ
		while (tmp[len - 1] == 0 && len >= 1) --len;
		int[] result = new int[len];
		for (int i = 0; i < len; ++i) result[i] = tmp[i];
		return new MyBigInteger(result, signum * a.signum);
	}
	
	// ���ڴ������ļ򵥱ȽϷ���
	public boolean equals(MyBigInteger a) {
		if (signum != a.signum) return false;
		if (length != a.length) return false;
		for (int i = 0; i < length; ++i)
			if (d[i] != a.d[i]) return false;
		return true;
	}
	
	// �ַ�����ʾ
	public String toString() {
		if (signum == 0) return "0";
		StringBuilder sb = new StringBuilder();
		if (signum == -1) sb.append('-');
		for (int i = length - 1; i >= 0; --i) {
			sb.append((char)(48 + d[i]));
		}
		return sb.toString();
	}
}
