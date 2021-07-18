package com.ninos.hotelbookings.integration.h2.jpa.entities;

import com.ninos.hotelbookings.core.business.model.Hotel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Hotel")
@Table(name = "hotels")
public class HotelJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "star_ranking")
    private int starRanking;

    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BookingJpaEntity> bookings = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public int getStarRanking() {
        return starRanking;
    }

    public void setStarRanking(final int starRanking) {
        this.starRanking = starRanking;
    }

    public Set<BookingJpaEntity> getBookings() {
        return bookings;
    }

    public void setBookings(final Set<BookingJpaEntity> bookings) {
        this.bookings = bookings;
    }

    public HotelJpaEntity() {
    }

    public HotelJpaEntity(Hotel hotel) {
        name = hotel.getName();
        address = hotel.getAddress();
        starRanking = hotel.getStarRating();
    }

    public void addBooking(BookingJpaEntity booking) {
        booking.setHotel(this);
        bookings.add(booking);
    }

    public void removeBooking(BookingJpaEntity booking) {
        bookings.remove(booking);
        booking.setHotel(null);
    }

    public Hotel toModel() {
        Hotel hotel = new Hotel();
        hotel.setId(id);
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setStarRating(starRanking);

        return hotel;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HotelJpaEntity that = (HotelJpaEntity) o;
        return id == that.id &&
                starRanking == that.starRanking &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, starRanking);
    }

    @Override
    public String toString() {
        return "HotelJpaEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", starRanking=" + starRanking +
                '}';
    }
}
