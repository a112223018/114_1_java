import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerParseDemo {
    public static void main(String[] args) {
        createTestFile();
        
        try (Scanner scanner = new Scanner(new File("scores.txt"))) {
            System.out.println("=== 成績資料 ===");
            
            while (scanner.hasNext()) {
                String name = scanner.next();      // 讀取字串
                int score = scanner.nextInt();     // 讀取整數
                double gpa = scanner.nextDouble(); // 讀取浮點數
                
                System.out.printf("%s: %d 分, GPA: %.2f%n", name, score, gpa);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static void createTestFile() {
        try (PrintWriter pw = new PrintWriter("scores.txt")) {
            pw.println("王小明 85 3.5");
            pw.println("李小華 92 4.0");
            pw.println("張小美 78 3.2");
            pw.println("陳志豪 88 3.6");
            pw.println("林怡君 90 3.8");
            pw.println("張家豪 76 2.9");
            pw.println("黃詩涵 95 4.0");
            pw.println("吳柏翰 81 3.1");
            pw.println("劉欣怡 87 3.4");
            pw.println("蔡宗翰 79 3.0");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
