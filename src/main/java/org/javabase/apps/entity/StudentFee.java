/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author raj.shah
 */
@Entity
@Table(name = "student_fee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentFee.findAll", query = "SELECT s FROM StudentFee s")
    , @NamedQuery(name = "StudentFee.findById", query = "SELECT s FROM StudentFee s WHERE s.id = :id")
    , @NamedQuery(name = "StudentFee.findByAmountPaid", query = "SELECT s FROM StudentFee s WHERE s.amountPaid = :amountPaid")
    , @NamedQuery(name = "StudentFee.findByPaymentMode", query = "SELECT s FROM StudentFee s WHERE s.paymentMode = :paymentMode")
    , @NamedQuery(name = "StudentFee.findByChequeNumber", query = "SELECT s FROM StudentFee s WHERE s.chequeNumber = :chequeNumber")
    , @NamedQuery(name = "StudentFee.findByFeeCollectedBy", query = "SELECT s FROM StudentFee s WHERE s.feeCollectedBy = :feeCollectedBy")
    , @NamedQuery(name = "StudentFee.findByDataCreateTime", query = "SELECT s FROM StudentFee s WHERE s.dataCreateTime = :dataCreateTime")
    , @NamedQuery(name = "StudentFee.findByDataUpdateTime", query = "SELECT s FROM StudentFee s WHERE s.dataUpdateTime = :dataUpdateTime")})
public class StudentFee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "discount")
    private Double discount;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "payment_mode")
    private String paymentMode;
    @Column(name = "cheque_number")
    private String chequeNumber;
    @Column(name = "fee_collected_by")
    private String feeCollectedBy;
    
    @CreationTimestamp
    @Column(name = "data_create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCreateTime;
    @UpdateTimestamp
    @Column(name = "data_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUpdateTime;
    
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Batch batchId;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "semester_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Semester semesterId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;

    public StudentFee() {
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
    public StudentFee(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getFeeCollectedBy() {
        return feeCollectedBy;
    }

    public void setFeeCollectedBy(String feeCollectedBy) {
        this.feeCollectedBy = feeCollectedBy;
    }

    public Date getDataCreateTime() {
        return dataCreateTime;
    }

    public void setDataCreateTime(Date dataCreateTime) {
        this.dataCreateTime = dataCreateTime;
    }

    public Date getDataUpdateTime() {
        return dataUpdateTime;
    }

    public void setDataUpdateTime(Date dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime;
    }

    public Batch getBatchId() {
        return batchId;
    }

    public void setBatchId(Batch batchId) {
        this.batchId = batchId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Semester getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Semester semesterId) {
        this.semesterId = semesterId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentFee)) {
            return false;
        }
        StudentFee other = (StudentFee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.StudentFee[ id=" + id + " ]";
    }
    
}
