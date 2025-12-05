import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountTest {
    public static void main(String[] args) {
        List<Account> customers = new ArrayList<>();

        // 1. 初始化預設資料
        System.out.println("--- 初始化系統 ---");
        safeAddCustomer(customers, new Account("A001", "Alice", 5000));
        safeAddCustomer(customers, new Account("A002", "Bob", 3000));
        // 測試錯誤的建立 (金額為負)
        safeAddCustomer(customers, createAccountSafe("A003", "Charlie", -100));
        System.out.println("------------------\n");

        // 2. 進入主選單
        operation(customers);
    }

    /** 安全建立帳戶：若餘額非法，回傳 null */
    private static Account createAccountSafe(String accNum, String owner, double balance) {
        try {
            return new Account(accNum, owner, balance);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ 建立帳戶失敗 (" + owner + ")： " + e.getMessage());
            return null;
        }
    }

    /** 安全加入帳戶：null 就不加入 */
    private static void safeAddCustomer(List<Account> customers, Account account) {
        if (account != null) {
            customers.add(account);
            System.out.println("新增客戶成功：" + account.getAccountNumber());
        }
    }

    // ★ 主功能選單邏輯
    public static void operation(List<Account> customers) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu();
            System.out.print("請選擇功能(1-5): ");
            int choice = readInt(scanner);

            switch (choice) {
                case 1: // 新增客戶
                    System.out.print("請輸入帳號: ");
                    String newAccNum = scanner.next();
                    System.out.print("請輸入戶名: ");
                    String newOwner = scanner.next();
                    System.out.print("請輸入初始餘額: ");
                    double initBal = readDouble(scanner);

                    Account newAcc = createAccountSafe(newAccNum, newOwner, initBal);
                    safeAddCustomer(customers, newAcc);
                    break;

                case 2: // 查詢/操作帳戶
                    System.out.print("請輸入要操作的帳號: ");
                    String targetNum = scanner.next();
                    Account target = findAccount(customers, targetNum);

                    if (target != null) {
                        subMenuOperation(scanner, target); // 進入子選單
                    } else {
                        System.out.println("❌ 找不到帳號: " + targetNum);
                    }
                    break;

                case 3: // 顯示所有客戶
                    printCustomerAccounts(customers);
                    break;

                case 4: // 刪除客戶
                    System.out.print("請輸入要刪除的帳