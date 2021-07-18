package com.ninos.hotelbookings.integration.h2.jpa.entities;

import com.ninos.hotelbookings.core.business.model.Booking;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Booking")
@Table(
        name = "bookings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id", "customer_name", "customer_surname"})
)
public class BookingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelJpaEntity hotel;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_surname", nullable = false)
    private String customerSurname;

    @Column(name = "num_of_pax", nullable = false)
    private int numOfPax;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "currency", nullable = false)
    private String currency;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public HotelJpaEntity getHotel() {
        return hotel;
    }

    public void setHotel(final HotelJpaEntity hotel) {
        this.hotel = hotel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(final String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public int getNumOfPax() {
        return numOfPax;
    }

    public void setNumOfPax(final int numOfPax) {
        this.numOfPax = numOfPax;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public Booking toModel() {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setCustomerName(customerName);
        booking.setCustomerSurname(customerSurname);
        booking.setNumOfPax(numOfPax);
        booking.setHotelId(hotel.getId());
        booking.setPrice(price);
        booking.setCurrency(currency);

        return booking;
    }

    public BookingJpaEntity() {
    }

    public BookingJpaEntity(Booking booking) {
        id = booking.getId();
        customerName = booking.getCustomerName();
        customerSurname = booking.getCustomerSurname();
        numOfPax = booking.getNumOfPax();
        price = booking.getPrice();
        currency = booking.getCurrency();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BookingJpaEntity that = (BookingJpaEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).intValue();
    }

    @Override
    public String toString() {
        return "BookingJpaEntity{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", numOfPax=" + numOfPax +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
