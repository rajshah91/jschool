package org.javabase.apps.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.javabase.apps.entity.Batch;
import org.javabase.apps.entity.Course;
import org.javabase.apps.entity.Semester;
import org.javabase.apps.entity.Student;
import org.javabase.apps.entity.StudentAttendance;
import org.javabase.apps.service.StudentService;

/**
 *
 * @author raj.shah
 */
public class ExcelReadHandler {

    private StudentService studentService;
    
    private int totalDaysInMonth = 0;//need to set via totalcolun
    private int totalHolidaysInMonth = 0;
    private int totalPresent = 0;
    private int totalAbsence = 0;
    private int totalLeavesTakenInMonth = 0;

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    private Sheet getExcelWorkSheet(String fileName) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        return datatypeSheet;
    }

    public List<StudentAttendance> handleStudentAttendanceFile(String fileName, String filePath, Course course, Batch batch, Semester semester, String month) {
        List<StudentAttendance> studAttendance = new ArrayList<>();
        try {
            Sheet datatypeSheet = this.getExcelWorkSheet(filePath);//"C:\\Users\\raj.shah\\Downloads\\Temp.xlsx"
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                int currentRowIndex = currentRow.getRowNum();
//                No.	Student Name	Enrollment Number	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27	28	29	30	31

                StudentAttendance sa = new StudentAttendance();
                totalDaysInMonth = 0;//need to set via totalcolun
                totalHolidaysInMonth = 0;
                totalPresent = 0;
                totalAbsence = 0;
                totalLeavesTakenInMonth = 0;
                
                Student stud = new Student();
                totalDaysInMonth = (datatypeSheet.getRow(0).getLastCellNum()) - 3;

                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    int currentColumnIndex = currentCell.getColumnIndex();

                    if (currentRowIndex > 0) {
                        //Skip No. and Student Name column
                        if ((currentColumnIndex == 0 || currentColumnIndex == 1)) {
                            continue;
                        } else if (currentColumnIndex == 2) {//from 2nd row and enrollment number column

                            stud = studentService.getStudentByEnrollmentNumber(currentCell.getStringCellValue());

                        } else if (currentColumnIndex == 3) {
                            String val = currentCell.getStringCellValue();
                            sa.setA1(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 4) {
                            String val = currentCell.getStringCellValue();
                            sa.setA2(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 5) {
                            String val = currentCell.getStringCellValue();
                            sa.setA3(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 6) {
                            String val = currentCell.getStringCellValue();
                            sa.setA4(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 7) {
                            String val = currentCell.getStringCellValue();
                            sa.setA5(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 8) {
                            String val = currentCell.getStringCellValue();
                            sa.setA6(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 9) {
                            String val = currentCell.getStringCellValue();
                            sa.setA7(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 10) {
                            String val = currentCell.getStringCellValue();
                            sa.setA8(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 11) {
                            String val = currentCell.getStringCellValue();
                            sa.setA9(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 12) {
                            String val = currentCell.getStringCellValue();
                            sa.setA10(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 13) {
                            String val = currentCell.getStringCellValue();
                            sa.setA11(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 14) {
                            String val = currentCell.getStringCellValue();
                            sa.setA12(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 15) {
                            String val = currentCell.getStringCellValue();
                            sa.setA13(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 16) {
                            String val = currentCell.getStringCellValue();
                            sa.setA14(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 17) {
                            String val = currentCell.getStringCellValue();
                            sa.setA15(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 18) {
                            String val = currentCell.getStringCellValue();
                            sa.setA16(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 19) {
                            String val = currentCell.getStringCellValue();
                            sa.setA17(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 20) {
                            String val = currentCell.getStringCellValue();
                            sa.setA18(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 21) {
                            String val = currentCell.getStringCellValue();
                            sa.setA19(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 22) {
                            String val = currentCell.getStringCellValue();
                            sa.setA20(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 23) {
                            String val = currentCell.getStringCellValue();
                            sa.setA21(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 24) {
                            String val = currentCell.getStringCellValue();
                            sa.setA22(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 25) {
                            String val = currentCell.getStringCellValue();
                            sa.setA23(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 26) {
                            String val = currentCell.getStringCellValue();
                            sa.setA24(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 27) {
                            String val = currentCell.getStringCellValue();
                            sa.setA25(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 28) {
                            String val = currentCell.getStringCellValue();
                            sa.setA26(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 29) {
                            String val = currentCell.getStringCellValue();
                            sa.setA27(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 30) {
                            String val = currentCell.getStringCellValue();
                            sa.setA28(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 31) {
                            String val = currentCell.getStringCellValue();
                            sa.setA29(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 32) {
                            String val = currentCell.getStringCellValue();
                            sa.setA30(getFinalStringValue(val));
                            updateDaysCounter(val);
                        } else if (currentColumnIndex == 33) {
                            String val = currentCell.getStringCellValue();
                            sa.setA31(getFinalStringValue(val));
                            updateDaysCounter(val);
                        }
                    }
                }

                if (currentRowIndex > 0) {
                    sa.setTotalDaysInMonth(totalDaysInMonth);
                    sa.setTotalHolidaysInMonth(totalHolidaysInMonth);
                    sa.setTotalWorkingDaysInMonth(totalDaysInMonth - totalHolidaysInMonth);
                    sa.setTotalAbsentCount(totalAbsence);
                    sa.setTotalPresentCount(totalPresent);
                    sa.setTotalLeaveCount(totalLeavesTakenInMonth);

                    if (stud != null && stud.getId() != null) {
                        sa.setStudentId(stud);
                        sa.setBatchId(batch);
                        sa.setCourseId(course);
                        sa.setSemesterId(semester);
                        sa.setMonthName(month);
                    }

                    studAttendance.add(sa);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
        return studAttendance;
    }

    private void updateDaysCounter(String currentValue) {
        if (currentValue != null) {
            if (currentValue.equalsIgnoreCase("P")) {
                totalPresent = totalPresent + 1;
            } else if (currentValue.equalsIgnoreCase("A")) {
                totalAbsence = totalAbsence + 1;
            } else if (currentValue.equalsIgnoreCase("L")) {
                totalLeavesTakenInMonth = totalLeavesTakenInMonth + 1;
            } else if (currentValue.equalsIgnoreCase("H") || "".equals(currentValue)) {
                totalHolidaysInMonth = totalHolidaysInMonth + 1;
            }
        }
    }

    private String getFinalStringValue(String cellData) {
        List<String> list = Arrays.asList("H", "h", "P", "p", "A", "a", "l", "L");
        if ("".equals(cellData) || cellData == null) {
            return "H";
        } else if (list.contains(cellData)) {
            return cellData.toUpperCase();
        } else {
            return "H";
        }
    }

    public static boolean isCellEmpty(final XSSFCell cell) {
        if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
            return true;
        }

        if (cell.getCellTypeEnum() == CellType.BLANK) {
            return true;
        }

        if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
            return true;
        }

        return false;
    }

}
