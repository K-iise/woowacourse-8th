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

@Controller
public class ReservationController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Reservation> actorRowMapper = (resultSet, rowNum) -> {
        Reservation reservation = new Reservation(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getObject("date", LocalDate.class),
                resultSet.getObject("time", LocalTime.class)
        );
        return reservation;
    };


    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getReservation() {
        String sql = "select * from reservation";
        List<Reservation> reservations = jdbcTemplate.query(sql, actorRowMapper);
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
}
