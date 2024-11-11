package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DB와의 연결 및 쿼리 실행 담당
public class JdbcTemplate<T> {

    // SQL처리하여 DB에 데이터 업데이트 및 삽입
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // pstmt에 sql쿼리문 할당
            pstmtSetter.setParameters(pstmt);
            // sql문 실행
            pstmt.executeUpdate();
        }
    }

    // SQL쿼리 실행 후 리스트 형태로 반환
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();) {
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    // 단일 객체를 반환 하는 쿼리 실행
    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = null; // 쿼리의 결과
        T object = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery(); // 쿼리 실행 후 결과 반환
            if (rs.next()) { // 반환할게 있으면
                object = rowMapper.mapRow(rs);
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        return object;
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder keyHolder) throws SQLException {
        ResultSet rs = null; // 자동생성된 key가져오기

        try (Connection conn = ConnectionManager.getConnection(); // db와의 연결 생성
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setParameters(pstmt); // placeholder에 바인딩
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                keyHolder.setId((int) rs.getLong(1));
            }
        } finally {
            if (rs != null)
                rs.close();
        }
    }

    public List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        return objects;
    }
}
