package db;

//ѧ��ʵ����
public class Student {
	private String ID;
	private String Name;
	private String Class;
	private String Sex;
	private String Birth_date;
	private String Phone;
	
	//�����Ե�getter��setter����
	public void setID(String ID){
		this.ID=ID;
	}
	public String getID(){
		return ID;
	}
	public void setName(String name) {
		this.Name = name;
	}	
	public String getName() {
		return Name;
	}
	public void setClass(String Class){
		this.Class=Class;
	}
	public String getClasss(){//����Ϥjava��������������и����ȱ��
		return Class;
	}
	public void setSex(String sex) {
		this.Sex = sex;
	}
	public String getSex() {
		return Sex;
	}	
	public void setBirth_date(String date) {
		this.Birth_date = date;
	}
	public String getBirth_date() {
		return Birth_date;
	}	
	public void setPhone(String phone) {
		this.Phone = phone;
	}
	public String getPhone() {
		return Phone;
	}	
}
