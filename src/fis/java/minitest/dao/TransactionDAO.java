package fis.java.minitest.dao;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import fis.java.minitest.common.CustomException;
import fis.java.minitest.common.Data;
import fis.java.minitest.model.Account;
import fis.java.minitest.model.Transaction;
import fis.java.minitest.util.ValidateUtil;
import fis.java.minitest.util.impl.StringValidateImpl;

public class TransactionDAO {
	private static Scanner sc = new Scanner(System.in);

	private static ValidateUtil validateUtil = new StringValidateImpl();

	// chức năng chuyển tiền
	public static void inputTrans() throws CustomException {
		try {

			// lưu lỗi nếu phát sinh
			StringBuilder sb = new StringBuilder("");

			System.out.print("Nhập số tài khoản chuyển đi:");
			String fromAccount = sc.nextLine();
			if (!AccountDAO.isValidateAccNumber(fromAccount)) {
				sb.append("Tài khoản nguồn không hợp lệ!");
			}

			System.out.print("Nhập số tài khoản chuyển đến:");
			String toAccount = sc.nextLine();
			if (!AccountDAO.isValidateAccNumber(toAccount)) {
				sb.append("Tài khoản đích không hợp lệ!");
			}

			System.out.print("Nhập số tiền cần chuyển:");
			Double money = Double.parseDouble(sc.nextLine().trim());
			if (!AccountDAO.isBalance(fromAccount, toAccount, money)) {
				sb.append("Số dư tài khoản không đủ!");
			}

			System.out.print("Nhập nội dung chuyển khoản:");
			String content = sc.nextLine().trim();

			System.out.print("Bạn chắc chắn muốn thực hiện giao dịch?");
			String choose = sc.nextLine();
			if (choose.trim().equalsIgnoreCase("y")) {
				String error = sb.toString();

				if (error.isEmpty()) {
					System.out.println("Chuyen khoan thanh cong!");
					transfer(fromAccount, toAccount, money);
				}

				createTrans(fromAccount, toAccount, money, content, error);

			} else if (choose.trim().equalsIgnoreCase("n")) {
				return;
			} else {
				throw new CustomException("Thao tác không thành công");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException("Không đúng định dạng!");
		}

	}

	public static void transfer(String from, String to, Double money) {
		for (Account account : Data.list) {
			if (account.getAccountNumber().equals(from)) {
				account.setBalance(account.getBalance() - money);
			} else if (account.getAccountNumber().equals(from)) {
				account.setBalance(account.getBalance() + money);
			}
		}
	}

	public static Transaction createTrans(String from, String to, Double money, String content, String error) {
		Transaction transaction = new Transaction(AccountDAO.getIdFromNumber(from), AccountDAO.getIdFromNumber(to),
				money, content, error);
		return add(transaction);
	}

	public static Transaction add(Transaction tran) {
		Data.transactions.add(tran);
		return tran;
	}

	public static void checkTransfer(Transaction transaction) {
		StringBuilder sb = new StringBuilder("\n");
		Account account = Data.list.stream().filter(t -> t.getId().equals(transaction.getToAccount())).findFirst()
				.get();
		sb.append("TK ").append(account.getAccountName()).append(" | ").append(account.getAccountNumber())
				.append(" tăng ").append(transaction.getAmount()).append(" VND vào lúc ")
				.append(new SimpleDateFormat("dd-MM-yyyy").format(transaction.getTransactionDate()));
		System.out.println(sb.toString());
	}

	public static void printTrans() {
		Data.transactions.stream().forEach(i -> System.out.println(i.toString()));
	}

}
