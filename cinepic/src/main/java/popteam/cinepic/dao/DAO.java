package popteam.cinepic.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import popteam.cinepic.vo.Movie;
import popteam.cinepic.vo.Review;
import popteam.cinepic.vo.User;


@Repository
public class DAO {
	
	@Autowired
	SqlSession session;
	
	public double selectAverage(Review review) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectAverage(review);
	}

	public User selectId(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectId(user);
	}

	public int insertUser(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.insertUser(user);
	}

	public User selectUser(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectUser(user);
	}

	public User findId(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.findId(user);
	}

	public User findPw(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.findPw(user);
	}

	public int updateUser(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.updateUser(user);
	}

	public int deleteUser(User user) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.deleteUser(user);
	}
	
	public ArrayList<Movie> selectAllMovie(Movie movie) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectAllMovie(movie);
	}

	public Movie selectMovie(Movie movie) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectMovie(movie);
	}

	public ArrayList<Review> selectAllReview(Review review) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectAllReview(review);
	}

	public int insertReview(Review review) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.insertReview(review);
	}

	public int deleteReview(Review review) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.deleteReview(review);
	}

	public ArrayList<Review> selectMyReview(Review review) {
		Mapper mapper = session.getMapper(Mapper.class);
		return mapper.selectMyReview(review);
	}

}
