package org.example.realSystems.bookMyShow;

import org.example.realSystems.bookMyShow.enums.PaymentType;
import org.example.realSystems.bookMyShow.model.*;
import org.example.realSystems.bookMyShow.repository.BookingRepository;
import org.example.realSystems.bookMyShow.repository.MovieRepository;
import org.example.realSystems.bookMyShow.repository.ShowRepository;
import org.example.realSystems.bookMyShow.repository.TheatreRepository;
import org.example.realSystems.bookMyShow.service.BookingService;
import org.example.realSystems.bookMyShow.service.MovieService;
import org.example.realSystems.bookMyShow.service.ShowService;
import org.example.realSystems.bookMyShow.service.TheatreService;
import org.example.realSystems.bookMyShow.strategy.locking.InMemoryLockProvider;
import org.example.realSystems.bookMyShow.strategy.locking.LockProvider;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookMyShowMain {
    public static void main(final String[] args) {
        BookingRepository  bookingRepository = new BookingRepository();
        MovieRepository  movieRepository = new MovieRepository();
        ShowRepository  showRepository = new ShowRepository();
        TheatreRepository theatreRepository = new TheatreRepository();

        LockProvider lockProvider = new InMemoryLockProvider();

        TheatreService theatreService = new TheatreService(theatreRepository);
        ShowService showService = new ShowService(showRepository);
        MovieService movieService = new MovieService(movieRepository);
        BookingService bookingService = new BookingService(bookingRepository,lockProvider);

        Theatre pvr = theatreService.addTheatre("th-01","PVR");
        Screen screen1 = new Screen("Sc-1");
        pvr.addScreen(screen1);

        theatreService.addSeats("th-01", "Sc-1", new RegularSeat("t1s1s1",100d));
        theatreService.addSeats("th-01", "Sc-1", new RegularSeat("t1s1s2",100d));
        theatreService.addSeats("th-01", "Sc-1", new RegularSeat("t1s1s3",100d));
        theatreService.addSeats("th-01", "Sc-1", new RegularSeat("t1s1s4",100d));
        theatreService.addSeats("th-01", "Sc-1", new RegularSeat("t1s1s5",100d));
        theatreService.addSeats("th-01", "Sc-1", new RegularSeat("t1s1s6",100d));

        theatreService.addSeats("th-01", "Sc-1", new ReclinerSeat("t1s1s7",150d));
        theatreService.addSeats("th-01", "Sc-1", new ReclinerSeat("t1s1s8",150d));
        theatreService.addSeats("th-01", "Sc-1", new ReclinerSeat("t1s1s9",150d));
        theatreService.addSeats("th-01", "Sc-1", new ReclinerSeat("t1s1s10",150d));


        Movie interstellar = movieService.createMovie("movie1","Interstellar",120);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2026, Calendar.JUNE,17,12,0);
        Date showStartTime = calendar.getTime();

        Show show1 = showService.createShow("show1",pvr,screen1,interstellar,showStartTime);

        System.out.println("********* Demo 1 **********");
        List<Show> shows = showService.getShows("interstellar");
        for (Show show : shows) {
            System.out.println(show);
        }

        System.out.println("********* Demo 2 **********");
        Booking booking1 = bookingService.createBooking("user1",show1,List.of("t1s1s1","t1s1s2"));
        bookingService.confirmBooking(booking1.getBookingId(), PaymentType.UPI);

        System.out.println("********* Demo 3 **********");
        ExecutorService executorService  = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
           try{
               Booking booking2 = bookingService.createBooking("user2",show1,List.of("t1s1s3","t1s1s4"));
               Thread.sleep(1000);
               bookingService.confirmBooking(booking2.getBookingId(), PaymentType.UPI);
           } catch (Exception e) {
               throw new RuntimeException("user 2 booking failed");
           }
        });
        executorService.submit(()->{
            try{
                Booking booking2 = bookingService.createBooking("user3",show1,List.of("t1s1s4","t1s1s5"));
                Thread.sleep(1000);
                bookingService.confirmBooking(booking2.getBookingId(), PaymentType.UPI);
            } catch (Exception e) {
                throw new RuntimeException("user 3 booking failed");
            }
        });


        System.out.println("********* Demo 4 **********");
        try{
            Booking booking4 = bookingService.createBooking("user4",show1,List.of("t1s1s6","t1s1s7"));
            System.out.println("user4 created booking but did not pay");
            Thread.sleep(6000);
            System.out.println("user5 trying to book same seats after ttl expired");
            Booking booking5 = bookingService.createBooking("user5",show1,List.of("t1s1s6","t1s1s7"));
            try{
                System.out.println("user4 trying to confirm booking after ttl expired");
                bookingService.confirmBooking(booking4.getBookingId(), PaymentType.UPI);
            }catch (Exception e){
                System.out.println("user 4 booking failed");
            }
            try{
                System.out.println("user5 confirming the seats");
                bookingService.confirmBooking(booking5.getBookingId(), PaymentType.CARD);
            }catch (Exception e){
                System.out.println("user 5 booking failed");
            }
        } catch (Exception e) {
            throw new RuntimeException("user 3 booking failed");
        }
    }
}
