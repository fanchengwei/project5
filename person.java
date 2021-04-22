import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fid;
	private String fname;
	private int fage;

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getFage() {
		return fage;
	}

	public void setFage(int fage) {
		this.fage = fage;
	}

	public Person(String fid, String fname, int fage) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.fage = fage;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [fid=" + fid + ", fname=" + fname + ", fage=" + fage + "]";
	}
}
