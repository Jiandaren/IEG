package jiansk.ieg.io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jiansk on 17-7-6.
 */
public class FileReader {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> names = getInterfaceNames("D:\\");
        System.out.println(names.toString());
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("耗时" + cost/1000 + 1 +"s");
    }

    public static List<String> getInterfaceNames(String filePath){
        String regEx = ".*ServiceInter\\.java";
        List<String> names = new ArrayList<String>();
        File file = new File(filePath);
        int fileNum = 0;
        if(file.exists()){
            if(!file.isDirectory()){
                System.out.println(filePath + "指定扫描路径不正确");
            }
            LinkedList<File> fileList = new LinkedList<File>();
            File[] files = file.listFiles();
            for(File file1 : files){
                if (file1.isDirectory()) {
                    fileList.add(file1);
                } else if (file.getName().matches(regEx)) {
                    System.out.println("文件:" + file1.getAbsolutePath());
                    fileNum++;
                    names.add(file1.getName());
                }
            }
            while (!fileList.isEmpty()) {
                files = fileList.removeFirst().listFiles();
                if(files != null){
                    for (File file1 : files) {
                        if (file1 != null && file1.isDirectory()) {
                            fileList.add(file1);
                        } else if (file1 != null && file1.getName().matches(regEx)) {
                            System.out.println("文件:" + file1.getAbsolutePath());
                            fileNum++;
                            names.add(file1.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("共扫到文件：" + fileNum);
        return names;
    }
}
