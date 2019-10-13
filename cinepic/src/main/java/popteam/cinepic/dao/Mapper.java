package popteam.cinepic.dao;

import java.util.ArrayList;

import popteam.cinepic.vo.Movie;
import popteam.cinepic.vo.Review;
import popteam.cinepic.vo.User;

public interface Mapper {
	
	public double selectAverage(Review review);
	
	public User selectId(User user);
	
	public int insertUser(User user);

	public User selectUser(User user);

	public User findId(User user);

	public User findPw(User user);

	public int updateUser(User user);

	public int deleteUser(User user);

	public ArrayList<Movie> selectAllMovie(Movie movie);

	public Movie selectMovie(Movie movie);

	public ArrayList<Review> selectAllReview(Review review);

	public int insertReview(Review review);

	public int deleteReview(Review review);

	public ArrayList<Review> selectMyReview(Review review);
}
