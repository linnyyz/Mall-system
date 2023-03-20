package com.riverbeside.bilibili.entity;

/**收货地址实体类*/
public class Address extends BaseEntity {

    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName; //省名
    private String provinceCode; //省 行政代号
    private String cityName; // 市名
    private String cityCode;//市 行政代号
    private String areaName;//区名
    private String areaCode;//区 行政代号
    private String zip;//邮政编码
    private String address;//详细地址
    private String phone;//电话
    private String tel;//固话
    private String tag;//标签
    private Integer isDefault;//是否默认

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;

        Address address = (Address) o;

        if (getAid() != null ? !getAid().equals(address.getAid()) : address.getAid() != null) return false;
        if (getUid() != null ? !getUid().equals(address.getUid()) : address.getUid() != null) return false;
        if (getName() != null ? !getName().equals(address.getName()) : address.getName() != null) return false;
        if (getProvinceName() != null ? !getProvinceName().equals(address.getProvinceName()) : address.getProvinceName() != null)
            return false;
        if (getProvinceCode() != null ? !getProvinceCode().equals(address.getProvinceCode()) : address.getProvinceCode() != null)
            return false;
        if (getCityName() != null ? !getCityName().equals(address.getCityName()) : address.getCityName() != null)
            return false;
        if (getCityCode() != null ? !getCityCode().equals(address.getCityCode()) : address.getCityCode() != null)
            return false;
        if (getAreaName() != null ? !getAreaName().equals(address.getAreaName()) : address.getAreaName() != null)
            return false;
        if (getAreaCode() != null ? !getAreaCode().equals(address.getAreaCode()) : address.getAreaCode() != null)
            return false;
        if (getZip() != null ? !getZip().equals(address.getZip()) : address.getZip() != null) return false;
        if (getAddress() != null ? !getAddress().equals(address.getAddress()) : address.getAddress() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(address.getPhone()) : address.getPhone() != null) return false;
        if (getTel() != null ? !getTel().equals(address.getTel()) : address.getTel() != null) return false;
        if (getTag() != null ? !getTag().equals(address.getTag()) : address.getTag() != null) return false;
        return getIsDefault() != null ? getIsDefault().equals(address.getIsDefault()) : address.getIsDefault() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getAid() != null ? getAid().hashCode() : 0);
        result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getProvinceName() != null ? getProvinceName().hashCode() : 0);
        result = 31 * result + (getProvinceCode() != null ? getProvinceCode().hashCode() : 0);
        result = 31 * result + (getCityName() != null ? getCityName().hashCode() : 0);
        result = 31 * result + (getCityCode() != null ? getCityCode().hashCode() : 0);
        result = 31 * result + (getAreaName() != null ? getAreaName().hashCode() : 0);
        result = 31 * result + (getAreaCode() != null ? getAreaCode().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getTel() != null ? getTel().hashCode() : 0);
        result = 31 * result + (getTag() != null ? getTag().hashCode() : 0);
        result = 31 * result + (getIsDefault() != null ? getIsDefault().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TAddress{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", zip='" + zip + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", tag='" + tag + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }




}
