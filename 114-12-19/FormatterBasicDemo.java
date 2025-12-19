import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class FormatterBasicDemo {
    public static void main(String[] args) {
        String inputFile = "scores.txt";
        String outputFile = "outputScores.txt";

        List<Student> students = new ArrayList<>();

        // 1) 讀取檔案並解析成 Student 物件，放入 List
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // 範例格式: 姓名 分數 GPA
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) {
                    String name = parts[0];
                    int score = 0;
                    double gpa = 0.0;
                    try {
                        score = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException ex) {
                        // keep default 0
                    }
                    try {
                        gpa = Double.parseDouble(parts[2]);
                    } catch (NumberFormatException ex) {
                        // keep default 0.0
                    }

                    students.add(new Student(name, score, gpa));
                }
            }

        } catch (IOException e) {
            System.out.println("讀取檔案時發生錯誤: " + e.getMessage());
            return;
        }

        // 2) 使用 Formatter 寫入 outputScores.txt，每筆資料一行
        try (Formatter formatter = new Formatter(outputFile)) {
            for (Student s : students) {
                formatter.format("%s %d %.1f%n", s.name, s.score, s.gpa);
            }
            System.out.println("檔案寫入完成：" + outputFile);
        } catch (FileNotFoundException e) {
            System.out.println("無法建立檔案: " + e.getMessage());
        }
    }
}
// 簡單的 Student 類別
class Student {
        String name;
        int score;
        double gpa;

        Student(String name, int score, double gpa) {
            this.name = name;
            this.score = score;
            this.gpa = gpa;
        }
    }
