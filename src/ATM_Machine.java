import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ATM_Machine {
	
	//属性
	static String name;	//顧客の名前
	static int accNumber; //アカウントナンバー
	static double balance; //残高
	static int choice; //顧客の選択
	static boolean doTransaction = true; //処理を行うかどうか、確認するため
	
	static Scanner scan = new Scanner(System.in); //ユーザーの入力を扱うため
	
	//最初期のアカウント作成
	static void account(String name,int accNumber,double balance) {
		
		ATM_Machine.name = name;
		ATM_Machine.accNumber = accNumber;
		ATM_Machine.balance = balance;
		
	}

	public static void main(String[] args) {

		account("abc", 1234,100000); //アカウント作成
		
		//画面に表示すること
		System.out.println("=====================");
		System.out.println(" Welcome "+name+" to ATM");
		System.out.println("=====================");
		
		
		//処理を開始
		while(doTransaction) {
			//画面にトランザクションメニューを表示する
			System.out.println("Please select your desire transaction :");
			System.out.println("1. Deposit"); //預け入れ
			System.out.println("2. Withdraw"); //引き出す
			System.out.println("3. Check Balance"); //残高確認
			System.out.println("4. Exit"); //トランザクションが完了
			
			int button = scan.nextInt();
			
			//ユーザーの入力による、行う処理
			switch(button) {
			case 1:
				deposit(); //預け入れメソッドを呼び出す
				System.out.println("Do you want to make another transaction ?"); //他のトランザクションをするか、確認
				System.out.println("1. Yes");
				System.out.println("2. No");
				choice = scan.nextInt();
				if(choice==1) {
					//トランザクションメニューに戻る
					continue;
				}else if(choice==2) {
					//トランザクションが完了
					System.out.println("Thank You!");
					System.out.println("We would like to serve you again !");
					doTransaction=false;
					break;
				}
				break;
			case 2:
				withdraw(); //引き出すメソッドを呼び出す
				if(doTransaction==false) {
					break;
				}else {
				System.out.println("Do you want to make another transaction ?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				choice = scan.nextInt();
				if(choice==1) {
					//トランザクションメニューに戻る
					continue;
				}else if(choice==2) {
					//トランザクションが完了
					System.out.println("Thank You!");
					System.out.println("We would like to serve you again !");
					doTransaction=false;
					break;
				}
				}
				break;
			case 3:
				//残高確認の処理
				String fbalance = NumberFormat.getCurrencyInstance(Locale.JAPAN).format(balance); //金額はJPYに変換する
				System.out.println("Your current balance : "+fbalance); //画面上に表示する
				System.out.println("Do you want to make another transaction ?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				int choice = scan.nextInt();
				if(choice==1) {
					//トランザクションメニューに戻る
					continue;
				}else if(choice==2) {
					//トランザクションが完了
					System.out.println("Thank You!");
					System.out.println("We would like to serve you again !");
					doTransaction=false;
					break;
				}
				break;
			case 4:
				//トランザクションが完了
				System.out.println("Thank You!");
				doTransaction=false;
				break;
			}
		}

	}
	
	//預け入れメソッド
	static void deposit() {
		
		System.out.println("Please input the amount of money you want to deposit : ");
		double amount = scan.nextDouble(); //金額を入力
		balance = balance+amount; //残高を更新する
		String fbalance = NumberFormat.getCurrencyInstance(Locale.JAPAN).format(balance);
		System.out.println("Your current balance : "+fbalance); //画面上に残高を表示する
		
		
	}
	
	//引き出すメソッド
	static void withdraw() {
		
		System.out.println("Please input the amount of money you want to withdraw : ");
		double amount = scan.nextDouble(); //金額を入力
		//引き出したい金額は現在の残高より少ないかどうか、確認する
		if(amount<=balance) {
			balance=balance-amount; //残高を更新する
			String fbalance = NumberFormat.getCurrencyInstance(Locale.JAPAN).format(balance);
			System.out.println("Your current balance : "+fbalance); //画面上に残高を表示する
		}else if(amount>balance) {
			//エラーメッセージを表示する
			System.out.println("***ERROR***");
			System.out.println("The amount is exceed limit!");
			doTransaction=false;
		}
	}

}
