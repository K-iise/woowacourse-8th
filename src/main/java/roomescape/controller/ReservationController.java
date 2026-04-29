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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dto.ReservationRequest;
import roomescape.dto.ReservationResponse;
import roomescape.dto.TimeRequest;
import roomescape.dto.TimeResponse;

@RestController
public class ReservationController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<ReservationResponse>> getReservation() {
        String selectSql = "SELECT r.id, r.name, r.date, t.id as time_id, t.start_at " +
                "FROM reservation r " +
                "INNER JOIN reservation_time t ON r.time_id = t.id";

        RowMapper<ReservationResponse> reservationRowMapper = (rs, rowNum) -> {
            return new ReservationResponse(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getObject("date", LocalDate.class),
                    new TimeResponse(
                            rs.getLong("time_id"),
                            rs.getObject("start_at", LocalTime.class)
                    )
            );
        };

        List<ReservationResponse> reservations = jdbcTemplate.query(selectSql, reservationRowMapper);
        return ResponseEntity.ok().body(reservations);
    }

    @PostMapping("/reservations")
    @ResponseBody
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        String sql = "INSERT INTO reservation(name, date, time_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, reservationRequest.getName());
            ps.setObject(2, reservationRequest.getDate());
            ps.setObject(3, reservationRequest.getTimeId());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        String selectSql = "SELECT r.id, r.name, r.date, t.id as time_id, t.start_at " +
                "FROM reservation r " +
                "INNER JOIN reservation_time t ON r.time_id = t.id " +
                "WHERE r.id = ?";

        ReservationResponse response = jdbcTemplate.queryForObject(selectSql, (rs, rowNum) -> new ReservationResponse(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getObject("date", LocalDate.class),
                new TimeResponse(
                        rs.getLong("time_id"),
                        rs.getObject("start_at", LocalTime.class)
                )
        ), id);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/times")
    @ResponseBody
    public ResponseEntity<List<TimeResponse>> getReservationTime() {
        String sql = "select * from reservation_time";
        RowMapper<TimeResponse> timeRowMapper = (rs, rowNum) -> {
            return new TimeResponse(
                    rs.getLong("id"),
                    rs.getObject("start_at", LocalTime.class)
            );
        };

        List<TimeResponse> reservationTimes = jdbcTemplate.query(sql, timeRowMapper);
        return ResponseEntity.ok().body(reservationTimes);
    }

    @PostMapping("/times")
    @ResponseBody
    public ResponseEntity<TimeResponse> addReservationTime(@RequestBody TimeRequest timeRequest) {
        String sql = "insert into reservation_time(start_at) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setObject(1, timeRequest.getStartAt());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        TimeResponse newTimeResponse = new TimeResponse(id, timeRequest.getStartAt());

        return ResponseEntity.ok().body(newTimeResponse);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteReservationTime(@PathVariable Long id) {
        String sql = "delete from reservation_time where id = ?";
        jdbcTemplate.update(sql, id);
        return ResponseEntity.ok().build();
    }
}
