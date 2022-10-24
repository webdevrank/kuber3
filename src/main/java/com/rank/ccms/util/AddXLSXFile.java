package com.rank.ccms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddXLSXFile {

    private static final Logger logger = Logger.getLogger(AddXLSXFile.class);
    List<String> l1;
    List<String> notSavedListIndex;
    List<Integer> counterForBlankPos = new ArrayList();

    public List<List<String>> saveXLSX(InputStream inputStream, int size) throws IOException {
        int counterBlankRow = 0;
        List<List<String>> mstList = new ArrayList<>();
        notSavedListIndex = new ArrayList<>();
        counterForBlankPos = new ArrayList();
        String[] allColumNm = {"Name", "Email", "Phone", "Nationality"};

        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0);

        int mergedRg = sheet.getNumMergedRegions();
        if (mergedRg > 0) {
            logger.info("In 1");
            String msg = "Before uploading excel sheet, please delete note.";
            notSavedListIndex.add(msg);
        } else {
            logger.info("In 2");
            logger.info("sheet " + sheet.getLastRowNum());
            if (sheet.getLastRowNum() == 0) {
                logger.info("In 3");
                String msg = "";
                if (size == 4) {
                    msg = "File is Invalid.Name,Email,Phone,Nationality are missing";
                }
                notSavedListIndex.add(msg);
            } else {

                int cnt = 2;

                for (Row row : sheet) {

                    if (row.getLastCellNum() == -1) {
                        logger.info("In 5");
                        String msg = "";
                        if (size == 4) {
                            msg = "File is Invalid.Name,Email,Phone,Nationality are missing";
                        }
                        notSavedListIndex.add(msg);
                        break;
                    } else {
                        logger.info("In 6");
                        if (row.getRowNum() == 0) {

                            if (row.getLastCellNum() == -1) {
                                logger.info("In 8");
                                String msg = "";
                                if (size == 4) {
                                    msg = "File is Invalid.FirstName,MidName,LastName,PrimarySkill,CandidateId,Email,PanNo,HomePhone,CellPhone,BloodGroup,PreviousEmployer,PreviousJobDomain are missing";
                                }
                                notSavedListIndex.add(msg);
                                break;
                            } else if (row.getLastCellNum() == size) {

                                for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {

                                    if (sheet.getRow(i) == null) {

                                        String msg = "You can't left blank row number " + (i + 1) + ". Either remove it from sheet or fill the row with appropriate data.";
                                        counterForBlankPos.add(i + 1);
                                        notSavedListIndex.add(msg);

                                    }

                                }

                                int flag = 0;
                                logger.info("Name----11111111--" + row.getCell(0));
                                logger.info("Email------" + row.getCell(1));
                                logger.info("Phone------" + row.getCell(2));
                                logger.info("Nationality------" + row.getCell(3));
                                if (row.getCell(0) == null || !"Name".equals(row.getCell(0).toString())) {
                                    logger.info("FirstName------" + row.getCell(0));
                                    flag++;
                                } else if (row.getCell(1) == null || !"Email".equals(row.getCell(1).toString())) {
                                    logger.info("MidName------" + row.getCell(1));
                                    flag++;
                                } else if (row.getCell(2) == null || !"Phone".equals(row.getCell(2).toString())) {
                                    logger.info("LastName------" + row.getCell(2));
                                    flag++;
                                } else if (row.getCell(3) == null || !"Nationality".equals(row.getCell(3).toString())) {
                                    logger.info("PrimarySkill------" + row.getCell(3));
                                    flag++;
                                }
                                if (flag != 0) {
                                    String msg = "You can't change the order of column header.";
                                    notSavedListIndex.add(msg);
                                    break;
                                } else {
                                    continue;
                                }
                            } else if (row.getLastCellNum() != size) {
                                String msg = "File is Invalid.";
                                int count = 0;
                                int j = 0;
                                for (String s : allColumNm) {
                                    int flag = 0;

                                    for (int i = 0; i < row.getLastCellNum(); i++) {

                                        if (j < 12) {

                                            if (row.getCell(i) != null && s.equals(row.getCell(i).toString())) {

                                                flag = 0;
                                                break;
                                            } else {
                                                flag++;
                                            }
                                        }

                                    }
                                    if (flag != 0) {
                                        logger.info("In 16");
                                        msg += s + ",";
                                        count++;
                                    }
                                    j++;
                                }
                                if (count != 0) {
                                    logger.info("In 17");
                                    msg = msg.substring(0, msg.length() - 1);
                                    if (count > 1) {
                                        msg += " are missing";
                                    } else {
                                        msg += " is missing";
                                    }
                                }
                                if (msg.equalsIgnoreCase("File is Invalid.")) {

                                    msg += "Blank column/ Extra column not allowed in header.";
                                }
                                logger.info("File is Invalid due to less column......................." + msg);
                                notSavedListIndex.add(msg);
                                break;
                            } else {

                                String msg = "This file is invalid due unexpected reason";
                                notSavedListIndex.add(msg);
                                break;
                            }
                        }
                    }

                    l1 = new ArrayList<>();
                    for (int cn = 0; cn < size; cn++) {
                        Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:

                                l1.add(cell.getStringCellValue());
                                break;
                            case Cell.CELL_TYPE_ERROR:

                                break;
                            case Cell.CELL_TYPE_FORMULA:

                                break;
                            case Cell.CELL_TYPE_NUMERIC:

                                l1.add(String.valueOf(Double.valueOf(cell.getNumericCellValue()).longValue()));

                                break;
                            case Cell.CELL_TYPE_BOOLEAN:

                                l1.add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case Cell.CELL_TYPE_BLANK:

                                l1.add("");
                                break;
                            default:

                                l1.add("");
                        }

                    }

                    if (l1.size() == size) {
                        int count = 0;
                        for (int i = 0; i < l1.size(); i++) {
                            if (l1.get(i).length() == 0) {
                                count++;
                            }
                        }
                        if (count == size) {
                            String msg = "You can't left blank row number " + (row.getRowNum() + 1) + ". Either remove it from sheet or fill the row with appropriate data.";
                            notSavedListIndex.add(msg);
                            counterBlankRow++;

                        } else {
                            l1.add(String.valueOf(counterBlankRow));
                            mstList.add(l1);
                            counterBlankRow = 0;
                        }
                        logger.info("in if size of mstList" + mstList.size());
                    } else if (l1.size() > size) {
                        String msg = "values missing in row number  " + cnt;
                        notSavedListIndex.add(msg);
                    }
                    cnt = cnt + 1;

                }

                logger.info("mstList WithoutHeader====================================" + Arrays.asList(mstList));

            }
        }
        return mstList;

    }

    public int checkPos(int i) {
        logger.info("i--" + i);
        int checkPost = 0;
        boolean flag;
        logger.info("counterForBlankPos.size()--" + counterForBlankPos.size());
        for (int j = 0; j < counterForBlankPos.size(); j++) {
            logger.info("j--" + j);
            if (i == counterForBlankPos.get(j)) {
                logger.info("counterForBlankPos.get(j)===" + counterForBlankPos.get(j));
                flag = true;
                checkPost++;
                if (flag) {
                    for (int k = j + 1; k < counterForBlankPos.size(); k++) {
                        logger.info("k--" + k);
                        if (counterForBlankPos.get(k) == counterForBlankPos.get(k - 1) + 1) {
                            logger.info("counterForBlankPos.get(k)---" + counterForBlankPos.get(k) + "---counterForBlankPos.get(k - 1) + 1--" + counterForBlankPos.get(k - 1) + 1);
                            checkPost++;
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }
        }

        return checkPost;
    }

    public List<String> getNotSavedListIndex() {
        return notSavedListIndex;
    }

    public void setNotSavedListIndex(List<String> notSavedListIndex) {
        this.notSavedListIndex = notSavedListIndex;
    }

    public List<Integer> getCounterForBlankPos() {
        return counterForBlankPos;
    }

    public void setCounterForBlankPos(List<Integer> counterForBlankPos) {
        this.counterForBlankPos = counterForBlankPos;
    }

}
