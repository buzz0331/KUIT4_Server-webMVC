package core.jdbc;

import jwp.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
// ResultSet의 각 행을 객체로 변환
public interface RowMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}