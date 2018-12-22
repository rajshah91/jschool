/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.dto;

/**
 *
 * @author raj.shah
 */
public class TempStudentFee {
    private int id;
    private String studentId;
    private String courseId;
    private String batchId;
    private String semesterId;
    private Double totalFee;
    private Double discount;
    private Double remainingFee;
    private Double amountPaid;
    private String paymentMode;
    private String chequeNumber;
    private String feeCollectedBy;
    private String paymentDate;
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the courseId
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the batchId
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId the batchId to set
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return the semesterId
     */
    public String getSemesterId() {
        return semesterId;
    }

    /**
     * @param semesterId the semesterId to set
     */
    public void setSemesterId(String semesterId) {
        this.semesterId = semesterId;
    }

    /**
     * @return the totalFee
     */
    public Double getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee the totalFee to set
     */
    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return the remainingFee
     */
    public Double getRemainingFee() {
        return remainingFee;
    }

    /**
     * @param remainingFee the remainingFee to set
     */
    public void setRemainingFee(Double remainingFee) {
        this.remainingFee = remainingFee;
    }

    /**
     * @return the amountPaid
     */
    public Double getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return the paymentMode
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the chequeNumber
     */
    public String getChequeNumber() {
        return chequeNumber;
    }

    /**
     * @param chequeNumber the chequeNumber to set
     */
    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    /**
     * @return the feeCollectedBy
     */
    public String getFeeCollectedBy() {
        return feeCollectedBy;
    }

    /**
     * @param feeCollectedBy the feeCollectedBy to set
     */
    public void setFeeCollectedBy(String feeCollectedBy) {
        this.feeCollectedBy = feeCollectedBy;
    }

    /**
     * @return the paymentDate
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the paymentDate to set
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    
}
