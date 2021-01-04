package com.zhaoqiang.excelUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.zhaoqiang.bean.JavaBean;

public class ReadDataFromExcel {

	public XSSFSheet openExcel(String readPath, int index) {
		try {
			// 生成文件的输入流
			InputStream inexcel = new FileInputStream(readPath);
			// 生成输入excel文件的内存模型
			XSSFWorkbook workbook = new XSSFWorkbook(inexcel);
			XSSFSheet sheet = workbook.getSheetAt(index);
			return sheet;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<String> ReadTitle(XSSFSheet sheet) {
		List<Object> titleList = readExcel(sheet, 0);
		System.out.println("title值 = " + titleList);
		// 将title的内容转换成标识
		List<String> titleJsonList = new ArrayList<>();
		for (int i = 0; i < titleList.size(); i++) {
			titleJsonList.add(JavaBean.titleMap.get(titleList.get(i)));
		}
		System.out.println("titlejson值 = " + titleJsonList);
		return titleJsonList;
	}

	public List<Object> readExcel(XSSFSheet sheet, int indexRow) {
		try {
			// 读取第一行数据备用
			// 创建一个存放数据的list
			List<Object> titleList = new ArrayList<>();
			Row row1 = sheet.getRow(indexRow);
			for (int i = 0; i < row1.getLastCellNum(); i++) {
				Cell cell = row1.getCell(i);
				Object cellValue = null;
				CellType cellType = cell.getCellType();
//				System.out.println(cellType);
				switch (cellType) {
				case FORMULA:
					if (DateUtil.isCellDateFormatted(cell)) { // 日期格式
						cellValue = cell.getDateCellValue();
					} else { // 数字
						cellValue = String.valueOf(cell.getNumericCellValue());
					}
					break;
				case NUMERIC:
					double result = cell.getNumericCellValue();
					String string = String.valueOf(result);
					if (string.endsWith(".0")) {
						cellValue = String.valueOf((int)result);
					}else
						cellValue = String.valueOf((int)result);
					break;
				case STRING:
					cellValue = cell.getRichStringCellValue().getString();
					break;
				default:
					cellValue = cell.getRichStringCellValue().getString();
					break;
				}
				if (cell.getCellType() != CellType.BLANK) {
					titleList.add(cellValue);
				} else {
					continue;
				}

			}
			return titleList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
