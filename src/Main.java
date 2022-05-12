import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("请输入文件名:");
        File file = new File(in.nextLine());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[(int) file.length()];
        int c = inputStream.read(buffer);
        System.out.println("文件长度为" + c);
        System.out.println("文件内容如下\n" + new String(buffer));
        inputStream.close();


        System.out.println("请选择是否替换文件现有内容?[true/false]");
        String append = in.nextLine();
        OutputStream outputStream = null;
        if ("true".equals(append)) {
            outputStream = new BufferedOutputStream(new FileOutputStream(file, false));
            System.out.println("[warning: 替换模式] 请输入文件内容:");
        } else if ("false".equals(append)) {
            outputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            System.out.println("[warning: 追加模式] 请输入文件内容:");
        }else {
            System.out.println("输入有误,已自动为您开启追加模式");
            outputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            System.out.println("[warning: 追加模式] 请输入文件内容:");
        }


        String inputline = in.nextLine();
        if ("".equals(inputline)) {
            System.out.println(buffer.length);

            String line_feed = System.getProperty("line.separator");
            outputStream.write(line_feed.getBytes());
        }
        else {
            buffer = inputline.getBytes();

            System.out.println(buffer.length);
            outputStream.write(buffer);
        }
        outputStream.flush();


    }
}
