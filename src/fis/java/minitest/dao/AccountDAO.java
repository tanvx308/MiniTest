package fis.java.minitest.dao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import fis.java.minitest.common.CustomException;
import fis.java.minitest.common.Data;
import fis.java.minitest.model.Account;
import fis.java.minitest.util.ValidateUtil;
import fis.java.minitest.util.impl.NumberValidateImpl;
import fis.java.minitest.util.impl.StringValidateImpl;

public class AccountDAO {
	private static Scanner sc = new Scanner(System.in);

	private static ValidateUtil validateUtil = new StringValidateImpl();

	// hàm nhập thông tin account
	public static Account addAccount() throws CustomException {
		System.out.print("Nhập tên tài khoản: ");
		String accountName = sc.nextLine();
		if (!validateUtil.isValidate(5, 100, accountName)) {
			throw new CustomException("Tên tài khoản không hợp lệ!");
		}

		System.out.print("Nhập số tài khoản: ");
		String accountNumber = sc.nextLine();
		if (!validateUtil.isValidate(12, 12, accountNumber)) {
			throw new CustomException("Số tài khoản không hợp lệ!");
		}else if(isValidateAccNumber(accountNumber)) {
			throw new CustomException("Số tài khoản đã tồn tại!");
		}

		System.out.println("Thêm tài khoản thành công!");
		return add(new Account(accountName, accountNumber));
	}

	// hàm cập nhật thông tin account
	public static Account updateAccount() throws CustomException {
		System.out.print("Nhập Id tài khoản: ");
		try {
			Long id = Long.parseLong(sc.nextLine().trim());

			if (!isValidateId(id)) {
				throw new CustomException("Tài khoản chưa tồn tại");
			}

			System.out.print("Nhập tên tài khoản mới: ");
			String accountName = sc.nextLine();
			if (!validateUtil.isValidate(5, 100, accountName)) {
				throw new CustomException("Tên tài khoản không hợp lệ!");
			}

			System.out.print("Nhập trạng thái mới: ");
			String status = sc.nextLine();
			if (!new NumberValidateImpl().isValidate(0, 2, status)) {
				throw new CustomException("Trạng thái là 1 trong các số 0, 1, 2");
			}

			for (Account account : Data.list) {
				if (account.getId().equals(id)) {
					account.setAccountName(accountName);
					account.setStatus(Integer.parseInt(status));
					System.out.println("Cập nhật tài khoản thành công!");
					return account;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException("Không đúng định dạng");
		}
		return null;
	}
	
	
	// hàm xoá thông tin account
		public static void deleteAccount() throws CustomException {
			System.out.print("Nhập Id tài khoản: ");
			try {
				Long id = Long.parseLong(sc.nextLine().trim());

				if (!isValidateId(id)) {
					throw new CustomException("Tài khoản chưa tồn tại");
				}

				System.out.print("Bạn chắc chắn muốn xoá tài khoản có id là " + id + ": ");
				String choose = sc.nextLine();
				if(choose.equalsIgnoreCase("y")) {
					System.out.println("Xoá tài khoản thành công!");
					Data.list = Data.list.stream().filter(i -> !i.getId().equals(id)).collect(Collectors.toList());
				}else if(choose.equals("n")){
					return;
				}else {
					throw new CustomException("Thao tác không thành c");
				}
			} catch (Exception e) {
				// TODO: handle exception
				throw new CustomException("Không đúng định dạng");
			}
		}

	// hàm in danh sách account
	public static String outputAccount() {
		sort(new Comparator<Account>() {

			@Override
			public int compare(Account o1, Account o2) {
				// TODO Auto-generated method stub
				return o1.getAccountName().compareTo(o2.getAccountName());
			}
			
		}, Data.list);
		String formatter = "\n%-5S | %-30s | %-30s | %-10S | %-10s";
		StringBuilder sb = new StringBuilder("");
		sb.append(String.format(formatter, "ID", "TEN TAI KHOAN", "SO TAI KHOAN", "SO DU", "TRANG THAI"));
		Data.list.stream()
				.forEach(i -> sb.append(String.format(formatter, String.valueOf(i.getId()), i.getAccountName(),
						i.getAccountNumber(), String.valueOf(i.getBalance()), Data.map.get(i.getStatus()))));
		return sb.toString();
	}

	// sắp xếp danh sách
	public static void sort(Comparator<Account> cmp, List<Account> accounts) {
		Collections.sort(accounts, cmp);
	}

	// thêm tài khoản
	public static Account add(Account account) {
		Data.list.add(account);
		return account;
	}

	// hàm kiểm tra tài khoản có hợp lệ
	public static boolean isValidateId(Long id) {
		Set<Long> set = Data.list.stream().map(i -> i.getId()).collect(Collectors.toSet());
		return set.contains(id);
	}
	
	//hàm kiểm tra số tài khoản có tồn tại
	public static boolean isValidateAccNumber(String accountNumber) {
		return Data.list.stream().anyMatch(i -> i.getAccountNumber().equals(accountNumber));
	}
	
	
	//hàm kiểm tra số dư

	public static boolean isBalance(String from, String to, Double money) {
		Account fromAcc = Data.list.stream().filter(i -> i.getAccountNumber().equals(from)).findFirst().orElse(null);
		Account toAcc = Data.list.stream().filter(i -> i.getAccountNumber().equals(to)).findFirst().orElse(null);
		if(fromAcc == null || toAcc == null || fromAcc.getBalance().compareTo(toAcc.getBalance()) < 1) {
			return false;
		}
		return true;
	}
	
	//ham lay ID tu stk
	public static Long getIdFromNumber(String accountNumber) {
		Account acc =  Data.list.stream().filter(i -> i.getAccountNumber().equals(accountNumber)).findFirst().orElse(null);
		return acc != null ? acc.getId() : -1;
	}
	
}
