package roomescape.controller;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.model.Reservation;
import roomescape.model.ReservationTime;

@Controller
public class ReservationController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Reservation> reservationRowMapper = (resultSet, rowNum) -> {
        Reservation reservation = new Reservation(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getObject("date", LocalDate.class),
                resultSet.getObject("time", LocalTime.class)
        );
        return reservation;
    };

    private RowMapper<ReservationTime> timeRowMapper = (resultSet, rowNum) -> {
        ReservationTime reservationTime = new ReservationTime(
                resultSet.getLong("id"),
                resultSet.getObject("start_at", LocalTime.class)
        );
        return reservationTime;
    };


    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<Reservation>> getReservation() {
        String sql = "select * from reservation";
        List<Reservation> reservations = jdbcTemplate.query(sql, reservationRowMapper);
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        String sql = "INSERT INTO reservation(name, date, time) VALUES (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, reservation.getName());
            ps.setObject(2, reservation.getDate());
            ps.setObject(3, reservation.getTime());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        Reservation newReservation = Reservation.toEntity(id, reservation);
        return ResponseEntity.ok().body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        String sql = "DELETE FROM reservation WHERE id = ?;";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/times")
    @ResponseBody
    public ResponseEntity<List<ReservationTime>> getReservationTime() {
        String sql = "select * from reservation_time";
        List<ReservationTime> reservationTimes = jdbcTemplate.query(sql, timeRowMapper);
        return ResponseEntity.ok().body(reservationTimes);
    }

    @PostMapping("/times")
    @ResponseBody
    public ResponseEntity<ReservationTime> addReservationTime(@RequestBody ReservationTime reservationTime) {
        String sql = "insert into reservation_time(start_at) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setObject(1, reservationTime.getStartAt());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        ReservationTime newReservationTime = ReservationTime.toEntity(id, reservationTime);

        return ResponseEntity.ok().body(newReservationTime);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteReservationTime(@PathVariable Long id) {
        String sql = "delete from reservation_time where id = ?";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.ok().build();
    }
}
