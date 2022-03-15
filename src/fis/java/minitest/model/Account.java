package fis.java.minitest.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import fis.java.minitest.common.Data;

public class Account {
	// thuộc tính
	private Long id;
	private String accountNumber;
	private String accountName;
	private Double balance;
	private Integer status;

	public Account() {
	}

	public Account(String accountName, String accountNumber) {
		this.id = generateId();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = 100000d;
		this.status = 1;
	}
	
	

	public Account(Long id, String accountName,  String accountNumber,Double balance, Integer status) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Long generateId() {
		Set<Long> set =  Data.list.stream().map(i -> i.getId()).collect(Collectors.toSet());
		Long id = 0L;
		do {
			id = (long) (Math.random() * 1000);
		} while (set.contains(id));
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", accountName=" + accountName + ", balance="
				+ balance + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

}
