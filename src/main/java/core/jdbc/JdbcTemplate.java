package core.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {

    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //<동작 파라미터화>: preparedStatementSetter라는 동작을 파라미터로 받아 각 메서드에서 파라미터에
            //맞춰서 다른 동작을 수행할 수 있게 해줌
            pstmtSetter.setParameters(pstmt);

            pstmt.executeUpdate(); //DB의 정보를 update
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId((int) rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public<T> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery(); //DB의 정보를 조회해서 가져옴
             ){
            while(rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    public<T> List<T> query(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        List<T> objects = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);

        ){
            pstmtSetter.setParameters(pstmt);
            rs = pstmt.executeQuery(); //DB의 정보를 조회해서 가져옴

            while(rs.next()) {
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    public T queryforObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = null;
        T object = null;

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);)
        {
            pstmtSetter.setParameters(pstmt);

            //중요!! 여기서 ResultSet은 try with Resource문에 넣지 않는다!!
            rs = pstmt.executeQuery();//DB의 정보를 조회해서 가져옴
            while(rs.next()) {
                object = rowMapper.mapRow(rs);
            }
        }
        return object;
    }
}
