package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Answer;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

public class AnswerDao {

    private final JdbcTemplate<Answer> jdbcTemplate = new JdbcTemplate();

    public Answer insert(Answer answer) throws SQLException {
        String sql = "INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES(?,?,?,?)";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, answer.getWriter());
            pstmt.setString(2, answer.getContents());
            pstmt.setTimestamp(3, answer.getCreatedDate());
            pstmt.setInt(4, answer.getQuestionId());
        };
        KeyHolder keyHolder = new KeyHolder();
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByAnswerId(keyHolder.getId());
    }

    private Answer findByAnswerId(int id) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE answerId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, id);
        };
        RowMapper rowMapper = rs -> new Answer(
                rs.getInt("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("questionId")
        );
        return jdbcTemplate.queryforObject(sql, pstmtSetter, rowMapper);
    }

    public List<Answer> findAllAnswer(int questionId) throws SQLException {
        String sql = "SELECT * FROM ANSWERS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        RowMapper rowMapper = rs -> new Answer(
                rs.getInt("answerId"),
                rs.getString("writer"),
                rs.getString("contents"),
                rs.getTimestamp("createdDate"),
                rs.getInt("questionId")
        );
        return jdbcTemplate.query(sql, pstmtSetter, rowMapper);
    }

}
