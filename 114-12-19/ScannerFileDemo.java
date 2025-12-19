import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFileDemo {
    public static void main(String[] args) {
        // 使用 try-with-resources 確保讀取完畢後自動關閉資源
        try (Scanner scanner = new Scanner(new File("data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到檔案: " + e.getMessage());
        }
    }
}