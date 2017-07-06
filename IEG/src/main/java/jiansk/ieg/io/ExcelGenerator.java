package jiansk.ieg.io;

import jiansk.ieg.other.ExcelPojo;
import jiansk.ieg.other.FieldInfo;
import jiansk.ieg.test.inter.req.AssetUnsignReq;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiansk on 17-7-7.
 */
public class ExcelGenerator {

    public static void main(String[] args) throws IOException {
        createExcel(AssetUnsignReq.class);
    }

    private static void createExcel(Class<?> clazz) throws IOException {
        List<ExcelPojo> excelPojoList = new ArrayList<ExcelPojo>();
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for(Annotation a : annotations){
            System.out.println(a.toString());
        }
        Field[] fileds = clazz.getDeclaredFields();
        for(Field f : fileds){
            ExcelPojo excelPojo = new ExcelPojo();
            excelPojo.setEnName(f.getName());
            String type = StringUtils.substringAfterLast(f.getType().getName(), ".");
            excelPojo.setType(type);
            if(!f.isAccessible()){
                f.setAccessible(true);
            }
            FieldInfo fieldInfo = f.getAnnotation(FieldInfo.class);
            if(fieldInfo != null) {
                excelPojo.setZhName(fieldInfo.name());
                excelPojo.setComment(fieldInfo.comment());
            }
            NotNull notNull = f.getAnnotation(NotNull.class);
            if(notNull != null){
                excelPojo.setNotNull("Y");
            } else {
                excelPojo.setNotNull("N");
            }
            Length length = f.getAnnotation(Length.class);
            if(length != null){
                excelPojo.setLength(String.valueOf(length.max()));
            }
            excelPojoList.add(excelPojo);
        }
        HSSFWorkbook wb = new HSSFWorkbook();//创建HSSFWorkbook对象
        HSSFSheet sheet = wb.createSheet(clazz.getName());//创建HSSFSheet对象
        HSSFRow row = sheet.createRow(0);//创建HSSFRow对象
        HSSFCell cell=row.createCell(1);//创建HSSFCell对象
        cell.setCellValue("英文名称");//设置单元格的值
        cell=row.createCell(2);
        cell.setCellValue("中文名称");
        cell=row.createCell(3);
        cell.setCellValue("类型");
        cell=row.createCell(4);
        cell.setCellValue("长度");
        cell=row.createCell(5);
        cell.setCellValue("是否必输");
        cell=row.createCell(6);
        cell.setCellValue("备注");
        for(int i = 1;i <= excelPojoList.size();i++){
            row = sheet.createRow(i);
            cell=row.createCell(1);//创建HSSFCell对象
            cell.setCellValue(excelPojoList.get(i-1).getEnName());
            cell=row.createCell(2);
            cell.setCellValue(excelPojoList.get(i-1).getZhName());
            cell=row.createCell(3);
            cell.setCellValue(excelPojoList.get(i-1).getType());
            cell=row.createCell(4);
            cell.setCellValue(excelPojoList.get(i-1).getLength());
            cell=row.createCell(5);
            cell.setCellValue(excelPojoList.get(i-1).getNotNull());
            cell=row.createCell(6);
            cell.setCellValue(excelPojoList.get(i-1).getComment());
        }

        //输出Excel文件
        FileOutputStream output=new FileOutputStream("/home/jiansk/用户业务接口文档.xls");
        wb.write(output);
        output.flush();
    }
}
