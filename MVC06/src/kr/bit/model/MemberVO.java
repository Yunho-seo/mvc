package kr.bit.model;

// 회원(Object) -> MemberVO
public class MemberVO {
	private int num;
	private String id;
	private String pass;
	private String name;
	private int age;
	private String email;
	private String phone;
	public MemberVO() { }  // 디폴트 생성자
	
	// 회원가입 시 num은 Auto_increment라서 사용자가 입력하지 않음
	// 6개 정보를 묶는 생성자
	public MemberVO(String id, String pass, String name, int age, String email, String phone) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}
	
	// 이후 DB에서 VO 구조로 묶을 때, VO 구조가 한번 더 사용이 됨
	// 이때는 초기화를 하기 때문에, num까지 묶어주는 생성자를 하나 더 만들어야 함
	public MemberVO(int num, String id, String pass, String name, int age, String email, String phone) {
		super();
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {		// 디버깅용 (파라미터 잘 수집되었는지)
		return "MemberVO [num=" + num + ", id=" + id + ", pass=" + pass + ", name=" + name + ", age=" + age + ", email="
				+ email + ", phone=" + phone + "]";
	}
	
}
