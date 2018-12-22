/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author raj.shah
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")
    , @NamedQuery(name = "Student.findByEnrollmentNumber", query = "SELECT s FROM Student s WHERE s.enrollmentNumber = :enrollmentNumber")
    , @NamedQuery(name = "Student.findByFirstName", query = "SELECT s FROM Student s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "Student.findByMiddleName", query = "SELECT s FROM Student s WHERE s.middleName = :middleName")
    , @NamedQuery(name = "Student.findByLastName", query = "SELECT s FROM Student s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "Student.findByGender", query = "SELECT s FROM Student s WHERE s.gender = :gender")
    , @NamedQuery(name = "Student.findByBirthDate", query = "SELECT s FROM Student s WHERE s.birthDate = :birthDate")
    , @NamedQuery(name = "Student.findByEnrollmentDate", query = "SELECT s FROM Student s WHERE s.enrollmentDate = :enrollmentDate")
    , @NamedQuery(name = "Student.findByAddressLine1", query = "SELECT s FROM Student s WHERE s.addressLine1 = :addressLine1")
    , @NamedQuery(name = "Student.findByCity", query = "SELECT s FROM Student s WHERE s.city = :city")
    , @NamedQuery(name = "Student.findByState", query = "SELECT s FROM Student s WHERE s.state = :state")
    , @NamedQuery(name = "Student.findByCountry", query = "SELECT s FROM Student s WHERE s.country = :country")
    , @NamedQuery(name = "Student.findByPincode", query = "SELECT s FROM Student s WHERE s.pincode = :pincode")
    , @NamedQuery(name = "Student.findByMobileNumber", query = "SELECT s FROM Student s WHERE s.mobileNumber = :mobileNumber")
    , @NamedQuery(name = "Student.findByEmailId", query = "SELECT s FROM Student s WHERE s.emailId = :emailId")
    , @NamedQuery(name = "Student.findByGuardianFullName", query = "SELECT s FROM Student s WHERE s.guardianFullName = :guardianFullName")
    , @NamedQuery(name = "Student.findByGuardianFullAddress", query = "SELECT s FROM Student s WHERE s.guardianFullAddress = :guardianFullAddress")
    , @NamedQuery(name = "Student.findByGuardianMobileNumber", query = "SELECT s FROM Student s WHERE s.guardianMobileNumber = :guardianMobileNumber")
    , @NamedQuery(name = "Student.findByBloodGroup", query = "SELECT s FROM Student s WHERE s.bloodGroup = :bloodGroup")
    , @NamedQuery(name = "Student.findByDisability", query = "SELECT s FROM Student s WHERE s.disability = :disability")
    , @NamedQuery(name = "Student.findByDisabilityDetail", query = "SELECT s FROM Student s WHERE s.disabilityDetail = :disabilityDetail")
    , @NamedQuery(name = "Student.findByDataCreateTime", query = "SELECT s FROM Student s WHERE s.dataCreateTime = :dataCreateTime")
    , @NamedQuery(name = "Student.findByDataUpdateTime", query = "SELECT s FROM Student s WHERE s.dataUpdateTime = :dataUpdateTime")})
public class Student implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<StudentFee> studentFeeList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "enrollment_number")
    private String enrollmentNumber;
    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "enrollment_date")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "guardian_full_name")
    private String guardianFullName;
    @Column(name = "guardian_full_address")
    private String guardianFullAddress;
    @Column(name = "guardian_mobile_number")
    private String guardianMobileNumber;
    @Column(name = "blood_group")
    private String bloodGroup;
    @Column(name = "disability")
    private String disability;
    @Column(name = "disability_detail")
    private String disabilityDetail;
    
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

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String enrollmentNumber, String firstName, String lastName, String gender) {
        this.id = id;
        this.enrollmentNumber = enrollmentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGuardianFullName() {
        return guardianFullName;
    }

    public void setGuardianFullName(String guardianFullName) {
        this.guardianFullName = guardianFullName;
    }

    public String getGuardianFullAddress() {
        return guardianFullAddress;
    }

    public void setGuardianFullAddress(String guardianFullAddress) {
        this.guardianFullAddress = guardianFullAddress;
    }

    public String getGuardianMobileNumber() {
        return guardianMobileNumber;
    }

    public void setGuardianMobileNumber(String guardianMobileNumber) {
        this.guardianMobileNumber = guardianMobileNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getDisabilityDetail() {
        return disabilityDetail;
    }

    public void setDisabilityDetail(String disabilityDetail) {
        this.disabilityDetail = disabilityDetail;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.javabase.apps.entity.Student[ id=" + id + " ]";
    }

    @XmlTransient
    public List<StudentFee> getStudentFeeList() {
        return studentFeeList;
    }

    public void setStudentFeeList(List<StudentFee> studentFeeList) {
        this.studentFeeList = studentFeeList;
    }
    
}
