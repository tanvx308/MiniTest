package fis.java.minitest.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import fis.java.minitest.common.Data;

public class Transaction {

	// thuộc tính
	private Long id;
	private Date transactionDate;
	private Number fromAccount;
	private Number toAccount;
	private Double amount;
	private Integer status;
	private String content;
	private String errorReason;

	// constructor

	public Transaction() {

	}

	public Transaction(Number fromAccount, Number toAccount, Double amount,
			String content, String errorReason) {
		this.id = generateId();
		this.transactionDate = new Date();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.status = errorReason.isEmpty() ? 1 : 0;
		this.content = content;
		this.errorReason = errorReason;
	}

	// setter and getter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long generateId() {
		Set<Long> set =  Data.transactions.stream().map(i -> i.getId()).collect(Collectors.toSet());
		Long id = 0L;
		do {
			id = (long) (Math.random() * 1000);
		} while (set.contains(id));
		return id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Number getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Number fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Number getToAccount() {
		return toAccount;
	}

	public void setToAccount(Number toAccount) {
		this.toAccount = toAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
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
		Transaction other = (Transaction) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", transactionDate=" + transactionDate + ", fromAccount=" + fromAccount
				+ ", toAccount=" + toAccount + ", amount=" + amount + ", status=" + status + ", content=" + content
				+ ", errorReason=" + errorReason + "]";
	}

	
}
