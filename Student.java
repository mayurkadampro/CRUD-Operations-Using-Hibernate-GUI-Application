package p1;
class Student{
	private Integer rno;
	private String name;
	private Integer age;
	private String gender;
	
	public void setRno(Integer rno){
		this.rno = rno;
	}
	
	public Integer getRno(){
		return rno;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public void setGender(String gender){
		this.gender = gender;
	}
	
	public String getGender(){
		return gender;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}