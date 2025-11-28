/**
 * Account - 銀行帳戶類別
 * 定義帳戶的基本屬性與行為 (存款、提款、餘額查詢)
 */
public class Account {
    // 靜態變數：計算總開戶數
    private static int accountCount = 0;

    private String accountNumber; // 帳號
    private String ownerName;     // 戶名
    private double balance;       // 餘額

    // ========== 建構子 ==========

    // 完整建構子
    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;

        // 檢查初始餘額
        if (initialBalance < 0) {
            throw new IllegalArgumentException("初始餘額不能為負數");
        }
        this.balance = initialBalance;

        accountCount++; // 開戶數 +1
    }

    // 建構子過載 (Overloading)
    public Account(String accountNumber, double initialBalance) {
        this(accountNumber, "未知", initialBalance);
    }

    public Account() {
        this("未知", "未知", 0);
    }

    public Account(String accountNumber) {
        this(accountNumber, "未知", 0);
    }

    // ========== Getter 方法 ==========
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getOwnerName() { return ownerName; }
    public static int getAccountCount() { return accountCount; }

    // ========== Setter 方法 ==========
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // 通常不建議直接 setBalance，建議透過存提款修改，但若作業要求則保留
    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("餘額不能為負數");
        }
        this.balance = balance;
    }

    // ========== 業務邏輯方法 ==========

    // 存款
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("存款金額必須為正數");
        }
        balance += amount;
        System.out.println("✅ 成功存入 $" + amount + "，目前餘額: $" + balance);
    }

    // 提款
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("提款金額必須為正數");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("餘額不足！目前餘額: " + balance + "，欲提款: " + amount);
        }
        balance -= amount;
        System.out.println("✅ 成功取出 $" + amount + "，目前餘額: $" + balance);
    }

    @Override
    public String toString() {
        return "帳號: " + accountNumber + " | 戶名: " + ownerName + " | 餘額: $" + balance;
    }
}