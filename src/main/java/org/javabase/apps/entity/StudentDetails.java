package org.javabase.apps.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  Saurav Wahid<swahidfx@gmail.com>
 * @version 1.0.0
 * @since   1.0.0
 */
@Entity
@Table(name = "student_details")
public class StudentDetails implements java.io.Serializable {

	private static final long serialVersionUID = -5457596000511194801L;
        @Id
        @GeneratedValue
        @Column(name = "user_id", unique = true, nullable = false)
	private int userId;
        @Column(name = "birth_id", length = 45)
	private String birthId;
	@Column(name="student_name")
	private String name;
        @Column(name = "class_roel")
	private Integer classRoel;
	
        @Column(name = "present_address", length = 45)
	private String presentAddress;
        @Column(name = "upgrade_class")
	private Integer upgradeClass;
	@Column(name="course")
	private Integer courseId;
	@Column(name="gender")
	private String gender;
	@Column(name="mobile_number")
	private String mobileNumber;

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBirthId() {
		return this.birthId;
	}

	public void setBirthId(String birthId) {
		this.birthId = birthId;
	}

	public Integer getClassRoel() {
		return this.classRoel;
	}

	public void setClassRoel(Integer classRoel) {
		this.classRoel = classRoel;
	}

	public String getPresentAddress() {
		return this.presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public Integer getUpgradeClass() {
		return this.upgradeClass;
	}

	public void setUpgradeClass(Integer upgradeClass) {
		this.upgradeClass = upgradeClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
