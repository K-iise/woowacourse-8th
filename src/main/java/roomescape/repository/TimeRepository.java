package roomescape.repository;

import java.sql.PreparedStatement;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.model.ReservationTime;

@Repository
public class TimeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReservationTime> findAll() {
        String sql = "select * from reservation_time";
        RowMapper<ReservationTime> timeRowMapper = (rs, rowNum) -> {
            return new ReservationTime(
                    rs.getLong("id"),
                    rs.getObject("start_at", LocalTime.class)
            );
        };
        return jdbcTemplate.query(sql, timeRowMapper);
    }

    public void remove(Long id) {
        String sql = "delete from reservation_time where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public ReservationTime saveTime(LocalTime startAt) {
        String sql = "insert into reservation_time(start_at) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setObject(1, startAt);
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        return new ReservationTime(id, startAt);
    }
}
