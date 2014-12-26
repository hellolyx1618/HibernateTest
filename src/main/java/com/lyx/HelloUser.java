package com.lyx;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liyanxin on 2014/12/10.
 */
@Entity
@Table(name = "tb_hello_user")
public class HelloUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "string_flag")
    private String stringFlag;
    @Column(name = "int_flag", nullable = false)
    private int intFlag;
    @Column(name = "_float_flag", nullable = false)
    private float floatFlag;
    @Column(name = "byte_flag")
    private byte byteFlag;
    @Column(name = "char_flag")
    private char charFlag;
    @Column(name = "short_flag")
    private short shortFlag;
    @Column(name = "on_line", nullable = false)
    private boolean online;
    @Column(name = "long_flag")
    private long longFlag;
    @Column(name = "double_fg", nullable = false)
    private double doubleFlag;
    @Transient
    private String address;
    @Transient
    private Date birthday;
    @Column(name = "long_obj_flag")
    private Long longObjFlag;
    @Column(name = "double_obj_flag")
    private Double doubleObjFlag;
    @Column(name = "bigDecimalFlag", nullable = false)
    private BigDecimal bigDecimalFlag;
    @Column(name = "bool_obj_flag", nullable = false)
    private Boolean boolObjFlag;
    @Column(name = "float_obj_flag")
    private Float floatObjFlag;

    @Column(name = "integer_obj_flag", nullable = false)
    private Integer integerObjFlag;
    @Column(name = "short_obj_flag", nullable = false)
    private Short shortObjFlag;
    @Column(name = "char_obj_flag")
    private Character characterObjFlag;

    @Column(name = "last_login_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @Column(name = "date_flag")
    @Temporal(TemporalType.DATE)
    private Date dateFlag;

    @Column(name = "time_flag")
    @Temporal(TemporalType.TIME)
    private Date timeFlag;

    @Column(name = "user_gender_flag")
    @Enumerated(EnumType.STRING)
    private Gender uerGender;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_gender_flag")
    private Gender roleGender;

    ///////////////////////////////////////////
    //setter and getter
    public Date getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(Date timeFlag) {
        this.timeFlag = timeFlag;
    }

    public Date getDateFlag() {
        return dateFlag;
    }

    public void setDateFlag(Date dateFlag) {
        this.dateFlag = dateFlag;
    }

    public Gender getUerGender() {
        return uerGender;
    }

    public void setUerGender(Gender uerGender) {
        this.uerGender = uerGender;
    }

    public Gender getRoleGender() {
        return roleGender;
    }

    public void setRoleGender(Gender roleGender) {
        this.roleGender = roleGender;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStringFlag() {
        return this.stringFlag;
    }

    public void setStringFlag(String stringFlag) {
        this.stringFlag = stringFlag;
    }

    public int getIntFlag() {
        return this.intFlag;
    }

    public void setIntFlag(int intFlag) {
        this.intFlag = intFlag;
    }

    public char getCharFlag() {
        return this.charFlag;
    }

    public void setCharFlag(char charFlag) {
        this.charFlag = charFlag;
    }

    public short getShortFlag() {
        return this.shortFlag;
    }

    public void setShortFlag(short shortFlag) {
        this.shortFlag = shortFlag;
    }

    public boolean getOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public long getLongFlag() {
        return this.longFlag;
    }

    public void setLongFlag(long longFlag) {
        this.longFlag = longFlag;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getLongObjFlag() {
        return this.longObjFlag;
    }

    public void setLongObjFlag(Long longObjFlag) {
        this.longObjFlag = longObjFlag;
    }

    public Double getDoubleObjFlag() {
        return this.doubleObjFlag;
    }

    public void setDoubleObjFlag(Double doubleObjFlag) {
        this.doubleObjFlag = doubleObjFlag;
    }

    public BigDecimal getBigDecimalFlag() {
        return this.bigDecimalFlag;
    }

    public void setBigDecimalFlag(BigDecimal bigDecimalFlag) {
        this.bigDecimalFlag = bigDecimalFlag;
    }

    public Boolean getBoolObjFlag() {
        return this.boolObjFlag;
    }

    public void setBoolObjFlag(Boolean boolObjFlag) {
        this.boolObjFlag = boolObjFlag;
    }

    public Float getFloatObjFlag() {
        return this.floatObjFlag;
    }

    public void setFloatObjFlag(Float floatObjFlag) {
        this.floatObjFlag = floatObjFlag;
    }

    public Integer getIntegerObjFlag() {
        return this.integerObjFlag;
    }

    public void setIntegerObjFlag(Integer integerObjFlag) {
        this.integerObjFlag = integerObjFlag;
    }

    public Short getShortObjFlag() {
        return this.shortObjFlag;
    }

    public void setShortObjFlag(Short shortObjFlag) {
        this.shortObjFlag = shortObjFlag;
    }

    public Character getCharacterObjFlag() {
        return this.characterObjFlag;
    }

    public void setCharacterObjFlag(Character characterObjFlag) {
        this.characterObjFlag = characterObjFlag;
    }

    public float getFloatFlag() {
        return this.floatFlag;
    }

    public void setFloatFlag(float floatFlag) {
        this.floatFlag = floatFlag;
    }

    public byte getByteFlag() {
        return this.byteFlag;
    }

    public void setByteFlag(byte byteFlag) {
        this.byteFlag = byteFlag;
    }

    public double getDoubleFlag() {
        return this.doubleFlag;
    }

    public void setDoubleFlag(double doubleFlag) {
        this.doubleFlag = doubleFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", stringFlag='" + stringFlag + '\'' +
                ", intFlag=" + intFlag +
                ", floatFlag=" + floatFlag +
                ", byteFlag=" + byteFlag +
                ", charFlag=" + charFlag +
                ", shortFlag=" + shortFlag +
                ", online=" + online +
                ", longFlag=" + longFlag +
                ", doubleFlag=" + doubleFlag +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", longObjFlag=" + longObjFlag +
                ", doubleObjFlag=" + doubleObjFlag +
                ", bigDecimalFlag=" + bigDecimalFlag +
                ", boolObjFlag=" + boolObjFlag +
                ", floatObjFlag=" + floatObjFlag +
                ", integerObjFlag=" + integerObjFlag +
                ", shortObjFlag=" + shortObjFlag +
                ", characterObjFlag=" + characterObjFlag +
                ", lastLoginDate=" + lastLoginDate +
                ", dateFlag=" + dateFlag +
                ", timeFlag=" + timeFlag +
                ", uerGender=" + uerGender +
                ", roleGender=" + roleGender +
                '}';
    }
}
