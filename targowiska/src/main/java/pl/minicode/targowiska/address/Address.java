package pl.minicode.targowiska.address;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import pl.minicode.targowiska.entity.BasicEntity;

@Entity
public class Address extends BasicEntity {

	@NotBlank
	private String companyName;
	
	private String nip;
	
	private String line1;
	
	private String line2;
	
	private String line3;
	
	private String line4;
	
	private String bankAccount;
	
	private String phone1name;
	
	private String phone2name;
	
	private String phone3name;
	
	private String phone4name;
	
	private String phone1;
	
	private String phone2;

	private String phone3;
	
	private String phone4;
	
	@Email(message="Podana wartość nie jest adresem e-mail")
	private String email1;
	
	private String email2;
	
	private String email3;
	
	private String email4;
	
	private String email1name;
	
	private String email2name;
	
	private String email3name;
	
	private String email4name;
	
	private String regon;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getPhone1name() {
		return phone1name;
	}

	public void setPhone1name(String phone1name) {
		this.phone1name = phone1name;
	}

	public String getPhone2name() {
		return phone2name;
	}

	public void setPhone2name(String phone2name) {
		this.phone2name = phone2name;
	}

	public String getPhone3name() {
		return phone3name;
	}

	public void setPhone3name(String phone3name) {
		this.phone3name = phone3name;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail3() {
		return email3;
	}

	public void setEmail3(String email3) {
		this.email3 = email3;
	}

	public String getPhone4name() {
		return phone4name;
	}

	public void setPhone4name(String phone4name) {
		this.phone4name = phone4name;
	}

	public String getPhone4() {
		return phone4;
	}

	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}

	public String getEmail4() {
		return email4;
	}

	public void setEmail4(String email4) {
		this.email4 = email4;
	}

	public String getRegon() {
		return regon;
	}

	public void setRegon(String regon) {
		this.regon = regon;
	}

	public String getEmail1name() {
		return email1name;
	}

	public void setEmail1name(String email1name) {
		this.email1name = email1name;
	}

	public String getEmail2name() {
		return email2name;
	}

	public void setEmail2name(String email2name) {
		this.email2name = email2name;
	}

	public String getEmail3name() {
		return email3name;
	}

	public void setEmail3name(String email3name) {
		this.email3name = email3name;
	}

	public String getEmail4name() {
		return email4name;
	}

	public void setEmail4name(String email4name) {
		this.email4name = email4name;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getLine3() {
		return line3;
	}

	public void setLine3(String line3) {
		this.line3 = line3;
	}

	public String getLine4() {
		return line4;
	}

	public void setLine4(String line4) {
		this.line4 = line4;
	}
}
