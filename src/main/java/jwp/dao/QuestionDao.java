package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;

// JdbcTemplate 를 사용하여 Question 객체에 대한 DB 작업 수행
// 특정 모델 (Question)에 대한 CRUD 수행 / JdbcTemplate 기능 사용해서 SQL쿼리 실행
public class QuestionDao {

    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();

    // Question 객체를 DB에 삽입
    public Question insert(Question question) throws SQLException {
        KeyHolder keyHolder = new KeyHolder();

        String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate) VALUES (?, ?, ?, ?)";

        // setParameter 동작정의
        // pstmtSetter라는 구현체는 pstmt인자를 받을 때, {}내용으로 setParameters가 동작한다.
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setObject(4, question.getCreatedDate());
        };

        jdbcTemplate.update(sql, pstmtSetter, keyHolder);
        return findByQuestionId(keyHolder.getId());
    }

    // 기존의 Question 객체 업데이트
    public void update(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET title = ?, contents = ?, createdDate = ? WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContents());
            pstmt.setObject(3, question.getCreatedDate());
            pstmt.setLong( 4, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    // 특정 ID의 Question객체 삭제
    public void delete(int questionId) throws SQLException {
        String sql = "DELETE FROM QUESTIONS WHERE questionId = ?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }

    // DB의 모든 질문을 조회해서 리스트로 반환
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS ORDER BY questionId";
        RowMapper rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }

    // 특정 ID를 가진 질문 조회 및 반환
    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer " +
                "FROM QUESTIONS WHERE questionId=?";

        // sql부분의 placeholder 채우기
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }

    public void updateCountOfAnswer(Question question) throws SQLException {
        String sql = "UPDATE QUESTIONS SET countOfAnswer=? WHERE questionId=?";
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, question.getCountOfAnswer());
            pstmt.setInt(2, question.getQuestionId());
        };
        jdbcTemplate.update(sql, pstmtSetter);
    }
}
