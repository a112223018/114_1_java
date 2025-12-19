import java.io.FileWriter;
import java.io.IOException;

public class FileWriterAppendDemo {
    public static void main(String[] args) {
        // 覆寫模式（預設）
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write("這會覆蓋原有內容\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 附加模式（第二個參數設為 true）
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write("這行會附加在檔案最後\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
