package com.foreveross.webbase.common.utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by markz on 2016/4/11.
 * excel的工具类
 */
public class ExcelUtil {

    private static Logger logger = Logger.getLogger(ExcelUtil.class);
    /**
     * 导出excel 文件  带标题
     * @param fileName 标题名
     * @param titleList
     * @param list
     * @param response
     */
    public static void exportWithHeadExcel(String fileName, String[] titleList,
                                           List list, HttpServletResponse response) {
        Date now = new Date();
        SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
                "yyyy年MM月dd日HH时mm分");
        SimpleDateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
        String todayStr = df.format(new Date());
        String today = dateformat.format(now);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            String localFileName = fileName;
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");// 处理中文文件名的问题
            fileName = new String(fileName.getBytes("UTF-8"), "GBK");// 处理中文文件名的问题
            response.setContentType("application/vnd.ms-excel;");
            response.setHeader("Content-disposition", "attachment;filename=\""
                    + fileName + "_" + todayStr + ".xls\"");
            // 开始写入excel
            // 加标题
            // 标题字体
            jxl.write.WritableFont wfc = new jxl.write.WritableFont(
                    WritableFont.COURIER, 18, WritableFont.NO_BOLD, false);
            jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
                    wfc);
            wcfFC.setAlignment(jxl.format.Alignment.CENTRE);
            wcfFC.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            // 字段字体
            jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(
                    WritableFont.COURIER, 10, WritableFont.NO_BOLD, false);
            jxl.write.WritableCellFormat wcfFC1 = new jxl.write.WritableCellFormat(
                    wfc1);
            wcfFC1.setAlignment(jxl.format.Alignment.CENTRE);
            wcfFC1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            // 结果字体
            jxl.write.WritableCellFormat wcfFC2 = new jxl.write.WritableCellFormat();
            wcfFC2.setAlignment(jxl.format.Alignment.CENTRE);
            wcfFC2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            WritableWorkbook wbook = Workbook.createWorkbook(os);
            // 写sheet名称
            WritableSheet wsheet = wbook.createSheet(localFileName, 0);
            int i = 3;
            for (int m = 0; m < titleList.length; m++) {
                wsheet.setColumnView(m, 30);
            }
            // 加入字段名
            for (int n = 0; n < titleList.length; n++) {
                wsheet.addCell(new jxl.write.Label(n, 3, titleList[n], wcfFC1));
            }
            // 加入标题
            wsheet.mergeCells(0, 0, i - 1, 0);
            wsheet.addCell(new Label(0, 0, localFileName, wcfFC));
            // 加入打印时间
            wsheet.addCell(new Label(i - 2, 1, "打印日期:"));
            wsheet.addCell(new Label(i - 1, 1, today));
            // 写入流中
            int row = 0;
            for (int r = 0; r < list.size(); r++) {
                Object[] obj = (Object[]) list.get(r);
                for (int x = 0; x < titleList.length; x++) {
                    wsheet.addCell(new jxl.write.Label(x, row + 4,
                            obj[x] == null ? " " : obj[x].toString(), wcfFC1));
                }
                row++;
                if (row % 60000 == 0) {
                    row = 0;
                    // 写sheet名称
                    wsheet = wbook.createSheet(localFileName, 0);
                    i = 3;
                    for (int m = 0; m < titleList.length; m++) {
                        wsheet.setColumnView(m, 30);
                    }
                    // 加入字段名
                    for (int n = 0; n < titleList.length; n++) {
                        wsheet.addCell(new jxl.write.Label(n, 3, titleList[n],
                                wcfFC1));
                    }
                    // 加入标题
                    wsheet.mergeCells(0, 0, i - 1, 0);
                    wsheet.addCell(new Label(0, 0, localFileName, wcfFC));
                    // 加入打印时间
                    wsheet.addCell(new Label(i - 2, 1, "打印日期:"));
                    wsheet.addCell(new Label(i - 1, 1, today));
                }
            }
            wbook.write();
            wbook.close();
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os == null) {
                logger.info("os is null");
            } else {
                try {
                    os.close();
                    os = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
