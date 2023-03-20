package com.riverbeside.bilibili.entity;


import java.util.Date;
/**订单数据的实体*/
public class Order extends BaseEntity{

  private Integer oid;
  private Integer uid;
  private String recvName; //收货人姓名
  private String recvPhone; //收货人电话
  private String recvProvince; //收货人所在省
  private String recvCity; //收货人所在市
  private String recvArea; //收货人所在区
  private String recvAddress; //收货人的详细收货地址
  private long totalPrice; //订单总价
  private Integer status; //订单状态 0-未支付 1-已支付 2-已取消 3-已关闭 4-已完成
  private Date orderTime; //下单时间
  private Date payTime; //支付时间

  @Override
  public String toString() {
    return "Order{" +
            "oid=" + oid +
            ", uid=" + uid +
            ", recvName='" + recvName + '\'' +
            ", recvPhone='" + recvPhone + '\'' +
            ", recvProvince='" + recvProvince + '\'' +
            ", recvCity='" + recvCity + '\'' +
            ", recvArea='" + recvArea + '\'' +
            ", recvAddress='" + recvAddress + '\'' +
            ", totalPrice=" + totalPrice +
            ", status=" + status +
            ", orderTime=" + orderTime +
            ", payTime=" + payTime +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Order)) return false;
    if (!super.equals(o)) return false;

    Order order = (Order) o;

    if (getTotalPrice() != order.getTotalPrice()) return false;
    if (getOid() != null ? !getOid().equals(order.getOid()) : order.getOid() != null) return false;
    if (getUid() != null ? !getUid().equals(order.getUid()) : order.getUid() != null) return false;
    if (getRecvName() != null ? !getRecvName().equals(order.getRecvName()) : order.getRecvName() != null) return false;
    if (getRecvPhone() != null ? !getRecvPhone().equals(order.getRecvPhone()) : order.getRecvPhone() != null)
      return false;
    if (getRecvProvince() != null ? !getRecvProvince().equals(order.getRecvProvince()) : order.getRecvProvince() != null)
      return false;
    if (getRecvCity() != null ? !getRecvCity().equals(order.getRecvCity()) : order.getRecvCity() != null) return false;
    if (getRecvArea() != null ? !getRecvArea().equals(order.getRecvArea()) : order.getRecvArea() != null) return false;
    if (getRecvAddress() != null ? !getRecvAddress().equals(order.getRecvAddress()) : order.getRecvAddress() != null)
      return false;
    if (getStatus() != null ? !getStatus().equals(order.getStatus()) : order.getStatus() != null) return false;
    if (getOrderTime() != null ? !getOrderTime().equals(order.getOrderTime()) : order.getOrderTime() != null)
      return false;
    return getPayTime() != null ? getPayTime().equals(order.getPayTime()) : order.getPayTime() == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getOid() != null ? getOid().hashCode() : 0);
    result = 31 * result + (getUid() != null ? getUid().hashCode() : 0);
    result = 31 * result + (getRecvName() != null ? getRecvName().hashCode() : 0);
    result = 31 * result + (getRecvPhone() != null ? getRecvPhone().hashCode() : 0);
    result = 31 * result + (getRecvProvince() != null ? getRecvProvince().hashCode() : 0);
    result = 31 * result + (getRecvCity() != null ? getRecvCity().hashCode() : 0);
    result = 31 * result + (getRecvArea() != null ? getRecvArea().hashCode() : 0);
    result = 31 * result + (getRecvAddress() != null ? getRecvAddress().hashCode() : 0);
    result = 31 * result + (int) (getTotalPrice() ^ (getTotalPrice() >>> 32));
    result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
    result = 31 * result + (getOrderTime() != null ? getOrderTime().hashCode() : 0);
    result = 31 * result + (getPayTime() != null ? getPayTime().hashCode() : 0);
    return result;
  }

  public Integer getOid() {
    return oid;
  }

  public void setOid(Integer oid) {
    this.oid = oid;
  }

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getRecvName() {
    return recvName;
  }

  public void setRecvName(String recvName) {
    this.recvName = recvName;
  }

  public String getRecvPhone() {
    return recvPhone;
  }

  public void setRecvPhone(String recvPhone) {
    this.recvPhone = recvPhone;
  }

  public String getRecvProvince() {
    return recvProvince;
  }

  public void setRecvProvince(String recvProvince) {
    this.recvProvince = recvProvince;
  }

  public String getRecvCity() {
    return recvCity;
  }

  public void setRecvCity(String recvCity) {
    this.recvCity = recvCity;
  }

  public String getRecvArea() {
    return recvArea;
  }

  public void setRecvArea(String recvArea) {
    this.recvArea = recvArea;
  }

  public String getRecvAddress() {
    return recvAddress;
  }

  public void setRecvAddress(String recvAddress) {
    this.recvAddress = recvAddress;
  }

  public long getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(long totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  public Date getPayTime() {
    return payTime;
  }

  public void setPayTime(Date payTime) {
    this.payTime = payTime;
  }
}
