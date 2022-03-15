package fis.java.minitest.view;

import java.util.Date;
import java.util.Scanner;

import fis.java.minitest.common.CustomException;
import fis.java.minitest.common.Data;
import fis.java.minitest.dao.AccountDAO;
import fis.java.minitest.dao.TransactionDAO;
import fis.java.minitest.model.Transaction;

public class RunApp extends Thread{
	private static Scanner sc = new Scanner(System.in);

	private static int count = Data.transactions.size();
	
	public static void display() {	
		new Thread() {		
			@Override
			public synchronized void run() {		
				while (true) {	
					try {
						if(Data.transactions.size() > count) {
							int loop = 1;
							for(int i = count; i < Data.transactions.size(); i++) {
								loop++;
								Transaction transaction = Data.transactions.get(i);
								if(transaction.getStatus().equals(1)) {
									TransactionDAO.checkTransfer(transaction);
								}
							}
							count = Data.transactions.size();
						}					
						Thread.sleep(1000);
					} catch (Exception e) {
						break;
					}
				}
			}
		}.start();
		
		String answer = "";
		do {
			System.out.println();
			System.out.println("==========HE THONG QUAN LY TAI KHOAN==========");
			System.out.println("1. Danh sach tai khoan");
			System.out.println("2. Them moi tai khoan");
			System.out.println("3. Cap nhat thong tin tai khoan");
			System.out.println("4. Xoa tai khoan");
			System.out.println("5. Chuyen khoan");
			System.out.println("6. Danh sách giao dich");
			System.out.println("7. Thoat");
			System.out.print("Chon....");

			answer = sc.nextLine();

			switch (answer) {
			case "1": {
				System.out.println(AccountDAO.outputAccount());
				break;
			}
			case "2": {
				try {
					AccountDAO.addAccount();
				} catch (CustomException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case "3": {
				try {
					AccountDAO.updateAccount();
				} catch (CustomException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case "4": {
				try {
					AccountDAO.deleteAccount();
				} catch (CustomException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case "5": {
				try {
					TransactionDAO.inputTrans();
				} catch (CustomException e) {
					System.out.println(e.getMessage());
				}
				break;
			}
			case "7": {
				System.err.println("Hen gap lai");
				System.exit(0);
				break;
			}
			case "6": {
				TransactionDAO.printTrans();
				break;
			}
			default:
				System.out.println("Không có tác vụ này!");
			}
		} while (answer.equals("7"));

	}

}
